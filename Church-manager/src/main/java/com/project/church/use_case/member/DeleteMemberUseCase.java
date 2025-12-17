package com.project.church.use_case.member;

import com.project.church.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeleteMemberUseCase {

    private final MemberRepository repo;

    public void execute(Long id) {
        repo.deleteById(id);
    }
}
