create table tasks
(
    id          serial primary key,
    name        varchar   not null,
    description varchar,
    label       varchar   not null default 'Backend',
    created_at  timestamp not null default current_timestamp
);