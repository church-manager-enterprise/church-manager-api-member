package com.project.church.controller;

import com.project.church.model.dto.MemberDTO;
import com.project.church.services.DeleteMemberService;
import com.project.church.services.GetMemberService;
import com.project.church.services.ListMembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final ListMembersService list;
    private final GetMemberService get;
    private final DeleteMemberService delete;

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

