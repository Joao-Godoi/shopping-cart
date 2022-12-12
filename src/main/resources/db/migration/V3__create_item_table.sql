CREATE TABLE IF NOT EXISTS tb_item (
    id bigint generated by default as identity,
    quantity integer,
    unit_price numeric(38,2),
    shopping_cart_id bigint,
    product_code bigint,
    primary key (id)
);