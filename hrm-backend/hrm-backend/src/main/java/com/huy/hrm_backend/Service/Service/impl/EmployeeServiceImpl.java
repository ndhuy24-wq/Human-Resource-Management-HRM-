package com.huy.hrm_backend.Service.Service.impl;

import com.huy.hrm_backend.Dto.EmployeeResponse;
import com.huy.hrm_backend.Entity.Employee;
import com.huy.hrm_backend.Exception.EmailAlreadyExistsException;
import com.huy.hrm_backend.Exception.ResourceNotFoundException;
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
    public List<EmployeeResponse> getAllEmployees(){
        return employeeRepository.findAll() .stream() .map(this::mapToResponse).toList();
    }


    @Override
    public EmployeeResponse getEmployeeById (Long id ){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(
                        "Employee not found with id: " + id
                ));
        return mapToResponse(employee);
    }


    @Override
    public EmployeeResponse createEmployee(Employee employee) {
        if(employeeRepository.existsByEmail(employee.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        Employee savedEmployee = employeeRepository.save(employee);

        return mapToResponse(savedEmployee);
    }


    @Override
    public EmployeeResponse updateEmployee(Long id, Employee employeeRequest){
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Employee not found with id: " + id
                )
        );
        if (employeeRepository.existsByEmailAndIdNot(
                employeeRequest.getEmail(),id
        )){
            throw new EmailAlreadyExistsException(
                    "Email already exists"
            );
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

        Employee updatedEmployee = employeeRepository.save(employee);

        return mapToResponse(updatedEmployee);
    }
    @Override
    public void deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Employee not found with id: " + id
                )
        );
        employeeRepository.delete(employee);

    }

    private EmployeeResponse mapToResponse(Employee employee){
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFullName(employee.getFullName());
        response.setEmail(employee.getEmail());
        response.setPhone(employee.getPhone());
        response.setPositionName(employee.getPositionName());
        return response;
    }
}
