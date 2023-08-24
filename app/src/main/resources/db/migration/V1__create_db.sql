create TABLE client (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR (200) NOT NULL CHECK (CHAR_LENGTH(name) >= 3)
);

create TABLE planet (
	id VARCHAR (100) PRIMARY KEY NOT NULL CHECK REGEXP_LIKE (id, '^[A-Z0-9]+$'),
	name VARCHAR (500) NOT NULL
);

create TABLE ticket (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	client_id BIGINT NOT NULL,
	from_planet_id VARCHAR (100) NOT NULL CHECK REGEXP_LIKE (id, '^[A-Z0-9]+$'),
	to_planet_id VARCHAR (100) NOT NULL CHECK REGEXP_LIKE (id, '^[A-Z0-9]+$'),
    FOREIGN KEY (to_planet_id) REFERENCES planet(id) ON DELETE CASCADE,
    FOREIGN KEY (from_planet_id) REFERENCES planet(id) ON DELETE CASCADE,
	FOREIGN KEY (client_id) REFERENCES client(id),
	CHECK (from_planet_id <> to_planet_id)
);