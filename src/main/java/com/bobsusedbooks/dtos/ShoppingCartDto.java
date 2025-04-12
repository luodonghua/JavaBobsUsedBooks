package com.bobsusedbooks.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDto {
    private Long customerId;
    private List<ShoppingCartItemDto> items = new ArrayList<>();
    
    public int getItemCount() {
        return items.stream().mapToInt(ShoppingCartItemDto::getQuantity).sum();
    }
    
    public BigDecimal getTotalPrice() {
        return items.stream()
                .filter(item -> item.getBookPrice() != null)
                .map(item -> item.getBookPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
