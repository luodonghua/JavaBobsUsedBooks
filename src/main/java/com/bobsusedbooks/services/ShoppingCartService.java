package com.bobsusedbooks.services;

import com.bobsusedbooks.dtos.ShoppingCartDto;
import com.bobsusedbooks.dtos.ShoppingCartItemDto;
import com.bobsusedbooks.entities.Book;
import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.entities.ShoppingCartItem;
import com.bobsusedbooks.mappers.ShoppingCartItemMapper;
import com.bobsusedbooks.repositories.BookRepository;
import com.bobsusedbooks.repositories.CustomerRepository;
import com.bobsusedbooks.repositories.ShoppingCartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartItemRepository cartItemRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private ShoppingCartItemMapper cartItemMapper;
    
    public List<ShoppingCartItemDto> getCartItems(Long customerId) {
        List<ShoppingCartItem> items = cartItemRepository.findByCustomerId(customerId);
        return cartItemMapper.toDtoList(items);
    }
    
    public List<ShoppingCartItemDto> getWishlistItems(Long customerId) {
        List<ShoppingCartItem> items = cartItemRepository.findByCustomerIdAndIsWishlistItem(customerId, true);
        return cartItemMapper.toDtoList(items);
    }
    
    @Transactional
    public ShoppingCartItemDto addToCart(Long customerId, Long bookId, Integer quantity) {
        // Check if the item is already in the cart
        Optional<ShoppingCartItem> existingItem = cartItemRepository.findByCustomerIdAndBookId(customerId, bookId);
        
        if (existingItem.isPresent()) {
            // Update quantity
            ShoppingCartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            item.setIsWishlistItem(false);
            return cartItemMapper.toDto(cartItemRepository.save(item));
        } else {
            // Add new item
            ShoppingCartItem newItem = new ShoppingCartItem(customerId, bookId, quantity);
            newItem.setIsWishlistItem(false);
            return cartItemMapper.toDto(cartItemRepository.save(newItem));
        }
    }
    
    @Transactional
    public ShoppingCartItemDto addToWishlist(Long customerId, Long bookId) {
        // Check if the item is already in the wishlist
        Optional<ShoppingCartItem> existingItem = cartItemRepository.findByCustomerIdAndBookId(customerId, bookId);
        
        if (existingItem.isPresent()) {
            // Update to wishlist
            ShoppingCartItem item = existingItem.get();
            item.setIsWishlistItem(true);
            return cartItemMapper.toDto(cartItemRepository.save(item));
        } else {
            // Add new item to wishlist
            ShoppingCartItem newItem = new ShoppingCartItem(customerId, bookId, 1);
            newItem.setIsWishlistItem(true);
            return cartItemMapper.toDto(cartItemRepository.save(newItem));
        }
    }
    
    @Transactional
    public void updateCartItemQuantity(Long itemId, Integer quantity) {
        Optional<ShoppingCartItem> itemOpt = cartItemRepository.findById(itemId);
        
        if (itemOpt.isPresent()) {
            ShoppingCartItem item = itemOpt.get();
            item.setQuantity(quantity);
            cartItemRepository.save(item);
        }
    }
    
    @Transactional
    public void removeCartItem(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }
    
    @Transactional
    public void removeFromCart(Long customerId, Long bookId) {
        Optional<ShoppingCartItem> itemOpt = cartItemRepository.findByCustomerIdAndBookId(customerId, bookId);
        itemOpt.ifPresent(item -> cartItemRepository.deleteById(item.getId()));
    }
    
    @Transactional
    public void clearCart(Long customerId) {
        cartItemRepository.deleteByCustomerId(customerId);
    }
    
    @Transactional
    public ShoppingCartItemDto moveToCart(Long itemId, Long customerId) {
        Optional<ShoppingCartItem> itemOpt = cartItemRepository.findById(itemId);
        
        if (itemOpt.isPresent()) {
            ShoppingCartItem item = itemOpt.get();
            
            // Verify the item belongs to the customer
            if (item.getCustomerId().equals(customerId)) {
                item.setIsWishlistItem(false);
                item.setQuantity(1); // Default quantity when moving to cart
                return cartItemMapper.toDto(cartItemRepository.save(item));
            }
        }
        
        return null;
    }
    
    public ShoppingCartDto getCartDto(Long customerId) {
        List<ShoppingCartItem> cartItems = cartItemRepository.findByCustomerId(customerId);
        ShoppingCartDto cartDto = new ShoppingCartDto();
        
        // Populate cart DTO
        cartDto.setCustomerId(customerId);
        cartDto.setItems(cartItemMapper.toDtoList(cartItems));
        
        return cartDto;
    }
}
