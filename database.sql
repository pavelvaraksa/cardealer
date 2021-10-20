create database car_dealer_database
    with owner postgres;

create table if not exists users
(
    id bigserial
        constraint users_pk
            primary key,
    firstname varchar(20) not null,
    lastname varchar(30) not null,
    birth_date date,
    phone_number varchar(9) not null,
    login varchar(30) not null,
    password varchar(55) not null,
    email varchar(55) not null,
    role varchar(10) not null,
    is_blocked boolean default false not null,
    created timestamp not null,
    changed timestamp not null
);

alter table users owner to postgres;

create unique index if not exists users_id_uindex
    on users (id);

create unique index if not exists users_login_uindex
    on users (login);

create table if not exists dealers
(
    id bigserial
        constraint dealers_pk
            primary key,
    name varchar(30) not null,
    address varchar(55) not null,
    foundation_date date not null,
    city varchar(15) not null,
    created timestamp not null,
    changed timestamp not null
);

alter table dealers owner to postgres;

create unique index if not exists dealers_id_uindex
    on dealers (id);

create table if not exists cars
(
    id bigserial
        constraint cars_pk
            primary key,
    model varchar(20) not null,
    issue_country varchar(20) not null,
    guarantee_period integer not null,
    price numeric not null,
    created timestamp not null,
    changed timestamp not null,
    dealer_id bigint not null
        constraint cars_dealers_id_fk
            references dealers
);

alter table cars owner to postgres;

create table if not exists bodies
(
    id bigserial
        constraint bodies_pk
            primary key,
    color varchar(20) not null,
    body_type varchar(20) not null,
    created timestamp not null,
    changed timestamp not null,
    car_id bigint not null
        constraint bodies_cars_id_fk
            references cars
            on update cascade on delete cascade
);

alter table bodies owner to postgres;

create unique index if not exists bodies_id_uindex
    on bodies (id);

create unique index if not exists bodies_car_id_uindex
    on bodies (car_id);

create table if not exists engines
(
    id bigserial
        constraint engines_pk
            primary key,
    fuel_type varchar(15) not null,
    volume double precision,
    cylinders_count integer,
    created timestamp not null,
    changed timestamp not null,
    car_id bigint not null
        constraint engines_cars_id_fk
            references cars
            on update cascade on delete cascade
);

alter table engines owner to postgres;

create unique index if not exists engines_id_uindex
    on engines (id);

create unique index if not exists engines_car_id_uindex
    on engines (car_id);

create table if not exists transmissions
(
    id serial
        constraint transmissions_pk
            primary key,
    transmission_type varchar(15) not null,
    gears_count integer not null,
    weight integer not null,
    created timestamp not null,
    changed timestamp not null,
    car_id bigint not null
        constraint transmissions_cars_id_fk
            references cars
            on update cascade on delete cascade
);

alter table transmissions owner to postgres;

create unique index if not exists transmissions_id_uindex
    on transmissions (id);

create unique index if not exists transmissions_car_id_uindex
    on transmissions (car_id);

create table if not exists user_orders
(
    id bigserial
        constraint user_orders_pk
            primary key,
    created timestamp not null,
    changed timestamp not null,
    user_id bigint not null
        constraint user_orders_users_id_fk
            references users,
    car_id bigint not null
        constraint user_orders_cars_id_fk
            references cars
);

alter table user_orders owner to postgres;

create unique index if not exists user_orders_id_uindex
    on user_orders (id);

create unique index if not exists cars_id_uindex
    on cars (id);




insert into users (firstname, lastname, phone_number, login, password, email, role, is_blocked, created, changed)
values ('Admin', 'Admin', '000000000', 'admin', 'YWRtaW4=', 'javaTestProject2000@gmail.com', 'ADMIN', false, '2000-01-01 00:00:00.000000', '2000-01-01 00:00:00.000000');


insert into user_orders(order_name, created, changed, user_id)
values ('Null', '2000-01-01 00:00:00.000000', '2000-01-01 00:00:00.000000', 1)


insert into dealers (name, address, foundation_date, city, created, changed)
values ('Audi center Minsk', 'Nezavisimosti Avenue, 198', '1998-04-01', 'MINSK', '2021-09-26 00:00:00.000000', '2021-09-26 00:00:00.000000')

insert into dealers (name, address, foundation_date, city, created, changed)
values ('Audi center Brest', 'Gavrilova street, 17', '2001-07-22', 'BREST', '2021-09-26 00:00:00.000000', '2021-09-26 00:00:00.000000')

insert into dealers (name, address, foundation_date, city, created, changed)
values ('Audi center Grodno', 'Vostochnaya street, 64', '2002-2-13', 'GRODNO', '2021-09-26 00:00:00.000000', '2021-09-26 00:00:00.000000')

insert into dealers (name, address, foundation_date, city, created, changed)
values ('Audi center Vitebsk', 'Gornaya street, 23', '1999-12-05', 'VITEBSK', '2021-09-26 00:00:00.000000', '2021-09-26 00:00:00.000000')

insert into dealers (name, address, foundation_date, city, created, changed)
values ('Audi center Mogilev', 'Mira Avenue, 11', '2005-06-03', 'MOGILEV', '2021-09-26 00:00:00.000000', '2021-09-26 00:00:00.000000')

insert into dealers (name, address, foundation_date, city, created, changed)
values ('Audi center Gomel', 'Zhukova street, 198', '2001-08-17', 'GOMEL', '2021-09-26 00:00:00.000000', '2021-09-26 00:00:00.000000')


insert into cars (model, issue_country, guarantee_period, price, created, changed, user_order_id, dealer_id)
values ('A1', 'CZECH', 3, 18000, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1, 1);

insert into cars (model, issue_country, guarantee_period, price, created, changed, user_order_id, dealer_id)
values ('A3', 'GERMANY', 3, 22000, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1, 2);

insert into cars (model, issue_country, guarantee_period, price, created, changed, user_order_id, dealer_id)
values ('A5', 'GERMANY', 3, 25000, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1, 3);

insert into cars (model, issue_country, guarantee_period, price, created, changed, user_order_id, dealer_id)
values ('A7', 'POLAND', 3, 30000, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1, 4);

insert into cars (model, issue_country, guarantee_period, price, created, changed, user_order_id, dealer_id)
values ('A8', 'GERMANY', 3, 38000, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1, 5);

insert into cars (model, issue_country, guarantee_period, price, created, changed, user_order_id, dealer_id)
values ('Q3', 'GERMANY', 3, 33000, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1, 6);

insert into cars (model, issue_country, guarantee_period, price, created, changed, user_order_id, dealer_id)
values ('Q5', 'GERMANY', 3, 41000, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1, 2);

insert into cars (model, issue_country, guarantee_period, price, created, changed, user_order_id, dealer_id)
values ('Q7', 'CZECH', 3, 45000, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1, 4);

insert into cars (model, issue_country, guarantee_period, price, created, changed, user_order_id, dealer_id)
values ('Q8', 'POLAND', 3, 52000, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1, 6);

insert into cars (model, issue_country, guarantee_period, price, created, changed, user_order_id, dealer_id)
values ('A5', 'GERMANY', 3, 27000, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1, 1);

insert into cars (model, issue_country, guarantee_period, price, created, changed, user_order_id, dealer_id)
values ('A1', 'GERMANY', 3, 21000, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1, 5);

insert into cars (model, issue_country, guarantee_period, price, created, changed, user_order_id, dealer_id)
values ('Q3', 'POLAND', 3, 34000, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1, 3);


insert into bodies (color, body_type, created, changed, car_id)
values ('WHITE', 'HATCHBACK', '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1);

insert into bodies (color, body_type, created, changed, car_id)
values ('RED', 'SEDAN', '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 2);

insert into bodies (color, body_type, created, changed, car_id)
values ('BLACK', 'COUPE', '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 3);

insert into bodies (color, body_type, created, changed, car_id)
values ('WHITE', 'HATCHBACK', '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 4);

insert into bodies (color, body_type, created, changed, car_id)
values ('CHAMPAGNE', 'SEDAN', '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 5);

insert into bodies (color, body_type, created, changed, car_id)
values ('GREY', 'SUV', '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 6);

insert into bodies (color, body_type, created, changed, car_id)
values ('SILVER', 'SUV', '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 7);

insert into bodies (color, body_type, created, changed, car_id)
values ('ORANGE', 'SUV', '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 8);

insert into bodies (color, body_type, created, changed, car_id)
values ('BROWN', 'SUV', '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 9);

insert into bodies (color, body_type, created, changed, car_id)
values ('YELLOW', 'COUPE', '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 10);


insert into engines (fuel_type, volume, cylinders_count, created, changed, car_id)
values ('PETROL', 2.0, 4, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1);

insert into engines (fuel_type, volume, cylinders_count, created, changed, car_id)
values ('DIESEL', 2.2, 4, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 2);

insert into engines (fuel_type, volume, cylinders_count, created, changed, car_id)
values ('PETROL', 3.0, 6, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 3);

insert into engines (fuel_type, volume, cylinders_count, created, changed, car_id)
values ('DIESEL', 4.0, 8, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 4);

insert into engines (fuel_type, volume, cylinders_count, created, changed, car_id)
values ('PETROL', 4.0, 8, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 5);

insert into engines (fuel_type, volume, cylinders_count, created, changed, car_id)
values ('DIESEL', 2.0, 4, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 6);

insert into engines (fuel_type, volume, cylinders_count, created, changed, car_id)
values ('DIESEL', 3.0, 6, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 7);

insert into engines (fuel_type, volume, cylinders_count, created, changed, car_id)
values ('DIESEL', 4.0, 8, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 8);

insert into engines (fuel_type, volume, cylinders_count, created, changed, car_id)
values ('DIESEL', 4.0, 8, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 9);

insert into engines (fuel_type, volume, cylinders_count, created, changed, car_id)
values ('PETROL', 3.2, 6, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 10);


insert into transmissions (transmission_type, gears_count, weight, created, changed, car_id)
values ('MECHANIC', 5, 60, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 1);

insert into transmissions (transmission_type, gears_count, weight, created, changed, car_id)
values ('AUTOMATIC', 6, 70, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 2);

insert into transmissions (transmission_type, gears_count, weight, created, changed, car_id)
values ('ROBOTIC', 6, 70, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 3);

insert into transmissions (transmission_type, gears_count, weight, created, changed, car_id)
values ('AUTOMATIC', 6, 70, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 4);

insert into transmissions (transmission_type, gears_count, weight, created, changed, car_id)
values ('AUTOMATIC', 8, 80, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 5);

insert into transmissions (transmission_type, gears_count, weight, created, changed, car_id)
values ('AUTOMATIC', 6, 70, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 6);

insert into transmissions (transmission_type, gears_count, weight, created, changed, car_id)
values ('AUTOMATIC', 6, 70, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 7);

insert into transmissions (transmission_type, gears_count, weight, created, changed, car_id)
values ('ROBOTIC', 8, 80, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 8);

insert into transmissions (transmission_type, gears_count, weight, created, changed, car_id)
values ('AUTOMATIC', 8, 80, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 9);

insert into transmissions (transmission_type, gears_count, weight, created, changed, car_id)
values ('AUTOMATIC', 6, 70, '2021-10-10 22:19:23.000000', '2021-10-10 22:19:23.000000', 10);


