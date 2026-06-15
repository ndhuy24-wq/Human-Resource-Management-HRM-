package com.huy.hrm_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data // Tự động sinh getter/setter nhờ Lombok
@Entity // Khai báo đây là thực thể JPA đại diện cho bảng trong Database
@Table(name = "DEPARTMENTS") // Ánh xạ tới bảng DEPARTMENTS trong Oracle
public class Department {

    @Id // Khai báo trường khóa chính
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "department_seq") // Tự sinh ID dùng Sequence
    @SequenceGenerator(
            name = "department_seq",
            sequenceName = "DEPARTMENTS_SEQ", // Ánh xạ với Oracle Sequence
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME") // Ánh xạ với cột NAME (Tên phòng ban)
    private String name;

    @Column(name = "DESCRIPTION") // Ánh xạ với cột DESCRIPTION (Mô tả phòng ban)
    private String description;
}
