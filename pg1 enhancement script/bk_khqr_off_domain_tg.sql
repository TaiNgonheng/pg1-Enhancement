CREATE TRIGGER bk_khqr_off_domain_tg after insert
on fst_iroha_trx
for each row
execute procedure bk_khqr_off_domain_fn();