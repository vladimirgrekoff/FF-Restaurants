

create table restaurant_infos
(
    id                      bigserial primary key,
    restaurant_id           bigint not null references restaurants (id),
    description             varchar(1024),
    quisines                varchar(255),
    address                 varchar(1024),
    phone_number            varchar(255),
    email                   varchar(255),
    open_hours              varchar(255),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);



create table restaurant_requests
(
    id                      bigserial primary key,
    restaurant_title        varchar(255),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);


create table restaurant_request_items
(
    id                      bigserial primary key,
    request_id              bigint not null references restaurant_requests (id),
    dish_id                 bigint not null references dish (id),
    dish_title              varchar(255),
    dish_description        varchar(255),
    group_dish_title        varchar(255),
    calories                int,
    proteins                int,
    fats                    int,
    carbohydrates           int,
    healthy                 boolean,
    approved                boolean,
    lastname                varchar(255),
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);




