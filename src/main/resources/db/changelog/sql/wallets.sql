-- liquibase formatted sql

-- changeset Alemakave:1
CREATE TABLE wallets (
    id uuid PRIMARY KEY,
    balance int
);

-- changeset Alemakave:2
INSERT INTO wallets (id, balance) VALUES ('dc9b82b6-1235-449f-8d59-cd8ce7a6f6b5', 0);
INSERT INTO wallets (id, balance) VALUES ('31b3e0f7-1b57-492a-85be-ced59b93711b', 0);
INSERT INTO wallets (id, balance) VALUES ('60fa6efa-c754-47ee-b419-5cc3a4e5589d', 0);
INSERT INTO wallets (id, balance) VALUES ('73e6c5ba-b1ec-43f3-be9a-ee2c55aed829', 0);