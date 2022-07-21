package com.its.gramsecurity.controller;

import com.its.gramsecurity.Repository.MemberRepository;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.MemberEntity;
import com.its.gramsecurity.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping( "/")
    public String index(){
        return "index";
    }
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    @GetMapping("/board")
    public String board(){
        return "board";
    }
    @GetMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);
        return "board";
    }


    @PostMapping("/join")
    public String join(@ModelAttribute MemberDTO memberDTO){
        memberDTO.setRole("ROLE_USER");
        System.out.println("MemberController.join");
        System.out.println("memberDTO = " + memberDTO);
        String rawPassword=memberDTO.getMemberPassword();
        String encPassword=bCryptPasswordEncoder.encode(rawPassword);
        memberDTO.setMemberPassword(encPassword);
        memberService.save(memberDTO);
        return "redirect:/";
    }


}
