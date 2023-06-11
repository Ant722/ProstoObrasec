package ru.aston.adapter.rest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.aston.dto.request.EmployeeUpdateDto;
import ru.aston.facade.EmployeeFacade;
import ru.aston.rest.controller.EmployeeController;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static ru.aston.JsonStringConverter.asJsonString;

@WebMvcTest(EmployeeController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeControllerTest {

    @MockBean
    private EmployeeFacade employeeFacade;

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    private static final String EMPLOYEE_INFORMATION_ENDPOINT = "/api/v1/employee/{id}";

    private final String EMPLOYEE_UPDATE_ENDPOINT = "/api/v1/employee/{employee_uuid}";

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

    @ParameterizedTest
    @MethodSource("getInvalidEmployeeIdStream")
    void updateEmployee_shouldReturn400_whenEmployeeWrongUuid(String uuid) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put(EMPLOYEE_UPDATE_ENDPOINT, uuid))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateEmployee_shouldReturn400_whenEmployeeWrongDto() throws Exception {
        String uuid = "6bf20ff0-fa1e-4a2d-ac8a-8609914c575f";
        EmployeeUpdateDto employeeUpdateDto = new EmployeeUpdateDto();
        mockMvc.perform(MockMvcRequestBuilders.put(EMPLOYEE_UPDATE_ENDPOINT, uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employeeUpdateDto)))
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
}
