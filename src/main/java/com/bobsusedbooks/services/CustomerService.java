package com.bobsusedbooks.services;

import com.bobsusedbooks.dtos.CustomerDto;
import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.mappers.CustomerMapper;
import com.bobsusedbooks.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CustomerMapper customerMapper;
    
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::toDto).collect(Collectors.toList());
    }
    
    public Optional<CustomerDto> getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.map(customerMapper::toDto);
    }
    
    public Optional<CustomerDto> getCustomerByUsername(String username) {
        Optional<Customer> customer = customerRepository.findByUsername(username);
        return customer.map(customerMapper::toDto);
    }
    
    public Optional<CustomerDto> getCustomerByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.map(customerMapper::toDto);
    }
    
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }
    
    public long countCustomers() {
        return customerRepository.count();
    }
    
    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }
    
    @Transactional
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(customerDto.getId());
        
        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            customerMapper.updateEntityFromDto(customerDto, existingCustomer);
            Customer updatedCustomer = customerRepository.save(existingCustomer);
            return customerMapper.toDto(updatedCustomer);
        }
        
        throw new RuntimeException("Customer not found with id: " + customerDto.getId());
    }
    
    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
    
    public Optional<Customer> findCustomerById(long id) {
        return customerRepository.findById(id);
    }
    
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
