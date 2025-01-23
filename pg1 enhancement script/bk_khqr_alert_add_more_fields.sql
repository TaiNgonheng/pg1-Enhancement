ALTER TABLE bk_khqr
    ADD COLUMN src_account_type varchar(255) not null,
    ADD COLUMN dst_account_type varchar(255) not null,
    ADD COLUMN src_partcode varchar(255) not null,
    ADD COLUMN dst_partcode varchar(255) not null,
    ADD COLUMN sender_details jsonb;