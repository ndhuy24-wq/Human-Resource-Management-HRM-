package com.huy.hrm_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")  // tự sinh id user
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "USERS_SEQ",
            allocationSize = 1
    )
    @Column (name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE_NAME")
    private String roleName;

    @Column (name = "STATUS")
    private Integer status;


}
