# Bob's Used Books - Java Spring Boot Application

## Project Overview

Bob's Used Books is a comprehensive e-commerce application for a used bookstore, built with Java Spring Boot. The application allows customers to browse, purchase, and sell used books while providing administrators with tools for inventory management, order processing, and reporting.

This project is a Java Spring Boot implementation converted from an original ASP.NET Core version, featuring both web interfaces using Thymeleaf templates and RESTful APIs for potential mobile integration.

## Technology Stack

- **Backend**: Java 17, Spring Boot 3.1.5
- **Database**: Oracle (production)
- **ORM**: Spring Data JPA/Hibernate
- **Frontend**: Thymeleaf templates (server-side rendering)
- **Build Tool**: Maven
- **Security**: Spring Security

## Project Structure

The application follows a standard Spring Boot project structure with:

```
src/main/java/com/bobsusedbooks/
├── common/           # Common utilities and base classes
├── controllers/      # Web and REST API controllers
├── dtos/             # Data Transfer Objects
├── entities/         # JPA entity classes
├── enums/            # Enumeration types
├── repositories/     # Spring Data JPA repositories
├── services/         # Business logic services
├── security/         # Security configurations
└── utils/            # Utility classes
```

**Project Size:**
- 106 Java files
- 52 HTML templates

## Core Modules

### 1. Book Management
Browse, search, and view detailed information about books in inventory.

### 2. Customer Portal
User registration, authentication, profile management, and order history.

### 3. Shopping Cart
Add books to cart, manage quantities, and proceed to checkout.

### 4. Order Processing
Complete purchases, track order status, and view order history.

### 5. Book Resale
Submit offers to sell used books to the store.

### 6. Admin Portal (Hidden)
Secure administrative interface for inventory management, order processing, customer management, and reporting.

### 7. Reference Data Management
Manage genres, publishers, book types, and condition categories.

## Database Configuration

### Production (Oracle)

The application includes Oracle-compatible schema and data scripts for production deployment.
- src/main/resources/db/oracle/setup.sql
- src/main/resources/db/oracle/schema_oracle.sql
- src/main/resources/db/oracle/data_oracle_jpa.sql


## Running the Application

1. Make sure you have Java 17 and Maven installed
2. Create a Oracle database `XEFREE1`
3. Run the application using Maven:

```bash
mvn spring-boot:run
```

The application will be available at http://localhost:8081/

## API Endpoints

The application provides RESTful API endpoints for various operations:

- `/api/books` - Book management
- `/api/orders` - Order management
- `/api/cart` - Shopping cart operations
- `/api/offers` - Book resale offers
- `/api/customers` - Customer management
- `/api/reference-data` - Reference data management

## Security Features

- Role-based access control (USER and ADMIN roles)
- Secure password handling with BCrypt encryption
- Protected admin area
- Authentication workflows
- Session management

## Development Features

- Spring Boot DevTools for automatic restarts
- Comprehensive database scripts for both PostgreSQL and Oracle
- Flyway migrations for database schema evolution
- Detailed logging configuration

## Deployment

### Development
- Run with `mvn spring-boot:run` for local development

### Production
- Package as a JAR file with `mvn package`
- Deploy to a Java-compatible server
- Use the Oracle profile with `--spring.profiles.active=oracle`
- Configure production database settings in `application-oracle.properties`

## Future Enhancements

1. Enhanced frontend with responsive design
2. Email notifications for orders and offers
3. Comprehensive unit and integration tests
4. CI/CD pipeline setup
5. Advanced reporting capabilities
6. Customer reviews and ratings
7. Integration with payment gateways
