INSERT INTO usr (id, username, password, active, email, activation_code)
VALUES (1, 1, '$2a$16$yVY0cev7lwnWjJF9ToBESusjNKI/3FMXyb4qx4uH3Inook/oAwp22', true, null, null);

INSERT INTO user_role (user_id, roles) VALUES (1, 'ADMIN')