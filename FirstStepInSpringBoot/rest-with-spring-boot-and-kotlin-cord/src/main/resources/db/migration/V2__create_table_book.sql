create table book(
	id Int auto_increment primary key,
    name varchar(255) not null,
    price decimal(10,2) not null,
    status varchar(255) not null,
    customer_id Int not null,
    FOREIGN KEY(customer_id) REFERENCES customer(id)

);