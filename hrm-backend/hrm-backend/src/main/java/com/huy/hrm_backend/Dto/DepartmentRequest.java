package com.huy.hrm_backend.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class DepartmentRequest {
   @NotBlank (message = "DepartmentName is required")
    private String name;

   private String description;
}

