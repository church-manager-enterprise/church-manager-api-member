package com.project.church.use_case.auth;

import com.project.church.builder.MemberBuilder;
import com.project.church.exception.EntityAlreadyExistsException;
import com.project.church.exception.EntityNotFoundException;
import com.project.church.message.LogMessageEnum;
import com.project.church.model.entities.Member;
import com.project.church.model.entities.Role;
import com.project.church.model.request.RegisterRequest;
import com.project.church.repositories.MemberRepository;
import com.project.church.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterMemberUseCase {

    private static final String DEFAULT_ROLE = "USER";

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public Member execute(RegisterRequest request) {

        validateUniqueness(request);

        Role role = roleRepository.findByName(DEFAULT_ROLE)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(
                                LogMessageEnum.ROLE_NOT_FOUND.getMessage(),
                                DEFAULT_ROLE
                        )
                ));

        Member member = MemberBuilder.buildForRegister(
                request.getName(),
                request.getEmail(),
                request.getUsername(),
                request.getChurchId(),
                passwordEncoder.encode(request.getPassword()),
                role
        );

        return memberRepository.save(member);
    }

    private void validateUniqueness(RegisterRequest request) {

        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EntityAlreadyExistsException(
                    String.format(
                            LogMessageEnum.EMAIL_ALREADY_IN_USE.getMessage(),
                            request.getEmail()
                    )
            );
        }

        if (memberRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new EntityAlreadyExistsException(
                    String.format(
                            LogMessageEnum.USERNAME_ALREADY_IN_USE.getMessage(),
                            request.getUsername()
                    )
            );
        }
    }
}
