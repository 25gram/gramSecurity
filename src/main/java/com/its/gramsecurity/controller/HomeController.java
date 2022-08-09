package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.BoardDTO;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.MemberEntity;
import com.its.gramsecurity.service.BoardService;
import com.its.gramsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.security.Principal;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
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
        return "redirect:/home/";
    }

    //아이디 이메일 중복체크
    @PostMapping("/duplicateChk")
    public @ResponseBody String duplicateChk(@RequestParam("memberEmail") String memberEmail,
                                             @RequestParam("loginId") String loginId) {
        System.out.println("HomeController.duplicateChk");
        System.out.println("memberEmail = " + memberEmail + ", loginId = " + loginId);
        MemberEntity memberEmailChk = memberService.duplicateChkEmail(memberEmail);
        MemberEntity loginIdChk = memberService.duplicateChkId(loginId);
        String result = null;
        if (memberEmailChk == null && loginIdChk == null) {
            result = "true";
        } else {
            if (memberEmailChk != null) {
                result = "email";
            } else if (loginIdChk != null) {
                result = "id";
            }
        }
        return result;
    }

    @GetMapping("/test")
    String test(){
        return "Contents/modal1";
    }


}
