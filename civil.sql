
drop table billing;
drop table stock;
drop table employee;
drop table customer;

create sequence s1;
create table customer(c_id int DEFAULT nextval('s1'),c_name varchar(50) primary key,addr varchar(100)not null,phone varchar(13)not null,payment numeric ,bill_no int);
\d customer;
alter sequence s1 owned by customer.c_id;



create sequence s2;

create table employee(e_id int primary key DEFAULT nextval('s2') ,e_name varchar(40) not null,phone varchar(13)not null,email text not null,addr varchar(100)not null,salary numeric);
\d employee;
alter sequence s2 owned by employee.e_id;

create sequence s3;
create table stock(s_id int primary key DEFAULT nextval('s3') ,p_name varchar(50),p_type varchar(50)not null,cmpy varchar(50)not null,quanti int not null,price_of_one numeric not null);
\d stock;

alter sequence s3 owned by stock.s_id;
create sequence s4;
create table billing(b_no int primary key  DEFAULT nextval('s4'),b_date varchar(10) not null,t_price numeric not null,quantity int not null,c_name varchar(50) /*references customer(c_name)on delete cascade*/,/*s_id int references stock(s_id) on delete cascade*/amtr numeric,amtg numeric);
alter sequence s4 owned by billing.b_no;
\d billing;



