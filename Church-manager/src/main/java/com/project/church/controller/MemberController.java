package com.project.church.controller;

import com.project.church.model.dto.MemberDTO;
import com.project.church.use_case.member.DeleteMemberUseCase;
import com.project.church.use_case.member.GetMemberUseCase;
import com.project.church.use_case.member.ListMembersUseCase;
import com.project.church.use_case.member.ListMembersByChurchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final ListMembersUseCase list;
    private final GetMemberUseCase get;
    private final DeleteMemberUseCase delete;
    private final ListMembersByChurchUseCase listByChurch;

    @GetMapping
    public List<MemberDTO> list() {
        return list.execute();
    }

    @GetMapping("/{id}")
    public MemberDTO get(@PathVariable Long id) {
        return get.execute(id);
    }

    @GetMapping("/church/{churchId}")
    public List<MemberDTO> listByChurch(@PathVariable String churchId) {
        return listByChurch.execute(churchId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        delete.execute(id);
    }
}

