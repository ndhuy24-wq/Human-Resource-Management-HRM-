package com.huy.hrm_backend.Repository;

import com.huy.hrm_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    boolean existsByUserName(String userName);
    boolean existsByUserNameAndIdNot(String userName, Long id);
}
