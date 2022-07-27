package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Member;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberService memberService;


    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/joinForm")
    public String joinForm() {
        return "memberPages/joinForm";
    }

    //password 암호화 저장
    @PostMapping("/join")
    public String join(@ModelAttribute MemberDTO memberDTO) {
        memberDTO.setRole("ROLE_USER");
        String rawPassword = memberDTO.getMemberPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        memberDTO.setMemberPassword(encPassword);
        memberService.save(memberDTO);
        return "redirect:/main/";
    }

}
