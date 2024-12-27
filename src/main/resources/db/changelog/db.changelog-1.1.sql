-- liquibase formatted SQL

-- changeset joao.moreira:add_categories dbms:mysql
insert into users(email, name, creation_date)
values ('joao@4hands.com', 'João Luis', now())
-- ROLLBACK delete from users;