CREATE TABLE IF NOT EXISTS tb_product (
    code bigint generated by default as identity,
    description varchar(255),
    primary key (code)
);
