package ru.aston.adapter.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.aston.dto.request.EmployeeCreateDto;
import ru.aston.facade.EmployeeFacade;
import ru.aston.rest.controller.EmployeeController;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static ru.aston.adapter.rest.controller.dtoFactory.EmployeeControllerTestDtoFactory.getNevValidRegistrationEmployeeRequestDto;

@WebMvcTest(EmployeeController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeControllerTest {

    @MockBean
    private EmployeeFacade employeeFacade;

    @Autowired
    protected WebApplicationContext wac;
    private MockMvc mockMvc;



    private static final String REGISTRATION_EMPLOYEE_ENDPOINT = "/api/v1/employee/";
    private static final String EMPLOYEE_INFORMATION_ENDPOINT = "/api/v1/employee/{id}";

    @BeforeEach
    void setUp() {
        mockMvc = getMockMvc();
    }

    @ParameterizedTest
    @MethodSource("getInvalidEmployeeIdStream")
    void getEmployeeInformationById_ShouldReturn_400_While_InvalidId(String id) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(EMPLOYEE_INFORMATION_ENDPOINT, id))
                .andExpect(status().isBadRequest());
    }

    private static Stream<String> getInvalidEmployeeIdStream() {
        return Stream.of(
                "-9",
                "abc",
                "122q1",
                "100",
                "fc581e6g-f87e-11ed-b67e-0242ac120002",
                "fc581e6e-f87e-11ed-b67e-0242ac1200021");
    }

    private MockMvc getMockMvc(){
        return mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    void createNewEmployee_ShouldReturn200_WhenAddNewEmployee() throws Exception {
        EmployeeCreateDto createDto = getNevValidRegistrationEmployeeRequestDto();
        var builder = post(REGISTRATION_EMPLOYEE_ENDPOINT).
                contentType(MediaType.APPLICATION_JSON).
                content("""
                    {                                                                        
                        "surname":"ihover",
                        "name": "IHOR",
                        "middleName":"MIDL",
                        "roleDto":"ADMIN",
                        "login":"testlogin",
                        "statusDto":"ACTIVE",
                        "passportId":"testpass",
                        "passportDateIssue":"21.09.2002"                                    
                    }""");
        this.mockMvc.perform(builder)
                .andExpect(status().isOk());
    }
}

