create TABLE if not exists accident_type
(
    id   serial primary key,
    name varchar not null
);

create TABLE if not exists rule
(
    id   serial primary key,
    name varchar not null
);

create TABLE if not exists accident
(
    id               serial primary key,
    name             varchar,
    text             varchar,
    address          varchar,
    accident_type_id int NOT NULL REFERENCES accident_type (id)
);

create TABLE accident_rule
(
    id          SERIAL PRIMARY KEY,
    accident_id int NOT NULL REFERENCES accident (id),
    rule_id     int NOT NULL REFERENCES rule (id)
);