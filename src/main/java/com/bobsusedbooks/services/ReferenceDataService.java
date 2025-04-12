package com.bobsusedbooks.services;

import com.bobsusedbooks.dtos.BookTypeDto;
import com.bobsusedbooks.dtos.ConditionDto;
import com.bobsusedbooks.dtos.GenreDto;
import com.bobsusedbooks.dtos.PublisherDto;
import com.bobsusedbooks.entities.BookType;
import com.bobsusedbooks.entities.Condition;
import com.bobsusedbooks.entities.Genre;
import com.bobsusedbooks.entities.Publisher;
import com.bobsusedbooks.mappers.ReferenceDataMapper;
import com.bobsusedbooks.repositories.BookTypeRepository;
import com.bobsusedbooks.repositories.ConditionRepository;
import com.bobsusedbooks.repositories.GenreRepository;
import com.bobsusedbooks.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReferenceDataService {

    @Autowired
    private GenreRepository genreRepository;
    
    @Autowired
    private PublisherRepository publisherRepository;
    
    @Autowired
    private BookTypeRepository bookTypeRepository;
    
    @Autowired
    private ConditionRepository conditionRepository;
    
    @Autowired
    private ReferenceDataMapper referenceDataMapper;
    
    // Genre methods
    public List<GenreDto> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map(referenceDataMapper::toGenreDto).collect(Collectors.toList());
    }
    
    public Optional<GenreDto> getGenreById(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        return genre.map(referenceDataMapper::toGenreDto);
    }
    
    @Transactional
    public GenreDto createGenre(GenreDto genreDto) {
        Genre genre = referenceDataMapper.toGenreEntity(genreDto);
        Genre savedGenre = genreRepository.save(genre);
        return referenceDataMapper.toGenreDto(savedGenre);
    }
    
    @Transactional
    public GenreDto updateGenre(GenreDto genreDto) {
        Optional<Genre> existingGenreOpt = genreRepository.findById(genreDto.getId());
        
        if (existingGenreOpt.isPresent()) {
            Genre existingGenre = existingGenreOpt.get();
            referenceDataMapper.updateGenreFromDto(genreDto, existingGenre);
            Genre updatedGenre = genreRepository.save(existingGenre);
            return referenceDataMapper.toGenreDto(updatedGenre);
        }
        
        throw new RuntimeException("Genre not found with id: " + genreDto.getId());
    }
    
    @Transactional
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
    
    // Publisher methods
    public List<PublisherDto> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publishers.stream().map(referenceDataMapper::toPublisherDto).collect(Collectors.toList());
    }
    
    public Optional<PublisherDto> getPublisherById(Long id) {
        Optional<Publisher> publisher = publisherRepository.findById(id);
        return publisher.map(referenceDataMapper::toPublisherDto);
    }
    
    @Transactional
    public PublisherDto createPublisher(PublisherDto publisherDto) {
        Publisher publisher = referenceDataMapper.toPublisherEntity(publisherDto);
        Publisher savedPublisher = publisherRepository.save(publisher);
        return referenceDataMapper.toPublisherDto(savedPublisher);
    }
    
    @Transactional
    public PublisherDto updatePublisher(PublisherDto publisherDto) {
        Optional<Publisher> existingPublisherOpt = publisherRepository.findById(publisherDto.getId());
        
        if (existingPublisherOpt.isPresent()) {
            Publisher existingPublisher = existingPublisherOpt.get();
            referenceDataMapper.updatePublisherFromDto(publisherDto, existingPublisher);
            Publisher updatedPublisher = publisherRepository.save(existingPublisher);
            return referenceDataMapper.toPublisherDto(updatedPublisher);
        }
        
        throw new RuntimeException("Publisher not found with id: " + publisherDto.getId());
    }
    
    @Transactional
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }
    
    // BookType methods
    public List<BookTypeDto> getAllBookTypes() {
        List<BookType> bookTypes = bookTypeRepository.findAll();
        return bookTypes.stream().map(referenceDataMapper::toBookTypeDto).collect(Collectors.toList());
    }
    
    public Optional<BookTypeDto> getBookTypeById(Long id) {
        Optional<BookType> bookType = bookTypeRepository.findById(id);
        return bookType.map(referenceDataMapper::toBookTypeDto);
    }
    
    @Transactional
    public BookTypeDto createBookType(BookTypeDto bookTypeDto) {
        BookType bookType = referenceDataMapper.toBookTypeEntity(bookTypeDto);
        BookType savedBookType = bookTypeRepository.save(bookType);
        return referenceDataMapper.toBookTypeDto(savedBookType);
    }
    
    @Transactional
    public BookTypeDto updateBookType(BookTypeDto bookTypeDto) {
        Optional<BookType> existingBookTypeOpt = bookTypeRepository.findById(bookTypeDto.getId());
        
        if (existingBookTypeOpt.isPresent()) {
            BookType existingBookType = existingBookTypeOpt.get();
            referenceDataMapper.updateBookTypeFromDto(bookTypeDto, existingBookType);
            BookType updatedBookType = bookTypeRepository.save(existingBookType);
            return referenceDataMapper.toBookTypeDto(updatedBookType);
        }
        
        throw new RuntimeException("BookType not found with id: " + bookTypeDto.getId());
    }
    
    @Transactional
    public void deleteBookType(Long id) {
        bookTypeRepository.deleteById(id);
    }
    
    // Condition methods
    public List<ConditionDto> getAllConditions() {
        List<Condition> conditions = conditionRepository.findAll();
        return conditions.stream().map(referenceDataMapper::toConditionDto).collect(Collectors.toList());
    }
    
    public Optional<ConditionDto> getConditionById(Long id) {
        Optional<Condition> condition = conditionRepository.findById(id);
        return condition.map(referenceDataMapper::toConditionDto);
    }
    
    @Transactional
    public ConditionDto createCondition(ConditionDto conditionDto) {
        Condition condition = referenceDataMapper.toConditionEntity(conditionDto);
        Condition savedCondition = conditionRepository.save(condition);
        return referenceDataMapper.toConditionDto(savedCondition);
    }
    
    @Transactional
    public ConditionDto updateCondition(ConditionDto conditionDto) {
        Optional<Condition> existingConditionOpt = conditionRepository.findById(conditionDto.getId());
        
        if (existingConditionOpt.isPresent()) {
            Condition existingCondition = existingConditionOpt.get();
            referenceDataMapper.updateConditionFromDto(conditionDto, existingCondition);
            Condition updatedCondition = conditionRepository.save(existingCondition);
            return referenceDataMapper.toConditionDto(updatedCondition);
        }
        
        throw new RuntimeException("Condition not found with id: " + conditionDto.getId());
    }
    
    @Transactional
    public void deleteCondition(Long id) {
        conditionRepository.deleteById(id);
    }
}
