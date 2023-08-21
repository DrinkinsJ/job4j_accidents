create TABLE accident_types (
id serial primary key,
name varchar not null
)

create TABLE rules (
id serial primary key,
name varchar not null
)

create TABLE accidents (
  id serial primary key,
  name varchar,
  text varchar,
  address varchar,
  accident_types_id int REFERENCES accident_types(id),
  rules_id int REFERENCES rules(id)
);