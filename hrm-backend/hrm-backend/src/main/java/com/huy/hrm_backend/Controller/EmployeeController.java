package com.huy.hrm_backend.Controller;
import com.huy.hrm_backend.Entity.Employee;
import com.huy.hrm_backend.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.huy.hrm_backend.Service.EmployeeService;
import com.huy.hrm_backend.Dto.EmployeeRequest;
import jakarta.validation.Valid;

@RestController //ResAPI
@RequestMapping("/api/employees") //BaseURL http://localhost:8080/api/employees
@RequiredArgsConstructor //Lombok tự tạo constructer
@CrossOrigin("*")

public class EmployeeController {
    private final EmployeeRepository employeeService;

    @GetMapping //HTTP GET Method. (GET /api/employees) tương đương SELECT * FROM EMPLOYEES
    public List<Employee> getAllEmployees(){
        return employeeService.findAll();
    }




    @PostMapping //HTTP POST Method. (POST/api/employees) tương đương INSERT INTO
    public Employee createEmployee(@Valid @RequestBody EmployeeRequest request){
       Employee employee = new Employee();

       employee.setFullName(request.getFullName());
       employee.setEmail(request.getEmail());
       employee.setPhone(request.getPhone());
       employee.setGender(request.getGender());
       employee.setDateOfBirth(request.getDateOfBirth());
       employee.setAddress(request.getAddress());
       employee.setPositionName(request.getPositionName());
       employee.setSalaryBase(request.getSalaryBase());
       employee.setDepartmentId(request.getDepartmentId());
       employee.setUserId(request.getUserID());

        return employeeService.save(employee);
    }


    @GetMapping("/{id}")

    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.findById(id).orElse(null); //Spring tự query: SELECT * FROM EMPLOYEES WHERE ID = ?
    }



    @PutMapping("/{id}") //HTTP put Method. (PUT/api/employees) tương đương UPDATER

    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employeeRequest
    ){
        Employee employee = employeeService.findById(id).orElse(null);
        if(employee == null){
            return null;
        }
        employee.setFullName(employeeRequest.getFullName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setPhone(employeeRequest.getPhone());
        employee.setGender(employeeRequest.getGender());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setAddress(employeeRequest.getAddress());              //Update dữ liệu mới vào object cũ.
        employee.setPositionName(employeeRequest.getPositionName());
        employee.setSalaryBase(employeeRequest.getSalaryBase());
        employee.setDepartmentId(employeeRequest.getDepartmentId());
        employee.setUserId(employeeRequest.getUserId());

        return employeeService.save(employee); //Hibernate tự hiểu: Có ID rồi UPDATE




    }
    @DeleteMapping ("/{id}") //HTTP DELETE Method. (DELETE/api/employees) tương đương DELETE
    public String deleteEmployee(@PathVariable Long id){
        Employee employee = employeeService.findById(id).orElse(null);

        if(employee == null){
            return "Employee not found";
        }
        employeeService.deleteById(id);

        return "Delete employee succesfully";
    }
}
