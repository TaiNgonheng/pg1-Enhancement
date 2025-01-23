CREATE OR REPLACE FUNCTION bk_khqr_on_domain_fn() returns trigger
language plpgsql as $$
DECLARE
HASH_COUNT INT;
BEGIN
SELECT COUNT(*) INTO HASH_COUNT FROM bk_khqr WHERE trx_hash LIKE NEW.trx_hash;
IF OLD.status_string <> 'SUCCESS' AND NEW.status_string = 'SUCCESS' AND NEW.dest_account_id = 'oskikhppxxx@oski' AND
NEW.qr_code NOTNULL AND NEW.qr_code <> '' AND HASH_COUNT = 0
THEN
INSERT INTO bk_khqr (id, src_account_id, asset_id, amount, create_time, trx_hash, qr_code, trn_type, status, src_account_type, dst_account_type, src_partcode, dst_partcode, sender_details)
VALUES (NEW.id, NEW.src_account_id, NEW.asset_id, NEW.amount, NEW.create_time, NEW.trx_hash, NEW.qr_code, 'ON_DOMAIN', 'PENDING', NEW.src_account_type, NEW.dst_account_type, NEW.src_partcode, NEW.dst_partcode, (SELECT ft.sender_details from fst_transaction ft where NEW.fast_transaction_id is not null and NEW.fast_transaction_id = ft.id));
END IF;
RETURN NEW; END;
$$;