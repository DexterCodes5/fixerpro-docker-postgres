USE fixerpro;

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