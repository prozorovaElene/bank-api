CREATE TABLE customer
(
    id        BIGINT NOT NULL,
    full_name VARCHAR(255),
    type      VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE card
(
    id          BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    type        VARCHAR(20),
    card_number VARCHAR(20),
    expiry      TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_card_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE account
(
    id          BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    iban        VARCHAR(30),
    currency    VARCHAR(3),
    balance     NUMERIC(19, 2),
    PRIMARY KEY (id),
    CONSTRAINT fk_account_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

INSERT INTO customer VALUES
                         (0, 'John Smith', 'Personal'),
                         (1, 'Jane Doe', 'Business');

INSERT INTO card VALUES
                     (0, 0, 'Debit', '5236 5484 2365 4125', '2020-10-05 14:01:10'),
                     (1, 0, 'Credit', '8542 8974 2315 3254', '2021-02-24 13:05:26'),
                     (2, 1, 'Credit', '7841 2345 8912 7452', '2022-03-07 10:34:59'),
                     (3, 1, 'Debit', '1254 2538 8965 1245', '2018-09-17 09:25:34');

INSERT INTO account VALUES
                        (0, 0, 'DK9520000123456789', 'DKK', 1258.34),
                        (1, 0, 'LT601010012345678901', 'EUR', 516.25),
                        (2, 1, 'SE7280000810340009783242', 'SEK', 6231.84),
                        (3, 1, 'GB33BUKB20201555555555', 'GBP', 895.54);