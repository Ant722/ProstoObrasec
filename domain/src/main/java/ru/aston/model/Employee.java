package ru.aston.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.aston.model.enumeration.EmployeeRole;
import ru.aston.model.enumeration.EmployeeStatus;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    /**
     * Employee primary key. Generated by incrementation strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * UUID of employee. Used to connect to other services.
     * Must be unique and not null.
     */
    @Column(name = "uuid", nullable = false, unique = true)
    private UUID uuid;

    /**
     * Surname of employee. Represented as a 30-character string.
     * Can not be null.
     */
    @Column(name = "surname", nullable = false)
    private String surname;

    /**
     * Name of employee. Represented as a 30-character string.
     * Can not be null.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Middle name of employee. Represented as a 30-character string.
     * Can not be null.
     */
    @Column(name = "middle_name", nullable = false)
    private String middleName;

    /**
     * Login is a part of credentials used to access a service.
     * Represented as a 30-character string.
     * Must be unique and not null.
     */
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    /**
     * Password is a part of credentials used to access a service.
     * Represented as a 20-character string.
     * Can not be null.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Identification number of employees passport.
     * Can not be null.
     */
    @Column(name = "passport_id", nullable = false)
    private String passportId;

    /**
     * Employee passport issued date.
     * Can not be null.
     */
    @Column(name = "passport_date_issue", nullable = false)
    private LocalDate passportDateIssue;

    /**
     * Employee role.
     * Can not be null.
     * @see EmployeeRole
     */
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    /**
     * Employee status.
     * Can not be null.
     * @see EmployeeStatus
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    /**
     * Time of creation. Represent as a timestamp.
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    /**
     * Time of the last modification. Represent as a timestamp.
     */
    @Column(name = "modified_at")
    @UpdateTimestamp
    private Instant modifiedAt;
}
