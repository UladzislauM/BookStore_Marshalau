CREATE TABLE books(
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 title               CHARACTER VARYING (80) NOT NULL,
 name_Author         CHARACTER VARYING (60),
 date_Release_Book   DATE NOT null,
 status              CHARACTER VARYING (30) NOT null,
 price               double PRECISION,
 isbn                char(17) unique
);

INSERT INTO books (title, name_author, date_release_book, status, price, isbn) 
VALUES ('7Navikov', 'S.Kovy', '25-12-1991', 'in stock', 32.00, 9783164484100),
('GrafMonte', 'A.Dyma', '18-10-1896', 'in stock', 23.00, 9783164484111),
('GunsSteelAndGerms', 'D.Diamond', '17-06-2003', 'in stock', 44.00, 9783164484122),
('TheSubtleArtOfNotGivingAF', 'M.Mancon', '13-09-2016', 'in stock', 65.00, 9783164484133),
('SmertIvanaIlicha', 'L.Tolstoy', '05-11-1886', 'in stock', 35.00, 9783164484144),
('KamoGryadeshi', 'G.Synkevich', '14-05-1896', 'in stock', 73.00, 9783164484155),
('HoreOtUma', 'A.Griboedov', '24-08-1824', 'in stock', 59.00, 9783164484166),
('Neirofitnes', 'R.Djandial', '23-02-2019', 'in stock', 31.00, 9783164484200),
('JiznBezGranic', 'N.Vuitich', '20-04-2010', 'in stock', 77.00, 9783164484211),
('1984', 'D.Oruall', '28-05-1949', 'in stock', 11.00, 9783164484222),
('AnimalFarm', 'D.Oruall', '17-09-1945', 'in stock', 73.00, 9783164484233),
('ThreeMeninABoat(ToSayNothingOfTheDog)', 'D.Dgerom', '13-07-1889', 'in stock', 90.00, 9783164484244),
('Uliss', 'I.Achlabustin', '26-08-2015', 'in stock', 36.00, 9783161484255),
('Do Androids Dream of Electric Sheep?', 'P.Dick', '03-06-1968', 'in stock', 9.80, 9783164484266),
('Pineapple water for beautiful ladies', 'V.Pelevin', '25-11-2010', 'in stock', 12.20, 9783164484300),
('An Occurrence at Owl Creek Bridge', 'A.Birs', '05-06-1890', 'in stock', 24.30, 9783164484311),
('To Kill a Mockingbird', 'H.Li', '11-06-1960', 'in stock', 110.80, 9783164484333),
('What Doesn’t Kill Us', 'S.Carney', '20-11-2011', 'in stock', 26.00, 9783164484344),
('Sleeping Beauties', 'S.King', '01-09-2017', 'in stock', 45.70, 9783164484355),
('In search of the lost orpheus', 'A.Lurie', '04-04-1912', 'in stock', 84.30, 9783164484366);

UPDATE books SET isbn = '978-3-16-148410-0' WHERE id = 73;
UPDATE books SET isbn = '978-3-16-148410-1' WHERE id = 74;
UPDATE books SET isbn = '978-3-16-148410-2' WHERE id = 75;
UPDATE books SET isbn = '978-3-16-148410-3' WHERE id = 76;
UPDATE books SET isbn = '978-3-16-148410-4' WHERE id = 77;
UPDATE books SET isbn = '978-3-16-148410-5' WHERE id = 78;
UPDATE books SET isbn = '978-3-16-148410-6' WHERE id = 79;
UPDATE books SET isbn = '978-3-16-148411-0' WHERE id = 80;
UPDATE books SET isbn = '978-3-16-148411-1' WHERE id = 81;
UPDATE books SET isbn = '978-3-16-148411-2' WHERE id = 82;
UPDATE books SET isbn = '978-3-16-148411-3' WHERE id = 83;
UPDATE books SET isbn = '978-3-16-148411-4' WHERE id = 84;
UPDATE books SET isbn = '978-3-16-148411-5' WHERE id = 85;
UPDATE books SET isbn = '978-3-16-148411-6' WHERE id = 86;
UPDATE books SET isbn = '978-3-16-148412-0' WHERE id = 87;
UPDATE books SET isbn = '978-3-16-148412-1' WHERE id = 88;
UPDATE books SET isbn = '978-3-16-148412-2' WHERE id = 89;
UPDATE books SET isbn = '978-3-16-148412-3' WHERE id = 90;
UPDATE books SET isbn = '978-3-16-148412-4' WHERE id = 91;
UPDATE books SET isbn = '978-3-16-148412-5' WHERE id = 92;

DELETE FROM books WHERE id BETWEEN 94 AND 107; 

UPDATE books SET status = 'IN_STOCK' WHERE ID BETWEEN 73 AND 92;

CREATE TYPE status_book AS ENUM ('IN_STOCK', 'SOLD', 'RESERVE', 'DELIVERY_EXPECTED', 'OUT_OF_STOCK');

ALTER TABLE books ALTER COLUMN status TYPE status_book USING 'IN_STOCK';

ALTER TABLE books ALTER COLUMN status TYPE CHARACTER VARYING (30);


CREATE TABLE status(
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 num                 INTEGER NOT NULL,
 status_name         CHARACTER VARYING (60),
);

