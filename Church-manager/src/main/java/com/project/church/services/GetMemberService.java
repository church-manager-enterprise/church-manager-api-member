package com.project.church.services;

import com.project.church.builder.MemberBuilder;
import com.project.church.exception.EntityNotFoundException;
import com.project.church.model.dto.MemberDTO;
import com.project.church.model.entities.Member;
import com.project.church.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMemberService {

    private final MemberRepository repo;

    public MemberDTO execute(Long id) {
        Member member = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return MemberBuilder.toDTO(member);
    }
}
