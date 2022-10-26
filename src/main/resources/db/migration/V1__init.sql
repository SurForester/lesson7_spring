CREATE TABLE IF NOT EXISTS public.products
(
    id bigserial primary key,
    title text NOT NULL,
    cost numeric NOT NULL
    );

ALTER TABLE IF EXISTS public.products OWNER to postgres;

insert into products(title, cost)
values
    ('колбаса', 100), ('сосиски', 170),('молоко', 97),('рыба', 250),('сметана', 90),
    ('творог', 150.2),('каша', 27),('сало', 200),('кетчуп', 100),('майонез', 120),
    ('масло', 200),('мясо', 300),('кости', 100), ('птица', 170),('яйцо', 99),
    ('картоха', 57),('моркоха', 38),('лук', 33),('свекла', 57),('капуста', 77)
;

CREATE TABLE IF NOT EXISTS public.customers
(
    id bigserial primary key,
    customer_name text NOT NULL
    );

ALTER TABLE IF EXISTS public.customers OWNER to postgres;

insert into customers(customer_name)
values
    ('Вася пупкин'), ('Петр Петрович'),('Баширов'),('Мистер Х'),('Митсер Хе');

CREATE TABLE IF NOT EXISTS public.orders
(
    id bigserial primary key,
    customer_id bigint NOT NULL,
    product_id bigint NOT NULL,
    buying_date date NOT NULL,
    buying_price numeric NOT NULL,
    CONSTRAINT order_customer_id_fkey FOREIGN KEY (customer_id)
    REFERENCES public.customers (id),
    CONSTRAINT order_product_id_fkey FOREIGN KEY (product_id)
    REFERENCES public.products (id)
    );

ALTER TABLE IF EXISTS public.orders OWNER to postgres;

insert into orders(customer_id, product_id, buying_date, buying_price)
values
    (1, 1, '2020-08-22', 150), (2, 1, '2000-12-22', 100);