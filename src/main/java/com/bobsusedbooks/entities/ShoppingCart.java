package com.bobsusedbooks.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShoppingCart {
    
    private Long id;
    private Long customerId;
    private List<ShoppingCartItem> items = new ArrayList<>();
    
    public ShoppingCart(Long customerId) {
        this.customerId = customerId;
    }
    
    public void addItem(Book book, int quantity) {
        // Check if the book is already in the cart
        for (ShoppingCartItem item : items) {
            if (item.getBookId().equals(book.getId())) {
                // Update quantity
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        
        // Add new item
        ShoppingCartItem newItem = new ShoppingCartItem(customerId, book.getId(), quantity);
        items.add(newItem);
    }
    
    public void updateItemQuantity(Long bookId, int quantity) {
        for (ShoppingCartItem item : items) {
            if (item.getBookId().equals(bookId)) {
                item.setQuantity(quantity);
                return;
            }
        }
    }
    
    public void removeItem(Long bookId) {
        items.removeIf(item -> item.getBookId().equals(bookId));
    }
    
    public void clear() {
        items.clear();
    }
    
    public int getItemCount() {
        return items.stream().mapToInt(ShoppingCartItem::getQuantity).sum();
    }
    
    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(item -> item.getBook().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
