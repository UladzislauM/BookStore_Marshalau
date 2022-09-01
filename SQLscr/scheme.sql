/*
DROP TABLE IF EXISTS orders_item;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS cover;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS status;
*/

CREATE TABLE IF NOT EXISTS cover (
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 cover_name			 CHARACTER VARYING (60)
);

CREATE TABLE IF NOT EXISTS books(
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 title               CHARACTER VARYING (80) NOT NULL,
 name_Author         CHARACTER VARYING (60),
 date_Release_Book   DATE NOT null,
 price               DECIMAL(10,2),
 isbn                char(17) unique,
 cover_id            BIGINT REFERENCES cover(id),
 deleted			 BOOLEAN NOT NULL DEFAULT FALSE

);

CREATE TABLE IF NOT EXISTS role(
id                  BIGSERIAL PRIMARY KEY NOT NULL,
role_name           CHARACTER VARYING (60)
);

CREATE TABLE IF NOT EXISTS users(
 id                 BIGSERIAL PRIMARY KEY NOT NULL,
 name               CHARACTER VARYING (100) NOT NULL,
 last_name          CHARACTER VARYING (100) NOT NULL,
 email              CHARACTER VARYING (100) NOT NULL,
 password           CHARACTER VARYING (50) NOT null,
 role_id            BIGINT REFERENCES role(id),
 deleted			BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS status(
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 status_name         CHARACTER VARYING (60)
);

CREATE TABLE IF NOT EXISTS orders(
id                 BIGSERIAL PRIMARY KEY NOT NULL,
user_id			   BIGINT REFERENCES users(id),
total_cost  	   DECIMAL(8,2)NOT NULL,
timestamp		   DATE NOT NULL,
status_id          BIGINT REFERENCES status(id)
);

CREATE TABLE IF NOT EXISTS orders_item(
 id                 BIGSERIAL PRIMARY KEY NOT NULL,
 orders_id 			BIGINT REFERENCES orders(id),
 book_id			BIGINT REFERENCES books(id),
 quantity			INT2 NOT NULL,
 price 				DECIMAL (6,2)
);