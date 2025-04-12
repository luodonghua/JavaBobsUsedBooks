package com.bobsusedbooks.mappers;

import com.bobsusedbooks.dtos.AddressDto;
import com.bobsusedbooks.entities.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    public AddressDto toDto(Address address) {
        if (address == null) {
            return null;
        }
        
        AddressDto dto = new AddressDto();
        dto.setId(address.getId());
        
        if (address.getCustomer() != null) {
            dto.setCustomerId(address.getCustomer().getId());
        }
        
        dto.setAddressLine1(address.getAddressLine1());
        dto.setAddressLine2(address.getAddressLine2());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostalCode());
        dto.setCountry(address.getCountry());
        dto.setIsDefault(address.getIsDefault());
        dto.setAddressType(address.getAddressType());
        
        return dto;
    }
    
    public Address toEntity(AddressDto dto) {
        if (dto == null) {
            return null;
        }
        
        Address address = new Address();
        if (dto.getId() != null) {
            address.setId(dto.getId());
        }
        
        address.setAddressLine1(dto.getAddressLine1());
        address.setAddressLine2(dto.getAddressLine2());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());
        address.setIsDefault(dto.getIsDefault());
        address.setAddressType(dto.getAddressType());
        
        // Note: Customer would need to be set separately
        
        return address;
    }
    
    public List<AddressDto> toDtoList(List<Address> addresses) {
        return addresses.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
