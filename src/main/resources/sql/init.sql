create database if not exists interview;

create table if not exists genders(
    id bigint primary key auto_increment,
    name varchar(20) not null unique
);

create table if not exists users(
    id bigint primary key auto_increment,
    login varchar(50) unique not null,
    password varchar(100) not null,
    full_name varchar(256) not null,
    gender_id bigint not null,

    constraint user_gender_id_fk
        foreign key (gender_id) references genders(id)
);

create procedure if not exists removeByIds(id1 bigint, id2 bigint)
begin
    delete from `users` where id between id1 and id2;
end;

insert into genders(name) values ('male');
insert into genders(name) values ('female');