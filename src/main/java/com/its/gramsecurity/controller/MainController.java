package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;


    @GetMapping("/main")
    public String main(Principal principal,Model model) {
        String memberId= principal.getName();
        MemberDTO memberDTO=memberService.findByMemberId(memberId);
        model.addAttribute("memberDTO",memberDTO);
        System.out.println("MainController.main");
        System.out.println("principal = " + principal + ", model = " + model);
        return "main";
    }
}
