package com.huy.hrm_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data // Tự động tạo getter, setter, toString, equals, hashCode nhờ thư viện Lombok
@Entity // Khai báo lớp này là một thực thể (Entity) tương ứng với một bảng trong Database
@Table(name = "EMPLOYEES") // Map Entity này với bảng "EMPLOYEES" trong cơ sở dữ liệu
public class Employee {

    @Id // Khai báo đây là khóa chính (Primary Key) của bảng
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq") // Khai báo ID được sinh tự động bằng Sequence
    @SequenceGenerator(
            name = "employee_seq",
            sequenceName = "EMPLOYEES_SEQ", // Tên sequence trong Oracle Database
            allocationSize = 1 // Mỗi lần tăng lên 1 đơn vị
    )
    @Column(name = "ID") // Ánh xạ thuộc tính này với cột "ID" trong bảng
    private long id;

    @Column(name = "FULL_NAME") // Ánh xạ với cột "FULL_NAME"
    private String fullName;

    @Column(name = "EMAIL") // Ánh xạ với cột "EMAIL"
    private String email;

    @Column(name = "PHONE") // Ánh xạ với cột "PHONE"
    private String phone;

    @Column(name = "GENDER") // Ánh xạ với cột "GENDER"
    private String gender;

    @Column(name = "DATE_OF_BIRTH") // Ánh xạ với cột "DATE_OF_BIRTH"
    private LocalDate dateOfBirth;

    @Column(name = "ADDRESS") // Ánh xạ với cột "ADDRESS"
    private String address;

    @Column(name = "POSITION_NAME") // Ánh xạ với cột "POSITION_NAME"
    private String positionName;

    @Column(name = "SALARY_BASE") // Ánh xạ với cột "SALARY_BASE" (lương cơ bản)
    private double salaryBase;

    // QUAN HỆ NHIỀU - MỘT (Nhiều Nhân viên thuộc Một Phòng ban)
    // FetchType.LAZY: Cơ chế tải chậm, chỉ khi nào gọi đến department mới thực hiện câu SQL lấy phòng ban (tối ưu hiệu năng)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID") // Tên cột khóa ngoại (Foreign Key) trong bảng EMPLOYEES
    private Department department;

    // QUAN HỆ MỘT - MỘT (Mỗi Nhân viên liên kết với Một tài khoản User đăng nhập)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID") // Tên cột khóa ngoại (Foreign Key) trong bảng EMPLOYEES
    private User user;
}
