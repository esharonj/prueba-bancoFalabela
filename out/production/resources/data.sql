-- Usuario 1
INSERT INTO users (id, name, email, password, created, modified, last_login, token, is_active)
VALUES ('11111111-1111-1111-1111-111111111111', 'Juan Rodriguez', 'juan@rodriguez.org', 'hunter2',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'token-juan', true);

INSERT INTO phones (id, number, citycode, contrycode, user_id)
VALUES (1, '1234567', '1', '57', '11111111-1111-1111-1111-111111111111');

-- Usuario 2
INSERT INTO users (id, name, email, password, created, modified, last_login, token, is_active)
VALUES ('22222222-2222-2222-2222-222222222222', 'Maria Gonzalez', 'maria.gonzalez@example.com', 'clave123',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'token-maria', true);

INSERT INTO phones (id, number, citycode, contrycode, user_id)
VALUES (2, '7654321', '2', '34', '22222222-2222-2222-2222-222222222222');

-- Usuario 3
INSERT INTO users (id, name, email, password, created, modified, last_login, token, is_active)
VALUES ('33333333-3333-3333-3333-333333333333', 'Carlos Perez', 'carlos.perez@testmail.com', 'secreto',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'token-carlos', true);

INSERT INTO phones (id, number, citycode, contrycode, user_id)
VALUES (3, '9876543', '5', '51', '33333333-3333-3333-3333-333333333333');

-- Usuario 4
INSERT INTO users (id, name, email, password, created, modified, last_login, token, is_active)
VALUES ('44444444-4444-4444-4444-444444444444', 'Ana Torres', 'ana.torres@mail.org', 'password!',
        CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'token-ana', true);

INSERT INTO phones (id, number, citycode, contrycode, user_id)
VALUES (4, '4567890', '7', '52', '44444444-4444-4444-4444-444444444444');
