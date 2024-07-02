package com.spyneAssesment.myProject.user_service.Controller;




import com.spyneAssesment.myProject.user_service.Entity.User;
import com.spyneAssesment.myProject.user_service.Model.UserRequest;
import com.spyneAssesment.myProject.user_service.Model.UserResponse;
import com.spyneAssesment.myProject.user_service.Service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to create a new user
    @PostMapping("")
    public ResponseEntity<Long> createUser(@RequestBody UserRequest user) {

        Long userId = userService.createUser(user);
        return new ResponseEntity<>(userId, HttpStatus.CREATED);
    }

    // Endpoint to update an existing user
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("userId") Long userId, @RequestBody UserRequest user) {
        UserResponse updatedUser = userService.updateUser(userId, user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete a user
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to get a user by userId
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") Long userId) {
        UserResponse user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to search users by name
    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUsersByName(@RequestParam("name") String name) {
        List<UserResponse> users = userService.searchUsersByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    // Endpoint to get all users
    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}