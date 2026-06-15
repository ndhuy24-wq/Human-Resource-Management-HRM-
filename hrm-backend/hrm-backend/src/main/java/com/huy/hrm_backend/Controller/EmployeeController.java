package com.huy.hrm_backend.Controller;

import com.huy.hrm_backend.Dto.EmployeeRequest;
import com.huy.hrm_backend.Dto.EmployeeResponse;
import com.huy.hrm_backend.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Đánh dấu lớp này là một Controller trong mô hình RESTful API
@RequestMapping("/api/employees") // Định nghĩa đường dẫn API gốc cho các nghiệp vụ liên quan đến Nhân viên
@RequiredArgsConstructor // Tự động sinh constructor nhận tham số cho các biến final (Inject Service)
@CrossOrigin("*") // Cho phép mọi nguồn (Domain/Frontend) truy cập vào API này (Tránh lỗi CORS)
public class EmployeeController {

    private final EmployeeService employeeService;

    // API lấy toàn bộ danh sách nhân viên
    // Method: GET
    // URL: http://localhost:8080/api/employees
    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // API lấy chi tiết một nhân viên dựa trên ID
    // Method: GET
    // URL: http://localhost:8080/api/employees/{id}
    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // API tạo mới một nhân viên
    // Method: POST
    // URL: http://localhost:8080/api/employees
    // @Valid: Kích hoạt kiểm tra dữ liệu đầu vào (Validation) khai báo trong EmployeeRequest
    // @RequestBody: Chuyển đổi dữ liệu JSON từ Client gửi lên thành đối tượng Java DTO
    @PostMapping
    public EmployeeResponse createEmployee(@Valid @RequestBody EmployeeRequest request) {
        // Ủy quyền trực tiếp cho Service xử lý logic nghiệp vụ và trả về kết quả
        return employeeService.createEmployee(request);
    }

    // API cập nhật thông tin nhân viên theo ID
    // Method: PUT
    // URL: http://localhost:8080/api/employees/{id}
    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(
            @PathVariable Long id, // Lấy ID của nhân viên cần sửa từ URL
            @Valid @RequestBody EmployeeRequest request // Lấy thông tin cập nhật mới từ JSON body
    ) {
        // Ủy quyền trực tiếp cho Service cập nhật dữ liệu và trả về kết quả
        return employeeService.updateEmployee(id, request);
    }

    // API xóa nhân viên theo ID
    // Method: DELETE
    // URL: http://localhost:8080/api/employees/{id}
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Delete employee successfully"; // Trả về thông báo xóa thành công dạng chuỗi
    }
}