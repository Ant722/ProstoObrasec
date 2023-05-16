package ru.aston.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.aston.model.enumeration.EmployeeStatus;

@Entity
@Table(name = "status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    /**
     * Status primary key. Generated by incrementation strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer id;

    /**
     * String representation of status.
     *
     * @see EmployeeStatus
     */
    @Column(name = "status_name")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus name;
}
