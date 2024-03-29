CREATE TABLE IF NOT EXISTS transaction_tb(
id SERIAL PRIMARY KEY,
payer_id INT NOT NULL,
payee_id INT NOT NULL
)