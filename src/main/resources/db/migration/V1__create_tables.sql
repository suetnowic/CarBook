create sequence hibernate_sequence start 1 increment 1;

CREATE TABLE usr
(
    id              bigint         not null,
    username        varchar(255) not null,
    password        varchar(255) not null,
    active          boolean      not null,
    email           varchar(255),
    activation_code varchar(255),
    primary key (id)
);

CREATE TABLE cars
(
    id              bigint not null,
    car_brand       varchar(255),
    car_model       varchar(255),
    year_of_issue   varchar(4),
    fuel_type       varchar(255),
    current_mileage float8,
    color           varchar(255),
    engine_capacity float8,
    engine_power    float8,
    transmission    varchar(20),
    body_type       varchar(40),
    vin             varchar(255),
    vrp             varchar(10),
    odometer        varchar(20),
    user_id         bigint,
    primary key (id)
);

CREATE TABLE user_role
(
    user_id bigint not null,
    roles   varchar(255)
);

CREATE TABLE events
(
    id               bigint not null,
    date_event       date,
    type_of_work     varchar(255),
    consumables      varchar(255),
    number_of_litres float8,
    price            float8,
    odometer_reading float8,
    note             varchar(255) default null,
    car_id           bigint,
    primary key (id)
);



alter table if exists cars
    add constraint car_user_fk foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk foreign key (user_id) references usr;

alter table if exists events
    add constraint car_event_fk foreign key (car_id) references cars;


