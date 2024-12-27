-- liquibase formatted SQL

-- changeset joao.moreira:creating_tables dbms:mysql
CREATE TABLE users
(
    id              BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email           VARCHAR(100)      NOT NULL,
    name            VARCHAR(200)      NOT NULL,
    creation_date   DATETIME          NOT NULL,
    UNIQUE (email)
);

CREATE TABLE customers
(
    id                  BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email               VARCHAR(100)        NOT NULL,
    name                VARCHAR(200)        NOT NULL,
    phone               VARCHAR(15)         NOT NULL,
    creation_date       DATETIME            NOT NULL,
    UNIQUE (email), UNIQUE (phone)
);

CREATE TABLE agendas
(
    id                  BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id             BIGINT        NOT NULL,
    customer_id         BIGINT        NOT NULL,
    event_date          DATETIME      NOT NULL,
    creation_date       DATETIME      NOT NULL,
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FOREIGN KEY (customer_id) REFERENCES customers (id)
);
-- ROLLBACK DROP TABLE user;
-- ROLLBACK DROP TABLE customer;
-- ROLLBACK DROP TABLE agendas;
