package com.huy.hrm_backend.Service;

import com.huy.hrm_backend.Dto.EmployeeRequest;
import com.huy.hrm_backend.Dto.EmployeeResponse;
import java.util.List;

// Interface định nghĩa các nghiệp vụ liên quan đến Nhân viên
public interface EmployeeService {
    
    // Lấy danh sách toàn bộ nhân viên
    List<EmployeeResponse> getAllEmployees();

    // Tìm kiếm nhân viên theo ID
    EmployeeResponse getEmployeeById(Long id);

    // Tạo mới nhân viên từ thông tin DTO gửi từ client
    EmployeeResponse createEmployee(EmployeeRequest request);

    // Cập nhật thông tin nhân viên theo ID và DTO mới
    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);

    // Xóa nhân viên theo ID
    void deleteEmployee(Long id);
}
