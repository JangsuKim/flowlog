// com/flowlog/dto/LoginRequest.java
package com.flowlog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequest {
    private String email;
    private String password;
}
