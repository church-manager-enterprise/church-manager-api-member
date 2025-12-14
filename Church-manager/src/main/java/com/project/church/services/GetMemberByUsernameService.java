package com.project.church.services;

import com.project.church.exception.EntityNotFoundException;
import com.project.church.message.LogMessageEnum;
import com.project.church.model.entities.Member;
import com.project.church.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMemberByUsernameService {

    private final MemberRepository memberRepository;

    public Member execute(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format(LogMessageEnum.MEMBER_BY_USERNAME_NOT_FOUND.getMessage(), username)
                        )
                );
    }
}
