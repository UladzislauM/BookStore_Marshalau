INSERT INTO status (status_name)
VALUES ('IN_STOCK'),
    ('SOLD'),
    ('RESERVE'),
    ('DELIVERY_EXPECTED'),
    ('OUT_OF_STOCK');

INSERT INTO books (title, name_author, date_release_book, price, isbn, status_id)
VALUES ('7Navikov', 'S.Kovy', '1991-12-25', 32.00, '978-3-16-148410-0', (SELECT Id FROM status WHERE status_name = 'SOLD')),
    ('GrafMonte', 'A.Dyma', '1896-10-18', 23.00, '978-3-16-148410-1', (SELECT Id FROM status WHERE status_name = 'SOLD')),
    ('GunsSteelAndGerms', 'D.Diamond', '2003-06-17', 44.00, '978-3-16-148410-2', (SELECT Id FROM status WHERE status_name = 'SOLD')),
    ('TheSubtleArtOfNotGivingAF', 'M.Mancon', '2016-09-13', 65.00, '978-3-16-148410-3', (SELECT Id FROM status WHERE status_name = 'SOLD')),
    ('SmertIvanaIlicha', 'L.Tolstoy', '1886-11-05', 35.00, '978-3-16-148410-4', (SELECT Id FROM status WHERE status_name = 'SOLD')),
    ('KamoGryadeshi', 'G.Synkevich', '1896-05-14', 73.00, '978-3-16-148410-5', (SELECT Id FROM status WHERE status_name = 'SOLD')),
    ('HoreOtUma', 'A.Griboedov', '1824-08-24', 59.00, '978-3-16-148410-6', (SELECT Id FROM status WHERE status_name = 'SOLD')),
    ('Neirofitnes', 'R.Djandial', '2019-02-23', 31.00, '978-3-16-148411-0', (SELECT Id FROM status WHERE status_name = 'SOLD')),
    ('JiznBezGranic', 'N.Vuitich', '2010-04-20', 77.00, '978-3-16-148411-1', (SELECT Id FROM status WHERE status_name = 'SOLD')),
    ('1984', 'D.Oruall', '1949-05-28', 11.00, '978-3-16-148411-2', (SELECT Id FROM status WHERE status_name = 'SOLD')),
    ('AnimalFarm', 'D.Oruall', '1945-09-17', 73.00, '978-3-16-148411-3', (SELECT Id FROM status WHERE status_name = 'IN_STOCK')),
    ('ThreeMeninABoat(ToSayNothingOfTheDog)', 'D.Dgerom', '1889-07-13', 90.00, '978-3-16-148411-4', (SELECT Id FROM status WHERE status_name = 'IN_STOCK')),
    ('Uliss', 'I.Achlabustin', '2015-08-26', 36.00, '978-3-16-148411-5', (SELECT Id FROM status WHERE status_name = 'IN_STOCK')),
    ('Do Androids Dream of Electric Sheep?', 'P.Dick', '1968-06-03', 9.80, '978-3-16-148411-6', (SELECT Id FROM status WHERE status_name = 'IN_STOCK')),
    ('Pineapple water for beautiful ladies', 'V.Pelevin', '2010-11-25', 12.20, '978-3-16-148412-0', (SELECT Id FROM status WHERE status_name = 'IN_STOCK')),
    ('An Occurrence at Owl Creek Bridge', 'A.Birs', '1890-06-05', 24.30, '978-3-16-148412-1', (SELECT Id FROM status WHERE status_name = 'IN_STOCK')),
    ('To Kill a Mockingbird', 'H.Li', '1960-06-11', 110.80, '978-3-16-148412-2', (SELECT Id FROM status WHERE status_name = 'IN_STOCK')),
    ('What Doesn’t Kill Us', 'S.Carney', '2011-11-20', 26.00, '978-3-16-148412-3', (SELECT Id FROM status WHERE status_name = 'IN_STOCK')),
    ('Sleeping Beauties', 'S.King', '2017-09-01', 45.70, '978-3-16-148412-4', (SELECT Id FROM status WHERE status_name = 'IN_STOCK')),
    ('In search of the lost orpheus', 'A.Lurie', '1912-04-04', 84.30, '978-3-16-148412-5', (SELECT Id FROM status WHERE status_name = 'IN_STOCK'));

INSERT INTO role(role_name)
VALUES ('ADMIN'),
    ('USER'),
    ('MANAGER');

INSERT INTO users(name, last_name, email, password, role_id)
VALUES ('Yauheni', 'Hlaholeu', 'jek94@gmail.com', '12qwaszx', (SELECT Id FROM role WHERE role_name = 'ADMIN')),
    ('Uladzislau', 'Solovev', 'sol44@yandex.by', 'qazxsw21', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Haliana', 'Sidoric', 'galina_sid@gmail.com', 'sid93LL', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Lana', 'Dimidova', 'dlana@mail.ru', 'vfAz1234', (SELECT Id FROM role WHERE role_name = 'MANAGER')),
    ('Andrey', 'Aksenov', 'AKsin@Gmail.com','12345678OOp', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Nazar', 'Vahtongov', 'vagan@mail.ru', '333eeeddfd', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Tatyana', 'Minikova', 'tMin@tut.by', 'trewrg', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Michail', 'Marshalau', 'Mix2020@rambler.by', 'hgnboenoenernv', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Francs', 'Nikiforof', 'rdko@mail.ru', 'roinrv', (SELECT Id FROM role WHERE role_name = 'MANAGER')),
    ('Adi', 'Huseinov', 'gitler21@gmail.com', 'tirmid', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Kristafor', 'Djigurda', 'Americo1789@mail.ru', '534rrr', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Haliana', 'Dombrouskaya', 'hali-gali@yahoo.com', 'ddffgg445566', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Katserina', 'Mudalovich', 'gali@moli.ru', 'irjfncv', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Yauheni', 'Jimolost', 'tolick@rambler.ru', 'fkfldmc.', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Marck', 'Shagal', 'Markusik@rambler.ru', 'wertyuhgf', (SELECT Id FROM role WHERE role_name = 'MANAGER')),
    ('Ivan', 'Mandelshtamm', 'manid@Gmail.com', '324rewf', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('IoganSebostian', 'Bah', 'bahbah-12@mail.ru', '45tfe', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Pavel', 'Krishtofsky', 'pashok98@mail.ru', 'rtyjgf', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Artemiy', 'Potrahunchik', 'temavsem424@Gmail.com', '4567876543', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Vlad', 'Topalov', 'topal34@mail.ru', '345ygf', (SELECT Id FROM role WHERE role_name = 'USER')),
    ('Vlad', 'Marshalau', 'rigfd2020@rambler.by', 'srdfhgjthr', (SELECT id FROM role WHERE role_name = 'ADMIN'));