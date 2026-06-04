package com.huy.hrm_backend.Dto;

import lombok.Data;

@Data
public class UserResponse {
    private Long id;

    private  String userName;

    private String roleName;

    private Integer status;
}
