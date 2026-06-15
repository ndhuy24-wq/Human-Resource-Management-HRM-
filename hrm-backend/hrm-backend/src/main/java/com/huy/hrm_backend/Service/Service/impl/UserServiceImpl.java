package com.huy.hrm_backend.Service.Service.impl;

import com.huy.hrm_backend.Dto.UserResponse;
import com.huy.hrm_backend.Entity.User;
import com.huy.hrm_backend.Exception.ResourceNotFoundException;
import com.huy.hrm_backend.Exception.UsernameAlreadyExistsException;
import com.huy.hrm_backend.Repository.UserRepository;
import com.huy.hrm_backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private UserResponse mapToResponse(User user){
        UserResponse response =  new UserResponse();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setRoleName(user.getRoleName());
        response.setStatus(user.getStatus());

        return response;
    }

    @Override
    public List<UserResponse> getAllUser(){
        return userRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id));
        return mapToResponse(user);
    }

    @Override
    public UserResponse createUser(User user){
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new UsernameAlreadyExistsException("Username already exists: " + user.getUserName());
        }
        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Long id, User userRequest){
        User user  = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id));

        if (userRepository.existsByUserNameAndIdNot(userRequest.getUserName(), id)) {
            throw new UsernameAlreadyExistsException("Username already exists: " + userRequest.getUserName());
        }

        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        user.setRoleName(userRequest.getRoleName());
        user.setStatus(userRequest.getStatus());

        User updateUser = userRepository.save(user);
        return mapToResponse(updateUser);
    }

    @Override
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }
}
