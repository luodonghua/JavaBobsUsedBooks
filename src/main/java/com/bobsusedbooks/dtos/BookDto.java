package com.bobsusedbooks.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookDto {
    private Long id;
    private String name;
    private String author;
    private String isbn;
    private String description;
    private String summary;
    private BigDecimal price;
    private Integer quantity;
    private Long genreId;
    private String genreName;
    private Long publisherId;
    private String publisherName;
    private Long conditionId;
    private String conditionName;
    private Long bookTypeId;
    private String bookTypeName;
    private Boolean isAvailable;
    private Boolean isFeatured;
    private Boolean isBestseller;
    private Boolean isNewArrival;
    private Boolean isUserListing;
    private Boolean isUserOffer;
    private Integer listingStatus;
    private Long sellerId;
    private String coverImageUrl;
    private LocalDate publishDate;
    private LocalDate lastRestockDate;
    private Integer year;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Long offerSourceId;
    private String createdBy;

    // Constructors
    public BookDto() {
        this.isAvailable = true;
        this.isFeatured = false;
        this.isBestseller = false;
        this.isNewArrival = false;
        this.isUserListing = false;
        this.isUserOffer = false;
        this.quantity = 1;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public Long getConditionId() {
        return conditionId;
    }

    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public Long getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(Long bookTypeId) {
        this.bookTypeId = bookTypeId;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public Boolean getIsBestseller() {
        return isBestseller;
    }

    public void setIsBestseller(Boolean isBestseller) {
        this.isBestseller = isBestseller;
    }

    public Boolean getIsNewArrival() {
        return isNewArrival;
    }

    public void setIsNewArrival(Boolean isNewArrival) {
        this.isNewArrival = isNewArrival;
    }

    public Boolean getIsUserListing() {
        return isUserListing;
    }

    public void setIsUserListing(Boolean isUserListing) {
        this.isUserListing = isUserListing;
    }

    public Boolean getIsUserOffer() {
        return isUserOffer;
    }

    public void setIsUserOffer(Boolean isUserOffer) {
        this.isUserOffer = isUserOffer;
    }

    public Integer getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(Integer listingStatus) {
        this.listingStatus = listingStatus;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
    
    public LocalDate getLastRestockDate() {
        return lastRestockDate;
    }

    public void setLastRestockDate(LocalDate lastRestockDate) {
        this.lastRestockDate = lastRestockDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
    
    // Legacy methods for backward compatibility
    @Deprecated
    public LocalDateTime getcreatedOn() {
        return createdOn;
    }

    @Deprecated
    public void setcreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getOfferSourceId() {
        return offerSourceId;
    }

    public void setOfferSourceId(Long offerSourceId) {
        this.offerSourceId = offerSourceId;
    }
}
