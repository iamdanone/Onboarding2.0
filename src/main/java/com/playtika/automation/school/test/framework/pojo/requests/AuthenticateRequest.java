package com.playtika.automation.school.test.framework.pojo.requests;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AuthenticateRequest {

    String grantType;
    String userName;
    String password;
}