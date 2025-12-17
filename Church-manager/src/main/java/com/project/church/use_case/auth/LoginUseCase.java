package com.project.church.use_case.auth;


import com.project.church.exception.InvalidCredentialsException;
import com.project.church.message.LogMessageEnum;
import com.project.church.model.request.LoginRequest;
import com.project.church.model.response.JwtResponse;
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

    public JwtResponse execute(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            String token = tokenService.generateToken(request.getUsername());
            return new JwtResponse(token);

        } catch (AuthenticationException ex) {
            throw new InvalidCredentialsException(
                    LogMessageEnum.INVALID_CREDENTIALS.getMessage()
            );
        }
    }
}
