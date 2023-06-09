package ru.aston.request;

import lombok.Data;

/**
 * Search criteria for employee search with partial surname match and filter by status and role.
 */
@Data
public class EmployeeSearchCriteria {

    /**
     * Search by status
     */
    private String status;

    /**
     * Search by role
     */
    private String role;

    /**
     * Sorting parameter
     */
    private String sort;

    /**
     * String for partial surname match
     */
    private String surname;

    /**
     * Current page
     */
    private Integer page;
}
