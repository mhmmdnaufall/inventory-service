CREATE TABLE inventories
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    sku_code VARCHAR(100) NOT NULL,
    quantity INT          NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB;