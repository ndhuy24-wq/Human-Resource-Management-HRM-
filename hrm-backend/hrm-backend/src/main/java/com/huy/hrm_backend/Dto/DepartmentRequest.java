package com.huy.hrm_backend.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class DepartmentRequest {
   @NotBlank (message = "DepartmentName is required")
   @Size(max = 100, message = "Department name must not exceed 100 characters")
    private String name;

   private String description;
}

