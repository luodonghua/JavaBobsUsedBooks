package com.bobsusedbooks.services;

import com.bobsusedbooks.dtos.OrderDto;
import com.bobsusedbooks.dtos.OrderItemDto;
import com.bobsusedbooks.dtos.ShoppingCartItemDto;
import com.bobsusedbooks.entities.Address;
import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.entities.Order;
import com.bobsusedbooks.entities.OrderItem;
import com.bobsusedbooks.mappers.OrderMapper;
import com.bobsusedbooks.repositories.CustomerRepository;
import com.bobsusedbooks.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private ShoppingCartService shoppingCartService;
    
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }
    
    public Page<OrderDto> getAllOrdersPaginated(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(orderMapper::toDto);
    }
    
    public Optional<OrderDto> getOrderById(Long id) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            OrderDto dto = orderMapper.toDto(order);
            
            // Ensure items are properly set
            if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
                List<OrderItemDto> itemDtos = order.getOrderItems().stream()
                    .map(orderMapper::toOrderItemDto)
                    .collect(Collectors.toList());
                dto.setItems(itemDtos);
                dto.setOrderItems(itemDtos);
            }
            
            return Optional.of(dto);
        }
        return Optional.empty();
    }
    
    public List<OrderDto> getOrdersByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerIdOrderByCreatedOnDesc(customerId);
        return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }
    
    public Page<OrderDto> getOrdersByCustomerId(Long customerId, Pageable pageable) {
        Page<Order> orders = orderRepository.findByCustomerIdOrderByOrderDateDesc(customerId, pageable);
        return orders.map(orderMapper::toDto);
    }
    
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }
    
    @Transactional
    public OrderDto updateOrder(OrderDto orderDto) {
        Optional<Order> existingOrderOpt = orderRepository.findById(orderDto.getId());
        
        if (existingOrderOpt.isPresent()) {
            Order existingOrder = existingOrderOpt.get();
            orderMapper.updateEntityFromDto(orderDto, existingOrder);
            
            // Handle order items separately
            if (orderDto.getItems() != null && !orderDto.getItems().isEmpty()) {
                // Clear existing items
                existingOrder.getOrderItems().clear();
                
                // Add new items
                for (OrderItemDto itemDto : orderDto.getItems()) {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(existingOrder.getId());
                    orderItem.setBookId(itemDto.getBookId());
                    orderItem.setQuantity(itemDto.getQuantity());
                    orderItem.setPrice(itemDto.getPrice());
                    orderItem.setBookPrice(itemDto.getBookPrice());
                    orderItem.setDiscount(itemDto.getDiscount());
                    orderItem.setOrder(existingOrder);
                    existingOrder.getOrderItems().add(orderItem);
                }
            }
            
            Order updatedOrder = orderRepository.save(existingOrder);
            return orderMapper.toDto(updatedOrder);
        }
        
        throw new RuntimeException("Order not found with id: " + orderDto.getId());
    }
    
    @Transactional
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    
    @Transactional
    public OrderDto createOrderFromCart(Long customerId, Long addressId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        
        if (customerOpt.isEmpty()) {
            throw new RuntimeException("Customer not found with id: " + customerId);
        }
        
        Customer customer = customerOpt.get();
        
        // Get cart items first to check if cart is empty
        List<ShoppingCartItemDto> cartItems = shoppingCartService.getCartItems(customerId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }
        
        // Create a new order
        Order order = new Order(customerId);
        order.setCustomerId(customerId);
        
        // Set timestamps to ensure not-null constraint is satisfied
        LocalDateTime now = LocalDateTime.now();
        order.setCreatedOn(now);
        
        // Set order status to ensure not-null constraint is satisfied
        order.setOrderStatus(0); // 0 = Pending
        order.setStatus("PENDING");
        
        // Set order date
        order.setOrderDate(now.toLocalDate());
        
        // Set shipping address
        if (addressId != null && !customer.getAddresses().isEmpty()) {
            customer.getAddresses().stream()
                    .filter(address -> address.getId().equals(addressId))
                    .findFirst()
                    .ifPresent(address -> {
                        order.setAddressId(addressId);
                        order.setShippingAddress(address.getFullAddress());
                    });
        } else if (!customer.getAddresses().isEmpty()) {
            // Use default address
            customer.getAddresses().stream()
                    .filter(Address::getIsDefault)
                    .findFirst()
                    .ifPresent(address -> {
                        order.setAddressId(address.getId());
                        order.setShippingAddress(address.getFullAddress());
                    });
        }
        
        // Save the order first to get an ID
        Order savedOrder = orderRepository.save(order);
        Long orderId = savedOrder.getId();
        
        // Calculate order totals
        BigDecimal subtotal = BigDecimal.ZERO;
        
        // Now create order items with the order ID
        for (ShoppingCartItemDto cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setOrderId(orderId);
            orderItem.setBookId(cartItem.getBookId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getBookPrice());
            orderItem.setBookPrice(cartItem.getBookPrice());
            orderItem.setDiscount(BigDecimal.ZERO);
            
            // Calculate item subtotal
            BigDecimal itemTotal = cartItem.getBookPrice().multiply(new BigDecimal(cartItem.getQuantity()));
            subtotal = subtotal.add(itemTotal);
            
            savedOrder.getOrderItems().add(orderItem);
        }
        
        // Update the order with totals
        savedOrder.setTotalAmount(subtotal);
        
        // Save the updated order with items
        Order finalOrder = orderRepository.save(savedOrder);
        
        // Clear the customer's cart
        shoppingCartService.clearCart(customerId);
        
        // Create the DTO with proper calculations
        OrderDto orderDto = orderMapper.toDto(finalOrder);
        
        return orderDto;
    }
    
    public OrderDto cancelOrder(Long orderId, String reason) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            throw new RuntimeException("Order not found with id: " + orderId);
        }
        
        Order order = orderOpt.get();
        order.setStatus("Cancelled");
        order.setCancellationReason(reason);
        Order savedOrder = orderRepository.save(order);
        
        return orderMapper.toDto(savedOrder);
    }
    
    public long countOrders() {
        return orderRepository.count();
    }
    
    public BigDecimal calculateTotalSales() {
        return orderRepository.calculateTotalSales();
    }
    
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
    
    public Optional<Order> findOrderById(long id) {
        return orderRepository.findById(id);
    }
    
    /**
     * Updates the status of an order
     * 
     * @param orderId The ID of the order to update
     * @param status The new status code (0=Pending, 1=Processing, 2=Shipped, 3=Delivered, 4=Cancelled)
     * @param statusText The text representation of the status
     * @return The updated order DTO
     */
    public OrderDto updateOrderStatus(Long orderId, Integer status, String statusText) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            throw new RuntimeException("Order not found with id: " + orderId);
        }
        
        Order order = orderOpt.get();
        order.setOrderStatus(status);
        order.setStatus(statusText);
        
        // Update date fields based on status
        if (status == 2) { // Shipped
            order.setShippedDate(LocalDate.now());
        } else if (status == 3) { // Delivered
            order.setDeliveredDate(LocalDate.now());
        } else if (status == 4) { // Cancelled
            order.setCancelledDate(LocalDate.now());
        }
        
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }
    
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
    
    // Report-related methods
    
    public double getTotalSalesForPeriod(LocalDate startDate, LocalDate endDate) {
        BigDecimal totalSales = orderRepository.calculateSalesForPeriod(startDate, endDate);
        return totalSales != null ? totalSales.doubleValue() : 0.0;
    }
    
    public int getOrderCountForPeriod(LocalDate startDate, LocalDate endDate) {
        Integer orderCount = orderRepository.countOrdersForPeriod(startDate, endDate);
        return orderCount != null ? orderCount : 0;
    }
    
    public Map<LocalDate, Double> getDailySalesForPeriod(LocalDate startDate, LocalDate endDate) {
        List<Object[]> results = orderRepository.findDailySalesForPeriod(startDate, endDate);
        Map<LocalDate, Double> dailySales = new HashMap<>();
        
        for (Object[] result : results) {
            LocalDate date = (LocalDate) result[0];
            BigDecimal sales = (BigDecimal) result[1];
            dailySales.put(date, sales != null ? sales.doubleValue() : 0.0);
        }
        
        // Fill in missing dates with zero values
        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            if (!dailySales.containsKey(current)) {
                dailySales.put(current, 0.0);
            }
            current = current.plusDays(1);
        }
        
        return dailySales;
    }
    
    public Map<String, Double> getSalesByCategoryForPeriod(LocalDate startDate, LocalDate endDate) {
        List<Object[]> results = orderRepository.findSalesByCategoryForPeriod(startDate, endDate);
        Map<String, Double> salesByCategory = new HashMap<>();
        
        for (Object[] result : results) {
            String category = (String) result[0];
            BigDecimal sales = (BigDecimal) result[1];
            salesByCategory.put(category, sales != null ? sales.doubleValue() : 0.0);
        }
        
        return salesByCategory;
    }
    
    public List<OrderDto> getRecentOrders(int count) {
        Pageable pageable = PageRequest.of(0, count);
        List<Order> orders = orderRepository.findRecentOrders(pageable);
        return orders.stream().map(orderMapper::toDto).collect(Collectors.toList());
    }
}
