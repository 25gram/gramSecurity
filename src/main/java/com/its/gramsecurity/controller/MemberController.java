package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/joinForm")
    public String joinForm() {
        return "memberPages/joinForm";
    }

    //구글 로그인 후처리
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
        String rawPassword = memberDTO.getMemberPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        memberDTO.setMemberPassword(encPassword);
        memberService.save(memberDTO);
        return "redirect:/";
    }
    //회원정보수정 폼
    @GetMapping("/updateForm")
    public String updateForm(Principal principal, Model model){
        String memberId=principal.getName();
        MemberDTO memberDTO=memberService.findByMemberId(memberId);
        model.addAttribute("memberDTO",memberDTO);
        return "memberPages/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return null;
    }



}
