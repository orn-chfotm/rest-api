package com.spring.restapi.core.enums;

public enum Role {
    MEMBER("USER"),
    ADMIN("ADMIN");

    private String role;

    private Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
