INSERT INTO employee
(
    id, uuid, surname, name, middle_name, role, login, password, status, passport_ID, passport_date_issue
)
SELECT id, gen_random_uuid (), CONCAT('surname', id), CONCAT('name', id), CONCAT('middle_name', id), 'ADMIN',
       CONCAT('login', id), 'password', 'ACTIVE', '100', '2023-01-01'
FROM generate_series(1,100) id;

INSERT INTO employee
(
    id, uuid, surname, name, middle_name, role, login, password, status, passport_ID, passport_date_issue
)
SELECT id, gen_random_uuid (), CONCAT('surname', id), CONCAT('name', id), CONCAT('middle_name', id), 'PRODUCT_MANAGER',
       CONCAT('login', id), 'password', 'TRANSFERRED', '100', '2023-01-01'
FROM generate_series(101,200) id;