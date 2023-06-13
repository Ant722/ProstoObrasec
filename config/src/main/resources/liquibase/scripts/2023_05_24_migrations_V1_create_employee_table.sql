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