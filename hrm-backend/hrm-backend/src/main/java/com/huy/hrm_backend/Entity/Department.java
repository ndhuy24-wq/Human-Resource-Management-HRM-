package com.huy.hrm_backend.Entity;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "DEPARTMENTS")
public class Department {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE , generator = "department_seq") // tự sinh id
    @SequenceGenerator(
            name = "department_seq",
            sequenceName ="DEPARTMENTS_seq", //Map với Department_seq Oracle
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;
}
