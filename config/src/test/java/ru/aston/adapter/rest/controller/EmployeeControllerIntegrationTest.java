package ru.aston.adapter.rest.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.aston.adapter.rest.AbstractIntegrationTest;
import ru.aston.entity.factory.EmployeeRequestBodyFactory;
import ru.aston.exception.EmployeeNotFoundException;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.aston.JsonStringConverter.asJsonString;

@Transactional
class EmployeeControllerIntegrationTest extends AbstractIntegrationTest {

    private static final String EMPLOYEE_INFORMATION_ENDPOINT = "/api/v1/employee/{uuid}";
    private static final String EMPLOYEE_UPDATE_ENDPOINT = "/api/v1/employee/{employee_uuid}";
    private static final String EXISTED_EMPLOYEE_UUID = "9771203f-be0a-4ecf-9ed7-72978a35d201";
    private static final String NOT_EXISTED_EMPLOYEE_UUID = "9771203f-be0a-4ecf-9ed7-72978a35d202";
    private static final String UUID_FOR_EMPLOYEE_NOT_FOUND = "93f30873-6955-403a-b78a-05faca0f69dc";
    private static final String INVALID_UUID = "invalid-uuid";

    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql("classpath:sql/employeeController/getEmployeeInformationById.sql")
    void getEmployeeInformationById_Should_Return200_WhenExistedEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(EMPLOYEE_INFORMATION_ENDPOINT, EXISTED_EMPLOYEE_UUID))
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
                String.format(EmployeeNotFoundException.EMPLOYEE_NOT_FOUND_BY_UUID, NOT_EXISTED_EMPLOYEE_UUID);
        mockMvc.perform(MockMvcRequestBuilders.get(EMPLOYEE_INFORMATION_ENDPOINT, NOT_EXISTED_EMPLOYEE_UUID))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(expectedErrorMessage));
    }

    @Test
    @Sql("classpath:sql/employeeController/employee_update_data.sql")
    void updateEmployee_shouldReturn200_whenCorrectUuidAndDto() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(EMPLOYEE_UPDATE_ENDPOINT, EXISTED_EMPLOYEE_UUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(asJsonString(EmployeeRequestBodyFactory.getValidRequestBody())))
                .andExpect(status().isOk());
    }

    @Test
    @Sql("classpath:sql/employeeController/employee_update_data.sql")
    void updateEmployee_shouldReturn409_whenConflict() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(EMPLOYEE_UPDATE_ENDPOINT, EXISTED_EMPLOYEE_UUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(asJsonString(EmployeeRequestBodyFactory.getRequestBodyWithLoginConflict())))
                .andExpect(status().isConflict());
    }

    @Test
    @Sql("classpath:sql/employeeController/employee_update_data.sql")
    void updateEmployee_shouldReturn400_whenIncorrectRequestData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(EMPLOYEE_UPDATE_ENDPOINT, EXISTED_EMPLOYEE_UUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(asJsonString(EmployeeRequestBodyFactory.getRequestBodyWithIncorrectFields())))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql("classpath:sql/employeeController/employee_update_data.sql")
    void updateEmployee_shouldReturn404_whenUuidNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(EMPLOYEE_UPDATE_ENDPOINT, UUID_FOR_EMPLOYEE_NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(asJsonString(EmployeeRequestBodyFactory.getValidRequestBody())))
                .andExpect(status().isNotFound());
    }

    @Test
    @Sql("classpath:sql/employeeController/employee_update_data.sql")
    void updateEmployee_shouldReturn400_whenUuidIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(EMPLOYEE_UPDATE_ENDPOINT, INVALID_UUID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(asJsonString(EmployeeRequestBodyFactory.getValidRequestBody())))
                .andExpect(status().isBadRequest());
    }
}
