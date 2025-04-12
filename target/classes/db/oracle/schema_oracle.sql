  CREATE TABLE BOOK_TYPES 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	CREATED_ON TIMESTAMP (6), 
	DESCRIPTION VARCHAR2(255 CHAR), 
	NAME VARCHAR2(255 CHAR) NOT NULL ENABLE, 
	UPDATED_ON TIMESTAMP (6), 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE
   ) ;

  CREATE TABLE CONDITIONS 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	CREATED_ON TIMESTAMP (6), 
	DESCRIPTION VARCHAR2(255 CHAR), 
	NAME VARCHAR2(255 CHAR) NOT NULL ENABLE, 
	UPDATED_ON TIMESTAMP (6), 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE
   ) ;


  CREATE TABLE GENRES 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	CREATED_ON TIMESTAMP (6), 
	DESCRIPTION VARCHAR2(255 CHAR), 
	NAME VARCHAR2(255 CHAR) NOT NULL ENABLE, 
	UPDATED_ON TIMESTAMP (6), 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE
   ) ;

  CREATE TABLE PUBLISHERS 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	CREATED_ON TIMESTAMP (6), 
	DESCRIPTION VARCHAR2(255 CHAR), 
	NAME VARCHAR2(255 CHAR) NOT NULL ENABLE, 
	UPDATED_ON TIMESTAMP (6), 
	WEBSITE VARCHAR2(255 CHAR), 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE
   ) ;



  CREATE TABLE BOOKS 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	AUTHOR VARCHAR2(255 CHAR), 
	BOOK_TYPE_ID NUMBER(19,0), 
	CONDITION_ID NUMBER(19,0), 
	COVER_IMAGE_URL VARCHAR2(255 CHAR), 
	CREATED_BY VARCHAR2(255 CHAR), 
	CREATED_ON TIMESTAMP (6), 
	DESCRIPTION VARCHAR2(255 CHAR), 
	GENRE_ID NUMBER(19,0), 
	IS_AVAILABLE NUMBER(1,0), 
	IS_BESTSELLER NUMBER(1,0), 
	IS_FEATURED NUMBER(1,0), 
	IS_NEW_ARRIVAL NUMBER(1,0), 
	IS_USER_LISTING NUMBER(1,0), 
	IS_USER_OFFER NUMBER(1,0), 
	ISBN VARCHAR2(255 CHAR), 
	LAST_RESTOCK_DATE DATE, 
	LISTING_STATUS NUMBER(10,0), 
	NAME VARCHAR2(255 CHAR), 
	OFFER_SOURCE_ID NUMBER(19,0), 
	PRICE NUMBER(38,2), 
	PUBLISH_DATE DATE, 
	PUBLISHER_ID NUMBER(19,0), 
	QUANTITY NUMBER(10,0), 
	SELLER_ID NUMBER(19,0), 
	SUMMARY VARCHAR2(1000 CHAR), 
	UPDATED_ON TIMESTAMP (6), 
	YEAR NUMBER(10,0), 
	SEARCH_TEXT VARCHAR2(1000), 
	 CHECK (is_available in (0,1)) ENABLE, 
	 CHECK (is_bestseller in (0,1)) ENABLE, 
	 CHECK (is_featured in (0,1)) ENABLE, 
	 CHECK (is_new_arrival in (0,1)) ENABLE, 
	 CHECK (is_user_listing in (0,1)) ENABLE, 
	 CHECK (is_user_offer in (0,1)) ENABLE, 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE, 
	 CONSTRAINT FK_BOOKS_BOOK_TYPE FOREIGN KEY (BOOK_TYPE_ID)
	  REFERENCES BOOK_TYPES (ID) ENABLE, 
	 CONSTRAINT FK_BOOKS_CONDITION FOREIGN KEY (CONDITION_ID)
	  REFERENCES CONDITIONS (ID) ENABLE, 
	 CONSTRAINT FK_BOOKS_GENRE FOREIGN KEY (GENRE_ID)
	  REFERENCES GENRES (ID) ENABLE, 
	 CONSTRAINT FK_BOOKS_PUBLISHER FOREIGN KEY (PUBLISHER_ID)
	  REFERENCES PUBLISHERS (ID) ENABLE
   ) ;

  CREATE TABLE AUTHORS 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	CREATED_BY VARCHAR2(255 CHAR), 
	CREATED_ON TIMESTAMP (6), 
	UPDATED_ON TIMESTAMP (6), 
	BIOGRAPHY VARCHAR2(255 CHAR), 
	BIRTH_DATE DATE, 
	IMAGE_URL VARCHAR2(255 CHAR), 
	NAME VARCHAR2(255 CHAR), 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE
   ) ;


  CREATE TABLE CUSTOMERS 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	ACCOUNT_EXPIRED NUMBER(1,0), 
	ACCOUNT_LOCKED NUMBER(1,0), 
	CREATED_ON TIMESTAMP (6), 
	CREDENTIALS_EXPIRED NUMBER(1,0), 
	DATE_OF_BIRTH DATE, 
	EMAIL VARCHAR2(255 CHAR), 
	EMAIL_VERIFIED NUMBER(1,0), 
	FIRST_NAME VARCHAR2(255 CHAR), 
	LAST_LOGIN TIMESTAMP (6), 
	LAST_NAME VARCHAR2(255 CHAR), 
	PASSWORD_HASH VARCHAR2(255 CHAR), 
	PHONE_NUMBER VARCHAR2(255 CHAR), 
	ROLE VARCHAR2(255 CHAR), 
	SUB VARCHAR2(255 CHAR), 
	USERNAME VARCHAR2(255 CHAR), 
	 CHECK (account_expired in (0,1)) ENABLE, 
	 CHECK (account_locked in (0,1)) ENABLE, 
	 CHECK (credentials_expired in (0,1)) ENABLE, 
	 CHECK (email_verified in (0,1)) ENABLE, 
	 PRIMARY KEY (ID) USING INDEX  ENABLE
) ;

CREATE TABLE ADDRESSES 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	ADDRESS_LINE1 VARCHAR2(255 CHAR), 
	ADDRESS_LINE2 VARCHAR2(255 CHAR), 
	ADDRESS_TYPE VARCHAR2(255 CHAR), 
	CITY VARCHAR2(255 CHAR), 
	COUNTRY VARCHAR2(255 CHAR), 
	CUSTOMER_ID NUMBER(19,0), 
	IS_DEFAULT NUMBER(1,0), 
	POSTAL_CODE VARCHAR2(255 CHAR), 
	STATE VARCHAR2(255 CHAR), 
	 CHECK (is_default in (0,1)) ENABLE, 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE, 
	 CONSTRAINT FK_ADDRESSES_CUSTOMER FOREIGN KEY (CUSTOMER_ID)
	  REFERENCES CUSTOMERS (ID) ENABLE
   ) ;


  CREATE TABLE OFFERS 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	ADMIN_NOTES VARCHAR2(1000 CHAR), 
	OFFERED_PRICE NUMBER(38,2), 
	BOOK_AUTHOR VARCHAR2(255 CHAR), 
	BOOK_COMMENT VARCHAR2(1000 CHAR), 
	BOOK_ID NUMBER(19,0), 
	BOOK_PRICE NUMBER(38,2), 
	BOOK_TITLE VARCHAR2(255 CHAR) NOT NULL ENABLE, 
	BOOK_TYPE_ID NUMBER(19,0), 
	CONDITION_ID NUMBER(19,0), 
	CONTACT_EMAIL VARCHAR2(255 CHAR), 
	CONTACT_NAME VARCHAR2(255 CHAR), 
	CONTACT_PHONE VARCHAR2(255 CHAR), 
	CREATED_ON TIMESTAMP (6), 
	CUSTOMER_ID NUMBER(19,0), 
	DESCRIPTION VARCHAR2(1000 CHAR), 
	FRONT_URL VARCHAR2(255 CHAR), 
	GENRE_ID NUMBER(19,0), 
	IS_APPROVED NUMBER(1,0), 
	IS_REJECTED NUMBER(1,0), 
	ISBN VARCHAR2(255 CHAR), 
	OFFER_STATUS NUMBER(10,0), 
	PROCESSED_AT TIMESTAMP (6), 
	PROCESSED_BY NUMBER(19,0), 
	PUBLISHER_ID NUMBER(19,0), 
	REJECTION_REASON VARCHAR2(1000 CHAR), 
	STATUS NUMBER(10,0) NOT NULL ENABLE, 
	SUMMARY VARCHAR2(1000 CHAR), 
	UPDATED_ON TIMESTAMP (6), 
	 CHECK (is_approved in (0,1)) ENABLE, 
	 CHECK (is_rejected in (0,1)) ENABLE, 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE, 
	 CONSTRAINT FK_OFFERS_BOOK FOREIGN KEY (BOOK_ID)
	  REFERENCES BOOKS (ID) ENABLE, 
	 CONSTRAINT FK_OFFERS_CONDITION FOREIGN KEY (CONDITION_ID)
	  REFERENCES CONDITIONS (ID) ENABLE, 
	 CONSTRAINT FK_OFFERS_CUSTOMER FOREIGN KEY (CUSTOMER_ID)
	  REFERENCES CUSTOMERS (ID) ENABLE
   ) ;


  CREATE TABLE ORDERS 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	ADDRESS_ID NUMBER(19,0), 
	CANCELLATION_REASON VARCHAR2(255 CHAR), 
	CANCELLED_DATE DATE, 
	CREATED_ON TIMESTAMP (6) NOT NULL ENABLE, 
	CUSTOMER_ID NUMBER(19,0), 
	DELIVERED_DATE DATE, 
	DELIVERY_DATE DATE, 
	NOTES VARCHAR2(255 CHAR), 
	ORDER_DATE DATE, 
	ORDER_STATUS NUMBER(10,0) NOT NULL ENABLE, 
	SHIPPED_DATE DATE, 
	SHIPPING_ADDRESS VARCHAR2(255 CHAR), 
	STATUS VARCHAR2(255 CHAR), 
	TOTAL_AMOUNT NUMBER(38,2), 
	TRACKING_NUMBER VARCHAR2(255 CHAR), 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE
   ) ;


  CREATE TABLE ORDER_ITEMS 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	BOOK_ID NUMBER(19,0), 
	BOOK_PRICE NUMBER(38,2) NOT NULL ENABLE, 
	DISCOUNT NUMBER(38,2), 
	ORDER_ID NUMBER(19,0), 
	PRICE NUMBER(38,2), 
	QUANTITY NUMBER(10,0), 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE, 
	 CONSTRAINT FK_ORDER_ITEMS_BOOK FOREIGN KEY (BOOK_ID)
	  REFERENCES BOOKS (ID) ENABLE, 
	 CONSTRAINT FK_ORDER_ITEMS_ORDER FOREIGN KEY (ORDER_ID)
	  REFERENCES ORDERS (ID) ENABLE
   ) ;


  CREATE TABLE PASSWORD_RESET_TOKENS 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	EXPIRY_DATE TIMESTAMP (6), 
	TOKEN VARCHAR2(255 CHAR), 
	USED NUMBER(1,0), 
	CUSTOMER_ID NUMBER(19,0), 
	 CHECK (used in (0,1)) ENABLE, 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE, 
	 CONSTRAINT FK_PASSWORD_RESET_CUSTOMER FOREIGN KEY (CUSTOMER_ID)
	  REFERENCES CUSTOMERS (ID) ENABLE
   ) ;

  CREATE TABLE PERSISTENT_LOGINS 
   (USERNAME VARCHAR2(64) NOT NULL ENABLE, 
	SERIES VARCHAR2(64), 
	TOKEN VARCHAR2(64) NOT NULL ENABLE, 
	LAST_USED TIMESTAMP (6) NOT NULL ENABLE, 
	 PRIMARY KEY (SERIES)
  USING INDEX  ENABLE
   ) ;



  CREATE TABLE REFERENCE_DATA 
   (ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	CREATED_BY VARCHAR2(255 CHAR), 
	CREATED_ON TIMESTAMP (6), 
	UPDATED_ON TIMESTAMP (6), 
	DESCRIPTION VARCHAR2(255 CHAR), 
	IS_ACTIVE NUMBER(1,0) NOT NULL ENABLE, 
	NAME VARCHAR2(255 CHAR), 
	TYPE VARCHAR2(255 CHAR), 
	 CHECK (is_active in (0,1)) ENABLE, 
	 CHECK (type in ('PUBLISHER','BOOK_TYPE','GENRE','CONDITION')) ENABLE, 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE
   ) ;



  CREATE TABLE SHOPPING_CART_ITEMS 
   (	ID NUMBER(19,0) GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  NOT NULL ENABLE, 
	BOOK_ID NUMBER(19,0), 
	CUSTOMER_ID NUMBER(19,0), 
	IS_WISHLIST_ITEM NUMBER(1,0), 
	QUANTITY NUMBER(10,0), 
	 CHECK (is_wishlist_item in (0,1)) ENABLE, 
	 PRIMARY KEY (ID)
  USING INDEX  ENABLE, 
	 CONSTRAINT FK_CART_ITEMS_BOOK FOREIGN KEY (BOOK_ID)
	  REFERENCES BOOKS (ID) ENABLE
   ) ;


-- Indexes for BOOKS table foreign keys
CREATE INDEX IDX_BOOKS_BOOK_TYPE_ID ON BOOKS(BOOK_TYPE_ID);
CREATE INDEX IDX_BOOKS_CONDITION_ID ON BOOKS(CONDITION_ID);
CREATE INDEX IDX_BOOKS_GENRE_ID ON BOOKS(GENRE_ID);
CREATE INDEX IDX_BOOKS_PUBLISHER_ID ON BOOKS(PUBLISHER_ID);

-- Index for ADDRESSES table foreign key
CREATE INDEX IDX_ADDRESSES_CUSTOMER_ID ON ADDRESSES(CUSTOMER_ID);

-- Indexes for OFFERS table foreign keys
CREATE INDEX IDX_OFFERS_BOOK_ID ON OFFERS(BOOK_ID);
CREATE INDEX IDX_OFFERS_CONDITION_ID ON OFFERS(CONDITION_ID);
CREATE INDEX IDX_OFFERS_CUSTOMER_ID ON OFFERS(CUSTOMER_ID);
CREATE INDEX IDX_OFFERS_PUBLISHER_ID ON OFFERS(PUBLISHER_ID);
CREATE INDEX IDX_OFFERS_BOOK_TYPE_ID ON OFFERS(BOOK_TYPE_ID);
CREATE INDEX IDX_OFFERS_GENRE_ID ON OFFERS(GENRE_ID);

-- Indexes for ORDER_ITEMS table foreign keys
CREATE INDEX IDX_ORDER_ITEMS_BOOK_ID ON ORDER_ITEMS(BOOK_ID);
CREATE INDEX IDX_ORDER_ITEMS_ORDER_ID ON ORDER_ITEMS(ORDER_ID);

-- Index for PASSWORD_RESET_TOKENS table foreign key
CREATE INDEX IDX_PASSWORD_RESET_CUSTOMER_ID ON PASSWORD_RESET_TOKENS(CUSTOMER_ID);

-- Indexes for SHOPPING_CART_ITEMS table foreign keys
CREATE INDEX IDX_CART_ITEMS_BOOK_ID ON SHOPPING_CART_ITEMS(BOOK_ID);
CREATE INDEX IDX_CART_ITEMS_CUSTOMER_ID ON SHOPPING_CART_ITEMS(CUSTOMER_ID);

-- Additional index for ORDERS table (not a foreign key but likely used in joins)
CREATE INDEX IDX_ORDERS_CUSTOMER_ID ON ORDERS(CUSTOMER_ID);


-- Prepare data for Full Text Search
CREATE OR REPLACE TRIGGER trg_book_search_text
BEFORE INSERT OR UPDATE OF NAME, AUTHOR, ISBN ON BOOKS
FOR EACH ROW
BEGIN
  :NEW.SEARCH_TEXT := LOWER(:NEW.NAME) || ' ' ||
                     LOWER(NVL(:NEW.AUTHOR, '')) || ' ' ||
                     LOWER(NVL(:NEW.ISBN, ''));
END;
/

-- Create the Oracle Text index
CREATE INDEX BOOKS_TEXT_IDX ON BOOKS(SEARCH_TEXT)
INDEXTYPE IS CTXSYS.CONTEXT
PARAMETERS ('SYNC (ON COMMIT)');



-- Package specification
CREATE OR REPLACE PACKAGE bookpkg AS
    -- Procedure to update order status from PROCESSING to DELIVERED
    PROCEDURE update_order_status;
    
    -- Procedure to schedule the job to run every 5 minutes
    PROCEDURE schedule_status_update_job;
END bookpkg;
/

-- Package body
CREATE OR REPLACE PACKAGE BODY bookpkg AS
    -- Procedure to update order status from PROCESSING to DELIVERED
    PROCEDURE update_order_status IS
    BEGIN
        -- Update orders with status 'PROCESSING' to 'DELIVERED'
        UPDATE orders
        SET status = 'DELIVERED'
        WHERE status = 'PROCESSING';
        
        -- Commit the transaction
        COMMIT;
        
        -- Log the update (optional)
        DBMS_OUTPUT.PUT_LINE('Order status update completed at ' || TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
        
    EXCEPTION
        WHEN OTHERS THEN
            -- Log error and rollback
            DBMS_OUTPUT.PUT_LINE('Error updating order status: ' || SQLERRM);
            ROLLBACK;
    END update_order_status;
    
    -- Procedure to schedule the job to run every 5 minutes
    PROCEDURE schedule_status_update_job IS
        v_job_exists NUMBER;
    BEGIN
        -- Check if job already exists
        SELECT COUNT(*) INTO v_job_exists
        FROM user_scheduler_jobs
        WHERE job_name = 'ORDER_STATUS_UPDATE_JOB';
        
        -- If job exists, drop it
        IF v_job_exists > 0 THEN
            DBMS_SCHEDULER.DROP_JOB(
                job_name => 'ORDER_STATUS_UPDATE_JOB',
                force => TRUE
            );
        END IF;
        
        -- Create a new job that runs every 5 minutes
        DBMS_SCHEDULER.CREATE_JOB(
            job_name => 'ORDER_STATUS_UPDATE_JOB',
            job_type => 'STORED_PROCEDURE',
            job_action => 'bookpkg.update_order_status',
            start_date => SYSTIMESTAMP,
            repeat_interval => 'FREQ=MINUTELY;INTERVAL=5', -- Run every 5 minutes
            enabled => TRUE,
            comments => 'Job to update order status from PROCESSING to DELIVERED every 5 minutes'
        );
        
        DBMS_OUTPUT.PUT_LINE('Job ORDER_STATUS_UPDATE_JOB scheduled successfully');
        
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error scheduling job: ' || SQLERRM);
    END schedule_status_update_job;
    
END bookpkg;
/

-- Grant necessary privileges (run as DBA)
-- GRANT CREATE JOB TO your_schema_name;
-- GRANT EXECUTE ON DBMS_SCHEDULER TO your_schema_name;

-- Example of how to call the procedure to schedule the job
-- EXEC bookpkg.schedule_status_update_job;






-- Extra schema objects to demostrate Oracle to other database migration
-- 1. Simple function to get book count by genre
CREATE OR REPLACE FUNCTION get_book_count_by_genre(p_genre_id NUMBER)
RETURN NUMBER
IS
  v_count NUMBER;
BEGIN
  SELECT COUNT(*) INTO v_count
  FROM BOOKS
  WHERE GENRE_ID = p_genre_id;

  RETURN v_count;
END;
/

-- 2. Simple procedure to update book price
CREATE OR REPLACE PROCEDURE update_book_price(
  p_book_id IN NUMBER,
  p_new_price IN NUMBER
)
IS
BEGIN
  UPDATE BOOKS
  SET PRICE = p_new_price,
      UPDATED_ON = SYSTIMESTAMP
  WHERE ID = p_book_id;

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    RAISE;
END;
/

-- 3. Simple materialized view for bestsellers
CREATE MATERIALIZED VIEW MV_BESTSELLERS
REFRESH ON DEMAND
AS
SELECT B.ID, B.NAME, B.AUTHOR, B.PRICE, B.ISBN
FROM BOOKS B
WHERE B.IS_BESTSELLER = 1
ORDER BY B.NAME;

-- 4. Medium complexity function to calculate order total with discount
CREATE OR REPLACE FUNCTION calculate_order_total(p_order_id NUMBER)
RETURN NUMBER
IS
  v_total NUMBER := 0;
BEGIN
  SELECT SUM(OI.BOOK_PRICE * OI.QUANTITY - NVL(OI.DISCOUNT, 0))
  INTO v_total
  FROM ORDER_ITEMS OI
  WHERE OI.ORDER_ID = p_order_id;

  RETURN NVL(v_total, 0);
END;
/

-- 5. Medium complexity procedure to process book offer
CREATE OR REPLACE PROCEDURE process_book_offer(
  p_offer_id IN NUMBER,
  p_approved IN BOOLEAN,
  p_admin_notes IN VARCHAR2,
  p_admin_id IN NUMBER
)
IS
  v_book_id NUMBER;
BEGIN
  IF p_approved THEN
    -- Get offer details
    SELECT BOOK_ID INTO v_book_id
    FROM OFFERS
    WHERE ID = p_offer_id;

    -- Update offer status
    UPDATE OFFERS
    SET IS_APPROVED = 1,
        IS_REJECTED = 0,
        ADMIN_NOTES = p_admin_notes,
        PROCESSED_AT = SYSTIMESTAMP,
        PROCESSED_BY = p_admin_id,
        OFFER_STATUS = 2, -- Approved status
        UPDATED_ON = SYSTIMESTAMP
    WHERE ID = p_offer_id;

    -- If no book exists yet, we'd create one here
    IF v_book_id IS NULL THEN
      -- Logic to create book from offer would go here
      NULL;
    END IF;
  ELSE
    -- Reject the offer
    UPDATE OFFERS
    SET IS_APPROVED = 0,
        IS_REJECTED = 1,
        ADMIN_NOTES = p_admin_notes,
        PROCESSED_AT = SYSTIMESTAMP,
        PROCESSED_BY = p_admin_id,
        OFFER_STATUS = 3, -- Rejected status
        UPDATED_ON = SYSTIMESTAMP
    WHERE ID = p_offer_id;
  END IF;

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    RAISE;
END;
/

-- 6. Medium complexity materialized view for customer order summary
CREATE MATERIALIZED VIEW MV_CUSTOMER_ORDER_SUMMARY
REFRESH ON DEMAND
AS
SELECT
  C.ID AS CUSTOMER_ID,
  C.FIRST_NAME,
  C.LAST_NAME,
  C.EMAIL,
  COUNT(O.ID) AS ORDER_COUNT,
  SUM(O.TOTAL_AMOUNT) AS TOTAL_SPENT,
  MAX(O.CREATED_ON) AS LAST_ORDER_DATE
FROM CUSTOMERS C
LEFT JOIN ORDERS O ON C.ID = O.CUSTOMER_ID
GROUP BY C.ID, C.FIRST_NAME, C.LAST_NAME, C.EMAIL;

-- 7. Complex function to calculate customer loyalty score
CREATE OR REPLACE FUNCTION calculate_customer_loyalty_score(p_customer_id NUMBER)
RETURN NUMBER
IS
  v_order_count NUMBER;
  v_total_spent NUMBER;
  v_days_since_first_order NUMBER;
  v_days_since_last_order NUMBER;
  v_loyalty_score NUMBER;
BEGIN
  -- Get order statistics
  SELECT
    COUNT(*),
    NVL(SUM(TOTAL_AMOUNT), 0),
    NVL(TRUNC(SYSDATE - MIN(CREATED_ON)), 0),
    NVL(TRUNC(SYSDATE - MAX(CREATED_ON)), 999)
  INTO
    v_order_count,
    v_total_spent,
    v_days_since_first_order,
    v_days_since_last_order
  FROM ORDERS
  WHERE CUSTOMER_ID = p_customer_id;

  -- Calculate loyalty score based on multiple factors
  -- More orders, more spent, longer customer history, and recent activity all increase score
  v_loyalty_score := (v_order_count * 10) +
                    (v_total_spent / 100) +
                    (LEAST(v_days_since_first_order, 1000) / 10) -
                    (LEAST(v_days_since_last_order, 365) / 10);

  RETURN GREATEST(0, ROUND(v_loyalty_score, 2));
END;
/

-- 8. Complex procedure to restock inventory and update book status
CREATE OR REPLACE PROCEDURE restock_inventory(
  p_book_id IN NUMBER,
  p_quantity IN NUMBER,
  p_update_price IN BOOLEAN DEFAULT FALSE,
  p_new_price IN NUMBER DEFAULT NULL
)
IS
  v_current_quantity NUMBER;
  v_current_price NUMBER;
  v_is_available NUMBER;
BEGIN
  -- Get current book info
  SELECT QUANTITY, PRICE, IS_AVAILABLE
  INTO v_current_quantity, v_current_price, v_is_available
  FROM BOOKS
  WHERE ID = p_book_id;

  -- Update inventory
  UPDATE BOOKS
  SET QUANTITY = v_current_quantity + p_quantity,
      IS_AVAILABLE = CASE
                       WHEN v_current_quantity + p_quantity > 0 THEN 1
                       ELSE 0
                     END,
      PRICE = CASE
                WHEN p_update_price AND p_new_price IS NOT NULL THEN p_new_price
                ELSE v_current_price
              END,
      LAST_RESTOCK_DATE = SYSDATE,
      UPDATED_ON = SYSTIMESTAMP
  WHERE ID = p_book_id;

  -- If book was out of stock but now available, log this change
  IF v_is_available = 0 AND p_quantity > 0 THEN
    -- Here you might insert into a log table or send notifications
    NULL;
  END IF;

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    RAISE;
END;
/

-- 9. Complex materialized view for book sales analytics
CREATE MATERIALIZED VIEW MV_BOOK_SALES_ANALYTICS
REFRESH COMPLETE ON DEMAND
START WITH SYSDATE
NEXT SYSDATE + 1
AS
SELECT
  B.ID AS BOOK_ID,
  B.NAME AS BOOK_NAME,
  B.AUTHOR,
  G.NAME AS GENRE_NAME,
  P.NAME AS PUBLISHER_NAME,
  COUNT(OI.ID) AS TIMES_ORDERED,
  SUM(OI.QUANTITY) AS TOTAL_QUANTITY_SOLD,
  SUM(OI.BOOK_PRICE * OI.QUANTITY) AS GROSS_REVENUE,
  SUM(OI.DISCOUNT) AS TOTAL_DISCOUNTS,
  SUM(OI.BOOK_PRICE * OI.QUANTITY - NVL(OI.DISCOUNT, 0)) AS NET_REVENUE,
  ROUND(AVG(OI.BOOK_PRICE), 2) AS AVG_SELLING_PRICE,
  MIN(O.CREATED_ON) AS FIRST_SALE_DATE,
  MAX(O.CREATED_ON) AS LAST_SALE_DATE
FROM BOOKS B
JOIN ORDER_ITEMS OI ON B.ID = OI.BOOK_ID
JOIN ORDERS O ON OI.ORDER_ID = O.ID
LEFT JOIN GENRES G ON B.GENRE_ID = G.ID
LEFT JOIN PUBLISHERS P ON B.PUBLISHER_ID = P.ID
GROUP BY B.ID, B.NAME, B.AUTHOR, G.NAME, P.NAME;

-- 10. Simple package for customer management
CREATE OR REPLACE PACKAGE customer_mgmt AS
  -- Get customer by ID
  FUNCTION get_customer(p_customer_id NUMBER) RETURN CUSTOMERS%ROWTYPE;

  -- Create new customer
  PROCEDURE create_customer(
    p_first_name IN VARCHAR2,
    p_last_name IN VARCHAR2,
    p_email IN VARCHAR2,
    p_phone_number IN VARCHAR2,
    p_username IN VARCHAR2,
    p_password_hash IN VARCHAR2,
    p_customer_id OUT NUMBER
  );

  -- Update customer email
  PROCEDURE update_email(
    p_customer_id IN NUMBER,
    p_new_email IN VARCHAR2
  );
END customer_mgmt;
/

CREATE OR REPLACE PACKAGE BODY customer_mgmt AS
  -- Get customer by ID
  FUNCTION get_customer(p_customer_id NUMBER) RETURN CUSTOMERS%ROWTYPE IS
    v_customer CUSTOMERS%ROWTYPE;
  BEGIN
    SELECT * INTO v_customer
    FROM CUSTOMERS
    WHERE ID = p_customer_id;

    RETURN v_customer;
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      RETURN NULL;
  END get_customer;

  -- Create new customer
  PROCEDURE create_customer(
    p_first_name IN VARCHAR2,
    p_last_name IN VARCHAR2,
    p_email IN VARCHAR2,
    p_phone_number IN VARCHAR2,
    p_username IN VARCHAR2,
    p_password_hash IN VARCHAR2,
    p_customer_id OUT NUMBER
  ) IS
  BEGIN
    INSERT INTO CUSTOMERS (
      FIRST_NAME,
      LAST_NAME,
      EMAIL,
      PHONE_NUMBER,
      USERNAME,
      PASSWORD_HASH,
      CREATED_ON,
      ACCOUNT_EXPIRED,
      ACCOUNT_LOCKED,
      CREDENTIALS_EXPIRED,
      EMAIL_VERIFIED,
      ROLE
    ) VALUES (
      p_first_name,
      p_last_name,
      p_email,
      p_phone_number,
      p_username,
      p_password_hash,
      SYSTIMESTAMP,
      0, -- not expired
      0, -- not locked
      0, -- credentials not expired
      0, -- email not verified yet
      'USER' -- default role
    )
    RETURNING ID INTO p_customer_id;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END create_customer;

  -- Update customer email
  PROCEDURE update_email(
    p_customer_id IN NUMBER,
    p_new_email IN VARCHAR2
  ) IS
  BEGIN
    UPDATE CUSTOMERS
    SET EMAIL = p_new_email,
        EMAIL_VERIFIED = 0 -- Reset verification status
    WHERE ID = p_customer_id;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END update_email;
END customer_mgmt;
/

-- 11. Medium complexity package for order processing
CREATE OR REPLACE PACKAGE order_processing AS
  -- Create a new order
  PROCEDURE create_order(
    p_customer_id IN NUMBER,
    p_address_id IN NUMBER,
    p_notes IN VARCHAR2,
    p_order_id OUT NUMBER
  );

  -- Add item to order
  PROCEDURE add_order_item(
    p_order_id IN NUMBER,
    p_book_id IN NUMBER,
    p_quantity IN NUMBER,
    p_discount IN NUMBER DEFAULT NULL
  );

  -- Update order status
  PROCEDURE update_order_status(
    p_order_id IN NUMBER,
    p_status IN NUMBER
  );

  -- Calculate order total
  FUNCTION get_order_total(p_order_id NUMBER) RETURN NUMBER;

  -- Cancel order
  PROCEDURE cancel_order(
    p_order_id IN NUMBER,
    p_reason IN VARCHAR2
  );
END order_processing;
/

CREATE OR REPLACE PACKAGE BODY order_processing AS
  -- Create a new order
  PROCEDURE create_order(
    p_customer_id IN NUMBER,
    p_address_id IN NUMBER,
    p_notes IN VARCHAR2,
    p_order_id OUT NUMBER
  ) IS
  BEGIN
    INSERT INTO ORDERS (
      CUSTOMER_ID,
      ADDRESS_ID,
      NOTES,
      ORDER_DATE,
      ORDER_STATUS,
      CREATED_ON
    ) VALUES (
      p_customer_id,
      p_address_id,
      p_notes,
      SYSDATE,
      1, -- New order status
      SYSTIMESTAMP
    )
    RETURNING ID INTO p_order_id;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END create_order;

  -- Add item to order
  PROCEDURE add_order_item(
    p_order_id IN NUMBER,
    p_book_id IN NUMBER,
    p_quantity IN NUMBER,
    p_discount IN NUMBER DEFAULT NULL
  ) IS
    v_book_price NUMBER;
  BEGIN
    -- Get current book price
    SELECT PRICE INTO v_book_price
    FROM BOOKS
    WHERE ID = p_book_id;

    -- Add item to order
    INSERT INTO ORDER_ITEMS (
      ORDER_ID,
      BOOK_ID,
      QUANTITY,
      BOOK_PRICE,
      DISCOUNT
    ) VALUES (
      p_order_id,
      p_book_id,
      p_quantity,
      v_book_price,
      p_discount
    );

    -- Update book inventory
    UPDATE BOOKS
    SET QUANTITY = QUANTITY - p_quantity,
        IS_AVAILABLE = CASE
                         WHEN QUANTITY - p_quantity > 0 THEN 1
                         ELSE 0
                       END,
        UPDATED_ON = SYSTIMESTAMP
    WHERE ID = p_book_id;

    -- Update order total
    UPDATE ORDERS
    SET TOTAL_AMOUNT = get_order_total(p_order_id)
    WHERE ID = p_order_id;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END add_order_item;

  -- Update order status
  PROCEDURE update_order_status(
    p_order_id IN NUMBER,
    p_status IN NUMBER
  ) IS
  BEGIN
    UPDATE ORDERS
    SET ORDER_STATUS = p_status,
        SHIPPED_DATE = CASE WHEN p_status = 2 THEN SYSDATE ELSE SHIPPED_DATE END,
        DELIVERED_DATE = CASE WHEN p_status = 3 THEN SYSDATE ELSE DELIVERED_DATE END
    WHERE ID = p_order_id;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END update_order_status;

  -- Calculate order total
  FUNCTION get_order_total(p_order_id NUMBER) RETURN NUMBER IS
    v_total NUMBER;
  BEGIN
    SELECT SUM(BOOK_PRICE * QUANTITY - NVL(DISCOUNT, 0))
    INTO v_total
    FROM ORDER_ITEMS
    WHERE ORDER_ID = p_order_id;

    RETURN NVL(v_total, 0);
  END get_order_total;

  -- Cancel order
  PROCEDURE cancel_order(
    p_order_id IN NUMBER,
    p_reason IN VARCHAR2
  ) IS
    CURSOR c_order_items IS
      SELECT BOOK_ID, QUANTITY
      FROM ORDER_ITEMS
      WHERE ORDER_ID = p_order_id;
  BEGIN
    -- Update order status
    UPDATE ORDERS
    SET ORDER_STATUS = 4, -- Cancelled status
        CANCELLATION_REASON = p_reason,
        CANCELLED_DATE = SYSDATE
    WHERE ID = p_order_id;

    -- Return items to inventory
    FOR item IN c_order_items LOOP
      UPDATE BOOKS
      SET QUANTITY = QUANTITY + item.QUANTITY,
          IS_AVAILABLE = 1,
          UPDATED_ON = SYSTIMESTAMP
      WHERE ID = item.BOOK_ID;
    END LOOP;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END cancel_order;
END order_processing;
/

-- 12. Complex package for book inventory management
CREATE OR REPLACE PACKAGE book_inventory AS
  -- Add new book
  PROCEDURE add_book(
    p_name IN VARCHAR2,
    p_author IN VARCHAR2,
    p_isbn IN VARCHAR2,
    p_description IN VARCHAR2,
    p_price IN NUMBER,
    p_quantity IN NUMBER,
    p_publisher_id IN NUMBER,
    p_genre_id IN NUMBER,
    p_book_type_id IN NUMBER,
    p_condition_id IN NUMBER,
    p_publish_date IN DATE DEFAULT NULL,
    p_year IN NUMBER DEFAULT NULL,
    p_book_id OUT NUMBER
  );

  -- Update book details
  PROCEDURE update_book(
    p_book_id IN NUMBER,
    p_name IN VARCHAR2 DEFAULT NULL,
    p_author IN VARCHAR2 DEFAULT NULL,
    p_description IN VARCHAR2 DEFAULT NULL,
    p_price IN NUMBER DEFAULT NULL,
    p_publisher_id IN NUMBER DEFAULT NULL,
    p_genre_id IN NUMBER DEFAULT NULL
  );

  -- Set book flags
  PROCEDURE set_book_flags(
    p_book_id IN NUMBER,
    p_is_bestseller IN NUMBER DEFAULT NULL,
    p_is_featured IN NUMBER DEFAULT NULL,
    p_is_new_arrival IN NUMBER DEFAULT NULL
  );

  -- Search books by criteria
  FUNCTION search_books(
    p_search_text IN VARCHAR2,
    p_genre_id IN NUMBER DEFAULT NULL,
    p_publisher_id IN NUMBER DEFAULT NULL,
    p_min_price IN NUMBER DEFAULT NULL,
    p_max_price IN NUMBER DEFAULT NULL,
    p_is_available IN NUMBER DEFAULT 1
  ) RETURN SYS_REFCURSOR;

  -- Get low stock books
  FUNCTION get_low_stock_books(p_threshold NUMBER DEFAULT 5) RETURN SYS_REFCURSOR;

  -- Get book sales history
  FUNCTION get_book_sales_history(p_book_id NUMBER) RETURN SYS_REFCURSOR;
END book_inventory;
/

CREATE OR REPLACE PACKAGE BODY book_inventory AS
  -- Add new book
  PROCEDURE add_book(
    p_name IN VARCHAR2,
    p_author IN VARCHAR2,
    p_isbn IN VARCHAR2,
    p_description IN VARCHAR2,
    p_price IN NUMBER,
    p_quantity IN NUMBER,
    p_publisher_id IN NUMBER,
    p_genre_id IN NUMBER,
    p_book_type_id IN NUMBER,
    p_condition_id IN NUMBER,
    p_publish_date IN DATE DEFAULT NULL,
    p_year IN NUMBER DEFAULT NULL,
    p_book_id OUT NUMBER
  ) IS
  BEGIN
    INSERT INTO BOOKS (
      NAME,
      AUTHOR,
      ISBN,
      DESCRIPTION,
      PRICE,
      QUANTITY,
      PUBLISHER_ID,
      GENRE_ID,
      BOOK_TYPE_ID,
      CONDITION_ID,
      PUBLISH_DATE,
      YEAR,
      IS_AVAILABLE,
      IS_BESTSELLER,
      IS_FEATURED,
      IS_NEW_ARRIVAL,
      CREATED_ON,
      UPDATED_ON,
      SEARCH_TEXT
    ) VALUES (
      p_name,
      p_author,
      p_isbn,
      p_description,
      p_price,
      p_quantity,
      p_publisher_id,
      p_genre_id,
      p_book_type_id,
      p_condition_id,
      p_publish_date,
      p_year,
      CASE WHEN p_quantity > 0 THEN 1 ELSE 0 END,
      0, -- not bestseller by default
      0, -- not featured by default
      1, -- new arrival by default
      SYSTIMESTAMP,
      SYSTIMESTAMP,
      LOWER(p_name || ' ' || p_author || ' ' || p_isbn)
    )
    RETURNING ID INTO p_book_id;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END add_book;

  -- Update book details
  PROCEDURE update_book(
    p_book_id IN NUMBER,
    p_name IN VARCHAR2 DEFAULT NULL,
    p_author IN VARCHAR2 DEFAULT NULL,
    p_description IN VARCHAR2 DEFAULT NULL,
    p_price IN NUMBER DEFAULT NULL,
    p_publisher_id IN NUMBER DEFAULT NULL,
    p_genre_id IN NUMBER DEFAULT NULL
  ) IS
    v_name BOOKS.NAME%TYPE;
    v_author BOOKS.AUTHOR%TYPE;
    v_isbn BOOKS.ISBN%TYPE;
  BEGIN
    -- Get current values for search text update
    SELECT NAME, AUTHOR, ISBN
    INTO v_name, v_author, v_isbn
    FROM BOOKS
    WHERE ID = p_book_id;

    -- Update only provided fields
    UPDATE BOOKS
    SET NAME = NVL(p_name, NAME),
        AUTHOR = NVL(p_author, AUTHOR),
        DESCRIPTION = NVL(p_description, DESCRIPTION),
        PRICE = NVL(p_price, PRICE),
        PUBLISHER_ID = NVL(p_publisher_id, PUBLISHER_ID),
        GENRE_ID = NVL(p_genre_id, GENRE_ID),
        UPDATED_ON = SYSTIMESTAMP,
        SEARCH_TEXT = LOWER(
          NVL(p_name, v_name) || ' ' ||
          NVL(p_author, v_author) || ' ' ||
          v_isbn
        )
    WHERE ID = p_book_id;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END update_book;

  -- Set book flags
  PROCEDURE set_book_flags(
    p_book_id IN NUMBER,
    p_is_bestseller IN NUMBER DEFAULT NULL,
    p_is_featured IN NUMBER DEFAULT NULL,
    p_is_new_arrival IN NUMBER DEFAULT NULL
  ) IS
  BEGIN
    UPDATE BOOKS
    SET IS_BESTSELLER = NVL(p_is_bestseller, IS_BESTSELLER),
        IS_FEATURED = NVL(p_is_featured, IS_FEATURED),
        IS_NEW_ARRIVAL = NVL(p_is_new_arrival, IS_NEW_ARRIVAL),
        UPDATED_ON = SYSTIMESTAMP
    WHERE ID = p_book_id;

    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ROLLBACK;
      RAISE;
  END set_book_flags;

  -- Search books by criteria
  FUNCTION search_books(
    p_search_text IN VARCHAR2,
    p_genre_id IN NUMBER DEFAULT NULL,
    p_publisher_id IN NUMBER DEFAULT NULL,
    p_min_price IN NUMBER DEFAULT NULL,
    p_max_price IN NUMBER DEFAULT NULL,
    p_is_available IN NUMBER DEFAULT 1
  ) RETURN SYS_REFCURSOR IS
    v_result SYS_REFCURSOR;
  BEGIN
    OPEN v_result FOR
      SELECT B.*,
             G.NAME AS GENRE_NAME,
             P.NAME AS PUBLISHER_NAME,
             BT.NAME AS BOOK_TYPE_NAME,
             C.NAME AS CONDITION_NAME
      FROM BOOKS B
      LEFT JOIN GENRES G ON B.GENRE_ID = G.ID
      LEFT JOIN PUBLISHERS P ON B.PUBLISHER_ID = P.ID
      LEFT JOIN BOOK_TYPES BT ON B.BOOK_TYPE_ID = BT.ID
      LEFT JOIN CONDITIONS C ON B.CONDITION_ID = C.ID
      WHERE (p_search_text IS NULL OR
             INSTR(B.SEARCH_TEXT, LOWER(p_search_text)) > 0)
        AND (p_genre_id IS NULL OR B.GENRE_ID = p_genre_id)
        AND (p_publisher_id IS NULL OR B.PUBLISHER_ID = p_publisher_id)
        AND (p_min_price IS NULL OR B.PRICE >= p_min_price)
        AND (p_max_price IS NULL OR B.PRICE <= p_max_price)
        AND (p_is_available IS NULL OR B.IS_AVAILABLE = p_is_available)
      ORDER BY
        CASE WHEN p_search_text IS NOT NULL AND INSTR(LOWER(B.NAME), LOWER(p_search_text)) = 1 THEN 0 ELSE 1 END,
        B.IS_BESTSELLER DESC,
        B.IS_FEATURED DESC,
        B.NAME;

    RETURN v_result;
  END search_books;

  -- Get low stock books
  FUNCTION get_low_stock_books(p_threshold NUMBER DEFAULT 5) RETURN SYS_REFCURSOR IS
    v_result SYS_REFCURSOR;
  BEGIN
    OPEN v_result FOR
      SELECT B.*,
             G.NAME AS GENRE_NAME,
             P.NAME AS PUBLISHER_NAME
      FROM BOOKS B
      LEFT JOIN GENRES G ON B.GENRE_ID = G.ID
      LEFT JOIN PUBLISHERS P ON B.PUBLISHER_ID = P.ID
      WHERE B.QUANTITY <= p_threshold
        AND B.QUANTITY > 0
      ORDER BY B.QUANTITY;

    RETURN v_result;
  END get_low_stock_books;

  -- Get book sales history
  FUNCTION get_book_sales_history(p_book_id NUMBER) RETURN SYS_REFCURSOR IS
    v_result SYS_REFCURSOR;
  BEGIN
    OPEN v_result FOR
      SELECT
        O.ID AS ORDER_ID,
        O.CREATED_ON AS ORDER_DATE,
        OI.QUANTITY,
        OI.BOOK_PRICE,
        OI.DISCOUNT,
        (OI.BOOK_PRICE * OI.QUANTITY - NVL(OI.DISCOUNT, 0)) AS TOTAL_AMOUNT,
        C.ID AS CUSTOMER_ID,
        C.FIRST_NAME || ' ' || C.LAST_NAME AS CUSTOMER_NAME
      FROM ORDER_ITEMS OI
      JOIN ORDERS O ON OI.ORDER_ID = O.ID
      JOIN CUSTOMERS C ON O.CUSTOMER_ID = C.ID
      WHERE OI.BOOK_ID = p_book_id
      ORDER BY O.CREATED_ON DESC;

    RETURN v_result;
  END get_book_sales_history;
END book_inventory;
/

-- 13. Simple materialized view for book inventory status
CREATE MATERIALIZED VIEW MV_BOOK_INVENTORY_STATUS
REFRESH ON DEMAND
AS
SELECT
  B.ID,
  B.NAME,
  B.AUTHOR,
  B.ISBN,
  B.QUANTITY,
  B.PRICE,
  B.IS_AVAILABLE,
  G.NAME AS GENRE,
  P.NAME AS PUBLISHER
FROM BOOKS B
LEFT JOIN GENRES G ON B.GENRE_ID = G.ID
LEFT JOIN PUBLISHERS P ON B.PUBLISHER_ID = P.ID;

-- 14. Medium complexity materialized view for customer shopping patterns
CREATE MATERIALIZED VIEW MV_CUSTOMER_SHOPPING_PATTERNS
REFRESH COMPLETE ON DEMAND
START WITH SYSDATE
NEXT SYSDATE + 7
AS
SELECT
  C.ID AS CUSTOMER_ID,
  C.FIRST_NAME || ' ' || C.LAST_NAME AS CUSTOMER_NAME,
  G.NAME AS GENRE_NAME,
  COUNT(DISTINCT OI.ID) AS PURCHASE_COUNT,
  SUM(OI.QUANTITY) AS BOOKS_PURCHASED,
  ROUND(AVG(OI.BOOK_PRICE), 2) AS AVG_BOOK_PRICE,
  MIN(O.CREATED_ON) AS FIRST_PURCHASE_DATE,
  MAX(O.CREATED_ON) AS LAST_PURCHASE_DATE
FROM CUSTOMERS C
JOIN ORDERS O ON C.ID = O.CUSTOMER_ID
JOIN ORDER_ITEMS OI ON O.ID = OI.ORDER_ID
JOIN BOOKS B ON OI.BOOK_ID = B.ID
JOIN GENRES G ON B.GENRE_ID = G.ID
GROUP BY C.ID, C.FIRST_NAME || ' ' || C.LAST_NAME, G.NAME;

-- 15. Simple function to get book availability
CREATE OR REPLACE FUNCTION is_book_available(p_book_id NUMBER)
RETURN VARCHAR2
IS
  v_quantity NUMBER;
  v_is_available NUMBER;
BEGIN
  SELECT QUANTITY, IS_AVAILABLE
  INTO v_quantity, v_is_available
  FROM BOOKS
  WHERE ID = p_book_id;

  IF v_is_available = 1 AND v_quantity > 0 THEN
    RETURN 'Available';
  ELSIF v_quantity = 0 THEN
    RETURN 'Out of Stock';
  ELSE
    RETURN 'Unavailable';
  END IF;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    RETURN 'Book Not Found';
END;
/

-- 16. Simple procedure to add item to shopping cart
CREATE OR REPLACE PROCEDURE add_to_cart(
  p_customer_id IN NUMBER,
  p_book_id IN NUMBER,
  p_quantity IN NUMBER DEFAULT 1,
  p_is_wishlist IN NUMBER DEFAULT 0
)
IS
  v_cart_item_id NUMBER;
  v_existing_quantity NUMBER;
BEGIN
  -- Check if item already exists in cart
  BEGIN
    SELECT ID, QUANTITY
    INTO v_cart_item_id, v_existing_quantity
    FROM SHOPPING_CART_ITEMS
    WHERE CUSTOMER_ID = p_customer_id
      AND BOOK_ID = p_book_id
      AND IS_WISHLIST_ITEM = p_is_wishlist;

    -- Update existing cart item
    UPDATE SHOPPING_CART_ITEMS
    SET QUANTITY = v_existing_quantity + p_quantity
    WHERE ID = v_cart_item_id;

  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      -- Add new cart item
      INSERT INTO SHOPPING_CART_ITEMS (
        CUSTOMER_ID,
        BOOK_ID,
        QUANTITY,
        IS_WISHLIST_ITEM
      ) VALUES (
        p_customer_id,
        p_book_id,
        p_quantity,
        p_is_wishlist
      );
  END;

  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    RAISE;
END;
/

-- 17. Medium complexity function to get recommended books
CREATE OR REPLACE FUNCTION get_recommended_books(
  p_customer_id NUMBER,
  p_limit NUMBER DEFAULT 10
) RETURN SYS_REFCURSOR
IS
  v_result SYS_REFCURSOR;
BEGIN
  -- This function recommends books based on customer's purchase history
  -- It finds genres the customer has purchased before and recommends other books in those genres
  -- that the customer hasn't purchased yet
  OPEN v_result FOR
    WITH customer_genres AS (
      -- Find genres this customer has purchased
      SELECT DISTINCT B.GENRE_ID
      FROM ORDERS O
      JOIN ORDER_ITEMS OI ON O.ID = OI.ORDER_ID
      JOIN BOOKS B ON OI.BOOK_ID = B.ID
      WHERE O.CUSTOMER_ID = p_customer_id
    ),
    purchased_books AS (
      -- Books the customer has already purchased
      SELECT DISTINCT OI.BOOK_ID
      FROM ORDERS O
      JOIN ORDER_ITEMS OI ON O.ID = OI.ORDER_ID
      WHERE O.CUSTOMER_ID = p_customer_id
    )
    SELECT B.ID, B.NAME, B.AUTHOR, B.PRICE, B.ISBN,
           G.NAME AS GENRE_NAME,
           P.NAME AS PUBLISHER_NAME
    FROM BOOKS B
    JOIN GENRES G ON B.GENRE_ID = G.ID
    JOIN PUBLISHERS P ON B.PUBLISHER_ID = P.ID
    WHERE B.GENRE_ID IN (SELECT GENRE_ID FROM customer_genres)
      AND B.IS_AVAILABLE = 1
      AND B.ID NOT IN (SELECT BOOK_ID FROM purchased_books)
    ORDER BY
      B.IS_BESTSELLER DESC,
      B.IS_FEATURED DESC,
      DBMS_RANDOM.VALUE  -- Add some randomness to recommendations
    FETCH FIRST p_limit ROWS ONLY;

  RETURN v_result;
END;
/

-- 18. Complex procedure for generating monthly sales report
CREATE OR REPLACE PROCEDURE generate_monthly_sales_report(
  p_year IN NUMBER,
  p_month IN NUMBER,
  p_report_cursor OUT SYS_REFCURSOR
)
IS
  v_start_date DATE;
  v_end_date DATE;
BEGIN
  -- Calculate date range for the report
  v_start_date := TO_DATE(p_year || '-' || p_month || '-01', 'YYYY-MM-DD');
  v_end_date := LAST_DAY(v_start_date) + INTERVAL '1' DAY - INTERVAL '1' SECOND;

  -- Generate the report
  OPEN p_report_cursor FOR
    WITH monthly_sales AS (
      SELECT
        B.ID AS BOOK_ID,
        B.NAME AS BOOK_TITLE,
        B.AUTHOR,
        G.NAME AS GENRE,
        P.NAME AS PUBLISHER,
        SUM(OI.QUANTITY) AS QUANTITY_SOLD,
        SUM(OI.BOOK_PRICE * OI.QUANTITY) AS GROSS_REVENUE,
        SUM(OI.DISCOUNT) AS TOTAL_DISCOUNTS,
        SUM(OI.BOOK_PRICE * OI.QUANTITY - NVL(OI.DISCOUNT, 0)) AS NET_REVENUE
      FROM BOOKS B
      JOIN ORDER_ITEMS OI ON B.ID = OI.BOOK_ID
      JOIN ORDERS O ON OI.ORDER_ID = O.ID
      LEFT JOIN GENRES G ON B.GENRE_ID = G.ID
      LEFT JOIN PUBLISHERS P ON B.PUBLISHER_ID = P.ID
      WHERE O.CREATED_ON BETWEEN v_start_date AND v_end_date
        AND O.ORDER_STATUS != 4  -- Exclude cancelled orders
      GROUP BY B.ID, B.NAME, B.AUTHOR, G.NAME, P.NAME
    ),
    genre_sales AS (
      SELECT
        G.NAME AS GENRE,
        COUNT(DISTINCT B.ID) AS UNIQUE_BOOKS_SOLD,
        SUM(OI.QUANTITY) AS QUANTITY_SOLD,
        SUM(OI.BOOK_PRICE * OI.QUANTITY - NVL(OI.DISCOUNT, 0)) AS NET_REVENUE
      FROM GENRES G
      JOIN BOOKS B ON G.ID = B.GENRE_ID
      JOIN ORDER_ITEMS OI ON B.ID = OI.BOOK_ID
      JOIN ORDERS O ON OI.ORDER_ID = O.ID
      WHERE O.CREATED_ON BETWEEN v_start_date AND v_end_date
        AND O.ORDER_STATUS != 4  -- Exclude cancelled orders
      GROUP BY G.NAME
    ),
    daily_sales AS (
      SELECT
        TRUNC(O.CREATED_ON) AS SALE_DATE,
        COUNT(DISTINCT O.ID) AS ORDER_COUNT,
        SUM(O.TOTAL_AMOUNT) AS DAILY_REVENUE
      FROM ORDERS O
      WHERE O.CREATED_ON BETWEEN v_start_date AND v_end_date
        AND O.ORDER_STATUS != 4  -- Exclude cancelled orders
      GROUP BY TRUNC(O.CREATED_ON)
    )
    SELECT
      'MONTHLY_SUMMARY' AS REPORT_SECTION,
      TO_CHAR(v_start_date, 'YYYY-MM') AS REPORT_PERIOD,
      COUNT(DISTINCT ms.BOOK_ID) AS UNIQUE_BOOKS_SOLD,
      SUM(ms.QUANTITY_SOLD) AS TOTAL_BOOKS_SOLD,
      SUM(ms.GROSS_REVENUE) AS GROSS_REVENUE,
      SUM(ms.TOTAL_DISCOUNTS) AS TOTAL_DISCOUNTS,
      SUM(ms.NET_REVENUE) AS NET_REVENUE,
      (SELECT COUNT(DISTINCT O.ID)
       FROM ORDERS O
       WHERE O.CREATED_ON BETWEEN v_start_date AND v_end_date
         AND O.ORDER_STATUS != 4) AS ORDER_COUNT,
      (SELECT COUNT(DISTINCT O.CUSTOMER_ID)
       FROM ORDERS O
       WHERE O.CREATED_ON BETWEEN v_start_date AND v_end_date
         AND O.ORDER_STATUS != 4) AS CUSTOMER_COUNT
    FROM monthly_sales ms

    UNION ALL

    SELECT
      'TOP_BOOKS' AS REPORT_SECTION,
      NULL AS REPORT_PERIOD,
      NULL AS UNIQUE_BOOKS_SOLD,
      ms.QUANTITY_SOLD AS TOTAL_BOOKS_SOLD,
      ms.GROSS_REVENUE,
      ms.TOTAL_DISCOUNTS,
      ms.NET_REVENUE,
      NULL AS ORDER_COUNT,
      NULL AS CUSTOMER_COUNT
    FROM monthly_sales ms
    ORDER BY ms.QUANTITY_SOLD DESC
    FETCH FIRST 10 ROWS ONLY

    UNION ALL

    SELECT
      'GENRE_PERFORMANCE' AS REPORT_SECTION,
      gs.GENRE AS REPORT_PERIOD,
      gs.UNIQUE_BOOKS_SOLD,
      gs.QUANTITY_SOLD AS TOTAL_BOOKS_SOLD,
      NULL AS GROSS_REVENUE,
      NULL AS TOTAL_DISCOUNTS,
      gs.NET_REVENUE,
      NULL AS ORDER_COUNT,
      NULL AS CUSTOMER_COUNT
    FROM genre_sales gs
    ORDER BY gs.NET_REVENUE DESC

    UNION ALL

    SELECT
      'DAILY_SALES' AS REPORT_SECTION,
      TO_CHAR(ds.SALE_DATE, 'YYYY-MM-DD') AS REPORT_PERIOD,
      NULL AS UNIQUE_BOOKS_SOLD,
      NULL AS TOTAL_BOOKS_SOLD,
      NULL AS GROSS_REVENUE,
      NULL AS TOTAL_DISCOUNTS,
      ds.DAILY_REVENUE AS NET_REVENUE,
      ds.ORDER_COUNT,
      NULL AS CUSTOMER_COUNT
    FROM daily_sales ds
    ORDER BY ds.SALE_DATE;
END;
/

-- 19. Complex materialized view for customer segmentation
CREATE MATERIALIZED VIEW MV_CUSTOMER_SEGMENTATION
REFRESH COMPLETE ON DEMAND
START WITH SYSDATE
NEXT SYSDATE + 30
AS
WITH customer_metrics AS (
  SELECT
    C.ID AS CUSTOMER_ID,
    C.FIRST_NAME || ' ' || C.LAST_NAME AS CUSTOMER_NAME,
    C.EMAIL,
    COUNT(DISTINCT O.ID) AS ORDER_COUNT,
    SUM(O.TOTAL_AMOUNT) AS TOTAL_SPENT,
    ROUND(AVG(O.TOTAL_AMOUNT), 2) AS AVG_ORDER_VALUE_SIMPLE,
    MAX(O.CREATED_ON) AS LAST_ORDER_DATE,
    MIN(O.CREATED_ON) AS FIRST_ORDER_DATE,
    ROUND(MONTHS_BETWEEN(SYSDATE, MIN(O.CREATED_ON)), 1) AS CUSTOMER_TENURE_MONTHS,
    ROUND(MONTHS_BETWEEN(SYSDATE, MAX(O.CREATED_ON)), 1) AS MONTHS_SINCE_LAST_ORDER,
    COUNT(DISTINCT TRUNC(O.CREATED_ON, 'MM')) AS ACTIVE_MONTHS
  FROM CUSTOMERS C
  LEFT JOIN ORDERS O ON C.ID = O.CUSTOMER_ID AND O.ORDER_STATUS != 4 -- Exclude cancelled orders
  GROUP BY C.ID, C.FIRST_NAME || ' ' || C.LAST_NAME, C.EMAIL
)
SELECT
  CM.*,
  CASE
    WHEN CM.ORDER_COUNT = 0 THEN 'Inactive'
    WHEN CM.MONTHS_SINCE_LAST_ORDER > 12 THEN 'Dormant'
    WHEN CM.ORDER_COUNT = 1 AND CM.MONTHS_SINCE_LAST_ORDER <= 3 THEN 'New Customer'
    WHEN CM.ORDER_COUNT >= 4 AND CM.TOTAL_SPENT >= 500 AND CM.MONTHS_SINCE_LAST_ORDER <= 3 THEN 'VIP'
    WHEN CM.ORDER_COUNT >= 2 AND CM.MONTHS_SINCE_LAST_ORDER <= 6 THEN 'Active'
    ELSE 'At Risk'
  END AS CUSTOMER_SEGMENT,
  CASE
    WHEN CM.ORDER_COUNT > 0 THEN ROUND(CM.TOTAL_SPENT / CM.ORDER_COUNT, 2)
    ELSE 0
  END AS AVG_ORDER_VALUE,
  CASE
    WHEN CM.CUSTOMER_TENURE_MONTHS > 0 AND CM.ACTIVE_MONTHS > 0 THEN
      ROUND(CM.ACTIVE_MONTHS / CM.CUSTOMER_TENURE_MONTHS, 2)
    ELSE 0
  END AS ENGAGEMENT_RATIO,
  CASE
    WHEN CM.CUSTOMER_TENURE_MONTHS > 0 THEN
      ROUND(CM.TOTAL_SPENT / CM.CUSTOMER_TENURE_MONTHS, 2)
    ELSE 0
  END AS MONTHLY_VALUE
FROM customer_metrics CM;

-- 20. Complex package for reporting and analytics
CREATE OR REPLACE PACKAGE book_analytics AS
  -- Get sales trend by time period
  FUNCTION get_sales_trend(
    p_start_date IN DATE,
    p_end_date IN DATE,
    p_interval IN VARCHAR2 DEFAULT 'MONTH' -- 'DAY', 'WEEK', 'MONTH', 'QUARTER', 'YEAR'
  ) RETURN SYS_REFCURSOR;

  -- Get bestselling books for a period
  FUNCTION get_bestsellers(
    p_start_date IN DATE,
    p_end_date IN DATE,
    p_limit IN NUMBER DEFAULT 10,
    p_genre_id IN NUMBER DEFAULT NULL
  ) RETURN SYS_REFCURSOR;

  -- Get customer purchase history
  FUNCTION get_customer_history(
    p_customer_id IN NUMBER
  ) RETURN SYS_REFCURSOR;

  -- Calculate inventory turnover
  FUNCTION calculate_inventory_turnover(
    p_start_date IN DATE,
    p_end_date IN DATE
  ) RETURN SYS_REFCURSOR;

  -- Generate revenue forecast
  PROCEDURE generate_revenue_forecast(
    p_months_ahead IN NUMBER DEFAULT 3,
    p_forecast_cursor OUT SYS_REFCURSOR
  );
END book_analytics;
/

CREATE OR REPLACE PACKAGE BODY book_analytics AS
  -- Get sales trend by time period
  FUNCTION get_sales_trend(
    p_start_date IN DATE,
    p_end_date IN DATE,
    p_interval IN VARCHAR2 DEFAULT 'MONTH' -- 'DAY', 'WEEK', 'MONTH', 'QUARTER', 'YEAR'
  ) RETURN SYS_REFCURSOR IS
    v_result SYS_REFCURSOR;
    v_trunc_format VARCHAR2(20);
  BEGIN
    -- Determine the truncation format based on interval
    CASE UPPER(p_interval)
      WHEN 'DAY' THEN v_trunc_format := 'DD';
      WHEN 'WEEK' THEN v_trunc_format := 'IW';
      WHEN 'MONTH' THEN v_trunc_format := 'MM';
      WHEN 'QUARTER' THEN v_trunc_format := 'Q';
      WHEN 'YEAR' THEN v_trunc_format := 'YYYY';
      ELSE v_trunc_format := 'MM'; -- Default to month
    END CASE;

    OPEN v_result FOR
      SELECT
        TRUNC(O.CREATED_ON, v_trunc_format) AS PERIOD_START,
        CASE UPPER(p_interval)
          WHEN 'DAY' THEN TO_CHAR(TRUNC(O.CREATED_ON, v_trunc_format), 'YYYY-MM-DD')
          WHEN 'WEEK' THEN 'Week ' || TO_CHAR(TRUNC(O.CREATED_ON, v_trunc_format), 'IW') || ', ' || TO_CHAR(TRUNC(O.CREATED_ON, v_trunc_format), 'YYYY')
          WHEN 'MONTH' THEN TO_CHAR(TRUNC(O.CREATED_ON, v_trunc_format), 'YYYY-MM')
          WHEN 'QUARTER' THEN 'Q' || TO_CHAR(TRUNC(O.CREATED_ON, v_trunc_format), 'Q') || ' ' || TO_CHAR(TRUNC(O.CREATED_ON, v_trunc_format), 'YYYY')
          WHEN 'YEAR' THEN TO_CHAR(TRUNC(O.CREATED_ON, v_trunc_format), 'YYYY')
          ELSE TO_CHAR(TRUNC(O.CREATED_ON, v_trunc_format), 'YYYY-MM')
        END AS PERIOD_LABEL,
        COUNT(DISTINCT O.ID) AS ORDER_COUNT,
        SUM(O.TOTAL_AMOUNT) AS REVENUE,
        COUNT(DISTINCT O.CUSTOMER_ID) AS CUSTOMER_COUNT,
        SUM(OI.QUANTITY) AS BOOKS_SOLD,
        ROUND(AVG(O.TOTAL_AMOUNT), 2) AS AVG_ORDER_VALUE
      FROM ORDERS O
      JOIN ORDER_ITEMS OI ON O.ID = OI.ORDER_ID
      WHERE O.CREATED_ON BETWEEN p_start_date AND p_end_date
        AND O.ORDER_STATUS != 4 -- Exclude cancelled orders
      GROUP BY TRUNC(O.CREATED_ON, v_trunc_format)
      ORDER BY TRUNC(O.CREATED_ON, v_trunc_format);

    RETURN v_result;
  END get_sales_trend;

  -- Get bestselling books for a period
  FUNCTION get_bestsellers(
    p_start_date IN DATE,
    p_end_date IN DATE,
    p_limit IN NUMBER DEFAULT 10,
    p_genre_id IN NUMBER DEFAULT NULL
  ) RETURN SYS_REFCURSOR IS
    v_result SYS_REFCURSOR;
  BEGIN
    OPEN v_result FOR
      SELECT
        B.ID,
        B.NAME,
        B.AUTHOR,
        G.NAME AS GENRE,
        P.NAME AS PUBLISHER,
        SUM(OI.QUANTITY) AS QUANTITY_SOLD,
        SUM(OI.BOOK_PRICE * OI.QUANTITY - NVL(OI.DISCOUNT, 0)) AS REVENUE,
        COUNT(DISTINCT O.ID) AS ORDER_COUNT,
        COUNT(DISTINCT O.CUSTOMER_ID) AS CUSTOMER_COUNT,
        ROUND(AVG(OI.BOOK_PRICE), 2) AS AVG_PRICE
      FROM BOOKS B
      JOIN ORDER_ITEMS OI ON B.ID = OI.BOOK_ID
      JOIN ORDERS O ON OI.ORDER_ID = O.ID
      LEFT JOIN GENRES G ON B.GENRE_ID = G.ID
      LEFT JOIN PUBLISHERS P ON B.PUBLISHER_ID = P.ID
      WHERE O.CREATED_ON BETWEEN p_start_date AND p_end_date
        AND O.ORDER_STATUS != 4 -- Exclude cancelled orders
        AND (p_genre_id IS NULL OR B.GENRE_ID = p_genre_id)
      GROUP BY B.ID, B.NAME, B.AUTHOR, G.NAME, P.NAME
      ORDER BY SUM(OI.QUANTITY) DESC
      FETCH FIRST p_limit ROWS ONLY;

    RETURN v_result;
  END get_bestsellers;

  -- Get customer purchase history
  FUNCTION get_customer_history(
    p_customer_id IN NUMBER
  ) RETURN SYS_REFCURSOR IS
    v_result SYS_REFCURSOR;
  BEGIN
    OPEN v_result FOR
      SELECT
        O.ID AS ORDER_ID,
        O.CREATED_ON AS ORDER_DATE,
        O.ORDER_STATUS,
        CASE O.ORDER_STATUS
          WHEN 1 THEN 'New'
          WHEN 2 THEN 'Shipped'
          WHEN 3 THEN 'Delivered'
          WHEN 4 THEN 'Cancelled'
          ELSE 'Unknown'
        END AS STATUS_DESCRIPTION,
        O.TOTAL_AMOUNT,
        B.ID AS BOOK_ID,
        B.NAME AS BOOK_TITLE,
        B.AUTHOR,
        G.NAME AS GENRE,
        OI.QUANTITY,
        OI.BOOK_PRICE,
        OI.DISCOUNT,
        (OI.BOOK_PRICE * OI.QUANTITY - NVL(OI.DISCOUNT, 0)) AS ITEM_TOTAL
      FROM ORDERS O
      JOIN ORDER_ITEMS OI ON O.ID = OI.ORDER_ID
      JOIN BOOKS B ON OI.BOOK_ID = B.ID
      LEFT JOIN GENRES G ON B.GENRE_ID = G.ID
      WHERE O.CUSTOMER_ID = p_customer_id
      ORDER BY O.CREATED_ON DESC, O.ID, B.NAME;

    RETURN v_result;
  END get_customer_history;

  -- Calculate inventory turnover
  FUNCTION calculate_inventory_turnover(
    p_start_date IN DATE,
    p_end_date IN DATE
  ) RETURN SYS_REFCURSOR IS
    v_result SYS_REFCURSOR;
  BEGIN
    OPEN v_result FOR
      WITH book_sales AS (
        SELECT
          B.ID,
          B.NAME,
          B.AUTHOR,
          G.NAME AS GENRE,
          SUM(OI.QUANTITY) AS QUANTITY_SOLD,
          SUM(OI.BOOK_PRICE * OI.QUANTITY) AS REVENUE
        FROM BOOKS B
        JOIN ORDER_ITEMS OI ON B.ID = OI.BOOK_ID
        JOIN ORDERS O ON OI.ORDER_ID = O.ID
        LEFT JOIN GENRES G ON B.GENRE_ID = G.ID
        WHERE O.CREATED_ON BETWEEN p_start_date AND p_end_date
          AND O.ORDER_STATUS != 4 -- Exclude cancelled orders
        GROUP BY B.ID, B.NAME, B.AUTHOR, G.NAME
      )
      SELECT
        BS.ID,
        BS.NAME,
        BS.AUTHOR,
        BS.GENRE,
        B.QUANTITY AS CURRENT_STOCK,
        BS.QUANTITY_SOLD,
        BS.REVENUE,
        CASE
          WHEN B.QUANTITY > 0 THEN ROUND(BS.QUANTITY_SOLD / B.QUANTITY, 2)
          ELSE NULL
        END AS TURNOVER_RATIO,
        CASE
          WHEN B.QUANTITY > 0 AND BS.QUANTITY_SOLD > 0
            THEN ROUND(B.QUANTITY / (BS.QUANTITY_SOLD / ((p_end_date - p_start_date) / 30)), 1)
          ELSE NULL
        END AS MONTHS_OF_INVENTORY
      FROM book_sales BS
      JOIN BOOKS B ON BS.ID = B.ID
      ORDER BY
        CASE
          WHEN B.QUANTITY > 0 THEN BS.QUANTITY_SOLD / B.QUANTITY
          ELSE 0
        END DESC;

    RETURN v_result;
  END calculate_inventory_turnover;

  -- Generate revenue forecast
  PROCEDURE generate_revenue_forecast(
    p_months_ahead IN NUMBER DEFAULT 3,
    p_forecast_cursor OUT SYS_REFCURSOR
  ) IS
    v_months_of_history NUMBER := 12; -- Use 12 months of history for forecasting
    v_start_date DATE;
  BEGIN
    -- Calculate start date for historical data
    v_start_date := ADD_MONTHS(TRUNC(SYSDATE, 'MM'), -v_months_of_history);

    -- Generate forecast based on historical data
    OPEN p_forecast_cursor FOR
      WITH monthly_sales AS (
        SELECT
          TRUNC(O.CREATED_ON, 'MM') AS MONTH_START,
          TO_CHAR(TRUNC(O.CREATED_ON, 'MM'), 'YYYY-MM') AS MONTH_LABEL,
          SUM(O.TOTAL_AMOUNT) AS MONTHLY_REVENUE,
          COUNT(DISTINCT O.ID) AS ORDER_COUNT,
          SUM(OI.QUANTITY) AS BOOKS_SOLD
        FROM ORDERS O
        JOIN ORDER_ITEMS OI ON O.ID = OI.ORDER_ID
        WHERE O.CREATED_ON >= v_start_date
          AND O.ORDER_STATUS != 4 -- Exclude cancelled orders
        GROUP BY TRUNC(O.CREATED_ON, 'MM')
        ORDER BY TRUNC(O.CREATED_ON, 'MM')
      ),
      monthly_stats AS (
        SELECT
          AVG(MONTHLY_REVENUE) AS AVG_MONTHLY_REVENUE,
          STDDEV(MONTHLY_REVENUE) AS STDDEV_MONTHLY_REVENUE,
          REGR_SLOPE(MONTHLY_REVENUE, ROWNUM) AS REVENUE_TREND,
          AVG(ORDER_COUNT) AS AVG_ORDER_COUNT,
          AVG(BOOKS_SOLD) AS AVG_BOOKS_SOLD
        FROM monthly_sales
      ),
      forecast_months AS (
        SELECT
          ADD_MONTHS(TRUNC(SYSDATE, 'MM'), LEVEL) AS FORECAST_MONTH,
          TO_CHAR(ADD_MONTHS(TRUNC(SYSDATE, 'MM'), LEVEL), 'YYYY-MM') AS MONTH_LABEL,
          LEVEL AS MONTH_NUM
        FROM DUAL
        CONNECT BY LEVEL <= p_months_ahead
      )
      SELECT
        FM.MONTH_LABEL,
        -- Calculate forecasted revenue with trend and seasonal adjustments
        ROUND(
          MS.AVG_MONTHLY_REVENUE +
          (MS.REVENUE_TREND * FM.MONTH_NUM) +
          -- Add seasonal adjustment based on month (simplified)
          CASE TO_CHAR(FM.FORECAST_MONTH, 'MM')
            WHEN '11' THEN MS.AVG_MONTHLY_REVENUE * 0.2 -- Holiday season boost
            WHEN '12' THEN MS.AVG_MONTHLY_REVENUE * 0.3 -- Holiday season boost
            WHEN '01' THEN MS.AVG_MONTHLY_REVENUE * -0.1 -- Post-holiday dip
            ELSE 0
          END,
          2
        ) AS FORECASTED_REVENUE,
        -- Calculate lower bound of forecast (95% confidence)
        ROUND(
          MS.AVG_MONTHLY_REVENUE +
          (MS.REVENUE_TREND * FM.MONTH_NUM) -
          (1.96 * MS.STDDEV_MONTHLY_REVENUE / SQRT(v_months_of_history)),
          2
        ) AS LOWER_BOUND,
        -- Calculate upper bound of forecast (95% confidence)
        ROUND(
          MS.AVG_MONTHLY_REVENUE +
          (MS.REVENUE_TREND * FM.MONTH_NUM) +
          (1.96 * MS.STDDEV_MONTHLY_REVENUE / SQRT(v_months_of_history)),
          2
        ) AS UPPER_BOUND,
        ROUND(MS.AVG_ORDER_COUNT * (1 + (MS.REVENUE_TREND / MS.AVG_MONTHLY_REVENUE) * FM.MONTH_NUM)) AS FORECASTED_ORDERS,
        ROUND(MS.AVG_BOOKS_SOLD * (1 + (MS.REVENUE_TREND / MS.AVG_MONTHLY_REVENUE) * FM.MONTH_NUM)) AS FORECASTED_BOOKS_SOLD
      FROM forecast_months FM
      CROSS JOIN monthly_stats MS
      ORDER BY FM.FORECAST_MONTH;
  END generate_revenue_forecast;
END book_analytics;
/
