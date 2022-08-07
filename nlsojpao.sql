CREATE TABLE users(
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 name               CHARACTER VARYING (100) NOT NULL,
 last_name          CHARACTER VARYING (100) NOT NULL,
 email              CHARACTER VARYING (100) NOT NULL,
 password           CHARACTER VARYING (50) NOT null,
);

INSERT INTO users(name, last_name, email, password) 
VALUES ('Yauheni', 'Hlaholeu', 'jek94@gmail.com', '12qwaszx'),
('Uladzislau', 'Solovev', 'sol44@yandex.by', 'qazxsw21'),
('Haliana', 'Sidoric', 'galina_sid@gmail.com', 'sid93LL'),
('Lana', 'Dimidova', 'dlana@mail.ru', 'vfAz1234'),
('Andrey', 'Aksenov', 'AKsin@Gmail.com','12345678OOp'),
('Nazar', 'Vahtongov', 'vagan@mail.ru', '333eeeddfd'),
('Tatyana', 'Minikova', 'tMin@tut.by', 'trewrg'),
('Michail', 'Marshalau', 'Mix2020@rambler.by', 'hgnboenoenernv'),
('Francs', 'Nikiforof', 'rdko@mail.ru', 'roinrv'),
('Adi', 'Huseinov', 'gitler21@gmail.com', 'tirmid'),
('Kristafor', 'Djigurda', 'Americo1789@mail.ru', '534rrr'),
('Haliana', 'Dombrouskaya', 'hali-gali@yahoo.com', 'ddffgg445566'),
('Katserina', 'Mudalovich', 'gali@moli.ru', 'irjfncv'),
('Yauheni', 'Jimolost', 'tolick@rambler.ru', 'fkfldmc.'),
('Marck', 'Shagal', 'Markusik@rambler.ru', 'wertyuhgf'),
('Ivan', 'Mandelshtamm', 'manid@Gmail.com', '324rewf'),
('IoganSebostian', 'Bah', 'bahbah-12@mail.ru', '45tfe'),
('Pavel', 'Krishtofsky', 'pashok98@mail.ru', 'rtyjgf'),
('Artemiy', 'Potrahunchik', 'temavsem424@Gmail.com', '4567876543'),
('Vlad', 'Topalov', 'topal34@mail.ru', '345ygf');

CREATE TABLE role(
id                  BIGSERIAL PRIMARY KEY NOT NULL,
role_name           CHARACTER VARYING (60)
);

INSERT INTO role(role_name)
VALUES ('ADMIN'),
('USER'),
('MANAGER');

ALTER TABLE users  
ADD role_id BIGINT  
REFERENCES role(id);

UPDATE users  
SET role_id = (SELECT Id FROM role WHERE role_name = 'USER') 
WHERE Id>8;

UPDATE users  
SET role_id = (SELECT Id FROM role WHERE role_name = 'MANAGER') 
WHERE id BETWEEN 3 AND 7;

UPDATE users  
SET role_id = (SELECT Id FROM role WHERE role_name = 'ADMIN') 
WHERE id BETWEEN 1 and 2;

SELECT users.id,name, last_name, email, PASSWORD, role.role_name
FROM users  JOIN role 
ON users.role_id = role.id;

INSERT INTO users (name, last_name, email, password, role_id) 
VALUES ('Vlad', 'Marshalau', 'rigfd2020@rambler.by', 'srdfhgjthr', (SELECT id FROM role WHERE role_name = 'ADMIN'));


