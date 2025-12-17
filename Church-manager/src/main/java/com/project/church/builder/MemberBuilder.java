package com.project.church.builder;

import com.project.church.model.dto.MemberDTO;
import com.project.church.model.entities.Member;
import com.project.church.model.entities.Role;

import java.util.UUID;

public final class MemberBuilder {

    private MemberBuilder() {
    }

    public static MemberDTO toDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    public static Member buildForRegister(
            String name,
            String email,
            String username,
            String encodedPassword,
            Role role
    ) {
        return Member.builder()
                .uuid(UUID.randomUUID())
                .name(name)
                .email(email)
                .username(username)
                .password(encodedPassword)
                .role(role)
                .build();
    }
}


