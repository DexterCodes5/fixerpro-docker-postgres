USE fixerpro;

DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS accounts;

CREATE TABLE accounts (
	username varchar(50) NOT NULL PRIMARY KEY,
    password char(60) DEFAULT NULL,
    enabled boolean NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
    hash_code int DEFAULT NULL,
    provider varchar(15) NOT NULL
);

INSERT INTO accounts VALUES
('dexter', '$2a$12$Baj5s7zDjOfMXyHRYWmJTONJuOdAiw9jofeJWbDCk/wqhaKtXYEYi', true, 'dexter@gmail.com', 1, 'LOCAL'),
('john', '$2a$12$Baj5s7zDjOfMXyHRYWmJTONJuOdAiw9jofeJWbDCk/wqhaKtXYEYi', true, 'john@gmail.com', 1, 'LOCAL');

CREATE TABLE authorities (
	username varchar(50) NOT NULL,
    authority varchar(50) NOT NULL,
    UNIQUE (username, authority),
    CONSTRAINT authorities_fk FOREIGN KEY (username) REFERENCES accounts (username)
);

INSERT INTO authorities VALUES
('dexter', 'ROLE_CLIENT'),
('dexter', 'ROLE_ADMIN'),
('john', 'ROLE_CLIENT');