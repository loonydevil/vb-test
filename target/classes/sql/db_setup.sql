create database acme;

use acme;

drop table if exists country;
drop table if exists address;
drop table if exists customer;
drop table if exists invoice_type;
drop table if exists invoice;

create table country (
    id int not null auto_increment primary key,
    name varchar(50) unique
);

create table address (
    id int not null auto_increment primary key,
    country_id int not null,
    location varchar(80) not null,
    area varchar(80),
    street varchar(100),
    building varchar(100),
    apartment varchar(100),
    
    constraint fk_country_id 
    foreign key (country_id) references country(id)
    on delete cascade
);

create table customer (
    id int not null auto_increment primary key,
    first_name varchar (50) not null,
    last_name varchar (50) not null
);

create table invoice_type (
    id int not null auto_increment primary key,
    name varchar(50) not null unique
);

create table invoice (
    id int not null auto_increment primary key,
    customer_id int not null,
    address_id int not null,
    type_id int not null,
    creation_date datetime not null,
    due_date datetime not null,
    number int not null,
    amount decimal(10,2) not null,
	vat decimal(10,2) not null,
    total decimal(10,2) not null,
    
    constraint fk_address_id
    foreign key (address_id) references address(id),
    constraint fk_type_id
    foreign key (type_id) references invoice_type(id),
    constraint fk_customer_id
    foreign key (customer_id) references customer(id)
);
