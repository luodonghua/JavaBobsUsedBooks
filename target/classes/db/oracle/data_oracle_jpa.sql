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
Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Li Juan',1,2,'/images/coverimages/apocalypse.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'),'A chilling science fiction tale that imagines the world on the brink of collapse during the tumultuous year 2020. Li Juan weaves a haunting narrative of survival, resilience, and unexpected hope as society faces unprecedented global challenges that test the limits of humanity.',6, null,0,1,1, null, null,'6556784356', null, null,'2020: The Apocalypse', null,10.95, null,1,25, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Nikki Wolf',1,3,'/images/coverimages/childrenofiron.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'),'Set in a post-industrial wasteland, this literary masterpiece follows three siblings born into a society where humans are classified by their utility. As they discover their true heritage, they must navigate a world where compassion is scarce and rebellion means death. Wolf's prose is both lyrical and devastating.',4, null,1,0,0, null, null,'7665438976', null, null,'Children Of Iron', null,13.95, null,2,3, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Richard Roe',1,1,'/images/coverimages/goldinthedark.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'),'A mesmerizing fantasy adventure where a young prospector discovers a mysterious substance that glows only in complete darkness. As she unravels its origins, she becomes entangled in an ancient conflict between shadow dwellers and light bearers that could determine the fate of both their realms.',6, null,1,1,0, null, null,'5442280765', null, null,'Gold In The Dark', null,6.5, null,3,10, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Pat Candella',2,3,'/images/coverimages/leaguesofsmoke.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'),'An atmospheric novel set in a fog-shrouded coastal town where reality blurs with myth. When strange ships appear on the horizon, bringing with them an impenetrable mist, the townspeople must confront their darkest secrets and the ancient legends they've long dismissed as mere superstition.',4, null,0,0,0, null, null,'4556789542', null, null,'Leagues Of Smoke', null,3, null,4,1, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Carlos Salazar',2,1,'/images/coverimages/alonewiththestars.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'),'A gripping psychological thriller about an astronaut stranded on the International Space Station after a catastrophic event on Earth cuts off all communication. As isolation takes its toll, she begins receiving mysterious radio transmissions that challenge her perception of reality and her will to survive.',5, null,1,0,1, null, null,'4563358087', null, null,'Alone With The Stars', null,15.95, null,5,5, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Terri Whitlock',1,2,'/images/coverimages/girlinthepolaroid.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'),'When a vintage Polaroid camera is discovered in an estate sale, the new owner becomes obsessed with a mysterious girl who appears in every photo taken, despite not being present when the pictures were snapped. This haunting mystery blends supernatural elements with a cold-case investigation that spans decades.',5, null,0,1,1, null, null,'2354435678', null, null,'The Girl In The Polaroid', null,8.25, null,6,2, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Mary Major',2,1,'/images/coverimages/1001jokes.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'),'A hilarious collection of jokes, puns, and witty one-liners for all occasions, curated by renowned comedian Mary Major. From clever wordplay to laugh-out-loud situational humor, this book promises to brighten any day and is perfect for breaking the ice or entertaining friends and family.',6, null,0,0,1, null, null,'6554789632', null, null,'1001 Jokes', null,13.95, null,7,7, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Mateo Jackson',3,3,'/images/coverimages/mysearchformeaning.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'),'In this profound memoir, Mateo Jackson recounts his journey from tragedy to purpose after losing everything in a natural disaster. Through encounters with diverse philosophies and spiritual traditions, he crafts a deeply personal framework for finding significance in life's most challenging moments. A moving testament to human resilience.',1, null,1,1,0, null, null,'4558786554', null, null,'My Search For Meaning', null,5, null,8,15, null, null,to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);


I've removed the ID column from all the INSERT statements, as it will be automatically generated by the identity column
in your database.

> the column description shall be summary column, change accordingly

I understand now. Let me update the statements to move the descriptions to the summary column instead of the description
column:

sql
Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Li Juan',1,2,'/images/coverimages/apocalypse.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,6, null,0,1,1, null, null,'6556784356', null, null,'2020: The Apocalypse', null,10.95, null,1,25, null, 'A chilling science fiction tale that imagines the world on the brink of collapse during the tumultuous year 2020. Li Juan weaves a haunting narrative of survival, resilience, and unexpected hope as society faces unprecedented global challenges that test the limits of humanity.',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Nikki Wolf',1,3,'/images/coverimages/childrenofiron.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,4, null,1,0,0, null, null,'7665438976', null, null,'Children Of Iron', null,13.95, null,2,3, null, 'Set in a post-industrial wasteland, this literary masterpiece follows three siblings born into a society where humans are classified by their utility. As they discover their true heritage, they must navigate a world where compassion is scarce and rebellion means death. Wolf''s prose is both lyrical and devastating.',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Richard Roe',1,1,'/images/coverimages/goldinthedark.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,6, null,1,1,0, null, null,'5442280765', null, null,'Gold In The Dark', null,6.5, null,3,10, null, 'A mesmerizing fantasy adventure where a young prospector discovers a mysterious substance that glows only in complete darkness. As she unravels its origins, she becomes entangled in an ancient conflict between shadow dwellers and light bearers that could determine the fate of both their realms.',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Pat Candella',2,3,'/images/coverimages/leaguesofsmoke.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,4, null,0,0,0, null, null,'4556789542', null, null,'Leagues Of Smoke', null,3, null,4,1, null, 'An atmospheric novel set in a fog-shrouded coastal town where reality blurs with myth. When strange ships appear on the horizon, bringing with them an impenetrable mist, the townspeople must confront their darkest secrets and the ancient legends they''ve long dismissed as mere superstition.',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Carlos Salazar',2,1,'/images/coverimages/alonewiththestars.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,5, null,1,0,1, null, null,'4563358087', null, null,'Alone With The Stars', null,15.95, null,5,5, null, 'A gripping psychological thriller about an astronaut stranded on the International Space Station after a catastrophic event on Earth cuts off all communication. As isolation takes its toll, she begins receiving mysterious radio transmissions that challenge her perception of reality and her will to survive.',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Terri Whitlock',1,2,'/images/coverimages/girlinthepolaroid.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,5, null,0,1,1, null, null,'2354435678', null, null,'The Girl In The Polaroid', null,8.25, null,6,2, null, 'When a vintage Polaroid camera is discovered in an estate sale, the new owner becomes obsessed with a mysterious girl who appears in every photo taken, despite not being present when the pictures were snapped. This haunting mystery blends supernatural elements with a cold-case investigation that spans decades.',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Mary Major',2,1,'/images/coverimages/1001jokes.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,6, null,0,0,1, null, null,'6554789632', null, null,'1001 Jokes', null,13.95, null,7,7, null, 'A hilarious collection of jokes, puns, and witty one-liners for all occasions, curated by renowned comedian Mary Major. From clever wordplay to laugh-out-loud situational humor, this book promises to brighten any day and is perfect for breaking the ice or entertaining friends and family.',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);

Insert into books (author,book_type_id,condition_id,cover_image_url,created_by,created_on,description,genre_id,is_available,is_bestseller,is_featured,is_new_arrival,is_user_listing,is_user_offer,isbn,last_restock_date,listing_status,name,offer_source_id,price,publish_date,publisher_id,quantity,seller_id,summary,updated_on,year)
values ('Mateo Jackson',3,3,'/images/coverimages/mysearchformeaning.png','system',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null,1, null,1,1,0, null, null,'4558786554', null, null,'My Search For Meaning', null,5, null,8,15, null, 'In this profound memoir, Mateo Jackson recounts his journey from tragedy to purpose after losing everything in a natural disaster. Through encounters with diverse philosophies and spiritual traditions, he crafts a deeply personal framework for finding significance in life''s most challenging moments. A moving testament to human resilience.',to_timestamp('24/03/25 11:32:29.372642000','DD/MM/RR HH24:MI:SSXFF'), null);


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

commit;


SET DEFINE OFF

-- Additional Data
-- Additional 10 genres for Bob's Used Books
INSERT INTO genres (name, description) 
VALUES ('Business & Economics', 'Books about business practices, economics, and finance');

INSERT INTO genres (name, description)
VALUES ('Self-Help', 'Books focused on personal development and improvement');

INSERT INTO genres (name, description)
VALUES ('Cookbooks & Food', 'Recipe collections and books about food and cooking');

INSERT INTO genres (name, description)
VALUES ('Art & Photography', 'Books about visual arts, design, and photography');

INSERT INTO genres (name, description)
VALUES ('Religion & Spirituality', 'Books exploring religious and spiritual topics');

INSERT INTO genres (name, description)
VALUES ('Science & Technology', 'Books about scientific discoveries and technological advancements');

INSERT INTO genres (name, description)
VALUES ('Health & Wellness', 'Books about physical and mental health');

INSERT INTO genres (name, description)
VALUES ('Poetry', 'Collections of poems and works about poetry');

INSERT INTO genres (name, description)
VALUES ('Comics & Graphic Novels', 'Illustrated stories and sequential art');

INSERT INTO genres (name, description)
VALUES ('Education & Reference', 'Textbooks, encyclopedias, and educational materials');

COMMIT;



