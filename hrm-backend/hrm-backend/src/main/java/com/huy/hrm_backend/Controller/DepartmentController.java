package com.huy.hrm_backend.Controller;

import com.huy.hrm_backend.Dto.DepartmentRequest;
import com.huy.hrm_backend.Dto.DepartmentResponse;
import com.huy.hrm_backend.Entity.Department;
import com.huy.hrm_backend.Service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentResponse> getAllDepartment(){
        return departmentService.getAllDepartments();
    }


    @GetMapping("/{id}")
    public DepartmentResponse getDepartmentById(@PathVariable Long id){
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public DepartmentResponse createDepartment(@Valid @RequestBody DepartmentRequest request){
        Department department = new Department();

        department.setName(request.getName());
        department.setDescription(request.getDescription());

        return departmentService.createDepartment(department);
    }

    @PutMapping("/{id}")
    public DepartmentResponse updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentRequest request ){

        Department department = new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());

        return departmentService.updateDepartment(id, department);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment (@PathVariable Long id){
        departmentService.deleteDepartment(id);

        return "Delete departmant successfuly";
    }
}

