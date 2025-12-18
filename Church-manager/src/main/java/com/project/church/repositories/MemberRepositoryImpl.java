package com.project.church.repositories;

import com.project.church.exception.EntityNotFoundException;
import com.project.church.infrastructure.MemberJpaRepository;
import com.project.church.message.LogMessageEnum;
import com.project.church.model.entities.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository repo;

    @Override
    public Member save(Member member) {
        return repo.save(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return repo.findByIdAndRemovedAtIsNull(id);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return repo.findByEmailAndRemovedAtIsNull(email);
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return repo.findByUsernameAndRemovedAtIsNull(username);
    }

    @Override
    public List<Member> findAll() {
        return repo.findAllByRemovedAtIsNull();
    }

    @Override
    public void deleteById(Long id) {
        repo.findByIdAndRemovedAtIsNull(id).ifPresentOrElse(
                m -> {
                    m.setRemovedAt(LocalDateTime.now());
                    m.setUpdatedAt(LocalDateTime.now());
                    repo.save(m);
                },
                () -> {
                    throw new EntityNotFoundException(
                            String.format(
                                    LogMessageEnum.MEMBER_BY_ID_NOT_FOUND.getMessage(),
                                    id.toString()
                            )
                    );
                }
        );
    }

    @Override
    public List<Member> findByChurchId(String churchId) {
        return repo.findByChurchIdAndRemovedAtIsNull(churchId);
    }
}
