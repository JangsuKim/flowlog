package com.flowlog.dto;

import com.flowlog.entity.Team;
import com.flowlog.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String tokenType;
    private Long userId;
    private String email;
    private String name;
    private RoleType role;
    private Team team;
}
