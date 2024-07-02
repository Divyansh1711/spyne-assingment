package com.spyneAssesment.myProject.user_service.Service;

import com.spyneAssesment.myProject.user_service.Entity.User;
import com.spyneAssesment.myProject.user_service.Exception.RestResponseEntityExceptionHandler;
import com.spyneAssesment.myProject.user_service.Exception.UserServiceCustomException;
import com.spyneAssesment.myProject.user_service.Model.ErrorResponse;
import com.spyneAssesment.myProject.user_service.Model.UserRequest;
import com.spyneAssesment.myProject.user_service.Model.UserResponse;
import com.spyneAssesment.myProject.user_service.Repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public long createUser(UserRequest user) {
        log.info("creating a user");
        User user1= new User().builder()
                .name(user.getName())
                .email(user.getEmail())
                .mobile_no(user.getMobile_no())
                .createdAt(Date.from(Instant.now()))
                .build();
        try {
            userRepository.save(user1);
        }
        catch (Exception ex){
            ErrorResponse errorResponse
                    = new ErrorResponse(Integer.toString(HttpStatus.INTERNAL_SERVER_ERROR.value()), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse).getStatusCodeValue();
        }
        log.info("user created");
        return user1.getId();
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new UserServiceCustomException("USER NOT FOUND",userId.toString()));
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setMobile_no(userRequest.getMobile_no());
        userRepository.save(user);
        return buildUserResponseFromUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserResponse getUserById(Long userId) {
        User user= userRepository
                .findById(userId).orElseThrow(()->new UserServiceCustomException("User Not found",userId.toString()));

        return buildUserResponseFromUser(user);
    }

    @Override
    public List<UserResponse> searchUsersByName(String name) {
        List<User> list= userRepository.findAllByName(name);
        return converUserListToUserResponseList(list);
    }
    // method for building user response from user
    private UserResponse buildUserResponseFromUser(User user){
        UserResponse userResponse
                = new UserResponse()
                .builder()
                .name(user.getName())
                .id(user.getId())
                .email(user.getEmail())
                .mobile_no(user.getMobile_no())
                .created_on(user.getCreatedAt())
                .build();
        return userResponse;
    }
    // method for converting userList to userResponseList
    private List<UserResponse> converUserListToUserResponseList(List<User> userList){
        return userList.stream()
                .map(this::buildUserResponseFromUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return converUserListToUserResponseList(userList);
    }
}
