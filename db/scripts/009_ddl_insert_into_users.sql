INSERT INTO users (username, enabled, password, authority_id)
VALUES ('root', true, '$2a$10$Zz1Z7dnQO5.n57u5xuc6zeuCs72I0XXs0cUcTZSQOotzhffyJGDFy',
(SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN'));