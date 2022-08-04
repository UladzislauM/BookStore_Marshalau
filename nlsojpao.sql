CREATE TYPE role_user as ENUM ('ADMIN', 'MANAGER');

CREATE TABLE users(
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 name               CHARACTER VARYING (100) NOT NULL,
 last_name          CHARACTER VARYING (100) NOT NULL,
 email              CHARACTER VARYING (100) NOT NULL,
 password           CHARACTER VARYING (50) NOT null,
 role               role_user
);

INSERT INTO users (name, last_name, email, password, role) 
VALUES ('Yauheni', 'Hlaholeu', 'jek94@gmail.com', '12qwaszx', 'ADMIN'),
('Uladzislau', 'Solovev', 'sol44@yandex.by', 'qazxsw21', 'MANAGER'),
('Haliana', 'Sidoric', 'galina_sid@gmail.com', 'sid93LL', 'MANAGER'),
('Lana', 'Dimidova', 'dlana@mail.ru', 'vfAz1234', 'MANAGER'),
('Andrey', 'Aksenov', 'AKsin@Gmail.com','12345678OOp', 'MANAGER');
