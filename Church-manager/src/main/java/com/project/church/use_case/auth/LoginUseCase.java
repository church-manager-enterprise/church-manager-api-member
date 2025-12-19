package com.project.church.use_case.auth;

import com.project.church.exception.InvalidCredentialsException;
import com.project.church.message.LogMessageEnum;
import com.project.church.model.entities.Member;
import com.project.church.model.request.LoginRequest;
import com.project.church.model.response.JwtResponse;
import com.project.church.model.response.MemberUserResponse;
import com.project.church.repositories.MemberRepository;
import com.project.church.services.JwtTokenService;
import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;

@Component
@RequiredArgsConstructor
public class LoginUseCase {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService tokenService;
    private final MemberRepository memberRepository;

    public JwtResponse execute(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

            Member member = memberRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


            String token = tokenService.generateToken(request.getEmail());

            MemberUserResponse userResponse = new MemberUserResponse(
                    member.getId().toString(),
                    member.getEmail(),
                    member.getName(),
                    member.getRole().getName(),
                    member.getUsername(),
                    member.getChurchId());

            return new JwtResponse(token, userResponse);

        } catch (AuthenticationException ex) {
            throw new InvalidCredentialsException(
                    LogMessageEnum.INVALID_CREDENTIALS.getMessage());
        }
    }
}
