package ru.aston.rest.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
public class EmployeeController {

    @PutMapping("/employee/{employee_id}")
    public String editEmployee() {
        return "helloworld";
    }
}