package com.bobsusedbooks.mappers;

import com.bobsusedbooks.dtos.OrderDto;
import com.bobsusedbooks.dtos.OrderItemDto;
import com.bobsusedbooks.entities.Order;
import com.bobsusedbooks.entities.OrderItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    
    public OrderDto toDto(Order entity) {
        if (entity == null) {
            return null;
        }
        
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setOrderDate(entity.getOrderDate());
        dto.setDeliveryDate(entity.getDeliveryDate());
        dto.setOrderStatus(entity.getOrderStatus());
        
        // Set order status text based on status code
        switch (entity.getOrderStatus()) {
            case 0:
                dto.setOrderStatusText("Pending");
                break;
            case 1:
                dto.setOrderStatusText("Processing");
                break;
            case 2:
                dto.setOrderStatusText("Shipped");
                break;
            case 3:
                dto.setOrderStatusText("Delivered");
                break;
            case 4:
                dto.setOrderStatusText("Cancelled");
                break;
            default:
                dto.setOrderStatusText("Unknown");
        }
        
        dto.setTrackingNumber(entity.getTrackingNumber());
        dto.setNotes(entity.getNotes());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setAddressId(entity.getAddressId());
        dto.setShippingAddress(entity.getShippingAddress());
        dto.setAddressSummary(entity.getShippingAddress());
        
        // Calculate order totals
        BigDecimal subtotal = BigDecimal.ZERO;
        
        // Map order items
        if (entity.getOrderItems() != null) {
            List<OrderItemDto> orderItemDtos = entity.getOrderItems().stream()
                    .map(this::toOrderItemDto)
                    .collect(Collectors.toList());
            dto.setOrderItems(orderItemDtos);
            dto.setItems(orderItemDtos); // Set both for compatibility
            
            // Calculate subtotal from order items
            for (OrderItem item : entity.getOrderItems()) {
                if (item.getPrice() != null && item.getQuantity() != null) {
                    BigDecimal itemTotal = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
                    subtotal = subtotal.add(itemTotal);
                }
            }
        }
        
        // Set financial values with proper decimal formatting
        dto.setSubtotal(subtotal.setScale(2, java.math.RoundingMode.HALF_UP));
        dto.setShippingCost(new BigDecimal("5.00")); // Default shipping cost
        dto.setTax(subtotal.multiply(new BigDecimal("0.09")).setScale(2, java.math.RoundingMode.HALF_UP)); // 9% tax
        dto.setTotal(subtotal.add(dto.getShippingCost()).add(dto.getTax()).setScale(2, java.math.RoundingMode.HALF_UP));
        
        return dto;
    }
    
    public Order toEntity(OrderDto dto) {
        if (dto == null) {
            return null;
        }
        
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setCustomerId(dto.getCustomerId());
        entity.setOrderDate(dto.getOrderDate());
        entity.setDeliveryDate(dto.getDeliveryDate());
        entity.setOrderStatus(dto.getOrderStatus());
        entity.setTrackingNumber(dto.getTrackingNumber());
        entity.setNotes(dto.getNotes());
        entity.setCreatedOn(dto.getCreatedOn());
        entity.setAddressId(dto.getAddressId());
        entity.setShippingAddress(dto.getShippingAddress());
        
        // Map order items
        if (dto.getOrderItems() != null) {
            List<OrderItem> orderItems = dto.getOrderItems().stream()
                    .map(this::toOrderItemEntity)
                    .collect(Collectors.toList());
            entity.setOrderItems(orderItems);
        } else if (dto.getItems() != null) {
            // Try with items field for compatibility
            List<OrderItem> orderItems = dto.getItems().stream()
                    .map(this::toOrderItemEntity)
                    .collect(Collectors.toList());
            entity.setOrderItems(orderItems);
        } else {
            entity.setOrderItems(new ArrayList<>());
        }
        
        return entity;
    }
    
    public void updateEntityFromDto(OrderDto dto, Order entity) {
        if (dto == null || entity == null) {
            return;
        }
        
        entity.setCustomerId(dto.getCustomerId());
        entity.setOrderDate(dto.getOrderDate());
        entity.setDeliveryDate(dto.getDeliveryDate());
        entity.setOrderStatus(dto.getOrderStatus());
        entity.setTrackingNumber(dto.getTrackingNumber());
        entity.setNotes(dto.getNotes());
        entity.setAddressId(dto.getAddressId());
        entity.setShippingAddress(dto.getShippingAddress());
        
        // Don't update order items here, handle separately in service
    }
    
    public OrderItemDto toOrderItemDto(OrderItem entity) {
        if (entity == null) {
            return null;
        }
        
        OrderItemDto dto = new OrderItemDto();
        dto.setId(entity.getId());
        dto.setOrderId(entity.getOrderId());
        dto.setBookId(entity.getBookId());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        dto.setUnitPrice(entity.getPrice()); // Set unitPrice as well
        dto.setBookPrice(entity.getBookPrice()); // Map book_price field
        dto.setDiscount(entity.getDiscount());
        
        // Set book details if available
        if (entity.getBook() != null) {
            dto.setBookTitle(entity.getBook().getName());
            dto.setBookAuthor(entity.getBook().getAuthor());
            dto.setBookCoverUrl(entity.getBook().getCoverImageUrl());
        }
        
        return dto;
    }
    
    public OrderItem toOrderItemEntity(OrderItemDto dto) {
        if (dto == null) {
            return null;
        }
        
        OrderItem entity = new OrderItem();
        entity.setId(dto.getId());
        entity.setOrderId(dto.getOrderId());
        entity.setBookId(dto.getBookId());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getUnitPrice() != null ? dto.getUnitPrice() : dto.getPrice());
        entity.setBookPrice(dto.getBookPrice() != null ? dto.getBookPrice() : dto.getPrice());
        entity.setDiscount(dto.getDiscount());
        
        return entity;
    }
}
