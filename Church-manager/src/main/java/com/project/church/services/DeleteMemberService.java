package com.project.church.services;

import com.project.church.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeleteMemberService {

    private final MemberRepository repo;

    public void execute(Long id) {
        repo.deleteById(id);
    }
}
