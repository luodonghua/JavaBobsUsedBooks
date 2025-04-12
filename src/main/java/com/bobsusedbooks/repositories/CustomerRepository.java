package com.bobsusedbooks.repositories;

import com.bobsusedbooks.dtos.CustomerHistoryDTO;
import com.bobsusedbooks.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    Optional<Customer> findByUsername(String username);
    
    Optional<Customer> findByEmail(String email);
    
    Optional<Customer> findBySub(String sub);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
    
    boolean existsBySub(String sub);
    
    /**
     * Calls the Oracle stored procedure get_customer_history to retrieve a customer's purchase history
     * 
     * @param customerId The ID of the customer
     * @return List of CustomerHistoryDTO objects containing the customer's purchase history
     */
    @Query(value = "{ ? = call get_customer_history(:customerId) }", nativeQuery = true)
    List<CustomerHistoryDTO> getCustomerPurchaseHistory(@Param("customerId") Long customerId);
}