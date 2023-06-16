INSERT INTO generate_password
(id, password ) VALUES (1, 'password');

INSERT INTO employee
(
     id,
     uuid,
     surname,
     name,
     middle_name,
     role,
     login,
     generate_password_id,
     status,
     passport_ID,
     passport_date_issue,
     created_at,
     modified_at
)
VALUES
(1, '9771203f-be0a-4ecf-9ed7-72978a35d201', 'Ivanov', 'Ivan', 'Ivanovich', 'ADMIN',
 'login', 1, 'ACTIVE', '100', '2023-01-01', '2023-02-02 12:00:00', '2023-03-03 12:00:00');