package com.bobsusedbooks.mappers;

import com.bobsusedbooks.dtos.BookTypeDto;
import com.bobsusedbooks.dtos.ConditionDto;
import com.bobsusedbooks.dtos.GenreDto;
import com.bobsusedbooks.dtos.PublisherDto;
import com.bobsusedbooks.entities.BookType;
import com.bobsusedbooks.entities.Condition;
import com.bobsusedbooks.entities.Genre;
import com.bobsusedbooks.entities.Publisher;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReferenceDataMapper {

    // Genre mapping methods
    public GenreDto toGenreDto(Genre genre) {
        if (genre == null) {
            return null;
        }
        
        GenreDto dto = new GenreDto();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        dto.setDescription(genre.getDescription());
        dto.setCreatedOn(genre.getCreatedOn());
        dto.setUpdatedOn(genre.getUpdatedOn());
        
        return dto;
    }
    
    public Genre toGenreEntity(GenreDto dto) {
        if (dto == null) {
            return null;
        }
        
        Genre genre = new Genre();
        genre.setId(dto.getId());
        genre.setName(dto.getName());
        genre.setDescription(dto.getDescription());
        
        // Set audit fields if creating a new entity
        if (genre.getCreatedOn() == null) {
            genre.setCreatedOn(LocalDateTime.now());
        }
        genre.setUpdatedOn(LocalDateTime.now());
        
        return genre;
    }
    
    public void updateGenreFromDto(GenreDto dto, Genre genre) {
        if (dto == null || genre == null) {
            return;
        }
        
        genre.setName(dto.getName());
        genre.setDescription(dto.getDescription());
        genre.setUpdatedOn(LocalDateTime.now());
    }
    
    // Publisher mapping methods
    public PublisherDto toPublisherDto(Publisher publisher) {
        if (publisher == null) {
            return null;
        }
        
        PublisherDto dto = new PublisherDto();
        dto.setId(publisher.getId());
        dto.setName(publisher.getName());
        dto.setDescription(publisher.getDescription());
        dto.setWebsite(publisher.getWebsite());
        dto.setCreatedOn(publisher.getCreatedOn());
        dto.setUpdatedOn(publisher.getUpdatedOn());
        
        return dto;
    }
    
    public Publisher toPublisherEntity(PublisherDto dto) {
        if (dto == null) {
            return null;
        }
        
        Publisher publisher = new Publisher();
        publisher.setId(dto.getId());
        publisher.setName(dto.getName());
        publisher.setDescription(dto.getDescription());
        publisher.setWebsite(dto.getWebsite());
        
        // Set audit fields if creating a new entity
        if (publisher.getCreatedOn() == null) {
            publisher.setCreatedOn(LocalDateTime.now());
        }
        publisher.setUpdatedOn(LocalDateTime.now());
        
        return publisher;
    }
    
    public void updatePublisherFromDto(PublisherDto dto, Publisher publisher) {
        if (dto == null || publisher == null) {
            return;
        }
        
        publisher.setName(dto.getName());
        publisher.setDescription(dto.getDescription());
        publisher.setWebsite(dto.getWebsite());
        publisher.setUpdatedOn(LocalDateTime.now());
    }
    
    // BookType mapping methods
    public BookTypeDto toBookTypeDto(BookType bookType) {
        if (bookType == null) {
            return null;
        }
        
        BookTypeDto dto = new BookTypeDto();
        dto.setId(bookType.getId());
        dto.setName(bookType.getName());
        dto.setDescription(bookType.getDescription());
        dto.setCreatedOn(bookType.getCreatedOn());
        dto.setUpdatedOn(bookType.getUpdatedOn());
        
        return dto;
    }
    
    public BookType toBookTypeEntity(BookTypeDto dto) {
        if (dto == null) {
            return null;
        }
        
        BookType bookType = new BookType();
        bookType.setId(dto.getId());
        bookType.setName(dto.getName());
        bookType.setDescription(dto.getDescription());
        
        // Set audit fields if creating a new entity
        if (bookType.getCreatedOn() == null) {
            bookType.setCreatedOn(LocalDateTime.now());
        }
        bookType.setUpdatedOn(LocalDateTime.now());
        
        return bookType;
    }
    
    public void updateBookTypeFromDto(BookTypeDto dto, BookType bookType) {
        if (dto == null || bookType == null) {
            return;
        }
        
        bookType.setName(dto.getName());
        bookType.setDescription(dto.getDescription());
        bookType.setUpdatedOn(LocalDateTime.now());
    }
    
    // Condition mapping methods
    public ConditionDto toConditionDto(Condition condition) {
        if (condition == null) {
            return null;
        }
        
        ConditionDto dto = new ConditionDto();
        dto.setId(condition.getId());
        dto.setName(condition.getName());
        dto.setDescription(condition.getDescription());
        dto.setCreatedOn(condition.getCreatedOn());
        dto.setUpdatedOn(condition.getUpdatedOn());
        
        return dto;
    }
    
    public Condition toConditionEntity(ConditionDto dto) {
        if (dto == null) {
            return null;
        }
        
        Condition condition = new Condition();
        condition.setId(dto.getId());
        condition.setName(dto.getName());
        condition.setDescription(dto.getDescription());
        
        // Set audit fields if creating a new entity
        if (condition.getCreatedOn() == null) {
            condition.setCreatedOn(LocalDateTime.now());
        }
        condition.setUpdatedOn(LocalDateTime.now());
        
        return condition;
    }
    
    public void updateConditionFromDto(ConditionDto dto, Condition condition) {
        if (dto == null || condition == null) {
            return;
        }
        
        condition.setName(dto.getName());
        condition.setDescription(dto.getDescription());
        condition.setUpdatedOn(LocalDateTime.now());
    }
}
