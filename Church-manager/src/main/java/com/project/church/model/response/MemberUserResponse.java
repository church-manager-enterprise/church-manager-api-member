package com.project.church.model.response;

public record MemberUserResponse(
    String id,
    String email,
    String name,
    String role,
    String username,
    String churchId
) {}
