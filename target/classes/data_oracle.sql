-- Oracle version of data.sql for production deployment

-- Reference data for book conditions
INSERT INTO conditions (id, name, description) 
SELECT 4, 'New', 'Brand new, never used, in perfect condition' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM conditions WHERE id = 4);

INSERT INTO conditions (id, name, description) 
SELECT 5, 'Like New', 'Looks new but may have been read once or twice' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM conditions WHERE id = 5);

INSERT INTO conditions (id, name, description) 
SELECT 6, 'Good', 'Shows some signs of wear, may have markings' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM conditions WHERE id = 6);

INSERT INTO conditions (id, name, description) 
SELECT 7, 'Acceptable', 'Definitely used, may have markings or damage' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM conditions WHERE id = 7);

-- Reference data for book types
INSERT INTO book_types (id, name, description) 
SELECT 1, 'Hardcover', 'Book with a rigid binding' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM book_types WHERE id = 1);

INSERT INTO book_types (id, name, description) 
SELECT 2, 'Trade Paperback', 'Book with a flexible paper binding, higher quality' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM book_types WHERE id = 2);

INSERT INTO book_types (id, name, description) 
SELECT 3, 'Mass Market Paperback', 'Smaller format paperback book' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM book_types WHERE id = 3);

-- Reference data for genres
INSERT INTO genres (id, name, description) 
SELECT 8, 'Biographies', 'Non-fiction account of someone''s life' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM genres WHERE id = 8);

INSERT INTO genres (id, name, description) 
SELECT 9, 'Children''s Books', 'Books for children' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM genres WHERE id = 9);

INSERT INTO genres (id, name, description) 
SELECT 10, 'History', 'Non-fiction about past events' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM genres WHERE id = 10);

INSERT INTO genres (id, name, description) 
SELECT 11, 'Literature & Fiction', 'Literary works based on imagination' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM genres WHERE id = 11);

INSERT INTO genres (id, name, description) 
SELECT 12, 'Mystery, Thriller & Suspense', 'Fiction dealing with suspense, crime or puzzles' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM genres WHERE id = 12);

INSERT INTO genres (id, name, description) 
SELECT 13, 'Science Fiction & Fantasy', 'Fiction with scientific or fantastical elements' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM genres WHERE id = 13);

INSERT INTO genres (id, name, description) 
SELECT 14, 'Travel', 'Books about travel and destinations' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM genres WHERE id = 14);

-- Reference data for publishers
INSERT INTO publishers (id, name, description) 
SELECT 15, 'Arcadia Books', 'Independent publisher' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE id = 15);

INSERT INTO publishers (id, name, description) 
SELECT 16, 'Astral Publishing', 'Publishing company' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE id = 16);

INSERT INTO publishers (id, name, description) 
SELECT 17, 'Moonlight Publishing', 'Publishing company' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE id = 17);

INSERT INTO publishers (id, name, description) 
SELECT 18, 'Dreamscape Press', 'Publishing company' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE id = 18);

INSERT INTO publishers (id, name, description) 
SELECT 19, 'Enchanted Library', 'Publishing company' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE id = 19);

INSERT INTO publishers (id, name, description) 
SELECT 20, 'Fantasia House', 'Publishing company' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE id = 20);

INSERT INTO publishers (id, name, description) 
SELECT 21, 'Horizon Books', 'Publishing company' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE id = 21);

INSERT INTO publishers (id, name, description) 
SELECT 22, 'Infinity Press', 'Publishing company' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE id = 22);

INSERT INTO publishers (id, name, description) 
SELECT 23, 'Paradigm Publishing', 'Publishing company' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE id = 23);

INSERT INTO publishers (id, name, description) 
SELECT 24, 'Aurora Publishing', 'Publishing company' FROM dual
WHERE NOT EXISTS (SELECT 1 FROM publishers WHERE id = 24);

-- Sample books
INSERT INTO books (id, name, author, isbn, publisher_id, book_type_id, genre_id, condition_id, price, quantity, cover_image_url, is_featured, is_bestseller, is_new_arrival, created_by, created_on, updated_on)
SELECT 1, '2020: The Apocalypse', 'Li Juan', '6556784356', 15, 1, 13, 5, 10.95, 25, '/images/coverimages/apocalypse.png', 1, 0, 1, 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM books WHERE id = 1);

INSERT INTO books (id, name, author, isbn, publisher_id, book_type_id, genre_id, condition_id, price, quantity, cover_image_url, is_featured, is_bestseller, is_new_arrival, created_by, created_on, updated_on)
SELECT 2, 'Children Of Iron', 'Nikki Wolf', '7665438976', 16, 1, 11, 6, 13.95, 3, '/images/coverimages/childrenofiron.png', 0, 1, 0, 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM books WHERE id = 2);

INSERT INTO books (id, name, author, isbn, publisher_id, book_type_id, genre_id, condition_id, price, quantity, cover_image_url, is_featured, is_bestseller, is_new_arrival, created_by, created_on, updated_on)
SELECT 3, 'Gold In The Dark', 'Richard Roe', '5442280765', 17, 1, 13, 5, 6.50, 10, '/images/coverimages/goldinthedark.png', 1, 1, 0, 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM books WHERE id = 3);

INSERT INTO books (id, name, author, isbn, publisher_id, book_type_id, genre_id, condition_id, price, quantity, cover_image_url, is_featured, is_bestseller, is_new_arrival, created_by, created_on, updated_on)
SELECT 4, 'Leagues Of Smoke', 'Pat Candella', '4556789542', 18, 2, 11, 7, 3.00, 1, '/images/coverimages/leaguesofsmoke.png', 0, 0, 0, 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM books WHERE id = 4);

INSERT INTO books (id, name, author, isbn, publisher_id, book_type_id, genre_id, condition_id, price, quantity, cover_image_url, is_featured, is_bestseller, is_new_arrival, created_by, created_on, updated_on)
SELECT 5, 'Alone With The Stars', 'Carlos Salazar', '4563358087', 19, 2, 12, 5, 15.95, 5, '/images/coverimages/alonewiththestars.png', 0, 1, 1, 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM books WHERE id = 5);

INSERT INTO books (id, name, author, isbn, publisher_id, book_type_id, genre_id, condition_id, price, quantity, cover_image_url, is_featured, is_bestseller, is_new_arrival, created_by, created_on, updated_on)
SELECT 6, 'The Girl In The Polaroid', 'Terri Whitlock', '2354435678', 20, 1, 12, 6, 8.25, 2, '/images/coverimages/girlinthepolaroid.png', 1, 0, 1, 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM books WHERE id = 6);

INSERT INTO books (id, name, author, isbn, publisher_id, book_type_id, genre_id, condition_id, price, quantity, cover_image_url, is_featured, is_bestseller, is_new_arrival, created_by, created_on, updated_on)
SELECT 7, '1001 Jokes', 'Mary Major', '6554789632', 21, 2, 11, 5, 13.95, 7, '/images/coverimages/1001jokes.png', 0, 0, 1, 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM books WHERE id = 7);

INSERT INTO books (id, name, author, isbn, publisher_id, book_type_id, genre_id, condition_id, price, quantity, cover_image_url, is_featured, is_bestseller, is_new_arrival, created_by, created_on, updated_on)
SELECT 8, 'My Search For Meaning', 'Mateo Jackson', '4558786554', 22, 3, 8, 7, 5.00, 15, '/images/coverimages/mysearchformeaning.png', 1, 1, 0, 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM books WHERE id = 8);

-- Sample customers (for development only)
-- Password is 'password' encrypted with BCrypt
INSERT INTO customers (id, sub, username, firstname, lastname, email, date_of_birth, phone, password_hash, role, email_verified, account_locked, account_expired, credentials_expired, created_on)
SELECT 1, 'auth0|123456', 'johndoe', 'John', 'Doe', 'john.doe@example.com', TO_TIMESTAMP('1985-05-15', 'YYYY-MM-DD'), '555-123-4567', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'USER', 1, 0, 0, 0, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE id = 1);

INSERT INTO customers (id, sub, username, firstname, lastname, email, date_of_birth, phone, password_hash, role, email_verified, account_locked, account_expired, credentials_expired, created_on)
SELECT 2, 'auth0|234567', 'janedoe', 'Jane', 'Doe', 'jane.doe@example.com', TO_TIMESTAMP('1990-10-20', 'YYYY-MM-DD'), '555-234-5678', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'USER', 1, 0, 0, 0, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE id = 2);

INSERT INTO customers (id, sub, username, firstname, lastname, email, date_of_birth, phone, password_hash, role, email_verified, account_locked, account_expired, credentials_expired, created_on)
SELECT 3, NULL, 'admin', 'Admin', 'User', 'admin@bobsusedbooks.com', TO_TIMESTAMP('1980-01-01', 'YYYY-MM-DD'), '555-999-8888', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'ADMIN', 1, 0, 0, 0, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM customers WHERE id = 3);

-- Sample addresses
INSERT INTO addresses (id, address_line_1, address_line_2, city, state, country, zip_code, customer_id, is_active, created_by, created_on, updated_on)
SELECT 1, '123 Main St', 'Apt 4B', 'Seattle', 'WA', 'USA', '98101', 1, 1, 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM addresses WHERE id = 1);

INSERT INTO addresses (id, address_line_2, address_line_1, city, state, country, zip_code, customer_id, is_active, created_by, created_on, updated_on)
SELECT 2, NULL, '456 Oak Ave', 'Portland', 'OR', 'USA', '97205', 2, 1, 'system', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP FROM dual
WHERE NOT EXISTS (SELECT 1 FROM addresses WHERE id = 2);

-- Update sequence values to continue after our inserted data
-- Oracle doesn't need this as we're using triggers with sequences
