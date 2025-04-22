CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE tests
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE products
(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(255),
    price NUMERIC
);

CREATE TABLE expenses
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    cost NUMERIC
);