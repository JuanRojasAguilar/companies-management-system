INSERT INTO user_types (name) 
VALUES ('ROLE_ADMIN'), 
       ('ROLE_USER'), 
       ('ROLE_WORKER'), 
       ('ROL_RR.HH'), 
       ('ROLE_GROCER'), 
       ('ROLE_MARKETING')
ON CONFLICT (name) DO NOTHING;