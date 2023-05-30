CREATE TABLE product
(
    id       INT PRIMARY KEY,
    sequence INT
);

CREATE TABLE size
(
    id         INT PRIMARY KEY,
    product_id INT,
    back_soon  BOOLEAN,
    special    BOOLEAN,
    FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE stock
(
    size_id  INT PRIMARY KEY,
    quantity INT,
    FOREIGN KEY (size_id) REFERENCES product (id)
);

-- COPY product (id, sequence)
--     FROM 'https://github.com/cafeteru/capitole/blob/config-flyway/src/main/resources/db/migration/product.csv'
--     DELIMITER ','
--     CSV HEADER;
--
-- COPY size (id, product_id, back_soon, special)
--     FROM 'filesystem:src/main/resources/db/migration/size.csv'
--     DELIMITER ','
--     CSV HEADER;
--
-- COPY stock (size_id, quantity)
--     FROM 'filesystem:src/main/resources/db/migration/stock.csv'
--     DELIMITER ','
--     CSV HEADER;
