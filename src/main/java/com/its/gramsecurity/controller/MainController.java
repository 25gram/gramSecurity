package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;


    @GetMapping("/main")
    public String main(Principal principal,Model model) {
        String memberId= principal.getName();
        MemberDTO memberDTO=memberService.findByMemberId(memberId);
        List<MemberDTO> findAll=memberService.findAll();
        model.addAttribute("memberDTO",memberDTO);
        model.addAttribute("findAll",findAll);
        return "main";
    }
}
