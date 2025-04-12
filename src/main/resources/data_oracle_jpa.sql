-- Oracle version of data.sql for production deployment

SET DEFINE OFF

-- Reference data for book conditions
INSERT INTO conditions (name, description) 
VALUES('New', 'Brand new, never used, in perfect condition');

INSERT INTO conditions (name, description) 
VALUES('Like New', 'Looks new but may have been read once or twice');

INSERT INTO conditions (name, description) 
VALUES('Good', 'Shows some signs of wear, may have markings');

INSERT INTO conditions (name, description) 
VALUES('Acceptable', 'Definitely used, may have markings or damage');



-- Reference data for book types
INSERT INTO book_types (name, description) 
VALUES ('Hardcover', 'Book with a rigid binding');

INSERT INTO book_types (name, description) 
VALUES ('Trade Paperback', 'Book with a flexible paper binding, higher quality');

INSERT INTO book_types (name, description) 
VALUES ('Mass Market Paperback', 'Smaller format paperback book');



-- Reference data for genres
INSERT INTO genres (name, description) 
VALUES ('Biographies', 'Non-fiction account of someone''s life');

INSERT INTO genres (name, description)
VALUES ('Children''s Books', 'Books for children');

INSERT INTO genres (name, description)
VALUES ('History', 'Non-fiction about past events');

INSERT INTO genres (name, description)
VALUES ('Literature & Fiction', 'Literary works based on imagination');

INSERT INTO genres (name, description)
VALUES ('Mystery, Thriller & Suspense', 'Fiction dealing with suspense, crime or puzzles');

INSERT INTO genres (name, description)
VALUES ('Science Fiction & Fantasy', 'Fiction with scientific or fantastical elements');

INSERT INTO genres (name, description)
VALUES ('Travel', 'Books about travel and destinations');


-- Reference data for publishers
INSERT INTO publishers (name, description) 
VALUES ('Arcadia Books', 'Independent publisher');

INSERT INTO publishers (name, description) 
VALUES ('Astral Publishing', 'Publishing company');

INSERT INTO publishers (name, description) 
VALUES ('Moonlight Publishing', 'Publishing company');

INSERT INTO publishers (name, description) 
VALUES ('Dreamscape Press', 'Publishing company');

INSERT INTO publishers (name, description) 
VALUES ('Enchanted Library', 'Publishing company');

INSERT INTO publishers (name, description) 
VALUES ('Fantasia House', 'Publishing company');

INSERT INTO publishers (name, description) 
VALUES ('Horizon Books', 'Publishing company');

INSERT INTO publishers (name, description) 
VALUES ('Infinity Press', 'Publishing company');

INSERT INTO publishers (name, description) 
VALUES ('Paradigm Publishing', 'Publishing company');

INSERT INTO publishers (name, description) 
VALUES ('Aurora Publishing', 'Publishing company');


-- Sample books

Insert into books (ID,author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year) 
values (1,'Li Juan',1,2,'/images/coverimages/apocalypse.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,6, null,0,1,1, null, null,'6556784356', null, null,'2020: The Apocalypse', null,10.95, null,1,25, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);
Insert into books (ID,author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year) 
values (2,'Nikki Wolf',1,3,'/images/coverimages/childrenofiron.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,4, null,1,0,0, null, null,'7665438976', null, null,'Children Of Iron', null,13.95, null,2,3, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);
Insert into books (ID,author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year) 
values (3,'Richard Roe',1,1,'/images/coverimages/goldinthedark.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,6, null,1,1,0, null, null,'5442280765', null, null,'Gold In The Dark', null,6.5, null,3,10, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);
Insert into books (ID,author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year) 
values (4,'Pat Candella',2,3,'/images/coverimages/leaguesofsmoke.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,4, null,0,0,0, null, null,'4556789542', null, null,'Leagues Of Smoke', null,3, null,4,1, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);
Insert into books (ID,author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year) 
values (5,'Carlos Salazar',2,1,'/images/coverimages/alonewiththestars.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,5, null,1,0,1, null, null,'4563358087', null, null,'Alone With The Stars', null,15.95, null,5,5, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);
Insert into books (ID,author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year) 
values (6,'Terri Whitlock',1,2,'/images/coverimages/girlinthepolaroid.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,5, null,0,1,1, null, null,'2354435678', null, null,'The Girl In The Polaroid', null,8.25, null,6,2, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);
Insert into books (ID,author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year) 
values (7,'Mary Major',2,1,'/images/coverimages/1001jokes.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,6, null,0,0,1, null, null,'6554789632', null, null,'1001 Jokes', null,13.95, null,7,7, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);
Insert into books (ID,author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year) 
values (8,'Mateo Jackson',3,3,'/images/coverimages/mysearchformeaning.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,1, null,1,1,0, null, null,'4558786554', null, null,'My Search For Meaning', null,5, null,8,15, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);


-- Sample customers (for development only)
-- Password is 'password' encrypted with BCrypt
REM INSERTING into CUSTOMERS
SET DEFINE OFF;


Insert into customers (account_expired,account_locked,created_on,credentials_expired,date_of_birth,email,email_verified,first_name,last_login,last_name,password_hash,phone_number,role,sub,username) 
values (0,0,to_timestamp('24/03/25 11:34:21.441957000','DD/MM/RR HH24:MI:SSXFF'),0,to_date('15/05/85','DD/MM/RR'),'john.doe@example.com',1,'John', null,'Doe','$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG','555-123-4567','USER','auth0|123456','johndoe');
Insert into customers (account_expired,account_locked,created_on,credentials_expired,date_of_birth,email,email_verified,first_name,last_login,last_name,password_hash,phone_number,role,sub,username) 
values (0,0,to_timestamp('24/03/25 11:34:21.481827000','DD/MM/RR HH24:MI:SSXFF'),0,to_date('20/10/90','DD/MM/RR'),'jane.doe@example.com',1,'Jane', null,'Doe','$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG','555-234-5678','USER','auth0|234567','janedoe');
Insert into customers (account_expired,account_locked,created_on,credentials_expired,date_of_birth,email,email_verified,first_name,last_login,last_name,password_hash,phone_number,role,sub,username) 
values (0,0,to_timestamp('24/03/25 11:34:21.528644000','DD/MM/RR HH24:MI:SSXFF'),0,to_date('01/01/80','DD/MM/RR'),'admin@bobsusedbooks.com',1,'Admin', null,'User','$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG','555-999-8888','ADMIN', null,'admin');


-- Sample addresses
Insert into addresses (address_line1,address_line2,address_type,city,country,customer_id,is_default,postal_code,state) 
values ('123 Main St','Apt 4B', null,'Seattle','USA',1,1,'98101','WA');
Insert into addresses (address_line1,address_line2,address_type,city,country,customer_id,is_default,postal_code,state) 
values ('456 Oak Ave', null, null,'Portland','USA',2,1,'97205','OR');

-- Update sequence values to continue after our inserted data
-- Oracle doesn't need this as we're using triggers with sequences

SET DEFINE ON


-- Create a concatenated text column for searching
ALTER TABLE BOOKS ADD (SEARCH_TEXT VARCHAR2(1000));

-- Update the search text column with concatenated data
UPDATE BOOKS SET SEARCH_TEXT = 
    LOWER(NAME) || ' ' || LOWER(AUTHOR) || '  ' ||LOWER(ISBN);


-- Create the Oracle Text index
CREATE INDEX BOOKS_TEXT_IDX ON BOOKS(SEARCH_TEXT)
INDEXTYPE IS CTXSYS.CONTEXT
PARAMETERS ('SYNC (ON COMMIT)');