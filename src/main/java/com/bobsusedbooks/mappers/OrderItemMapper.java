package com.bobsusedbooks.mappers;

import com.bobsusedbooks.dtos.OrderItemDto;
import com.bobsusedbooks.entities.Book;
import com.bobsusedbooks.entities.Order;
import com.bobsusedbooks.entities.OrderItem;
import com.bobsusedbooks.repositories.BookRepository;
import com.bobsusedbooks.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    public OrderItemDto toDto(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        
        OrderItemDto dto = new OrderItemDto();
        dto.setId(orderItem.getId());
        
        if (orderItem.getOrder() != null) {
            dto.setOrderId(orderItem.getOrder().getId());
        }
        
        if (orderItem.getBook() != null) {
            dto.setBookId(orderItem.getBook().getId());
            dto.setBookTitle(orderItem.getBook().getName());
            dto.setBookAuthor(orderItem.getBook().getAuthor());
            dto.setBookCoverUrl(orderItem.getBook().getCoverImageUrl());
        }
        
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        
        return dto;
    }
    
    public List<OrderItemDto> toDtoList(List<OrderItem> orderItems) {
        return orderItems.stream().map(this::toDto).collect(Collectors.toList());
    }
    
    public OrderItem toEntity(OrderItemDto dto) {
        if (dto == null) {
            return null;
        }
        
        Order order = null;
        Book book = null;
        
        if (dto.getOrderId() != null) {
            Optional<Order> orderOpt = orderRepository.findById(dto.getOrderId());
            order = orderOpt.orElse(null);
        }
        
        if (dto.getBookId() != null) {
            Optional<Book> bookOpt = bookRepository.findById(dto.getBookId());
            book = bookOpt.orElse(null);
        }
        
        if (order == null || book == null) {
            throw new IllegalArgumentException("Order and book are required for order item");
        }
        
        OrderItem orderItem = new OrderItem(order.getId(), book.getId(), dto.getQuantity(), dto.getPrice());
        
        if (dto.getId() != null) {
            orderItem.setId(dto.getId());
        }
        
        return orderItem;
    }
}
