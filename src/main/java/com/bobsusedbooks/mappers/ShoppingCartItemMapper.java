package com.bobsusedbooks.mappers;

import com.bobsusedbooks.dtos.ShoppingCartItemDto;
import com.bobsusedbooks.entities.ShoppingCartItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingCartItemMapper {
    
    public ShoppingCartItemDto toDto(ShoppingCartItem item) {
        if (item == null) {
            return null;
        }
        
        ShoppingCartItemDto dto = new ShoppingCartItemDto();
        dto.setId(item.getId());
        dto.setCustomerId(item.getCustomerId());
        dto.setBookId(item.getBookId());
        dto.setQuantity(item.getQuantity());
        dto.setIsWishlistItem(item.getIsWishlistItem());
        
        if (item.getBook() != null) {
            dto.setBookTitle(item.getBook().getName());
            dto.setBookAuthor(item.getBook().getAuthor());
            dto.setBookPrice(item.getBook().getPrice());
            dto.setBookCoverUrl(item.getBook().getCoverImageUrl());
            // No direct mapping for available quantity in the DTO
        }
        
        return dto;
    }
    
    public List<ShoppingCartItemDto> toDtoList(List<ShoppingCartItem> items) {
        return items.stream().map(this::toDto).collect(Collectors.toList());
    }
    
    public ShoppingCartItem toEntity(ShoppingCartItemDto dto) {
        if (dto == null) {
            return null;
        }
        
        ShoppingCartItem item = new ShoppingCartItem(dto.getCustomerId(), dto.getBookId(), dto.getQuantity());
        if (dto.getId() != null) {
            item.setId(dto.getId());
        }
        
        item.setCustomerId(dto.getCustomerId());
        item.setBookId(dto.getBookId());
        item.setQuantity(dto.getQuantity());
        item.setIsWishlistItem(dto.getIsWishlistItem());
        
        return item;
    }
}
