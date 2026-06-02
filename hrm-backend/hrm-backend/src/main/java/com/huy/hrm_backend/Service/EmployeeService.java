package com.huy.hrm_backend.Service;
import com.huy.hrm_backend.Dto.EmployeeResponse;
import com.huy.hrm_backend.Entity.Employee;
import  java.util.List;

public interface EmployeeService {
    List<EmployeeResponse> getAllEmployees();

    EmployeeResponse getEmployeeById (Long id);


    EmployeeResponse  createEmployee (Employee employee);


    EmployeeResponse  updateEmployee(Long id, Employee employeeRequest);

    void deleteEmployee(Long id);
}
