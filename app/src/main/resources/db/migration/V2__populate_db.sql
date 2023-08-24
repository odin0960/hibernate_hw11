INSERT INTO client (name)
VALUES
	('Ivan Petrenko'),
	('Semen Semenchenko'),
	('Petro Ivanov'),
	('Alex Petrov'),
	('Oleg Gerasimenko'),
	('Andriy Shevchenko'),
	('Vlad Vaschuk'),
	('Serhiy Petrenko'),
	('Denys Popov'),
	('Oleksandr Karavayev'),
	('Nazar Voloshyn'),
	('Heorhiy Bushchan');


INSERT INTO planet (id, name)
VALUES
    ( 'MER01', 'Mercury'),
    ( 'VEN02', 'Venus'),
    ( 'EAR03', 'Earth'),
    ( 'MAR04', 'Mars'),
    ( 'JUP05', 'Jupiter'),
    ( 'SAT06', 'Saturn'),
    ( 'URA07', 'Uranus'),
    ( 'NEP08', 'Neptune');


INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id)
VALUES
	('2023-08-09 17:36:11',1,'EAR03','JUP05'),
	('2023-08-04 17:36:11',2,'MAR04','EAR03'),
	('2023-07-30 17:36:11',3,'NEP08','MER01'),
	('2023-07-25 17:36:11',4,'URA07','JUP05'),
	('2023-07-20 17:36:11',5,'MER01','URA07'),
	('2023-07-15 17:36:11',6,'MER01','SAT06'),
	('2023-07-10 17:36:11',7,'EAR03','MER01'),
	('2023-07-05 17:36:11',8,'JUP05','EAR03'),
	('2023-06-30 17:36:11',9,'SAT06','MAR04'),
	('2023-06-25 17:36:11',10,'NEP08','MER01'),
	('2023-06-20 17:36:11',11,'VEN02','EAR03'),
	('2023-06-15 17:36:11',12,'EAR03','MAR04');

