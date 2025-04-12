package com.bobsusedbooks.repositories;

import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
    
    Optional<PasswordResetToken> findByToken(String token);
    
    List<PasswordResetToken> findByCustomer(Customer customer);
    
    List<PasswordResetToken> findByExpiryDateBeforeAndUsed(LocalDateTime now, Boolean used);
    
    void deleteByExpiryDateBefore(LocalDateTime now);
}
