package com.spyneAssesment.myProject.user_service.Service;

import com.spyneAssesment.myProject.user_service.Model.UserRequest;
import com.spyneAssesment.myProject.user_service.Model.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    long createUser(UserRequest user);

    UserResponse updateUser(Long userId, UserRequest user);

    void deleteUser(Long userId);

    UserResponse getUserById(Long userId);

    List<UserResponse> searchUsersByName(String name);

    List<UserResponse> getAllUsers();
}
