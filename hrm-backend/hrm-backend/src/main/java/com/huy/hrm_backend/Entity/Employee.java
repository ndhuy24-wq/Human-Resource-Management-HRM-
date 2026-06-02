package com.huy.hrm_backend.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "EMPLOYEES")
public class Employee {
   @Id
   @GeneratedValue (strategy = GenerationType.SEQUENCE,generator = "employee_seq") //Tự generate ID.
   @SequenceGenerator(
           name = "employee_seq",
           sequenceName = "EMPLOYEES_SEQ", //Map với Oracle sequence
           allocationSize = 1
   )
    @Column(name = "ID")
    private long id;

   @Column (name = "FULL_NAME")
    private String fullName;

   @Column(name = "EMAIL")
    private String email;

   @Column(name = "PHONE")
    private String phone;

   @Column (name ="GENDER")
    private String gender;

   @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

   @Column(name = "ADDRESS")
    private String address;

   @Column(name ="POSITION_NAME")
    private String positionName;

   @Column (name = "SALARY_BASE")
    private double salaryBase;

   @Column (name = "DEPARTMENT_ID")
    private Long departmentId;

   @Column(name ="USER_ID")
    private Long userId;
}
