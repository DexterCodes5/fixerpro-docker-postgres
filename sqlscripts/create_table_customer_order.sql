USE fixerpro; 

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