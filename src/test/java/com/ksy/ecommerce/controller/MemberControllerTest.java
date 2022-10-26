package com.ksy.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ksy.ecommerce.dto.MemberFormDto;
import com.ksy.ecommerce.entity.Member;
import com.ksy.ecommerce.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    public Member createMember(String email, String password) {
        MemberFormDto memberFormDto = MemberFormDto.builder()
                .email(email)
                .name("kkk")
                .address("seoul")
                .password(password)
                .build();
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        return memberService.saveMember(member);
    }

    @Test
    @DisplayName("register test")
    public void loginSuccessTest() throws Exception {
        String email = "kkk@email.com";
        String password = "1234";
      //  this.createMember(email,password); //register
        Map<String,String> input = new HashMap<>();
        input.put("email",email);
        input.put("password",password);
        String content = objectMapper.writeValueAsString(input);
        mockMvc.perform(post("/members/new").contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status()
                        .isOk()).andDo(print());

    }
}