# Bob's Used Books - Java Application Documentation

## Overview

Bob's Used Books is a comprehensive e-commerce application for a used bookstore, built with Java Spring Boot. The application allows customers to browse, purchase, and sell used books while providing administrators with tools for inventory management, order processing, and reporting.

## Technology Stack

- **Backend**: Java 17, Spring Boot 3.1.5
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA/Hibernate
- **Frontend**: Thymeleaf templates (server-side rendering)
- **Build Tool**: Maven
- **Security**: Spring Security

## Core Modules

### 1. Book Management Module

This module handles all aspects of book inventory management.

**Key Components:**
- `BookController`: Handles web requests for displaying books to customers
- `BookAdminController`: Provides administrative functions for book management
- `BookService`: Contains business logic for book operations
- `BookRepository`: Data access layer for book entities

**Features:**
- Browse books by category, author, or other criteria
- Search functionality with filters
- Book details display
- Featured books and bestsellers
- New arrivals section

### 2. Customer Module

Manages customer accounts, profiles, and authentication.

**Key Components:**
- `CustomerController`: Handles customer profile management
- `AuthController`: Manages authentication processes
- `CustomerService`: Business logic for customer operations
- `CustomerRepository`: Data access for customer entities

**Features:**
- User registration and login
- Profile management
- Address management
- Password reset functionality
- Account history

### 3. Shopping Cart Module

Handles the shopping cart functionality for customers.

**Key Components:**
- `CartController`: Web controller for cart operations
- `ShoppingCartController`: REST API for cart operations
- `ShoppingCartService`: Business logic for cart management
- `ShoppingCartRepository`: Data access for cart entities

**Features:**
- Add/remove items from cart
- Update quantities
- Cart summary
- Checkout process
- Wishlist functionality

### 4. Order Processing Module

Manages the order lifecycle from checkout to fulfillment.

**Key Components:**
- `OrderController`: Handles order creation and viewing
- `OrderService`: Business logic for order processing
- `OrderRepository`: Data access for order entities

**Features:**
- Order creation
- Order history
- Order status tracking
- Order details view
- Order confirmation

### 5. Book Resale Module

Allows customers to sell their used books to the store.

**Key Components:**
- `OfferController`: REST API for offer management
- `SellController`: Web interface for customers to create offers
- `OfferService`: Business logic for processing offers
- `OfferRepository`: Data access for offer entities

**Features:**
- Submit book resale offers
- Track offer status
- Offer approval/rejection process
- Convert approved offers to inventory

### 6. Reference Data Module

Manages reference data used throughout the application.

**Key Components:**
- `ReferenceDataController`: Manages reference data entities
- `ReferenceDataService`: Business logic for reference data
- Various repositories for reference entities

**Features:**
- Genre management
- Publisher management
- Book condition types
- Book format types

### 7. Admin Module (Hidden)

A secure administrative interface for store management.

**Key Components:**
- `AdminController`: Main entry point for admin dashboard
- `AdminAuthController`: Handles admin authentication
- `BookAdminController`: Admin-specific book management
- `ReportController`: Generates business reports

**Features:**
- Secure admin login (accessible at `/admin/login`)
- Dashboard with key metrics
- Inventory management
- Order management
- Customer management
- Offer review and processing
- Reference data management
- Reporting tools:
  - Inventory reports
  - Sales reports
  - Low stock alerts

### 8. Reporting Module

Provides business intelligence and reporting capabilities.

**Key Components:**
- `ReportController`: Generates various reports
- `ReportService`: Business logic for report generation

**Features:**
- Inventory valuation reports
- Sales analysis by category
- Monthly sales reports
- Low stock reports

### 9. Security Module

Handles authentication, authorization, and security concerns.

**Key Components:**
- `SecurityConfig`: Main security configuration
- `AdminSecurityConfig`: Admin-specific security rules
- `CustomUserDetailsService`: User authentication service

**Features:**
- Role-based access control
- Secure password handling
- Protected admin area
- Authentication workflows
- Session management

## Database Schema

The application uses a PostgreSQL database with the following main tables:
- `books`: Stores book inventory information
- `customers`: Customer account information
- `addresses`: Customer shipping addresses
- `orders`: Order header information
- `order_items`: Individual items within orders
- `shopping_cart_items`: Items in customer shopping carts
- `offers`: Book resale offers from customers
- Reference tables: `genres`, `publishers`, `book_types`, `conditions`

## API Endpoints

The application provides RESTful API endpoints for various operations:
- `/api/books` - Book management
- `/api/orders` - Order management
- `/api/cart` - Shopping cart operations
- `/api/offers` - Book resale offers
- `/api/customers` - Customer management
- `/api/reference-data` - Reference data management

## Web Routes

Key web routes for the application:
- `/` - Home page
- `/books` - Browse books
- `/search` - Search functionality
- `/cart` - Shopping cart
- `/checkout` - Checkout process
- `/orders` - Order history
- `/sell` - Book resale portal
- `/admin` - Admin dashboard (protected)
- `/admin/login` - Admin login

## Development and Deployment

### Development Setup
1. Install Java 17 and Maven
2. Create a PostgreSQL database named `bk2`
3. Configure database connection in `application.properties`
4. Run with `mvn spring-boot:run`

### Production Deployment
- Package as a JAR file with `mvn package`
- Deploy to a Java-compatible server
- Configure production database settings
- Set up proper security measures

## Future Enhancements

1. Enhanced frontend with responsive design
2. Email notifications for orders and offers
3. Comprehensive unit and integration tests
4. CI/CD pipeline setup
5. Advanced reporting capabilities
6. Customer reviews and ratings
7. Integration with payment gateways
