create table if not exists converter_user
(
    id        uuid         not null
        primary key,
    nickname  varchar(60),
    email     varchar(255) not null
        unique,
    user_pass varchar(72)  not null
);

alter table converter_user
    owner to postgres;

create table if not exists converter_transaction
(
    id              uuid    not null
        primary key,
    userid          uuid    not null
        constraint userid
            references converter_user,
    originalvalue   numeric not null,
    initialcurrency numeric not null,
    finalcurrency   numeric not null,
    conversionrate  numeric not null,
    created_at      date    not null
);

alter table converter_transaction
    owner to postgres;


