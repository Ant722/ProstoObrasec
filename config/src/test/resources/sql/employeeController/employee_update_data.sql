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

INSERT INTO employee (uuid, surname, name, middle_name, role, login, password, status, passport_id, passport_date_issue)
VALUES ('9771203f-be0a-4ecf-9ed7-72978a35d201',
        'surname',
        'name',
        'middlename',
        'ADMIN',
        'login',
        'password66',
        'ACTIVE',
        '1111 222333',
        '10.10.2010'),
       ('6bf20ff0-fa1e-4a2d-ac8a-8609914c575f',
        'Ivanov',
        'Ivan',
        'Petrovich',
        'PRODUCT_MANAGER',
        'i.ivanov',
        'pass',
        'ACTIVE',
        '6666 333666',
        '11.11.2022');
