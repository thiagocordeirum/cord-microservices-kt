create table customer(
	id Int auto_increment primary key,
    name varchar(255) not null,
    email varchar(255) not null unique

);