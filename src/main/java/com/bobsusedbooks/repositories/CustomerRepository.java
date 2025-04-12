package com.bobsusedbooks.repositories;

import com.bobsusedbooks.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    Optional<Customer> findByUsername(String username);
    
    Optional<Customer> findByEmail(String email);
    
    Optional<Customer> findBySub(String sub);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    boolean existsBySub(String sub);
}
