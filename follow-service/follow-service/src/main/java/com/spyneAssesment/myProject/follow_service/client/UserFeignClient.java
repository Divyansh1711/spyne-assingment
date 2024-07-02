package com.spyneAssesment.myProject.follow_service.client;


import com.spyneAssesment.myProject.follow_service.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserFeignClient {

    @GetMapping("/users/{userId}")
    UserResponse getUserById(@PathVariable("userId") Long userId);
}
