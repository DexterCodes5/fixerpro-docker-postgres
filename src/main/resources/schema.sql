--- SECURITY TABLES ---
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

-- PRODUCTS TABELS --

DROP TABLE IF EXISTS engine_oil;

CREATE TABLE engine_oil(
	id bigserial NOT NULL PRIMARY KEY,
    title varchar(100) NOT NULL,
    thumbnail varchar(100) NOT NULL,
    liters varchar(100) NOT NULL,
    price varchar(100) NOT NULL
);

INSERT INTO engine_oil(title, thumbnail, liters, price) VALUES
('CASTROL EDGE TURBO DIESEL TITANIUM 5W-40 5L', '/thumbnails/5l/castrol-edge-turbodiesel-5w40-5l.jpg', 5, '84.90'),
('CASTROL EDGE 5W-30 LL 5L', '/thumbnails/5l/castrol-edge-5w-30-ll-5l.jpg', 5, '94.90'),
('MOTUL 6100 SYNERGIE+ 10W-40 5L', '/thumbnails/5l/motul-6100-synergie-10w-40-5l.jpg', 5, '78.56'),
('CASTROL MAGNATEC DIESEL 10W-40 B4 5L', '/thumbnails/5l/castrol-magnatec-diesel-10w40-5l.jpg', 5, '75.90'),
('CASTROL MAGNATEC DIESEL 5W-40 DPF 5L', '/thumbnails/5l/castrol-magnatec-diesel-5w-40-dpf-5l.jpg', 5, '79.90'),
('CASTROL EDGE 5W-30 M 5L', '/thumbnails/5l/castrol-edge-5w-30-5l.jpg', 5, '94.90'),
('MOTUL 8100 X-CESS 5W-40 5L', '/thumbnails/5l/motul-8100-x-cess-5w-40-5l.jpg', 5, '89.82'),
('CASTROL MAGNATEC 10W-40 A3/B4 5L', '/thumbnails/5l/castrol-magnatec-10w-40-5l.jpg', 5, '75.90'),
('CASTROL MAGNATEC 5W-40 C3 5L', '/thumbnails/5l/castrol-magnatec-5w40-c3-5l.jpg', 5, '79.90'),
('MOBIL SUPER 3000 X1 5W-40 5L', '/thumbnails/5l/mobil-super-3000-x1-5w-40-5l.jpg', 5, '85.90'),
('MOBIL SUPER 2000 X1 10W-40 5L', '/thumbnails/5l/mobil-super-2000-x1-10w-40-5l.jpg', 5, '55.90'),
('CASTROL EDGE FST TITANIUM 5W-40 5L', '/thumbnails/5l/castrol-edge-fst-titanium-5w-40-5l.jpg', 5, '95.90'),
('MOBIL ESP FORMULA 5W-30 5L', '/thumbnails/5l/mobil-esp-formula-5w-30-5l.jpg', 5, '119.90'),
('MOTUL 8100 X-CLEAN 5W-40 5L', '/thumbnails/5l/motul-8100-x-clean-5w-40-5l.jpg', 5, '105.29'),
('MOTUL 8100 X-CLEAN EFE 5W-30 5L', '/thumbnails/5l/motul-8100-x-clean-fe-5w30-5l.jpg', 5, '102.77'),
('MOTUL SPECIFIC CNG/LPG 5W-40 5L', '/thumbnails/5l/motul-specific-cng-lpg-5w-40-5l.jpg', 5, '107.81'),
('CASTROL MAGNATEC STOP-START 5W-30 A5 5L', '/thumbnails/5l/castrol-magnatec-5w-30-a5-5l.jpg', 5, '89.90'),
('MOTUL SPECIFIC VW 505.01/502.00 5W-40 5L', '/thumbnails/5l/motul-specific-vw-50501-5w-40-5l.jpg', 5, '103.77'),
('MOTUL 6100 SYNERGIE+ 10W-40 5+1L', '/thumbnails/5l/motul-6100-synergie-10w-40-51l.jpg', 5, '97.70'),
('MOTUL 8100 ECO-CLEAN 5W-30 5L', '/thumbnails/5l/motul-8100-eco-clean-5w-30-5l.jpg', 5, '112.55'),
('CASTROL EDGE 0W-30 5L', '/thumbnails/5l/castrol-edge-0w-30-5l.jpg', 5, '115.90'),
('MOBIL SUPER 3000 XE 5W-30 5L', '/thumbnails/5l/mobil-super-3000-xe-5w-30-5l.jpg', 5, '89.90'),
('MOBIL 1 FS 0W40 5L', '/thumbnails/5l/mobil-1-new-life-0w-40-5l.jpg', 5, '124.90'),
('MOTUL 8100 X-CLEAN+ 5W-30 5L', '/thumbnails/5l/motul-8100-x-clean-plus-5w30-5l.jpg', 5, '118.31'),
('MOTUL 4100 POWER 15W-50 5L', '/thumbnails/5l/motul-4100-power-15w-50-5l.jpg', 5, '88.34'),
('MOTUL SPECIFIC VW 504.00/507.00 5W-30 5L', '/thumbnails/5l/motul-specific-vw-5040050700-5w-30-5l.jpg', 5, '118.49'),
('MOTUL SUBARU C2 5W-30 5L', '/thumbnails/5l/motul-subaru-c2-5w30-5l.jpg', 5, '134.10'),
('MOTUL 8100 ECO-CLEAN 0W-30 5L', '/thumbnails/5l/motul-8100-eco-clean-0w30-5l.jpg', 5, '136.57'),
('CASTROL EDGE 10W-60 5L', '/thumbnails/5l/castrol-edge-10w-60-5l.jpg', 5, '126.90'),
('MOTUL SPECIFIC 913D 5W-30 5L', '/thumbnails/5l/motul-specific-913d-5w-30-5l.jpg', 5, '97.07'),
('MOTUL 8100 X-CESS GEN2 5W-40 5L', '/thumbnails/5l/motul-8100-x-cess-gen2-5w-40-5l.jpg', 5, '92.58'),
('CASTROL EDGE TURBO DIESEL 5W-40 1L', '/thumbnails/1l/castrol-edge-turbodiesel-5w40-1l.jpg', 1, '21.90'),
('CASTROL EDGE 5W-30 M 1L', '/thumbnails/1l/castrol-edge-5w30-1l.jpg', 1, '23.90'),
('CASTROL EDGE 5W-30 LL 1L', '/thumbnails/1l/castrol-edge-5w-30-ll-1l.jpg', 1, '23.90'),
('CASTROL MAGNATEC DIESEL 5W-40 DPF 1L', '/thumbnails/1l/castrol-mag-diesel-5w40-1ldpf.jpg', 1, '18.90'),
('MOTUL 8100 X-CESS 5W-40 1L', '/thumbnails/1l/motul-8100-x-cess-5w-40-1l.jpg', 1, '22.07'),
('CASTROL MAGNATEC DIESEL 10W-40 B4 1L', '/thumbnails/1l/castrol-magnatec-diesel-10w40-1l.jpg', 1, '17.90'),
('MOBIL SUPER 3000 X1 5W-40 1L', '/thumbnails/1l/mobil-super-3000-x1-5w-40-1l.jpg', 1, '17.90'),
('CASTROL MAGNATEC 5W-40 C3 1L', '/thumbnails/1l/castrol-magnatec-5w-40-c3-1l.jpg', 1, '18.90'),
('CASTROL EDGE FST TITANIUM 5W-40 1L', '/thumbnails/1l/castrol-edge-fst-titanium-5w-40-1l.jpg', 1, '23.90'),
('CASTROL MAGNATEC 10W-40 A3/B4 1L', '/thumbnails/1l/castrol-magn10w40-1l.jpg', 1, '17.90'),
('MOTUL 6100 SYNERGIE+ 10W-40 1L', '/thumbnails/1l/motul-6100-synergie-10w-40-1l.jpg', 1, '19.14'),
('MOTUL MOTYLGEAR 75W-90 1L', '/thumbnails/1l/motul-motylgear-75w-90-1l.jpg', 1, '23.94'),
('MOBIL ESP FORMULA 5W-30 1L', '/thumbnails/1l/mobil-esp-formula-5w-30-1l.jpg', 1, '25.90'),
('CASTROL EDGE 0W-30 1L', '/thumbnails/1l/castrol-edge-0w-30-1l.jpg', 1, '25.90'),
('MOBIL SUPER 2000 X1 10W-40 1L', '/thumbnails/1l/mobil-super-2000-x1-10w-40-1l.jpg', 1, '12.90'),
('CASTROL 2T 1L Moto', '/thumbnails/1l/castrol-2t-1l.jpg', 1, '15.90'),
('MOTUL 8100 X-CLEAN 5W-40 1L', '/thumbnails/1l/motul-8100-x-clean-5w-40-1l.jpg', 1, '25.74'),
('CASTROL TRANSMAX LS 75W-140 LL 1L', '/thumbnails/1l/castrol-syntrax-ls-75w-140-1l.jpg', 1, '39.90'),
('CASTROL MAGNATEC STOP-START 5W-30 A5 1L', '/thumbnails/1l/castrol-magnatec-5w-30-a5-1l.jpg', 1, '21.90'),
('MOTUL 8100 ECO-CLEAN 5W-30 1L', '/thumbnails/1l/motul-8100-eco-clean-5w-30-1l.jpg', 1, '27.58'),
('MOBIL 1 FS 0W40 1L', '/thumbnails/1l/mobil-1-new-life-0w-40-1l.jpg', 1, '29.90'),
('MOBIL SUPER 3000 XE 5W-30 1L', '/thumbnails/1l/mobil-super-3000-xe-5w-30-1l.jpg', 1, '19.90'),
('MOTUL 8100 X-CLEAN+ 5W-30 1L', '/thumbnails/1l/motul-8100-x-clean-plus-5w-30-1l.jpg', 1, '27.92'),
('MOTUL 8100 X-CLEAN EFE 5W-30 1L', '/thumbnails/1l/motul-8100-x-clean-fe-5w-30-1l.jpg', 1, '23.87'),
('CASTROL EDGE PROFESSIONAL LONGLIFE III 5W-30 1L', '/thumbnails/1l/castrol-edge-professional-longlife-iii-5w30-1l.jpg', 1, '24.90'),
('MOTUL SCOOTER EXPERT T4 10W-40 1L Moto', '/thumbnails/1l/motul-scooter-exp-t4-10w-40-1l.jpg', 1, '22.32'),
('CASTROL EDGE 10W-60 1L', '/thumbnails/1l/castrol-edge-10w-60-1l.jpg', 1, '29.90'),
('MOTUL MULTI ATF 1L', '/thumbnails/1l/motul-multi-atf-1l.jpg', 1, '27.76'),
('MOTUL GEARBOX 80W-90 1L', '/thumbnails/1l/motul-gearbox-80w-90-1l.jpg', 1, '28.08'),
('MOTUL 8100 ECO-CLEAN 0W-30 1L', '/thumbnails/1l/motul-8100-eco-clean-0w-30-1l.jpg', 1, '30.08'),
('MOTUL SPECIFIC CNG/LPG 5W-40 1L', '/thumbnails/1l/motul-specific-cng-lpg-5w-40-1l.jpg', 1, '24.77'),
('CASTROL POWER 1 10W-40 4T 1L Moto', '/thumbnails/1l/castrol-power-1-4t-10w-40-1l.jpg', 1, '23.90');

DROP TABLE IF EXISTS filter;
 
CREATE TABLE filter(
	id bigserial NOT NULL,
    type varchar(30) NOT NULL,
    brand varchar(30) NOT NULL,
    number varchar(30) NOT NULL,
    compatible_cars text NOT NULL,
    thumbnail varchar(100) NOT NULL,
    price varchar(10) NOT NULL
);

INSERT INTO filter(type, brand, number, compatible_cars, thumbnail, price) VALUES 
('oil-filter', 'BOSCH',  '1 457 437 003', '["bmw-3-e46", "bmw-5-e39"]', '/thumbnails/filters/filter-bosch1.jpg', '16.21'),
('oil-filter', 'MANN-FILTER',  'HU 925/4 X', '["bmw-3-e46", "bmw-5-e39"]', '/thumbnails/filters/filter-mann1.jpg', '23.92'),
('air-filter', 'BOSCH',  '1 457 433 004', '["bmw-3-e46", "bmw-5-e39"]', '/thumbnails/filters/filter-bosch2.jpg', '21.94'),
('air-filter', 'MANN-FILTER',  'C 25 114/1', '["bmw-3-e46", "bmw-5-e39"]', '/thumbnails/filters/filter-mann2.jpg', '40.58'),
('fuel-filter', 'MANN-FILTER',  'WK 516/1', '["bmw-3-e46"]', '/thumbnails/filters/filter-mann3.jpg', '55.67'),
('fuel-filter', 'BOSCH',  '0 450 905 952', '["bmw-3-e46"]', '/thumbnails/filters/filter-bosch3.jpg', '109.63'),
('fuel-filter', 'BOSCH',  '0 450 905 960', '["bmw-5-e39"]', '/thumbnails/filters/filter-bosch4.jpg', '193.00'),
('fuel-filter', 'MANN-FILTER',  'WK 532', '["bmw-5-e39"]', '/thumbnails/filters/filter-mann4.jpg', '234.28'),
('oil-filter', 'BOSCH',  '1 457 429 141', '["bmw-7-e38"]', '/thumbnails/filters/filter-bosch5.jpg', '23.94'),
('oil-filter', 'MANN-FILTER',  'HU 938/4 X', '["bmw-7-e38"]', '/thumbnails/filters/filter-mann5.jpg', '29.33'),
('air-filter', 'BOSCH',  '1 457 433 698', '["bmw-7-e38"]', '/thumbnails/filters/filter-bosch6.jpg', '21.36'),
('air-filter', 'MANN-FILTER',  'C 26 151', '["bmw-7-e38"]', '/thumbnails/filters/filter-mann6.jpg', '30.89'),
('fuel-filter', 'BOSCH',  '0 450 905 905', '["bmw-7-e38"]', '/thumbnails/filters/filter-bosch7.jpg', '43.88'),
('fuel-filter', 'MANN-FILTER',  'WK 516/1', '["bmw-7-e38"]', '/thumbnails/filters/filter-mann7.jpg', '55.67'),
('pollen-filter', 'BOSCH',  '1 987 432 362', '["bmw-7-e38"]', '/thumbnails/filters/filter-bosch8.jpg', '119.80'),
('pollen-filter', 'MANN-FILTER',  'CU 3642-2', '["bmw-7-e38"]', '/thumbnails/filters/filter-mann8.jpg', '140.58'),
('pollen-filter', 'BOSCH',  '1 987 432 036', '["bmw-3-e46"]', '/thumbnails/filters/filter-bosch9.jpg', '36.54'),
('pollen-filter', 'MANN-FILTER',  'CU 6724', '["bmw-3-e46"]', '/thumbnails/filters/filter-mann9.jpg', '43.18'),
('pollen-filter', 'BOSCH',  '1 987 432 361', '["bmw-5-e39"]', '/thumbnails/filters/filter-bosch10.jpg', '69.61'),
('pollen-filter', 'MANN-FILTER',  'CU 2736-2', '["bmw-5-e39"]', '/thumbnails/filters/filter-mann10.jpg', '106.50'),
('oil-filter', 'BOSCH',  '1 457 429 261', '["mercedes-benz-c-class-w203", "mercedes-benz-e-class-w210", "mercedes-benz-s-class-w220"]', '/thumbnails/filters/filter-bosch11.jpg', '19.90'),
('oil-filter', 'MANN-FILTER',  'HU 718/5 X', '["mercedes-benz-c-class-w203", "mercedes-benz-e-class-w210", "mercedes-benz-s-class-w220"]', '/thumbnails/filters/filter-mann11.jpg', '31.92'),
('air-filter', 'BOSCH',  '1 457 433 071', '["mercedes-benz-c-class-w203", "mercedes-benz-s-class-w220"]', '/thumbnails/filters/filter-bosch12.jpg', '66.91'),
('air-filter', 'MANN-FILTER ',  'C 3698/3-2', '["mercedes-benz-c-class-w203", "mercedes-benz-s-class-w220"]', '/thumbnails/filters/filter-mann12.jpg', '72.13'),
('fuel-filter', 'MANN-FILTER ',  'WK 720', '["mercedes-benz-c-class-w203", "mercedes-benz-e-class-w210", "mercedes-benz-s-class-w220"]', '/thumbnails/filters/filter-mann13.jpg', '31.58'),
('fuel-filter', 'BOSCH',  '0 450 915 003', '["mercedes-benz-c-class-w203", "mercedes-benz-e-class-w210", "mercedes-benz-s-class-w220"]', '/thumbnails/filters/filter-bosch13.jpg', '135.11'),
('pollen-filter', 'BOSCH',  '1 987 432 070', '["mercedes-benz-c-class-w203"]', '/thumbnails/filters/filter-bosch14.jpg', '26.12'),
('pollen-filter', 'MANN-FILTER ',  'CU 3461/1', '["mercedes-benz-c-class-w203"]', '/thumbnails/filters/filter-mann14.jpg', '28.75'),
('air-filter', 'BOSCH',  '1 457 433 699', '["mercedes-benz-e-class-w210"]', '/thumbnails/filters/filter-bosch15.jpg', '25.86'),
('air-filter', 'MANN-FILTER ',  'C 34 175', '["mercedes-benz-e-class-w210"]', '/thumbnails/filters/filter-mann15.jpg', '36.89'),
('pollen-filter', 'BOSCH',  '1 987 432 020', '["mercedes-benz-e-class-w210", "mercedes-benz-s-class-w220"]', '/thumbnails/filters/filter-bosch16.jpg', '29.09'),
('pollen-filter', 'MANN-FILTER ',  'CU 2897', '["mercedes-benz-e-class-w210"]', '/thumbnails/filters/filter-mann16.jpg', '38.92'),
('pollen-filter', 'MANN-FILTER ',  'CU 2745-2', '["mercedes-benz-s-class-w220"]', '/thumbnails/filters/filter-mann17.jpg', '80.36'),
('oil-filter', 'BOSCH',  '0 451 103 314', '["vw-golf-iv", "vw-bora", "vw-passat-b5"]', '/thumbnails/filters/filter-bosch17.jpg', '13.36'),
('oil-filter', 'MANN-FILTER',  'W 719/30', '["vw-golf-iv", "vw-bora", "vw-passat-b5"]', '/thumbnails/filters/filter-mann18.jpg', '22.31'),
('air-filter', 'BOSCH',  '1 457 433 714', '["vw-golf-iv", "vw-bora"]', '/thumbnails/filters/filter-bosch18.jpg', '22.22'),
('air-filter', 'MANN-FILTER',  'C 37 153', '["vw-golf-iv", "vw-bora"]', '/thumbnails/filters/filter-mann19.jpg', '30.80'),
('fuel-filter', 'MANN-FILTER',  'WK 730/1', '["vw-golf-iv", "vw-bora"]', '/thumbnails/filters/filter-mann20.jpg', '25.31'),
('fuel-filter', 'BOSCH',  '0 450 905 318', '["vw-golf-iv", "vw-bora"]', '/thumbnails/filters/filter-bosch19.jpg', '32.56'),
('pollen-filter', 'MANN-FILTER',  'CUK 2862', '["vw-golf-iv", "vw-bora"]', '/thumbnails/filters/filter-mann21.jpg', '41.53'),
('pollen-filter', 'BOSCH',  '0 986 628 509', '["vw-golf-iv", "vw-bora"]', '/thumbnails/filters/filter-bosch20.jpg', '45.55'),
('air-filter', 'BOSCH',  '1 457 429 870', '["vw-passat-b5"]', '/thumbnails/filters/filter-bosch21.jpg', '21.76'),
('air-filter', 'MANN-FILTER',  'C 26 168', '["vw-passat-b5"]', '/thumbnails/filters/filter-mann22.jpg', '28.51'),
('fuel-filter', 'BOSCH',  '0 450 905 264', '["vw-passat-b5"]', '/thumbnails/filters/filter-bosch22.jpg', '29.09'),
('fuel-filter', 'MANN-FILTER',  'WK 830/7', '["vw-passat-b5"]', '/thumbnails/filters/filter-mann23.jpg', '29.24'),
('pollen-filter', 'BOSCH',  '1 987 432 012', '["vw-passat-b5"]', '/thumbnails/filters/filter-bosch23.jpg', '13.64'),
('pollen-filter', 'MANN-FILTER',  'CU 2882', '["vw-passat-b5"]', '/thumbnails/filters/filter-mann24.jpg', '22.37');

DROP TABLE IF EXISTS customer_order;

CREATE TABLE customer_order(
	id bigserial NOT NULL,
    name_and_surname varchar(100) NOT NULL,
    telephone varchar(100) NOT NULL,
    delivery_method varchar(100) NOT NULL,
    address varchar(100) NOT NULL,
    comment varchar(100) NOT NULL,
    total float NOT NULL,
    products text NOT NULL
);