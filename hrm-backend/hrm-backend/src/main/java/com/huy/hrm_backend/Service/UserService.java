package com.huy.hrm_backend.Service;

import com.huy.hrm_backend.Dto.UserResponse;
import com.huy.hrm_backend.Entity.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUser();


    UserResponse getUserById(Long id);


    UserResponse createUser (User user);


    UserResponse updateUser(Long id, User user);


    void deleteUser ( Long id);

}
