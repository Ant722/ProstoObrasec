INSERT INTO generate_password
(id, password )
SELECT id, CONCAT('password', id)
FROM generate_series(1,200) id;;

INSERT INTO employee
(
    id, uuid, surname, name, middle_name, role, login, generate_password_id, status, passport_ID, passport_date_issue
)
SELECT id, gen_random_uuid (), CONCAT('surname', id), CONCAT('name', id), CONCAT('middle_name', id), 'ADMIN',
       CONCAT('login', id), id, 'ACTIVE', '100', '2023-01-01'
FROM generate_series(1,100) id;

INSERT INTO employee
(
    id, uuid, surname, name, middle_name, role, login, generate_password_id, status, passport_ID, passport_date_issue
)
SELECT id, gen_random_uuid (), CONCAT('surname', id), CONCAT('name', id), CONCAT('middle_name', id), 'PRODUCT_MANAGER',
       CONCAT('login', id), id, 'TRANSFERRED', '100', '2023-01-01'
FROM generate_series(101,200) id;