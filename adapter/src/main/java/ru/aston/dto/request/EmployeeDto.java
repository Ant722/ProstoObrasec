package ru.aston.dto.request;

import lombok.Data;

@Data
public class EmployeeDto {
    String uuid;

    String employee_id;

    int status_id;

    int role_id;

    String name; // = [string]

    String surname; // = [string]

    String middlename; // = [string]

    String login; //= [string]

    String passport; // = [string]

    String passport_date_issue; // = [string]

    String email; // = [string]
}
