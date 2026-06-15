package com.huy.hrm_backend.Controller;

import com.huy.hrm_backend.Dto.DepartmentRequest;
import com.huy.hrm_backend.Dto.DepartmentResponse;
import com.huy.hrm_backend.Entity.Department;
import com.huy.hrm_backend.Service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Định nghĩa lớp này là một Controller cung cấp các REST API
@RequestMapping("/api/departments") // Đường dẫn API gốc cho quản lý Phòng ban
@RequiredArgsConstructor // Tự động inject DepartmentService qua Constructor
@CrossOrigin("*") // Cho phép gọi API từ mọi nguồn (Tránh CORS policy)
public class DepartmentController {
    
    private final DepartmentService departmentService;

    // API lấy toàn bộ danh sách phòng ban
    // URL: GET http://localhost:8080/api/departments
    @GetMapping
    public List<DepartmentResponse> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    // API lấy thông tin chi tiết một phòng ban theo ID
    // URL: GET http://localhost:8080/api/departments/{id}
    @GetMapping("/{id}")
    public DepartmentResponse getDepartmentById(@PathVariable Long id){
        return departmentService.getDepartmentById(id);
    }

    // API tạo mới một phòng ban
    // URL: POST http://localhost:8080/api/departments
    @PostMapping
    public DepartmentResponse createDepartment(@Valid @RequestBody DepartmentRequest request){
        Department department = new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());

        return departmentService.createDepartment(department);
    }

    // API sửa thông tin phòng ban theo ID
    // URL: PUT http://localhost:8080/api/departments/{id}
    @PutMapping("/{id}")
    public DepartmentResponse updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentRequest request ){
        Department department = new Department();
        department.setName(request.getName());
        department.setDescription(request.getDescription());

        return departmentService.updateDepartment(id, department);
    }

    // API xóa phòng ban theo ID
    // URL: DELETE http://localhost:8080/api/departments/{id}
    @DeleteMapping("/{id}")
    public String deleteDepartment (@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return "Delete department successfully";
    }
}

