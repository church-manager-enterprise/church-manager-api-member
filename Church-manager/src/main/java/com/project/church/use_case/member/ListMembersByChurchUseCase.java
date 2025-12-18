package com.project.church.use_case.member;

import com.project.church.builder.MemberBuilder;
import com.project.church.model.dto.MemberDTO;
import com.project.church.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListMembersByChurchUseCase {

    private final MemberRepository repo;

    public List<MemberDTO> execute(String churchId) {
        return repo.findByChurchId(churchId)
                .stream()
                .map(MemberBuilder::toDTO)
                .toList();
    }
}
