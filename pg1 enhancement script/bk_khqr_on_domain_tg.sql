CREATE TRIGGER bk_khqr_on_domain_tg after update
of status_string on fst_iroha_trx for each row
execute procedure bk_khqr_on_domain_fn();