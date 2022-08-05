ALTER TABLE books ALTER COLUMN status TYPE CHARACTER VARYING (30);


CREATE TABLE status(
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 num                 INTEGER NOT NULL,
 status_name         CHARACTER VARYING (60)
);

INSERT INTO status (num, status_name)
VALUES (0, 'IN_STOCK'),
   (1, 'SOLD'),
   (2, 'RESERVE'),
   (3, 'DELIVERY_EXPECTED'),
   (4, 'OUT_OF_STOCK');
  
  ALTER TABLE books ALTER COLUMN status TYPE INTEGER USING 1;
   
   
  SELECT status FROM books b JOIN status s ON b.status = s.status_name; 