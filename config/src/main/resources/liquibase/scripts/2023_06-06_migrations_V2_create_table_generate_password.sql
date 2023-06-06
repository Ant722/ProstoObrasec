ALTER TABLE employee
    DROP COLUMN password;

CREATE TABLE generate_password
(
    id          BIGSERIAL PRIMARY KEY,
    password    VARCHAR(20) NOT NULL,
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE employee
    ADD COLUMN generate_password_id BIGINT
        REFERENCES generate_password (id) UNIQUE NOT NULL;