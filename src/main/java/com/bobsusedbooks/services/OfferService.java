package com.bobsusedbooks.services;

import com.bobsusedbooks.dtos.BookDto;
import com.bobsusedbooks.dtos.OfferDto;
import com.bobsusedbooks.entities.Customer;
import com.bobsusedbooks.entities.Offer;
import com.bobsusedbooks.enums.OfferStatus;
import com.bobsusedbooks.mappers.OfferMapper;
import com.bobsusedbooks.repositories.CustomerRepository;
import com.bobsusedbooks.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private OfferMapper offerMapper;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private EmailService emailService;
    
    public List<OfferDto> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream().map(offerMapper::toDto).collect(Collectors.toList());
    }
    
    public Page<OfferDto> getAllOffers(Pageable pageable) {
        Page<Offer> offers = offerRepository.findAll(pageable);
        return offers.map(offerMapper::toDto);
    }
    
    public Optional<OfferDto> getOfferById(Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        return offer.map(offerMapper::toDto);
    }
    
    public List<OfferDto> getOffersByCustomerId(Long customerId) {
        List<Offer> offers = offerRepository.findByCustomerId(customerId);
        return offers.stream().map(offerMapper::toDto).collect(Collectors.toList());
    }
    
    public Page<OfferDto> getOffersByCustomerId(Long customerId, Pageable pageable) {
        Page<Offer> offers = offerRepository.findByCustomerId(customerId, pageable);
        return offers.map(offerMapper::toDto);
    }
    
    public List<OfferDto> getOffersByStatus(Integer status) {
        List<Offer> offers = offerRepository.findByStatus(status);
        return offers.stream().map(offerMapper::toDto).collect(Collectors.toList());
    }
    
    public List<OfferDto> getOffersByOfferStatus(Integer offerStatus) {
        List<Offer> offers = offerRepository.findByOfferStatus(offerStatus);
        return offers.stream().map(offerMapper::toDto).collect(Collectors.toList());
    }
    
    public Page<OfferDto> getOffersByOfferStatus(Integer offerStatus, Pageable pageable) {
        Page<Offer> offers = offerRepository.findByOfferStatus(offerStatus, pageable);
        return offers.map(offerMapper::toDto);
    }
    
    @Transactional
    public OfferDto createOffer(OfferDto offerDto) {
        Offer offer = offerMapper.toEntity(offerDto);
        offer.setCreatedOn(LocalDateTime.now());
        offer.setStatus(0); // Pending by default
        offer.setOfferStatus(0); // Pending by default
        
        Offer savedOffer = offerRepository.save(offer);
        return offerMapper.toDto(savedOffer);
    }
    
    @Transactional
    public OfferDto updateOffer(OfferDto offerDto) {
        Optional<Offer> existingOfferOpt = offerRepository.findById(offerDto.getId());
        
        if (existingOfferOpt.isPresent()) {
            Offer existingOffer = existingOfferOpt.get();
            offerMapper.updateEntityFromDto(offerDto, existingOffer);
            existingOffer.setUpdatedAt(LocalDateTime.now());
            
            Offer updatedOffer = offerRepository.save(existingOffer);
            return offerMapper.toDto(updatedOffer);
        }
        
        throw new RuntimeException("Offer not found with id: " + offerDto.getId());
    }
    
    @Transactional
    public OfferDto approveOffer(Long offerId, String BookComment) {
        Optional<Offer> offerOpt = offerRepository.findById(offerId);
        
        if (offerOpt.isPresent()) {
            Offer offer = offerOpt.get();
            offer.setStatus(1); // Approved
            offer.setOfferStatus(1); // Approved
            offer.setBookComment(BookComment);
            offer.setUpdatedAt(LocalDateTime.now());
            
            Offer updatedOffer = offerRepository.save(offer);
            
            // Notify customer
            if (offer.getCustomer() != null) {
                // In a real app, we would send an email
                // For this demo, we'll just log it
                System.out.println("Offer approved: " + offerId);
                System.out.println("Customer: " + offer.getCustomer().getEmail());
            }
            
            return offerMapper.toDto(updatedOffer);
        }
        
        throw new RuntimeException("Offer not found with id: " + offerId);
    }
    
    @Transactional
    public OfferDto rejectOffer(Long offerId, String BookComment) {
        Optional<Offer> offerOpt = offerRepository.findById(offerId);
        
        if (offerOpt.isPresent()) {
            Offer offer = offerOpt.get();
            offer.setStatus(2); // Rejected
            offer.setOfferStatus(2); // Rejected
            offer.setBookComment(BookComment);
            offer.setUpdatedAt(LocalDateTime.now());
            
            Offer updatedOffer = offerRepository.save(offer);
            
            // Notify customer
            if (offer.getCustomer() != null) {
                // In a real app, we would send an email
                // For this demo, we'll just log it
                System.out.println("Offer rejected: " + offerId);
                System.out.println("Customer: " + offer.getCustomer().getEmail());
            }
            
            return offerMapper.toDto(updatedOffer);
        }
        
        throw new RuntimeException("Offer not found with id: " + offerId);
    }
    
    @Transactional
    public OfferDto acceptOffer(Long offerId, java.math.BigDecimal bookPrice) {
        Optional<Offer> offerOpt = offerRepository.findById(offerId);
        
        if (offerOpt.isPresent()) {
            Offer offer = offerOpt.get();
            offer.setStatus(1); // Accepted
            offer.setOfferStatus(1); // Accepted
            offer.setBookPrice(bookPrice);
            offer.setUpdatedAt(LocalDateTime.now());
            
            Offer updatedOffer = offerRepository.save(offer);
            
            // Notify customer
            if (offer.getCustomer() != null) {
                // In a real app, we would send an email
                // For this demo, we'll just log it
                System.out.println("Offer accepted: " + offerId);
                System.out.println("Customer: " + offer.getCustomer().getEmail());
                System.out.println("Book Price: " + bookPrice);
            }
            
            return offerMapper.toDto(updatedOffer);
        }
        
        throw new RuntimeException("Offer not found with id: " + offerId);
    }
    
    @Transactional
    public BookDto convertOfferToBook(Long offerId) {
        Optional<Offer> offerOpt = offerRepository.findById(offerId);
        
        if (offerOpt.isPresent()) {
            Offer offer = offerOpt.get();
            
            // Create a new book from the offer
            BookDto bookDto = new BookDto();
            bookDto.setName(offer.getBookTitle());
            bookDto.setAuthor(offer.getAuthor());
            bookDto.setIsbn(offer.getIsbn());
            bookDto.setDescription(offer.getDescription());
            bookDto.setPrice(offer.getOfferedPrice());
            bookDto.setQuantity(1);
            bookDto.setCoverImageUrl(offer.getFrontUrl());
            bookDto.setIsFeatured(false);
            bookDto.setIsBestseller(false);
            bookDto.setIsNewArrival(true);
            bookDto.setSummary(offer.getSummary());
            
            // Set reference data
            bookDto.setGenreId(offer.getGenreId());
            bookDto.setPublisherId(offer.getPublisherId());
            bookDto.setBookTypeId(offer.getBookTypeId());
            bookDto.setConditionId(offer.getConditionId());
            
            // Set user offer fields
            bookDto.setIsUserOffer(true);
            bookDto.setOfferSourceId(offer.getId());
            
            // Save the book
            BookDto savedBook = bookService.createBook(bookDto);
            
            // Update the offer status
            offer.setStatus(3); // Converted to book
            offer.setOfferStatus(3); // Converted to book
            offer.setUpdatedAt(LocalDateTime.now());
            offerRepository.save(offer);
            
            return savedBook;
        }
        
        throw new RuntimeException("Offer not found with id: " + offerId);
    }
    
    @Transactional
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }
    
    @Transactional
    public OfferDto updateOfferStatus(Long offerId, OfferStatus status, String bookComment, String username) {
        Optional<Offer> offerOpt = offerRepository.findById(offerId);
        
        if (offerOpt.isEmpty()) {
            throw new RuntimeException("Offer not found with id: " + offerId);
        }
        
        Offer offer = offerOpt.get();
        offer.setStatus(status.ordinal());
        offer.setOfferStatus(status.ordinal());
        offer.setBookComment(bookComment);
        offer.setUpdatedAt(LocalDateTime.now());
        
        Offer updatedOffer = offerRepository.save(offer);
        
        // Notify customer if needed
        if (offer.getCustomer() != null) {
            // In a real app, we would send an email
            // For this demo, we'll just log it
            System.out.println("Offer status updated: " + offerId);
            System.out.println("New status: " + status);
            System.out.println("Customer: " + offer.getCustomer().getEmail());
        }
        
        return offerMapper.toDto(updatedOffer);
    }
}
