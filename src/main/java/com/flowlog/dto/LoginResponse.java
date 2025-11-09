package com.flowlog.dto;

import com.flowlog.entity.Team;
import com.flowlog.entity.User;
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
    private Long teamId;
    private String teamName;

    public static LoginResponse of(User u, String accessToken, String tokenType) {
        return new LoginResponse(
                accessToken,
                tokenType,
                u.getId(),
                u.getEmail(),
                u.getName(),
                u.getRole(),
                u.getTeam() != null ? u.getTeam().getId() : null,
                u.getTeam() != null ? u.getTeam().getName() : null
        );
    }
}
