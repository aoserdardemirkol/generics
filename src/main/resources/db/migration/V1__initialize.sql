CREATE TABLE products
(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(255),
    price DOUBLE PRECISION
);

CREATE TABLE expenses
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    cost FLOAT
);