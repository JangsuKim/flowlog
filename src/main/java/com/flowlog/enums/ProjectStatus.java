package com.flowlog.enums;

import lombok.Getter;

@Getter
public enum ProjectStatus {
    IN_PROGRESS(0, "進行中"),
    COMPLETED(1, "完了"),
    PENDING(2, "保留");

    private final int code;
    private final String label;

    ProjectStatus(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public static ProjectStatus fromCode(int code) {
        for (ProjectStatus s : values()) {
            if (s.code == code) return s;
        }
        throw new IllegalArgumentException("Unknown status code: " + code);
    }
}
