package com.huy.hrm_backend.Service;

import com.huy.hrm_backend.Entity.Department;

import com.huy.hrm_backend.Dto.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse getDepartmentById(Long id);

    DepartmentResponse createDepartment(Department department);

    DepartmentResponse updateDepartment(Long id, Department department);

   void deleteDepartment(Long id);

}
