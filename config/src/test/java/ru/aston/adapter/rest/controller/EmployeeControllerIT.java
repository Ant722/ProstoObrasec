package ru.aston.adapter.rest.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.aston.adapter.rest.AbstractIntegrationTest;
import ru.aston.exception.EmployeeNotFoundException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class EmployeeControllerIT extends AbstractIntegrationTest {

    private static final String EMPLOYEE_INFORMATION_ENDPOINT = "/api/v1/employee/{id}";

    private static final Long EXISTED_EMPLOYEE_ID = 1L;
    private static final Long NOT_EXISTED_EMPLOYEE_ID = 2L;

    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql("classpath:sql/employeeController/getEmployeeInformationById.sql")
    void getEmployeeInformationById_Should_Return200_WhenExistedEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(EMPLOYEE_INFORMATION_ENDPOINT, EXISTED_EMPLOYEE_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname").value("Ivanov"))
                .andExpect(jsonPath("$.name").value("Ivan"))
                .andExpect(jsonPath("$.middleName").value("Ivanovich"))
                .andExpect(jsonPath("$.role").value("ADMIN"))
                .andExpect(jsonPath("$.login").value("login"))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andExpect(jsonPath("$.passportId").value("100"))
                .andExpect(jsonPath("$.passportDateIssue").value("01.01.2023"))
                .andExpect(jsonPath("$.createdAt").value("02.02.2023"))
                .andExpect(jsonPath("$.modifiedAt").value("03.03.2023"));
    }

    @Test
    @Sql("classpath:sql/employeeController/getEmployeeInformationById.sql")
    void getEmployeeInformationById_Should_Return404_WhenNotExistedEmployee() throws Exception {
        String expectedErrorMessage =
                String.format(EmployeeNotFoundException.EMPLOYEE_NOT_FOUND_BY_ID, NOT_EXISTED_EMPLOYEE_ID);
        mockMvc.perform(MockMvcRequestBuilders.get(EMPLOYEE_INFORMATION_ENDPOINT, NOT_EXISTED_EMPLOYEE_ID))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(expectedErrorMessage));
    }
}
