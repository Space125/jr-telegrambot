-- ensure that the table with this name is removed before creating a new one.
drop table if exists tg_user;

-- create tg_user table
create table tg_user(
    chat_id varchar(100),
    active boolean
)