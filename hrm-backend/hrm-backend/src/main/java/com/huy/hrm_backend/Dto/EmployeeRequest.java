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
    @Pattern(
            regexp = "^(0[0-9]{9,10})$",
            message = "Phone number is invalid"
    )
    private  String phone;

    @NotBlank(message = "Gender is required")
    private  String gender;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private String address;

    private  String positionName;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary must be greater than 0")
    private  Double salaryBase;

    @NotNull(message = "Department is required")
    private Long departmentId;

    private Long userId;

}
