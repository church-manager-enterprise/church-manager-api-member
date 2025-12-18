package com.project.church.model.response;

public record JwtResponse(String token, MemberUserResponse user) {}