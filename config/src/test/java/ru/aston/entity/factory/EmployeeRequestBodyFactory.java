package ru.aston.entity.factory;

public class EmployeeRequestBodyFactory {

    private String status;
    private String role;
    private String name;
    private String surname;
    private String middleName;
    private String login;
    private String passport;
    private String passportDateIssue;

    private EmployeeRequestBodyFactory(String status, String role, String name, String surname, String middleName, String login, String passport, String passportDateIssue) {
        this.status = status;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.login = login;
        this.passport = passport;
        this.passportDateIssue = passportDateIssue;
    }

    public static EmployeeRequestBodyFactory getValidRequestBody() {
        return new EmployeeRequestBodyFactory(
                "ACTIVE",
                "CLIENT_MANAGER",
                "Name",
                "Surname",
                "MiddleName",
                "login",
                "111 333666",
                "10.10.2022"
        );
    }

    public static EmployeeRequestBodyFactory getRequestBodyWithLoginConflict() {
        return new EmployeeRequestBodyFactory(
                "ACTIVE",
                "CLIENT_MANAGER",
                "Name",
                "Surname",
                "MiddleName",
                "i.ivanov",
                "111 333666",
                "10.10.2022"
        );
    }

    public static EmployeeRequestBodyFactory getRequestBodyWithIncorrectFields() {
        return new EmployeeRequestBodyFactory(
                "ACTIVE",
                "CLIENT_MANAGER",
                "NameIsTooLongggggggggggggggggggg",
                "SurnameIsTooLonggggggggggggggggg",
                "MiddleName",
                "l.ogin",
                "111 333666isTooLong",
                "10.10.2022"
        );
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassportDateIssue() {
        return passportDateIssue;
    }

    public void setPassportDateIssue(String passportDateIssue) {
        this.passportDateIssue = passportDateIssue;
    }
}
