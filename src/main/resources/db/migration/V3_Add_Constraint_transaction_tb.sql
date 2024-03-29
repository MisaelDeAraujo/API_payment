ALTER TABLE transaction_tb
ADD CONSTRAINT fk_payer_id
FOREIGN KEY (payer_id) REFERENCES user(id),
ADD CONSTRAINT fk_payee_id
FOREIGN KEY (payee_id) REFERENCES user(id)