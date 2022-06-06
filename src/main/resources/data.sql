INSERT INTO users (id)
VALUES (1),(2);

INSERT INTO accounts (id, currency_type, account_number, balance, app_user_id)
VALUES (1, 'EUR', '12345678', 30.0, 1), (2, 'USD','12345679', 123.33, 1), (3, 'PLN', '123456780', 1050.53, 1),
       (4, 'EUR', '87654321', 127.0, 2), (5, 'USD', '87645321', 300, 2),  (6, 'PLN', '88654321', 275.35, 2);