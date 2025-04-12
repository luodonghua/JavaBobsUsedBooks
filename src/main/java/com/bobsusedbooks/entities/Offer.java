package com.bobsusedbooks.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "customer_id")
    private Long customerId;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;
    
    @Column(name = "book_title", nullable = false)
    private String bookTitle;
    
    @Column(name = "book_author")
    private String bookAuthor;
    
    @Column(name = "isbn")
    private String isbn;
    
    @Column(name = "condition_id")
    private Long conditionId;
    
    @ManyToOne
    @JoinColumn(name = "condition_id", insertable = false, updatable = false)
    private Condition condition;
    
    @Column(name = "description", length = 1000)
    private String description;
    
    @Column(name = "offered_price")
    private BigDecimal askingPrice;
    
    @Column(name = "book_price")
    private BigDecimal bookPrice;
    
    @Column(name = "status", nullable = false)
    private Integer status;
    
    @Column(name = "offer_status")
    private Integer offerStatus;
    
    @Column(name = "admin_notes", length = 1000)
    private String adminNotes;
    
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
    
    @Column(name = "processed_at")
    private LocalDateTime processedAt;
    
    @Column(name = "processed_by")
    private Long processedBy;
    
    @Column(name = "is_approved")
    private Boolean isApproved;
    
    @Column(name = "is_rejected")
    private Boolean isRejected;
    
    @Column(name = "rejection_reason", length = 1000)
    private String rejectionReason;
    
    @Column(name = "contact_email")
    private String contactEmail;
    
    @Column(name = "contact_phone")
    private String contactPhone;
    
    @Column(name = "contact_name")
    private String contactName;
    
    @Column(name = "book_id")
    private Long bookId;
    
    @ManyToOne
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;
    
    @Column(name = "book_comment", length = 1000)
    private String bookComment;    

    @Column(name = "front_url")
    private String frontUrl;
    
    @Column(name = "summary", length = 1000)
    private String summary;
    
    @Column(name = "genre_id")
    private Long genreId;
    
    @Column(name = "publisher_id")
    private Long publisherId;
    
    @Column(name = "book_type_id")
    private Long bookTypeId;
    
    // Constructors
    public Offer() {
        this.status = 0; // Pending
        this.offerStatus = 0; // Pending
        this.isApproved = false;
        this.isRejected = false;
        this.createdOn = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getConditionId() {
        return conditionId;
    }

    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(BigDecimal askingPrice) {
        this.askingPrice = askingPrice;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(Integer offerStatus) {
        this.offerStatus = offerStatus;
    }

    public String getAdminNotes() {
        return adminNotes;
    }

    public void setAdminNotes(String adminNotes) {
        this.adminNotes = adminNotes;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedOn; // Alias for updatedOn
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedOn = updatedAt; // Set to updatedOn
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public Long getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(Long processedBy) {
        this.processedBy = processedBy;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Boolean getIsRejected() {
        return isRejected;
    }

    public void setIsRejected(Boolean isRejected) {
        this.isRejected = isRejected;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    public String getBookComment() {
        return bookComment;
    }

    public void setBookComment(String bookComment) {
        this.bookComment = bookComment;
    }
    
    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }
    
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }
    
    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }
    
    public Long getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(Long bookTypeId) {
        this.bookTypeId = bookTypeId;
    }
    
    // Alias for bookAuthor to match the method used in OfferService
    public String getAuthor() {
        return bookAuthor;
    }
    
    public void setAuthor(String author) {
        this.bookAuthor = author;
    }
    
    // Alias for askingPrice to match the method used in OfferService
    public BigDecimal getOfferedPrice() {
        return askingPrice;
    }
    
    public void setOfferedPrice(BigDecimal offeredPrice) {
        this.askingPrice = offeredPrice;
    }
}
