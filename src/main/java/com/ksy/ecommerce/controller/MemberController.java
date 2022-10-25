package com.ksy.ecommerce.controller;

import com.ksy.ecommerce.dto.MemberFormDto;
import com.ksy.ecommerce.entity.Member;
import com.ksy.ecommerce.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm() {
        return "new";
    }

    @PostMapping("/new")
    public Member memberForm(@RequestBody MemberFormDto memberFormDto) {
       log.info("member: {}",memberFormDto);
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        memberService.saveMember(member);

        return member;
    }
}
