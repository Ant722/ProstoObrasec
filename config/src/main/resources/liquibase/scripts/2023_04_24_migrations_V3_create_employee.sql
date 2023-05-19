CREATE TABLE IF NOT EXISTS employee (
    employee_id BIGSERIAL PRIMARY KEY,
    employee_uuid UUID UNIQUE NOT NULL,
    surname VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    middle_name VARCHAR(30) NOT NULL,
    role_id BIGINT NOT NULL REFERENCES role(role_id),
    login  VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(20) NOT NULL,
    status_id BIGINT NOT NULL REFERENCES status(status_id),
    passport_ID BIGINT NOT NULL,
    passport_date_issue DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);