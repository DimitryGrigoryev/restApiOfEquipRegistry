create table equip_type
(
    code varchar
        constraint equip_type_pk
            primary key,
    name varchar(255) not null
        constraint equip_type_name_unique
            unique
);
comment on table equip_type is 'Тип техники';


create table equipment
(
    name           varchar(255)
        constraint equipment_pk primary key,
    equip_type_code varchar(255)
        constraint equipment_equip_type_code_fk
            references equip_type,
    country varchar,
    company varchar,
    order_online boolean,
    in_credit boolean

);
comment on table equipment is 'Техника';


create table model
(
    name           varchar(255)
        constraint model_pk primary key,
    equipment_name varchar(255)
        constraint model_equipment_name_fk
            references equipment,
    serial_num varchar(255) not null,
    color varchar,
    size integer,
    price DOUBLE PRECISION,
    available boolean
);
comment on table model is 'Модели';

create table options
(
    id          serial
        constraint options_pk
            primary key,
    name        varchar(255) not null,
    description    varchar(255) not null,
    model_name varchar(255) not null
        constraint options_model_name_fk
            references model
);
comment on table options is 'Опции модели';
