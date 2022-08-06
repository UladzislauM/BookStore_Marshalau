CREATE TABLE status(
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 status_name         CHARACTER VARYING (60)
);

INSERT INTO status (status_name)
VALUES ('IN_STOCK'),
   ('SOLD'),
   ('RESERVE'),
   ('DELIVERY_EXPECTED'),
   ('OUT_OF_STOCK');
   
CREATE TABLE books(
 id                  BIGSERIAL PRIMARY KEY NOT NULL,
 title               CHARACTER VARYING (80) NOT NULL,
 name_Author         CHARACTER VARYING (60),
 date_Release_Book   DATE NOT null,
 price               double PRECISION,
 isbn                char(17) unique
);

INSERT INTO books (title, name_author, date_release_book, status, price, isbn) 
VALUES ('7Navikov', 'S.Kovy', '25-12-1991', 32.00, '978-3-16-148410-0'),
('GrafMonte', 'A.Dyma', '18-10-1896', 23.00, '978-3-16-148410-1'),
('GunsSteelAndGerms', 'D.Diamond', '17-06-2003', 44.00, '978-3-16-148410-2'),
('TheSubtleArtOfNotGivingAF', 'M.Mancon', '13-09-2016', 65.00, '978-3-16-148410-3'),
('SmertIvanaIlicha', 'L.Tolstoy', '05-11-1886', 35.00, '978-3-16-148410-4'),
('KamoGryadeshi', 'G.Synkevich', '14-05-1896', 73.00, '978-3-16-148410-5'),
('HoreOtUma', 'A.Griboedov', '24-08-1824', 59.00, '978-3-16-148410-6'),
('Neirofitnes', 'R.Djandial', '23-02-2019', 31.00, '978-3-16-148411-0'),
('JiznBezGranic', 'N.Vuitich', '20-04-2010', 77.00, '978-3-16-148411-1'),
('1984', 'D.Oruall', '28-05-1949', 11.00, '978-3-16-148411-2'),
('AnimalFarm', 'D.Oruall', '17-09-1945', 73.00, '978-3-16-148411-3'),
('ThreeMeninABoat(ToSayNothingOfTheDog)', 'D.Dgerom', '13-07-1889', 90.00, '978-3-16-148411-4'),
('Uliss', 'I.Achlabustin', '26-08-2015', 36.00, '978-3-16-148411-5'),
('Do Androids Dream of Electric Sheep?', 'P.Dick', '03-06-1968', 9.80, '978-3-16-148411-6'),
('Pineapple water for beautiful ladies', 'V.Pelevin', '25-11-2010', 12.20, '978-3-16-148412-0'),
('An Occurrence at Owl Creek Bridge', 'A.Birs', '05-06-1890', 24.30, '978-3-16-148412-1'),
('To Kill a Mockingbird', 'H.Li', '11-06-1960', 110.80, '978-3-16-148412-2'),
('What Doesn’t Kill Us', 'S.Carney', '20-11-2011', 26.00, '978-3-16-148412-3'),
('Sleeping Beauties', 'S.King', '01-09-2017', 45.70, '978-3-16-148412-4'),
('In search of the lost orpheus', 'A.Lurie', '04-04-1912', 84.30, '978-3-16-148412-5');


ALTER TABLE books 
ADD status_id BIGINT  
REFERENCES status(id);

UPDATE books 
SET status_id = (SELECT Id FROM status WHERE status_name = 'SOLD') 
WHERE Id=1;

SELECT books.title, books.name_author, books.date_release_book, books.price, books.isbn, status.status_name 
FROM books JOIN status 
ON books.status_id = status.Id;

