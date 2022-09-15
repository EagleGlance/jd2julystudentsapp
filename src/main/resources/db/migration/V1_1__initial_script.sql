create table if not exists users
(
    id                serial
        constraint users_pk
            primary key,
    user_name         varchar(20)  default 'name'::character varying             not null,
    surname           varchar(50)  default 'surname'::character varying          not null,
    birth             timestamp(6)                                               not null,
    is_deleted        boolean      default false                                 not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6)                  not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6)                  not null,
    weight            double precision,
    user_login        varchar(100),
    user_password     varchar(200) default 'default_password'::character varying not null
);

alter table users
    owner to postgres;

create unique index if not exists users_id_uindex
    on users (id);

create index if not exists users_user_name_index
    on users (user_name);

create index if not exists users_birth_index
    on users (birth);

create index if not exists users_is_deleted_index
    on users (is_deleted);

create index if not exists users_user_name_surname_index
    on users (user_name, surname);

create table if not exists roles
(
    id                bigserial
        constraint roles_pk
            primary key,
    role_name         varchar(15) default 'USER'::character varying not null,
    creation_date     timestamp(6),
    modification_date timestamp(6)
);

alter table roles
    owner to postgres;

create unique index if not exists roles_id_uindex
    on roles (id);

create table if not exists channels
(
    id                bigserial
        constraint channels_pk
            primary key,
    name              varchar(100)                              not null,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    is_banned         boolean      default false                not null
);

alter table channels
    owner to postgres;

create table if not exists l_users_channels
(
    id                bigserial
        constraint l_users_channels_pk
            primary key,
    user_id           bigint                                    not null
        constraint l_users_channels_users_id_fk
            references users
            on update cascade on delete cascade,
    channel_id        bigint                                    not null
        constraint l_users_channels_channels_id_fk
            references channels
            on update cascade on delete cascade,
    creation_date     timestamp(6) default CURRENT_TIMESTAMP(6) not null,
    modification_date timestamp(6) default CURRENT_TIMESTAMP(6) not null
);

alter table l_users_channels
    owner to postgres;

create unique index if not exists l_users_channels_user_id_channel_id_uindex
    on l_users_channels (user_id, channel_id);

create table if not exists l_role_user
(
    id      bigserial
        constraint l_role_user_pk
            primary key,
    user_id bigint not null
        constraint l_role_user_users_id_fk
            references users
            on update cascade on delete cascade,
    role_id bigint not null
        constraint l_role_user_roles_id_fk
            references roles
            on update cascade on delete cascade
);

alter table l_role_user
    owner to postgres;

create unique index if not exists l_role_user_id_uindex
    on l_role_user (id);

create index if not exists l_role_user_user_id_role_id_index
    on l_role_user (user_id, role_id);