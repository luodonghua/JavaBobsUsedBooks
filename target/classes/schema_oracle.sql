-- Oracle version of schema.sql for production deployment
DROP SEQUENCE IF EXISTS conditions_seq;
DROP SEQUENCE IF EXISTS book_types_seq;
DROP SEQUENCE IF EXISTS genres_seq;
DROP SEQUENCE IF EXISTS publishers_seq;
DROP SEQUENCE IF EXISTS reference_data_seq;
DROP SEQUENCE IF EXISTS customers_seq;
DROP SEQUENCE IF EXISTS addresses_seq;
DROP SEQUENCE IF EXISTS books_seq;
DROP SEQUENCE IF EXISTS shopping_carts_seq;
DROP SEQUENCE IF EXISTS shopping_cart_items_seq;
DROP SEQUENCE IF EXISTS orders_seq;
DROP SEQUENCE IF EXISTS order_items_seq;
DROP SEQUENCE IF EXISTS offers_seq;
DROP SEQUENCE IF EXISTS password_reset_tokens_seq;

-- Create sequences for ID generation
CREATE SEQUENCE conditions_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE book_types_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE genres_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE publishers_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE reference_data_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE customers_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE addresses_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE books_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE shopping_carts_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE shopping_cart_items_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE orders_seq START WITH 1 INCREMENT BY 100;
CREATE SEQUENCE order_items_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE offers_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE password_reset_tokens_seq START WITH 100 INCREMENT BY 1;

-- Create tables for reference data
CREATE TABLE conditions (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL UNIQUE,
    description CLOB
);

CREATE TABLE book_types (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL UNIQUE,
    description CLOB
);

CREATE TABLE genres (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL UNIQUE,
    description CLOB
);

CREATE TABLE publishers (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL UNIQUE,
    description CLOB
);

-- Create reference_data table for generic reference data
CREATE TABLE reference_data (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    description CLOB,
    type VARCHAR2(50) NOT NULL,
    is_active NUMBER(1) DEFAULT 1,
    created_by VARCHAR2(100) DEFAULT 'System',
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create customer table
CREATE TABLE customers (
    id NUMBER PRIMARY KEY,
    sub VARCHAR2(450) UNIQUE,
    username VARCHAR2(100) NOT NULL UNIQUE,
    firstname VARCHAR2(100),
    lastname VARCHAR2(100),
    email VARCHAR2(255) NOT NULL UNIQUE,
    date_of_birth TIMESTAMP,
    phone VARCHAR2(50),
    password_hash VARCHAR2(255),
    email_verified NUMBER(1) DEFAULT 0,
    account_locked NUMBER(1) DEFAULT 0,
    account_expired NUMBER(1) DEFAULT 0,
    credentials_expired NUMBER(1) DEFAULT 0,
    last_login TIMESTAMP,
    role VARCHAR2(20) DEFAULT 'CUSTOMER',
    created_by VARCHAR2(100) DEFAULT 'System',
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create address table
CREATE TABLE addresses (
    id NUMBER PRIMARY KEY,
    address_line_1 VARCHAR2(255) NOT NULL,
    address_line_2 VARCHAR2(255),
    city VARCHAR2(100) NOT NULL,
    state VARCHAR2(100) NOT NULL,
    country VARCHAR2(100) NOT NULL,
    zip_code VARCHAR2(20) NOT NULL,
    customer_id NUMBER NOT NULL,
    is_active NUMBER(1) NOT NULL,
    created_by VARCHAR2(100) DEFAULT 'System',
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_address_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Create book table with seller fields
CREATE TABLE books (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(255) NOT NULL,
    author VARCHAR2(255) NOT NULL,
    year NUMBER,
    isbn VARCHAR2(50),
    publisher_id NUMBER,
    book_type_id NUMBER,
    genre_id NUMBER,
    condition_id NUMBER,
    cover_image_url VARCHAR2(255),
    summary CLOB,
    price NUMBER(10,2) NOT NULL,
    quantity NUMBER NOT NULL,
    seller_id NUMBER,
    listing_status NUMBER DEFAULT 0,
    is_user_listing NUMBER(1) DEFAULT 0,
    is_featured NUMBER(1) DEFAULT 0,
    is_bestseller NUMBER(1) DEFAULT 0,
    is_new_arrival NUMBER(1) DEFAULT 0,
    is_available NUMBER(1) DEFAULT 1,
    created_by VARCHAR2(100) DEFAULT 'System',
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_book_publisher FOREIGN KEY (publisher_id) REFERENCES publishers(id),
    CONSTRAINT fk_book_type FOREIGN KEY (book_type_id) REFERENCES book_types(id),
    CONSTRAINT fk_book_genre FOREIGN KEY (genre_id) REFERENCES genres(id),
    CONSTRAINT fk_book_condition FOREIGN KEY (condition_id) REFERENCES conditions(id),
    CONSTRAINT fk_book_seller FOREIGN KEY (seller_id) REFERENCES customers(id)
);

-- Create shopping cart table
CREATE TABLE shopping_carts (
    id NUMBER PRIMARY KEY,
    customer_id NUMBER,
    created_by VARCHAR2(100) DEFAULT 'System',
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cart_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Create shopping cart item table
CREATE TABLE shopping_cart_items (
    id NUMBER PRIMARY KEY,
    customer_id NUMBER NOT NULL,
    book_id NUMBER NOT NULL,
    quantity NUMBER NOT NULL,
    is_wishlist_item NUMBER(1) DEFAULT 0 NOT NULL,
    shopping_cart_id NUMBER,
    created_by VARCHAR2(100) DEFAULT 'System',
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_cart_item_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_cart_item_book FOREIGN KEY (book_id) REFERENCES books(id),
    CONSTRAINT fk_cart_item_cart FOREIGN KEY (shopping_cart_id) REFERENCES shopping_carts(id)
);

-- Create order table
CREATE TABLE orders (
    id NUMBER PRIMARY KEY,
    customer_id NUMBER NOT NULL,
    address_id NUMBER NOT NULL,
    delivery_date TIMESTAMP,
    order_status NUMBER NOT NULL,
    total_amount NUMBER(10,2) DEFAULT 0.00 NOT NULL,
    created_by VARCHAR2(100) DEFAULT 'System',
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
    CONSTRAINT fk_order_address FOREIGN KEY (address_id) REFERENCES addresses(id)
);

-- Create order item table
CREATE TABLE order_items (
    id NUMBER PRIMARY KEY,
    order_id NUMBER NOT NULL,
    book_id NUMBER NOT NULL,
    quantity NUMBER NOT NULL,
    book_price NUMBER(10,2) NOT NULL,
    created_by VARCHAR2(100) DEFAULT 'System',
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES orders(id),
    CONSTRAINT fk_order_item_book FOREIGN KEY (book_id) REFERENCES books(id)
);

-- Create offer table
CREATE TABLE offers (
    id NUMBER PRIMARY KEY,
    author VARCHAR2(255) NOT NULL,
    isbn VARCHAR2(50),
    book_name VARCHAR2(255) NOT NULL,
    cover_image_url VARCHAR2(255),
    genre_id NUMBER,
    condition_id NUMBER,
    publisher_id NUMBER,
    book_type_id NUMBER,
    summary CLOB,
    offer_status NUMBER NOT NULL,
    book_comment CLOB,
    customer_id NUMBER NOT NULL,
    offer_price NUMBER(10,2),
    created_by VARCHAR2(100) DEFAULT 'System',
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_offer_genre FOREIGN KEY (genre_id) REFERENCES genres(id),
    CONSTRAINT fk_offer_condition FOREIGN KEY (condition_id) REFERENCES conditions(id),
    CONSTRAINT fk_offer_publisher FOREIGN KEY (publisher_id) REFERENCES publishers(id),
    CONSTRAINT fk_offer_book_type FOREIGN KEY (book_type_id) REFERENCES book_types(id),
    CONSTRAINT fk_offer_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Create persistent_logins table for remember-me functionality
CREATE TABLE persistent_logins (
    username VARCHAR2(64) NOT NULL,
    series VARCHAR2(64) PRIMARY KEY,
    token VARCHAR2(64) NOT NULL,
    last_used TIMESTAMP NOT NULL
);

-- Create password_reset_tokens table for password reset functionality
CREATE TABLE password_reset_tokens (
    id NUMBER PRIMARY KEY,
    customer_id NUMBER NOT NULL,
    token VARCHAR2(100) NOT NULL UNIQUE,
    expiry_date TIMESTAMP NOT NULL,
    used NUMBER(1) DEFAULT 0,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reset_token_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Create triggers for ID generation
CREATE OR REPLACE TRIGGER conditions_trigger
BEFORE INSERT ON conditions
FOR EACH ROW
BEGIN
    SELECT conditions_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER book_types_trigger
BEFORE INSERT ON book_types
FOR EACH ROW
BEGIN
    SELECT book_types_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER genres_trigger
BEFORE INSERT ON genres
FOR EACH ROW
BEGIN
    SELECT genres_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER publishers_trigger
BEFORE INSERT ON publishers
FOR EACH ROW
BEGIN
    SELECT publishers_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER reference_data_trigger
BEFORE INSERT ON reference_data
FOR EACH ROW
BEGIN
    SELECT reference_data_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER customers_trigger
BEFORE INSERT ON customers
FOR EACH ROW
BEGIN
    SELECT customers_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER addresses_trigger
BEFORE INSERT ON addresses
FOR EACH ROW
BEGIN
    SELECT addresses_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER books_trigger
BEFORE INSERT ON books
FOR EACH ROW
BEGIN
    SELECT books_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER shopping_carts_trigger
BEFORE INSERT ON shopping_carts
FOR EACH ROW
BEGIN
    SELECT shopping_carts_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER shopping_cart_items_trigger
BEFORE INSERT ON shopping_cart_items
FOR EACH ROW
BEGIN
    SELECT shopping_cart_items_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER orders_trigger
BEFORE INSERT ON orders
FOR EACH ROW
BEGIN
    SELECT orders_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER order_items_trigger
BEFORE INSERT ON order_items
FOR EACH ROW
BEGIN
    SELECT order_items_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER offers_trigger
BEFORE INSERT ON offers
FOR EACH ROW
BEGIN
    SELECT offers_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

CREATE OR REPLACE TRIGGER password_reset_tokens_trigger
BEFORE INSERT ON password_reset_tokens
FOR EACH ROW
BEGIN
    SELECT password_reset_tokens_seq.NEXTVAL INTO :NEW.id FROM dual;
END;
/

-- Create indexes for better performance
CREATE INDEX idx_books_name ON books(name);
CREATE INDEX idx_books_author ON books(author);
CREATE INDEX idx_books_isbn ON books(isbn);
CREATE INDEX idx_customers_email ON customers(email);
CREATE INDEX idx_customers_username ON customers(username);
CREATE INDEX idx_orders_customer_id ON orders(customer_id);
CREATE INDEX idx_order_items_order_id ON order_items(order_id);
CREATE INDEX idx_shopping_cart_items_customer_id ON shopping_cart_items(customer_id);
CREATE INDEX idx_offers_customer_id ON offers(customer_id);
CREATE INDEX idx_password_reset_tokens_token ON password_reset_tokens(token);
CREATE INDEX idx_password_reset_tokens_customer_id ON password_reset_tokens(customer_id);
