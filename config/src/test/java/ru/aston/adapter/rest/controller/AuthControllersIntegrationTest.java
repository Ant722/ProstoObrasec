package ru.aston.adapter.rest.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.aston.adapter.rest.AbstractIntegrationTest;
import ru.aston.entity.factory.AuthRequestBodyFactory;
import ru.aston.exception.EmployeeNotFoundException;
import ru.aston.exception.WrongPasswordException;

import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.aston.JsonStringConverter.asJsonString;

@Transactional
class AuthControllersIntegrationTest extends AbstractIntegrationTest {

    private static final String AUTH_CONTROLLER_LOGIN_ENDPOINT = "/api/v1/admin";
    private static final String NOT_EXISTED_EMPLOYEE_LOGIN = "not_existed_login";

    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql("classpath:sql/employeeController/getEmployeeInformationById.sql")
    void authController_Should_ReturnOk_WhenExistedEmployee() throws Exception {
        mockMvc.perform(post(AUTH_CONTROLLER_LOGIN_ENDPOINT)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(AuthRequestBodyFactory.getValidAuthLoginAndPassword())))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.uuid").value("9771203f-be0a-4ecf-9ed7-72978a35d201"))
                .andExpect(jsonPath("$.password").value("P@ssw0rd123"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    @Sql("classpath:sql/employeeController/getEmployeeInformationById.sql")
    void authController_Should_Return404_WhenNotExistedLogin() throws Exception {
        String expectedErrorMessage =
                String.format(EmployeeNotFoundException.NOT_FOUND_BY_LOGIN_MESSAGE, NOT_EXISTED_EMPLOYEE_LOGIN);
        mockMvc.perform(post(AUTH_CONTROLLER_LOGIN_ENDPOINT)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(AuthRequestBodyFactory.getAuthBodyWithNotExistedLogin())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(expectedErrorMessage));
    }

    @Test
    @Sql("classpath:sql/employeeController/getEmployeeInformationById.sql")
    void authController_Should_Return404_WhenNotExistedPassword() throws Exception {
        String expectedErrorMessage =
                String.format(WrongPasswordException.WRONG_PASSWORD_MESSAGE);
        mockMvc.perform(post(AUTH_CONTROLLER_LOGIN_ENDPOINT)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(AuthRequestBodyFactory.getAuthBodyWithNotExistedPassword())))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errorMessage").value(expectedErrorMessage));
    }

    @Test
    @Sql("classpath:sql/employeeController/getEmployeeInformationById.sql")
    void authController_Should_Return400_WhenInvalidFormatPassword() throws Exception {
        mockMvc.perform(post(AUTH_CONTROLLER_LOGIN_ENDPOINT)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(AuthRequestBodyFactory.getAuthBodyWithInvalidPasswordFormat())))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource("getInvalidPassword")
    void authController_ShouldReturn_400_WhenPasswordIsInvalid(String password) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(AUTH_CONTROLLER_LOGIN_ENDPOINT)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(asJsonString(AuthRequestBodyFactory.getAuthBodyWithVariablePassword(password))))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    private static Stream<String> getInvalidPassword() {
        return Stream.of(
                "123",
                "1qW@",
                "@#$#@#$",
                "1234567",
                "234@#$#3",
                "ASDEWGFSSD",
                "SDFS@#$@",
                "SDFS2345",
                "GGW@#$234",
                "sdfwergsd",
                "sdfsd@#$@",
                "sdff2345",
                "sdf234@#$",
                "sdfEWR",
                "sdfSDF@#$",
                "sdfSDF224");
    }
}
