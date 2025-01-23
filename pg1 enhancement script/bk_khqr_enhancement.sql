CREATE TABLE bk_khqr
(
    id bigint not null,
    src_account_id varchar(255) not null,
    asset_id varchar(255) not null,
    amount varchar(255) not null,
    create_time timestamp not null,
    trx_hash varchar(255) not null constraint bk_khqr_hash_unique unique,
    qr_code varchar(7090) not null,
    trn_type varchar(25) not null,
    status varchar(255) not null,
    src_account_type varchar(255) not null,
    dst_account_type varchar(255) not null,
    src_partcode varchar(255) not null,
    dst_partcode varchar(255) not null,
    sender_details jsonb
);