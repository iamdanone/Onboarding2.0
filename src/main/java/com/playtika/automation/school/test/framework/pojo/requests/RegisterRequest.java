package com.playtika.automation.school.test.framework.pojo.requests;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RegisterRequest {

    String email;
    String password;
}