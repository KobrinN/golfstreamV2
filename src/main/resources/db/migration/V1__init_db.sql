CREATE TABLE IF NOT EXISTS public.client
(
    id serial NOT NULL,
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
    id serial NOT NULL,
    type character varying(100)[] NOT NULL,
    rate double precision NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.employee
(
    id serial NOT NULL,
    name character varying(100)[] NOT NULL,
    surname character varying(100)[] NOT NULL,
    secondname character varying(100)[] NOT NULL,
    type integer NOT NULL,
    country character varying(100)[],
    opening_hours integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.route
(
    id serial NOT NULL,
    from_where character varying(100)[],
    to_where character varying(100)[],
    departure date,
    arrival date,
    transportation character varying(100)[],
    id_instructor integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.voucher
(
    id serial NOT NULL,
    id_route integer,
    name character varying(100)[],
    price double precision,
    quantity integer,
    reservation integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.purchase
(
    id serial NOT NULL,
    id_client integer,
    id_voucher integer,
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
