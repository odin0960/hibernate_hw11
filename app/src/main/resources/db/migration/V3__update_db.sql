DELETE FROM client WHERE id > 10;

INSERT INTO ticket (created_at, client_id, from_planet_id, to_planet_id)
VALUES
	('2023-06-22 12:59:46',1,'VEN02','EAR03'),
	('2023-06-17 12:59:46',10,'EAR03','MAR04'),
	('2023-06-12 12:59:46',3,'MAR04','NEP08'),
	('2023-06-07 12:59:46',7,'EAR03','MER01'),
	('2023-06-02 12:59:46',5,'NEP08','JUP05'),
	('2023-05-28 12:59:46',10,'VEN02','EAR03');