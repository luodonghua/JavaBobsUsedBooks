package com.bobsusedbooks.mappers;

import com.bobsusedbooks.dtos.BookDto;
import com.bobsusedbooks.entities.Book;
import com.bobsusedbooks.entities.BookType;
import com.bobsusedbooks.entities.Condition;
import com.bobsusedbooks.entities.Genre;
import com.bobsusedbooks.entities.Publisher;
import com.bobsusedbooks.repositories.BookTypeRepository;
import com.bobsusedbooks.repositories.ConditionRepository;
import com.bobsusedbooks.repositories.GenreRepository;
import com.bobsusedbooks.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookMapper {
    
    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private PublisherRepository publisherRepository;
    
    @Autowired
    private BookTypeRepository bookTypeRepository;
    
    @Autowired
    private ConditionRepository conditionRepository;
    
    public BookDto toDto(Book entity) {
        if (entity == null) {
            return null;
        }
        
        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAuthor(entity.getAuthor());
        dto.setIsbn(entity.getIsbn());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setCoverImageUrl(entity.getCoverImageUrl());
        dto.setIsFeatured(entity.getIsFeatured());
        dto.setIsBestseller(entity.getIsBestseller());
        dto.setIsNewArrival(entity.getIsNewArrival());
        dto.setPublishDate(entity.getPublishDate());
        // Removed lastRestockDate reference
        dto.setYear(entity.getYear());
        dto.setSummary(entity.getSummary());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setUpdatedOn(entity.getUpdatedOn());
        dto.setCreatedBy(entity.getCreatedBy());
        
        // Reference data IDs
        dto.setGenreId(entity.getGenreId());
        dto.setPublisherId(entity.getPublisherId());
        dto.setBookTypeId(entity.getBookTypeId());
        dto.setConditionId(entity.getConditionId());
        
        // Set reference data names
        if (entity.getGenreId() != null) {
            Optional<Genre> genre = genreRepository.findById(entity.getGenreId());
            genre.ifPresent(g -> dto.setGenreName(g.getName()));
        }
        
        if (entity.getPublisherId() != null) {
            Optional<Publisher> publisher = publisherRepository.findById(entity.getPublisherId());
            publisher.ifPresent(p -> dto.setPublisherName(p.getName()));
        }
        
        if (entity.getBookTypeId() != null) {
            Optional<BookType> bookType = bookTypeRepository.findById(entity.getBookTypeId());
            bookType.ifPresent(bt -> dto.setBookTypeName(bt.getName()));
        }
        
        if (entity.getConditionId() != null) {
            Optional<Condition> condition = conditionRepository.findById(entity.getConditionId());
            condition.ifPresent(c -> dto.setConditionName(c.getName()));
        }
        
        // User listing fields
        dto.setSellerId(entity.getSellerId());
        dto.setListingStatus(entity.getListingStatus());
        dto.setIsUserListing(entity.getIsUserListing());
        dto.setIsUserOffer(entity.getIsUserOffer());
        dto.setOfferSourceId(entity.getOfferSourceId());
        
        return dto;
    }
    
    public Book toEntity(BookDto dto) {
        if (dto == null) {
            return null;
        }
        
        Book entity = new Book();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setIsbn(dto.getIsbn());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setCoverImageUrl(dto.getCoverImageUrl());
        entity.setIsFeatured(dto.getIsFeatured());
        entity.setIsBestseller(dto.getIsBestseller());
        entity.setIsNewArrival(dto.getIsNewArrival());
        entity.setPublishDate(dto.getPublishDate());
        // Removed lastRestockDate reference
        entity.setYear(dto.getYear());
        entity.setSummary(dto.getSummary());
        entity.setCreatedOn(dto.getCreatedOn());
        entity.setUpdatedOn(dto.getUpdatedOn());
        entity.setCreatedBy(dto.getCreatedBy());
        
        // Set isAvailable with default true if null
        entity.setIsAvailable(dto.getIsAvailable() != null ? dto.getIsAvailable() : true);
        
        // Reference data IDs
        entity.setGenreId(dto.getGenreId());
        entity.setPublisherId(dto.getPublisherId());
        entity.setBookTypeId(dto.getBookTypeId());
        entity.setConditionId(dto.getConditionId());
        
        // User listing fields
        entity.setSellerId(dto.getSellerId());
        entity.setListingStatus(dto.getListingStatus());
        entity.setIsUserListing(dto.getIsUserListing());
        entity.setIsUserOffer(dto.getIsUserOffer());
        entity.setOfferSourceId(dto.getOfferSourceId());
        
        return entity;
    }
    
    public void updateEntityFromDto(BookDto dto, Book entity) {
        if (dto == null || entity == null) {
            return;
        }
        
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setIsbn(dto.getIsbn());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setCoverImageUrl(dto.getCoverImageUrl());
        entity.setIsFeatured(dto.getIsFeatured());
        entity.setIsBestseller(dto.getIsBestseller());
        entity.setIsNewArrival(dto.getIsNewArrival());
        entity.setPublishDate(dto.getPublishDate());
        // Removed lastRestockDate reference
        entity.setYear(dto.getYear());
        entity.setSummary(dto.getSummary());
        entity.setUpdatedOn(dto.getUpdatedOn());
        
        // Reference data IDs
        entity.setGenreId(dto.getGenreId());
        entity.setPublisherId(dto.getPublisherId());
        entity.setBookTypeId(dto.getBookTypeId());
        entity.setConditionId(dto.getConditionId());
        
        // User listing fields
        entity.setListingStatus(dto.getListingStatus());
    }
}
