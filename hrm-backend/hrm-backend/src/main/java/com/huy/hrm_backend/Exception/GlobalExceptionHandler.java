package com.huy.hrm_backend.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // Đánh dấu lớp này để lắng nghe và xử lý ngoại lệ từ toàn bộ các Controller trong dự án
public class GlobalExceptionHandler {

    // Xử lý lỗi Validate dữ liệu đầu vào (Ví dụ: dữ liệu DTO thiếu @NotBlank, độ dài chuỗi không hợp lệ,...)
    // MethodArgumentNotValidException sẽ tự động được ném ra bởi Spring Boot khi kiểm tra @Valid thất bại
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerValidationException(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();

        // Duyệt qua tất cả lỗi của các trường dữ liệu và gom vào Map dạng {tên_trường: thông_báo_lỗi}
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    errors.put(error.getField(), error.getDefaultMessage());
                });

        // Trả về mã lỗi 400 Bad Request kèm theo danh sách các trường nhập sai
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Xử lý lỗi khi không tìm thấy tài nguyên trong database (Ví dụ: Phòng ban/Nhân viên/User không tồn tại)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlerResourceNotFoundException(
            ResourceNotFoundException ex
    ){
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        // Trả về mã lỗi 404 Not Found kèm thông điệp báo lỗi cụ thể
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Xử lý lỗi khi tạo/sửa nhân viên mà Email đã bị trùng trong hệ thống
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex
    ){
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        // Trả về mã lỗi 409 Conflict (Mâu thuẫn dữ liệu)
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // Xử lý lỗi khi tạo phòng ban có Tên đã tồn tại
    @ExceptionHandler(DepartmentAlreadyExistsException.class)
    public ResponseEntity<Map<String,String>> handleDepartmentAlreadyExistsException(
        DepartmentAlreadyExistsException ex
    ){
        Map<String,String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        // Trả về mã lỗi 409 Conflict
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    // Xử lý lỗi khi tạo/sửa tài khoản có Username đã tồn tại trong hệ thống
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUsernameAlreadyExistsException(
            UsernameAlreadyExistsException ex
    ) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());

        // Trả về mã lỗi 409 Conflict
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
