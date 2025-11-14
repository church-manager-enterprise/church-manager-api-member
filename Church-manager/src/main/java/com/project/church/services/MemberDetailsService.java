package com.project.church.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.project.church.model.MemberDetails;
import com.project.church.model.entities.Member;
import com.project.church.repositories.MemberRepository;

@Service
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    public MemberDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member m = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new MemberDetails(m);
    }
}
