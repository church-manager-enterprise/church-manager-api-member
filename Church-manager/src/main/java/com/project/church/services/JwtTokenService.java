package com.project.church.services;

import com.project.church.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final JwtUtil jwtUtil;

    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }
}