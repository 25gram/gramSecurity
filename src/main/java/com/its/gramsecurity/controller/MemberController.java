package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/login")
    public String loginForm() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }


    @RequestMapping(value = "/oauth2/authorization/google", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);
        return "/main";
    }

    //password 암호화 저장
    @PostMapping("/join")
    public String join(@ModelAttribute MemberDTO memberDTO) {
        memberDTO.setRole("ROLE_USER");
        System.out.println("MemberController.join");
        System.out.println("memberDTO = " + memberDTO);
        String rawPassword = memberDTO.getMemberPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        memberDTO.setMemberPassword(encPassword);
        memberService.save(memberDTO);
        return "redirect:/";
    }

}
