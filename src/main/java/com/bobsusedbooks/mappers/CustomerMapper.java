package com.bobsusedbooks.mappers;

import com.bobsusedbooks.dtos.CustomerDto;
import com.bobsusedbooks.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    
    public CustomerDto toDto(Customer entity) {
        if (entity == null) {
            return null;
        }
        
        CustomerDto dto = new CustomerDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setSub(entity.getSub());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setEmailVerified(entity.getEmailVerified());
        
        return dto;
    }
    
    public Customer toEntity(CustomerDto dto) {
        if (dto == null) {
            return null;
        }
        
        Customer entity = new Customer();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setSub(dto.getSub());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setCreatedOn(dto.getCreatedOn());
        entity.setEmailVerified(dto.getEmailVerified());
        
        return entity;
    }
    
    public void updateEntityFromDto(CustomerDto dto, Customer entity) {
        if (dto == null || entity == null) {
            return;
        }
        
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setEmailVerified(dto.getEmailVerified());
    }
}
