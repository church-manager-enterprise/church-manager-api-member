package com.project.church.use_case.member;

import com.project.church.builder.MemberBuilder;
import com.project.church.model.dto.MemberDTO;
import com.project.church.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListMembersUseCase {

    private final MemberRepository repo;

    public List<MemberDTO> execute() {
        return repo.findAll()
                .stream()
                .map(MemberBuilder::toDTO)
                .toList();
    }
}

