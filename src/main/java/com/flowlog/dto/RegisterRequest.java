package com.flowlog.dto;

import com.flowlog.enums.RoleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String email;
    private String password;
    private String name;
    private Long teamId;
    private RoleType role;
}