create table if not exists medical_info
(
    id                bigserial
        primary key,
    blood_type        int,
    rh                varchar(2)   not null,
    creation_date     timestamp(6) not null,
    modification_date timestamp(6) not null,
    user_id           bigint       not null
        constraint medical_info_users_id_fk
            references carshop.users
            on update cascade on delete cascade
);

create unique index if not exists medical_info_user_id_uindex
    on medical_info (user_id);
