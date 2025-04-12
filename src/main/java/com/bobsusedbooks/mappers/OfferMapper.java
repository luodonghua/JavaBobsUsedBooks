package com.bobsusedbooks.mappers;

import com.bobsusedbooks.dtos.OfferDto;
import com.bobsusedbooks.entities.Offer;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper {
    
    public OfferDto toDto(Offer entity) {
        if (entity == null) {
            return null;
        }
        
        OfferDto dto = new OfferDto();
        dto.setId(entity.getId());
        dto.setBookName(entity.getBookTitle());
        dto.setBookTitle(entity.getBookTitle());
        dto.setAuthor(entity.getAuthor());
        dto.setIsbn(entity.getIsbn());
        dto.setDescription(entity.getDescription());
        dto.setOfferedPrice(entity.getOfferedPrice());
        dto.setConditionId(entity.getConditionId());
        dto.setStatus(entity.getStatus());
        dto.setOfferStatus(entity.getOfferStatus());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setBookComment(entity.getBookComment());
        dto.setFrontUrl(entity.getFrontUrl());
        dto.setSummary(entity.getSummary());
        
        // Reference data
        dto.setGenreId(entity.getGenreId());
        dto.setPublisherId(entity.getPublisherId());
        dto.setBookTypeId(entity.getBookTypeId());
        
        // Customer info
        dto.setCustomerId(entity.getCustomerId());
        
        if (entity.getCustomer() != null) {
            dto.setCustomerName(entity.getCustomer().getFirstName() + " " + entity.getCustomer().getLastName());
        }
        
        return dto;
    }
    
    public Offer toEntity(OfferDto dto) {
        if (dto == null) {
            return null;
        }
        
        Offer entity = new Offer();
        entity.setId(dto.getId());
        entity.setBookTitle(dto.getBookTitle() != null ? dto.getBookTitle() : dto.getBookName());
        entity.setAuthor(dto.getAuthor());
        entity.setIsbn(dto.getIsbn());
        entity.setDescription(dto.getDescription());
        entity.setOfferedPrice(dto.getOfferedPrice());
        entity.setConditionId(dto.getConditionId());
        entity.setStatus(dto.getStatus());
        entity.setOfferStatus(dto.getOfferStatus());
        entity.setCreatedOn(dto.getCreatedOn());
        entity.setUpdatedAt(dto.getUpdatedAt());
        entity.setBookComment(dto.getBookComment());
        entity.setFrontUrl(dto.getFrontUrl());
        entity.setSummary(dto.getSummary());
        
        // Reference data
        entity.setGenreId(dto.getGenreId());
        entity.setPublisherId(dto.getPublisherId());
        entity.setBookTypeId(dto.getBookTypeId());
        
        // Customer info
        entity.setCustomerId(dto.getCustomerId());
        
        return entity;
    }
    
    public void updateEntityFromDto(OfferDto dto, Offer entity) {
        if (dto == null || entity == null) {
            return;
        }
        
        entity.setBookTitle(dto.getBookTitle() != null ? dto.getBookTitle() : dto.getBookName());
        entity.setAuthor(dto.getAuthor());
        entity.setIsbn(dto.getIsbn());
        entity.setDescription(dto.getDescription());
        entity.setOfferedPrice(dto.getOfferedPrice());
        entity.setConditionId(dto.getConditionId());
        entity.setStatus(dto.getStatus());
        entity.setOfferStatus(dto.getOfferStatus());
        entity.setUpdatedAt(dto.getUpdatedAt());
        entity.setBookComment(dto.getBookComment());
        entity.setFrontUrl(dto.getFrontUrl());
        entity.setSummary(dto.getSummary());
        
        // Reference data
        entity.setGenreId(dto.getGenreId());
        entity.setPublisherId(dto.getPublisherId());
        entity.setBookTypeId(dto.getBookTypeId());
    }
}
