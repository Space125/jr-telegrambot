set @table_name = 'group_x_user';
set @ref_table_name = 'tg_user';
set @fk_name = (select CONSTRAINT_NAME
                from information_schema.REFERENTIAL_CONSTRAINTS
                where TABLE_NAME = @table_name and
                        REFERENCED_TABLE_NAME = @ref_table_name);

set @drop_fk = concat('alter table ', @table_name, ' drop foreign key ', @fk_name);

prepare stmt from @drop_fk;
execute stmt;
deallocate prepare stmt;

alter table tg_user modify  chat_id bigint not null;
alter table group_x_user modify  user_id bigint not null;

alter table group_x_user add constraint group_x_user_ibfk_1 foreign key (user_id) references tg_user(chat_id);