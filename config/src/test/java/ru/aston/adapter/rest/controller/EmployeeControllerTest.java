package ru.aston.adapter.rest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import ru.aston.facade.EmployeeFacade;
import ru.aston.rest.controller.EmployeeController;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebMvcTest(EmployeeController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeControllerTest {

    @MockBean
    private EmployeeFacade employeeFacade;

    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    private static final String EMPLOYEE_INFORMATION_ENDPOINT = "/api/v1/employee/{id}";

    private static final String SEARCH_EMPLOYEE_BY_USERNAME_ENDPOINT = "/api/v1/employee";

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
    @MethodSource("getInvalidSearchRequestStream")
    void searchEmployeesByUsername_ShouldReturn_400_While_InvalidRequest(MultiValueMap<String, String> paramMap)
            throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(SEARCH_EMPLOYEE_BY_USERNAME_ENDPOINT)
                        .params(paramMap))
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

    private Stream<MultiValueMap<String, String>> getInvalidSearchRequestStream() {
        LinkedMultiValueMap<String, String> invalidStatus = new LinkedMultiValueMap<>();
        invalidStatus.add("status", "invalidStatus");
        invalidStatus.add("role", "ADMIN");
        invalidStatus.add("sort", "name");
        invalidStatus.add("page", "1");

        LinkedMultiValueMap<String, String> invalidRole = new LinkedMultiValueMap<>();
        invalidRole.add("status", "ACTIVE");
        invalidRole.add("role", "invalidRole");
        invalidRole.add("sort", "name");
        invalidRole.add("page", "1");

        LinkedMultiValueMap<String, String> invalidPage = new LinkedMultiValueMap<>();
        invalidPage.add("status", "ACTIVE");
        invalidPage.add("role", "ADMIN");
        invalidPage.add("sort", "name");
        invalidPage.add("page", "-1");

        LinkedMultiValueMap<String, String> nullParams = new LinkedMultiValueMap<>();

        return Stream.of(invalidStatus, invalidRole, invalidPage, nullParams);
    }

    private MockMvc getMockMvc(){
        return mockMvc = webAppContextSetup(wac).build();
    }
}
