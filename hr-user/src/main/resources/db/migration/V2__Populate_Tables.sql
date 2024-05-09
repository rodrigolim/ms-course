
INSERT INTO tb_role (id, role_name) VALUES
     (1, 'ROLE_OPERATOR'), (2, 'ROLE_ADMIN');


INSERT INTO tb_user (id, email,"name","password") VALUES
	 (1, 'nina@gmail.com','Nina Brown','$2a$10$yLOYL.VHX9RwdrcDqd73fuhaoWtD.efwDCCDnd5PvT9ItJKbv5PbW'),
	 (2, 'leia@gmail.com','Leia Red','$2a$10$yLOYL.VHX9RwdrcDqd73fuhaoWtD.efwDCCDnd5PvT9ItJKbv5PbW'),
	 (3, 'teste@teste.com','teste','$2a$10$.NbkgmS9FyvBTSz3TaRUnejCk3vgJKMCo72aM7mrjp.ilVgg/wGvm');


INSERT INTO tb_user_role (user_id,role_id) VALUES
	 (1,1),
	 (2,1),
	 (2,2);