package com.huy.hrm_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity // Đánh dấu đây là thực thể ánh xạ tới cơ sở dữ liệu
@Data // Tự động sinh getter/setter, toString từ Lombok
@Table(name = "USERS") // Ánh xạ tới bảng USERS trong Database
public class User {

    @Id // Khai báo khóa chính
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq") // Tự sinh ID bằng Sequence
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "USERS_SEQ", // Tên sequence trong Oracle Database
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME") // Tên tài khoản
    private String userName;

    @Column(name = "PASSWORD") // Mật khẩu (thường được mã hóa)
    private String password;

    @Column(name = "ROLE_NAME") // Vai trò hệ thống (Ví dụ: ADMIN, HR, EMPLOYEE)
    private String roleName;

    @Column(name = "STATUS") // Trạng thái hoạt động (Ví dụ: 1 = Active, 0 = Inactive)
    private Integer status;
}
