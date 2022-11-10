CREATE TABLE IF NOT EXISTS public.users
(
    id bigserial primary key,
    username varchar(33) NOT NULL,
    password varchar(80) NOT NULL
    );

ALTER TABLE IF EXISTS public.users OWNER to postgres;

insert into users(username, password)
values
    ('boss', '$2a$12$Pjbj/vlz8AoAv7ZNOghkX.jXl8RT98Vpfo8R/Zx9ok2HzCDKoVl7a'),
    ('manager', '$2a$12$XHOs2u/3KwGPNsr0fnZEJ./2V.Dt0k06XAmxLWHMjFQIcVrogNPQO'),
    ('Vasya', '$2a$12$4z.HGPlLn4Eihe7qeUZGcOFgqF70TQ1/ITVUwTgaKoRJyHvfSBSdK')
;

CREATE TABLE IF NOT EXISTS public.roles
(
    id serial primary key,
    name varchar(80) NOT NULL
    );

ALTER TABLE IF EXISTS public.roles OWNER to postgres;

insert into roles(name)
values
    ('ROLE_ADMIN'), ('ROLE_MANAGER'),('ROLE_USER');

CREATE TABLE IF NOT EXISTS public.users_roles
(
    user_id bigint NOT NULL,
    role_id int NOT NULL,
    CONSTRAINT order_users_id_fkey FOREIGN KEY (user_id)
    REFERENCES public.users (id),
    CONSTRAINT order_roles_id_fkey FOREIGN KEY (role_id)
    REFERENCES public.roles (id)
    );

ALTER TABLE IF EXISTS public.users_roles OWNER to postgres;

insert into users_roles(user_id, role_id)
values
    (1, 1), (1, 2), (1, 3),
    (2, 2), (2, 3),
    (3, 3);