/* Оставил для наглядности схемы*/
create table equip_type
(
    id  serial
        constraint equip_type_pk
            primary key,
    code varchar,
    name varchar(255) not null
        constraint equip_type_name_unique
            unique
);
comment on table equip_type is 'Тип техники';

create table equipment
(
    id  serial
        constraint equipment_pk
        primary key,
    name varchar(255) not null
        unique,
    equip_type_id integer
        constraint equipment_equip_type_id_fk
        references equip_type,
    country varchar,
    company varchar,
    order_online boolean
        not null,
    in_credit boolean
        not null
);
comment on table equipment is 'Вид Техники';

create table model
(
    id  serial
        constraint model_pk
        primary key,
    name varchar(255) not null unique,
    equipment_id integer not null
        constraint model_equipment_id_fk
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
    id  serial
        constraint options_pk
        primary key,
    name varchar(255) not null,
    description varchar(255) not null,
    model_id integer not null
        constraint options_model_id_fk
        references model
);
comment on table options is 'Опции модели';
