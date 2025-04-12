package com.bobsusedbooks.controllers;

import com.bobsusedbooks.dtos.BookDto;
import com.bobsusedbooks.dtos.OfferDto;
import com.bobsusedbooks.enums.OfferStatus;
import com.bobsusedbooks.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/offers")
public class RESTOffersController {

    @Autowired
    private OfferService offerService;

    @GetMapping
    public ResponseEntity<List<OfferDto>> getAllOffers() {
        List<OfferDto> offers = offerService.getAllOffers();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<OfferDto>> getAllOffersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<OfferDto> offers = offerService.getAllOffers(pageable);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable Long id) {
        Optional<OfferDto> offer = offerService.getOfferById(id);
        return offer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OfferDto>> getOffersByCustomerId(@PathVariable Long customerId) {
        List<OfferDto> offers = offerService.getOffersByCustomerId(customerId);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}/paginated")
    public ResponseEntity<Page<OfferDto>> getOffersByCustomerIdPaginated(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<OfferDto> offers = offerService.getOffersByCustomerId(customerId, pageable);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OfferDto> createOffer(@RequestBody OfferDto offerDto) {
        OfferDto createdOffer = offerService.createOffer(offerDto);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferDto> updateOffer(@PathVariable Long id, @RequestBody OfferDto offerDto) {
        offerDto.setId(id);
        OfferDto updatedOffer = offerService.updateOffer(offerDto);
        return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OfferDto> updateOfferStatus(
            @PathVariable Long id,
            @RequestParam OfferStatus status,
            @RequestParam(required = false) String comment) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        OfferDto updatedOffer = offerService.updateOfferStatus(id, status, comment, username);
        return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<OfferDto> approveOffer(
            @PathVariable Long id,
            @RequestParam(required = false) String comment) {
        
        OfferDto approvedOffer = offerService.approveOffer(id, comment);
        return new ResponseEntity<>(approvedOffer, HttpStatus.OK);
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<OfferDto> rejectOffer(
            @PathVariable Long id,
            @RequestParam(required = false) String comment) {
        
        OfferDto rejectedOffer = offerService.rejectOffer(id, comment);
        return new ResponseEntity<>(rejectedOffer, HttpStatus.OK);
    }

    @PostMapping("/{id}/convert")
    public ResponseEntity<BookDto> convertOfferToBook(@PathVariable Long id) {
        BookDto book = offerService.convertOfferToBook(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
