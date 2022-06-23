
create database if not exists BlogSystem;
use BlogSystem;

drop table if exists blog;
create table blog(
    blogId int primary key auto_increment,
    title varchar(1024),
    content mediumtext,
    postTime datetime,
    userId int
);

drop table if exists user;
create table user(
    userId int primary key auto_increment,
    username varchar(128) unique,
    password varchar(128)
);