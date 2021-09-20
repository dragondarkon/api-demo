drop table if exists user_test;
create table user_test (
    id identity,
    name varchar(25) not null,
    state varchar(25) not null,
    message varchar(80)
);