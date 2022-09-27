create table if not exists shop_order
(
    id            bigserial
        primary key,
    creation_date timestamp(6),
    sum           float4 not null,
    user_id       bigint not null
        constraint order_users_id_fk
            references carshop.users
            on update cascade on delete cascade
);

create index if not exists order_user_id_sum_index
    on shop_order (user_id, sum);