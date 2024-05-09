CREATE TABLE tb_worker (
	id bigserial NOT NULL,
	daily_income float8 NULL,
	"name" varchar(255) NULL,
	CONSTRAINT tb_worker_pkey PRIMARY KEY (id)
);