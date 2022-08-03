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