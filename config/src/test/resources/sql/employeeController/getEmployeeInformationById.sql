BEGIN;

DROP TABLE IF EXISTS employee;

CREATE TABLE IF NOT EXISTS employee
(
    id                  BIGSERIAL PRIMARY KEY,
    uuid                UUID UNIQUE        NOT NULL,
    surname             VARCHAR(30)        NOT NULL,
    name                VARCHAR(30)        NOT NULL,
    middle_name         VARCHAR(30)        NOT NULL,
    role                VARCHAR(20)        NOT NULL,
    login               VARCHAR(30) UNIQUE NOT NULL,
    password            VARCHAR(20)        NOT NULL,
    status              VARCHAR(20)        NOT NULL,
    passport_id         VARCHAR(11)        NOT NULL,
    passport_date_issue DATE               NOT NULL,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO employee
(id,
 uuid,
 surname,
 name,
 middle_name,
 role,
 login,
 password,
 status,
 passport_ID,
 passport_date_issue,
 created_at,
 modified_at)
VALUES (1, '9771203f-be0a-4ecf-9ed7-72978a35d201', 'Ivanov', 'Ivan', 'Ivanovich', 'ADMIN',
        'login', 'password', 'ACTIVE', '100', '2023-01-01', '2023-02-02 12:00:00', '2023-03-03 12:00:00');

COMMIT;