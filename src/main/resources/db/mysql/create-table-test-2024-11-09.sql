drop table if exists ddl_test;
create table if not exists ddl_test (
    id int not null auto_increment,
    name varchar(50),
    primary key (`id`)
);