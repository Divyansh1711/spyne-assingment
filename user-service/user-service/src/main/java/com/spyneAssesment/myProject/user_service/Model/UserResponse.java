package com.spyneAssesment.myProject.user_service.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private long id;
    private String name;
    private String mobile_no;
    private String email;
    private Date  created_on;
}
