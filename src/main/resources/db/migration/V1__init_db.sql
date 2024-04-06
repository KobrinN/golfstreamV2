CREATE TABLE IF NOT EXISTS public.client
(
    id bigserial NOT NULL,
    name character varying(100)[] NOT NULL,
    surname character varying(100)[] NOT NULL,
    secondname character varying(100)[] NOT NULL,
    mail character varying(100)[] NOT NULL,
    password character varying(100)[] NOT NULL,
    registration_date date,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.type_employee
(
    id bigserial NOT NULL,
    type character varying(100)[] NOT NULL,
    rate double precision NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.employee
(
    id bigserial NOT NULL,
    name character varying(100)[] NOT NULL,
    surname character varying(100)[] NOT NULL,
    secondname character varying(100)[] NOT NULL,
    type bigint NOT NULL,
    country character varying(100)[],
    opening_hours bigint,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.route
(
    id bigserial NOT NULL,
    from_where character varying(100)[],
    to_where character varying(100)[],
    departure date,
    arrival date,
    transportation character varying(100)[],
    id_instructor bigint,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.voucher
(
    id bigserial NOT NULL,
    id_route bigint,
    name character varying(100)[],
    price double precision,
    quantity bigint,
    reservation bigint,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.purchase
(
    id bigserial NOT NULL,
    id_client bigint,
    id_voucher bigint,
    date_of_purchase date,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.employee
    ADD CONSTRAINT fk_type FOREIGN KEY (type)
    REFERENCES public.type_employee (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.route
    ADD CONSTRAINT fk_employee FOREIGN KEY (id_instructor)
    REFERENCES public.employee (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.voucher
    ADD CONSTRAINT fk_route FOREIGN KEY (id_route)
    REFERENCES public.route (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.purchase
    ADD CONSTRAINT fk_client FOREIGN KEY (id_client)
    REFERENCES public.client (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID;


ALTER TABLE IF EXISTS public.purchase
    ADD CONSTRAINT fk_voucher FOREIGN KEY (id_voucher)
    REFERENCES public.voucher (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID;
