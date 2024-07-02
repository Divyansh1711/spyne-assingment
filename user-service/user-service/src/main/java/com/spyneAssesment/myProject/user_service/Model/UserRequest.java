package com.spyneAssesment.myProject.user_service.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserRequest {
    private String name;
    private String mobile_no;
    private String email;
}
