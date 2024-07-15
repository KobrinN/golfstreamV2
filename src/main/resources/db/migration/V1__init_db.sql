CREATE TABLE IF NOT EXISTS public.user
(
    id                bigserial    NOT NULL,
    username          varchar(255) NOT NULL,
    mail              varchar(255) NOT NULL,
    password          varchar(255) NOT NULL,
    registration_date date,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.type_employee
(
    id   bigserial        NOT NULL,
    type varchar(255)     NOT NULL,
    rate double precision NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.employee
(
    id            bigserial    NOT NULL,
    name          varchar(255) NOT NULL,
    surname       varchar(255) NOT NULL,
    secondname    varchar(255) NOT NULL,
    type          bigint       NOT NULL,
    country       varchar(255),
    opening_hours bigint,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.route
(
    id             bigserial NOT NULL,
    from_where     varchar(255),
    to_where       varchar(255),
    departure      date,
    arrival        date,
    transportation varchar(255),
    instructor_id  bigint    not null,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.voucher
(
    id          bigserial NOT NULL,
    route_id    bigint,
    name        varchar(255),
    price       double precision,
    quantity    bigint,
    reservation bigint,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.purchase
(
    id               bigserial NOT NULL,
    user_id          bigint    not null,
    voucher_id       bigint    not null,
    date_of_purchase date,
    PRIMARY KEY (id)
);

create table if not exists public.role
(
    id   bigserial not null,
    role varchar(255),
    user_id bigint NOT NULL,
    primary key (id)
);

ALTER TABLE IF EXISTS public.employee
    ADD CONSTRAINT fk_type FOREIGN KEY (type)
        REFERENCES public.type_employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.route
    ADD CONSTRAINT fk_employee FOREIGN KEY (instructor_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.voucher
    ADD CONSTRAINT fk_route FOREIGN KEY (route_id)
        REFERENCES public.route (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;


ALTER TABLE IF EXISTS public.purchase
    ADD CONSTRAINT fk_client FOREIGN KEY (user_id)
        REFERENCES public.user (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.purchase
    ADD CONSTRAINT fk_voucher FOREIGN KEY (voucher_id)
        REFERENCES public.voucher (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE;

alter table if exists public.role
    add constraint fk_user foreign key (user_id)
        references public.user (id) match simple
        on update cascade
        on delete cascade;
