package com.project.church.services;


import com.project.church.exception.EntityAlreadyExistsException;
import com.project.church.exception.EntityNotFoundException;
import com.project.church.exception.InvalidCredentialsException;
import com.project.church.message.LogMessageEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.church.builders.MemberBuilder;
import com.project.church.model.entities.Member;
import com.project.church.model.entities.Role;
import com.project.church.model.request.LoginRequest;
import com.project.church.model.request.RegisterRequest;
import com.project.church.model.response.JwtResponse;
import com.project.church.repositories.MemberRepository;
import com.project.church.repositories.RoleRepository;
import com.project.church.utils.JwtUtil;

@Service
public class AuthService {

    public static final String USER = "USER";
    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public AuthService(MemberRepository memberRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.memberRepository = memberRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }


    public ResponseEntity login(LoginRequest login) {
        try {
            var auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
            String token = jwtUtil.generateToken(login.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (AuthenticationException ex) {
            throw new InvalidCredentialsException(LogMessageEnum.INVALID_CREDENTIALS.getMessage());
        }
    }

    public Member register(RegisterRequest request) {

        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EntityAlreadyExistsException(String.format(LogMessageEnum.EMAIL_ALREADY_IN_USE.getMessage(), request.getEmail()));
        }

        if (memberRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new EntityAlreadyExistsException(String.format(LogMessageEnum.USERNAME_ALREADY_IN_USE.getMessage(), request.getUsername()));
        }

        Role role = roleRepository.findByName(USER)
                .orElseThrow(() -> new EntityNotFoundException(String.format(LogMessageEnum.ROLE_NOT_FOUND.getMessage(), USER)));

        Member member = MemberBuilder.build(request.getName(), request.getEmail(), request.getUsername(), passwordEncoder.encode(request.getPassword()), role);

        return memberRepository.save(member);
    }
}
