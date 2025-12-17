package com.project.church.controller;

import com.project.church.model.dto.MemberDTO;
import com.project.church.use_case.member.DeleteMemberUseCase;
import com.project.church.use_case.member.GetMemberUseCase;
import com.project.church.use_case.member.ListMembersUseCase;
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

    @GetMapping
    public List<MemberDTO> list() {
        return list.execute();
    }

    @GetMapping("/{id}")
    public MemberDTO get(@PathVariable Long id) {
        return get.execute(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        delete.execute(id);
    }
}

