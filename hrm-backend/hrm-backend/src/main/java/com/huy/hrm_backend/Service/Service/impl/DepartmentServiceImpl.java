package com.huy.hrm_backend.Service.Service.impl;

import com.huy.hrm_backend.Dto.DepartmentResponse;
import com.huy.hrm_backend.Entity.Department;
import com.huy.hrm_backend.Exception.ResourceNotFoundException;
import com.huy.hrm_backend.Repository.DepartmentRepository;
import com.huy.hrm_backend.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentResponse mapToResponse (Department department){
        DepartmentResponse response = new DepartmentResponse();
        response.setId(department.getId());
        response.setName(department.getName());
        response.setDescription(department.getDescription());
        return response;
    }

    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> getAllDepartments(){
        return departmentRepository.findAll() .stream() .map(this::mapToResponse).toList();
    }

    @Override
    public DepartmentResponse getDepartmentById (Long id){
        Department department = departmentRepository.findById(id).orElseThrow(()->
        new ResourceNotFoundException(
                "Department not found with id: " + id
        ));
        return mapToResponse(department);
    }
    @Override
    public DepartmentResponse createDepartment (Department department){
        Department savedDepartment = departmentRepository.save(department);
        return mapToResponse(savedDepartment);
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, Department departmentRequest){
        Department department  = departmentRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(
                        "Department not found with id: " + id
                )
                );
        department.setName(departmentRequest.getName());
        department.setDescription(departmentRequest.getDescription());

        Department updatedDepartment = departmentRepository.save(department);

        return mapToResponse(updatedDepartment);
    }
    @Override
    public  void  deleteDepartment(Long id){
        Department department = departmentRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Department not found with id: " + id
                )
                );
        departmentRepository.delete(department);
    }
}
