package com.project.church.infrastructure;

import com.project.church.model.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByIdAndRemovedAtIsNull(Long id);

    Optional<Member> findByEmailAndRemovedAtIsNull(String email);

    Optional<Member> findByUsernameAndRemovedAtIsNull(String username);

    List<Member> findAllByRemovedAtIsNull();
}



