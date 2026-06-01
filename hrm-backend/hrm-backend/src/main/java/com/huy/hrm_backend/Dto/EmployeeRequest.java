package com.huy.hrm_backend.Dto;
import lombok.Data;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Data
public class EmployeeRequest {
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Email invalid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank (message="Phone is requiredd")
    @Size(min = 10, max = 11, message = "Phone must be 10 - 11 number")
    private  String phone;

    @NotBlank(message = "Gender is required")
    private  String gender;

    private LocalDate dateOfBirth;

    private String address;

    private  String positionName;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be greater than 0")
    private  Double salaryBase;

    private Long departmentId;

    private Long userID;

}
