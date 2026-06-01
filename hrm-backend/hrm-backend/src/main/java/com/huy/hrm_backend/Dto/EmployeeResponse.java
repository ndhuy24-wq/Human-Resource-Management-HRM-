package com.huy.hrm_backend.Dto;
import lombok.Data;

@Data
public class EmployeeResponse {
    private Long id;
    private String fullName;
    private  String email;
    private String Phone;
    private  String positionName;
}
