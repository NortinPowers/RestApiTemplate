drop table if exists cities cascade;
create table cities
(
    id   bigint generated by default as identity
        constraint city_pk
            primary key,
    name varchar(50) not null
        constraint city_pk2
            unique,
    info varchar(1000)
);
