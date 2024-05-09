CREATE TABLE tb_user (
	id bigserial NOT NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	"password" varchar(255) NULL,
	CONSTRAINT tb_user_pkey PRIMARY KEY (id)
);


CREATE TABLE tb_role (
	id bigserial NOT NULL,
	role_name varchar(255) NULL,
	CONSTRAINT tb_role_pkey PRIMARY KEY (id)
);


CREATE TABLE tb_user_role (
	user_id bigserial NOT NULL,
	role_id bigserial NOT NULL,
	CONSTRAINT tb_user_role_pkey PRIMARY KEY (user_id, role_id)
);

ALTER TABLE tb_user_role ADD CONSTRAINT fk_tb_user_role_to_tb_user FOREIGN KEY (user_id) REFERENCES tb_user(id);
ALTER TABLE tb_user_role ADD CONSTRAINT fk_tb_user_role_to_tb_role FOREIGN KEY (role_id) REFERENCES tb_role(id);