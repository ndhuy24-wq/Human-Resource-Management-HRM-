package com.huy.hrm_backend.Dto;

import lombok.Data;
import java.time.LocalDate;

@Data // Tự động tạo getter, setter, toString cho DTO này
public class EmployeeResponse {
    
    private Long id; // ID của nhân viên

    private String fullName; // Họ và tên đầy đủ

    private String email; // Email liên hệ

    private String phone; // Số điện thoại

    private String gender; // Giới tính

    private LocalDate dateOfBirth; // Ngày sinh

    private String address; // Địa chỉ thường trú

    private String positionName; // Chức vụ / vị trí công việc

    private Double salaryBase; // Lương cơ bản

    private Long departmentId; // ID phòng ban liên kết

    private String departmentName; // Tên phòng ban để Frontend hiển thị trực tiếp

    private Long userId; // ID tài khoản liên kết (nếu có)

    private String userName; // Tên tài khoản người dùng
}
