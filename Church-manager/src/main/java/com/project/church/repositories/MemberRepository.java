package com.project.church.repositories;



import com.project.church.model.entities.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);
    List<Member> findAll();
    void deleteById(Long id);
    Optional<Member> findByUsername(String username);
    List<Member> findByChurchId(String churchId);
}
