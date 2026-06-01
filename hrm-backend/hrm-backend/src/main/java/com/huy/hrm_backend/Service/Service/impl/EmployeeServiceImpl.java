package com.huy.hrm_backend.Service.Service.impl;

import com.huy.hrm_backend.Entity.Employee;
import com.huy.hrm_backend.Repository.EmployeeRepository;
import com.huy.hrm_backend.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
 private final  EmployeeRepository employeeRepository;
 @Override
 public List<Employee> getAllEmployees(){
  return employeeRepository.findAll();
 }
 @Override
 public Employee getEmployeeById (Long id){
  return employeeRepository.findById(id).orElse(null);
 }
@Override
public  Employee createEmployee(Employee employee){
  return employeeRepository.save(employee);
}
@Override
public Employee updateEmployeeById(Long id, Employee employeeRequest){
  Employee employee = employeeRepository.findById(id).orElse(null);
  if(employee == null){
   return null;
  }
 employee.setFullName(employeeRequest.getFullName());
 employee.setEmail(employeeRequest.getEmail());
 employee.setPhone(employeeRequest.getPhone());
 employee.setGender(employeeRequest.getGender());
 employee.setDateOfBirth(employeeRequest.getDateOfBirth());
 employee.setAddress(employeeRequest.getAddress());
 employee.setPositionName(employeeRequest.getPositionName());
 employee.setSalaryBase(employeeRequest.getSalaryBase());
 employee.setDepartmentId(employeeRequest.getDepartmentId());
 employee.setUserId(employeeRequest.getUserId());

 return employeeRepository.save(employee);
}
@Override
 public void deleteEmployee(Long id){
  employeeRepository.deleteById(id);
}
}
