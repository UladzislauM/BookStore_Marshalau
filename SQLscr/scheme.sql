/*
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role;
*/

CREATE TABLE IF NOT EXISTS status(
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 status_name         CHARACTER VARYING (60)
);

CREATE TABLE IF NOT EXISTS books(
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 title               CHARACTER VARYING (80) NOT NULL,
 name_Author         CHARACTER VARYING (60),
 date_Release_Book   DATE NOT null,
 price               double PRECISION,
 isbn                char(17) unique,
 status_id           BIGINT REFERENCES status(id)
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
 role_id            BIGINT REFERENCES role(id)
);