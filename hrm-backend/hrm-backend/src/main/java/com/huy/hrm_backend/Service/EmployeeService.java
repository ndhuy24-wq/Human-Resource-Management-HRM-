package com.huy.hrm_backend.Service;
import com.huy.hrm_backend.Entity.Employee;
import  java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById (Long id);
    Employee createEmployee (Employee employee);
    Employee updateEmployeeById(Long id, Employee employeeRequest);

    void deleteEmployee(Long id);
}
