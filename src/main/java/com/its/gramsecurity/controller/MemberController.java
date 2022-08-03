package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;


@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    //구글 로그인 후처리
    @RequestMapping(value = "/oauth2/authorization/google", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);
        return "/main";
    }

    //회원정보수정 폼
    @GetMapping("updateForm")
    public String updateForm(Principal principal, Model model) {
        String memberId = principal.getName();
        MemberDTO memberDTO = memberService.findByMemberId(memberId);
        model.addAttribute("memberDTO", memberDTO);
        return "memberPages/update";
    }

    //일반회원 정보 수정
    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) throws IOException {
        memberService.update(memberDTO);
        return "redirect:/main/main";
    }

    //회원삭제
    @GetMapping("/delete")
    public String delete(Principal principal) {
        String memberId = principal.getName();
        memberService.delete(memberId);
        return "redirect:/member/logout";
    }

    //삭제 후 강제 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout
                (request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/home/";
    }

    //로그인체크
    @GetMapping("/loginCheck")
    public @ResponseBody void loginCheck(Principal principal) {
        String memberId = principal.getName();
        memberService.loginCheck(memberId);
    }

    //로그아웃 체크
    @GetMapping("/logoutCheck")
    public @ResponseBody void logoutCheck(Principal principal) {
        String memberId = principal.getName();
        memberService.logoutCheck(memberId);
    }
}