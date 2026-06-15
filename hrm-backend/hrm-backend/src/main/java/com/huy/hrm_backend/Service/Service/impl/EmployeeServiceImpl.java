package com.huy.hrm_backend.Service.Service.impl;

import com.huy.hrm_backend.Dto.EmployeeRequest;
import com.huy.hrm_backend.Dto.EmployeeResponse;
import com.huy.hrm_backend.Entity.Department;
import com.huy.hrm_backend.Entity.Employee;
import com.huy.hrm_backend.Entity.User;
import com.huy.hrm_backend.Exception.EmailAlreadyExistsException;
import com.huy.hrm_backend.Exception.ResourceNotFoundException;
import com.huy.hrm_backend.Repository.DepartmentRepository;
import com.huy.hrm_backend.Repository.EmployeeRepository;
import com.huy.hrm_backend.Repository.UserRepository;
import com.huy.hrm_backend.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Đánh dấu đây là một Spring Service Bean, được quản lý trong Spring Container
@RequiredArgsConstructor // Tự động sinh Constructor nhận tất cả các trường final (Dependency Injection)
public class EmployeeServiceImpl implements EmployeeService {

    // Khai báo các Repository cần thiết dưới dạng final để Spring tự động tiêm (inject) vào
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        // Lấy danh sách thực thể Employee từ database, chuyển đổi từng phần tử thành EmployeeResponse và trả về danh sách
        return employeeRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        // Tìm kiếm nhân viên theo ID, nếu không tìm thấy sẽ ném ra lỗi ResourceNotFoundException
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: " + id));
        return mapToResponse(employee);
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        // Kiểm tra xem email nhân viên mới đã tồn tại dưới DB hay chưa
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }

        // Tạo thực thể Employee mới và ánh xạ dữ liệu từ Request DTO sang
        Employee employee = new Employee();
        employee.setFullName(request.getFullName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setGender(request.getGender());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setAddress(request.getAddress());
        employee.setPositionName(request.getPositionName());
        employee.setSalaryBase(request.getSalaryBase());

        // Lấy thông tin phòng ban từ database và gán cho Employee
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.getDepartmentId()));
        employee.setDepartment(department);

        // Lấy thông tin User tài khoản (nếu có truyền vào) và gán cho Employee
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));
            employee.setUser(user);
        }

        // Lưu thông tin nhân viên xuống database
        Employee savedEmployee = employeeRepository.save(employee);
        return mapToResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        // Tìm kiếm nhân viên hiện tại để cập nhật
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: " + id));

        // Kiểm tra xem email mới có bị trùng với người khác hay không (loại trừ chính bản thân nhân viên này)
        if (employeeRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new EmailAlreadyExistsException("Email already exists: " + request.getEmail());
        }

        // Cập nhật thông tin cơ bản
        employee.setFullName(request.getFullName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setGender(request.getGender());
        employee.setDateOfBirth(request.getDateOfBirth());
        employee.setAddress(request.getAddress());
        employee.setPositionName(request.getPositionName());
        employee.setSalaryBase(request.getSalaryBase());

        // Tìm kiếm và gán lại Phòng ban liên kết mới
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + request.getDepartmentId()));
        employee.setDepartment(department);

        // Tìm kiếm và gán lại User tài khoản mới (nếu có truyền vào)
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getUserId()));
            employee.setUser(user);
        } else {
            employee.setUser(null); // Nếu không truyền userId thì hủy liên kết tài khoản
        }

        // Lưu thông tin nhân viên đã cập nhật
        Employee updatedEmployee = employeeRepository.save(employee);
        return mapToResponse(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        // Tìm nhân viên trước khi xóa, nếu không thấy thì báo lỗi
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    // Hàm phụ trợ (helper method) chuyển đổi từ Entity sang DTO Response để trả về cho Client
    private EmployeeResponse mapToResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFullName(employee.getFullName());
        response.setEmail(employee.getEmail());
        response.setPhone(employee.getPhone());
        response.setGender(employee.getGender());
        response.setDateOfBirth(employee.getDateOfBirth());
        response.setAddress(employee.getAddress());
        response.setPositionName(employee.getPositionName());
        response.setSalaryBase(employee.getSalaryBase());

        // Lấy thông tin từ thực thể Department liên kết để đưa vào Response DTO
        if (employee.getDepartment() != null) {
            response.setDepartmentId(employee.getDepartment().getId());
            response.setDepartmentName(employee.getDepartment().getName());
        }

        // Lấy thông tin từ thực thể User liên kết để đưa vào Response DTO
        if (employee.getUser() != null) {
            response.setUserId(employee.getUser().getId());
            response.setUserName(employee.getUser().getUserName());
        }

        return response;
    }
}
