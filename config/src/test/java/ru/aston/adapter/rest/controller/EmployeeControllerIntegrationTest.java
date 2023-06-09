package ru.aston.adapter.rest.controller;

import jakarta.transaction.Transactional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.aston.adapter.rest.AbstractIntegrationTest;
import ru.aston.exception.EmployeeNotFoundException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class EmployeeControllerIntegrationTest extends AbstractIntegrationTest {

    private static final String EMPLOYEE_INFORMATION_ENDPOINT = "/api/v1/employee/{uuid}";
    private static final String SEARCH_EMPLOYEE_BY_SURNAME_ENDPOINT = "/api/v1/employee";

    private static final String EXISTED_EMPLOYEE_UUID = "9771203f-be0a-4ecf-9ed7-72978a35d201";
    private static final String NOT_EXISTED_EMPLOYEE_UUID = "9771203f-be0a-4ecf-9ed7-72978a35d202";

    private static final String ACTIVE_STATUS = "ACTIVE";
    private static final String TRANSFERRED_STATUS = "TRANSFERRED";
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String PRODUCT_MANAGER_ROLE = "PRODUCT_MANAGER";

    @Autowired
    MockMvc mockMvc;

    @Value("${page.default-size}")
    private int defaultPageSize;

    @Test
    @Sql("classpath:sql/employeeController/getEmployeeInformationById.sql")
    void getEmployeeInformationById_Should_Return200_WhenExistedEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(EMPLOYEE_INFORMATION_ENDPOINT, EXISTED_EMPLOYEE_UUID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname").value("Ivanov"))
                .andExpect(jsonPath("$.name").value("Ivan"))
                .andExpect(jsonPath("$.middleName").value("Ivanovich"))
                .andExpect(jsonPath("$.role").value(ADMIN_ROLE))
                .andExpect(jsonPath("$.login").value("login"))
                .andExpect(jsonPath("$.status").value(ACTIVE_STATUS))
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
    @Sql("classpath:sql/employeeController/searchEmployeesByUsername.sql")
    void searchEmployeesByUsername_Should_Return_allActiveAdminEmployees() throws Exception {
        int currentPage = 1;
        int recordsCount = 100;
        int lastRecord = Math.min(defaultPageSize, recordsCount);
        mockMvc.perform(MockMvcRequestBuilders.get(SEARCH_EMPLOYEE_BY_SURNAME_ENDPOINT)
                        .param("status", ACTIVE_STATUS)
                        .param("role", ADMIN_ROLE)
                        .param("sort", "name")
                        .param("page", String.valueOf(currentPage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info.count").value(recordsCount))
                .andExpect(jsonPath("$.info.pages")
                        .value((int) Math.ceil((double) recordsCount / defaultPageSize)))
                .andExpect(jsonPath("$.result.[0].role").value(ADMIN_ROLE))
                .andExpect(jsonPath("$.result.[0].status").value(ACTIVE_STATUS))
                .andExpect(jsonPath("$.result.[%d].role", lastRecord - 1).value(ADMIN_ROLE))
                .andExpect(jsonPath("$.result.[%d].status", lastRecord - 1).value(ACTIVE_STATUS))
                .andExpect(jsonPath("$.result.[%d]", lastRecord).doesNotExist());
    }

    @Test
    @Sql("classpath:sql/employeeController/searchEmployeesByUsername.sql")
    void searchEmployeesByUsername_Should_Return_allTransferredProductManager_whereSurnameContains() throws Exception {
        int currentPage = 1;
        int recordsCount = 10;
        int lastRecord = Math.min(defaultPageSize, recordsCount);
        mockMvc.perform(MockMvcRequestBuilders.get(SEARCH_EMPLOYEE_BY_SURNAME_ENDPOINT)
                        .param("status", TRANSFERRED_STATUS)
                        .param("role", PRODUCT_MANAGER_ROLE)
                        .param("sort", "name")
                        .param("surname", "urname11")
                        .param("page", String.valueOf(currentPage)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.info.count").value(recordsCount))
                .andExpect(jsonPath("$.info.pages")
                        .value((int) Math.ceil((double) recordsCount / defaultPageSize)))
                .andExpect(jsonPath("$.result.[0].role").value(PRODUCT_MANAGER_ROLE))
                .andExpect(jsonPath("$.result.[0].status").value(TRANSFERRED_STATUS))
                .andExpect(jsonPath("$.result.[0].surname")
                        .value(Matchers.containsStringIgnoringCase("urname11")))
                .andExpect(jsonPath("$.result.[%d].role", lastRecord - 1).value(PRODUCT_MANAGER_ROLE))
                .andExpect(jsonPath("$.result.[%d].status", lastRecord - 1).value(TRANSFERRED_STATUS))
                .andExpect(jsonPath("$.result.[%d].surname",lastRecord - 1)
                        .value(Matchers.containsStringIgnoringCase("urname11")))
                .andExpect(jsonPath("$.result.[%d]", lastRecord).doesNotExist());
    }

    @Test
    @Sql("classpath:sql/employeeController/searchEmployeesByUsername.sql")
    void searchEmployeesByUsername_Should_Return400_whenInvalidSortField() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(SEARCH_EMPLOYEE_BY_SURNAME_ENDPOINT)
                        .param("status", ACTIVE_STATUS)
                        .param("role", ADMIN_ROLE)
                        .param("sort", "invalidSortField")
                        .param("page", "1"))
                .andExpect(status().isBadRequest());
    }
}
