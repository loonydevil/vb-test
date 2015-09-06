
insert into country 
    (name) 
values 
    ('Oceania'),
    ('Eurasia'),
    ('Eastasia');
    
insert into address
	(country_id, location)
values 
    (1, 'Canberra'),
    (1, 'Sydney'),
    (2, 'London'),
    (2, 'Paris'),
    (3, 'Tokyo'),
    (3, 'Deli');

insert into invoice_type
    (name)
values
    ('Advance Payment'),
    ('Credit Card Payment'),
    ('Some third type of Payment')
;

insert into customer
    (first_name, last_name)
values
    ('Winston', 'Smith'),
    ('O', 'Brien'),
    ('William', 'Goldstein')
;

insert into invoice
    (address_id, customer_id, type_id, creation_date, due_date, number, amount, vat, total)
values
    (1, 1, 1, now(), now(), 1, 1, 1, 1)
;
    
    
show table status where name = 'address';
describe address;
select * from address;
select * from country;
delete from country where id = 1;
