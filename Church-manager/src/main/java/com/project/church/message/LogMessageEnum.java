package com.project.church.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LogMessageEnum {
    EMAIL_ALREADY_IN_USE("Email already in use. [email: %s]"),
    USERNAME_ALREADY_IN_USE("Username already in use. [username: %s]"),
    MEMBER_BY_USERNAME_NOT_FOUND("Member with username not found. [username: %s]"),
    MEMBER_BY_ID_NOT_FOUND("Member with id not found. [id: %s]"),
    ROLE_NOT_FOUND("Role not found. [role: %s]"),
    INVALID_CREDENTIALS("Invalid credentials");
    private String message;
}
