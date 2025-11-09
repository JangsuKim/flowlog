package com.flowlog.enums;

import lombok.Getter;

@Getter
public enum RoleType {
    ADMIN(0, "admin"),
    MEMBER(1, "member"),
    LEADER(2, "leader");

    private final int code;
    private final String label;

    RoleType(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static RoleType fromCode(int code) {
        for (RoleType role : RoleType.values()) {
            if (role.getCode() == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid RoleType code: " + code);
    }
}
