package ru.aston.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.aston.model.enumeration.EmployeeRole;


@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    /**
     * Role primary key. Generated by incrementation strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;


    /**
     * String representation of role.
     *
     * @see EmployeeRole
     */
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private EmployeeRole name;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
