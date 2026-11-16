CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer VARCHAR(120) NOT NULL,
    amount DECIMAL(12, 2) NOT NULL,
    legacy_status VARCHAR(20)
);
