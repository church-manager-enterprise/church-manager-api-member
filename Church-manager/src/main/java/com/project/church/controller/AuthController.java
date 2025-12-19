package com.project.church.controller;

import com.project.church.model.response.JwtResponse;
import com.project.church.use_case.auth.LoginUseCase;
import com.project.church.use_case.auth.RegisterMemberUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.church.model.request.LoginRequest;
import com.project.church.model.request.RegisterRequest;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RegisterMemberUseCase registerMemberUseCase;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest login) {
        return  ResponseEntity.ok(loginUseCase.execute(login));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(registerMemberUseCase.execute(request));
    }
    
}
