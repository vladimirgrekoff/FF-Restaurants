drop table if exists products cascade;



create table restaurant_requests
(
    id                      bigserial primary key,
    restaurant_name         varchar(255),
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
    verified                boolean,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);




