package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.FollowDTO;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.entity.FollowEntity;
import com.its.gramsecurity.repository.FollowRepository;
import com.its.gramsecurity.service.BoardService;
import com.its.gramsecurity.service.FollowService;
import com.its.gramsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final FollowService followService;


    //구글 로그인 후처리
    @RequestMapping(value = "/oauth2/authorization/google", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        String referrer = request.getHeader("Referer");
        request.getSession().setAttribute("prevPage", referrer);
        return "/main";
    }

    //회원정보수정 폼
    @GetMapping("/updateForm")
    public String updateForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        String loginId = principalDetails.getUsername();
        MemberDTO memberDTO = memberService.findByLoginId(loginId);
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
    public String delete(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        memberService.delete(loginId);
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
    public @ResponseBody void loginCheck(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        memberService.loginCheck(loginId);
    }

    //로그아웃 체크
    @GetMapping("/logoutCheck")
    public @ResponseBody void logoutCheck(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        memberService.logoutCheck(loginId);
    }

    //업데이트 비밀번호 체크
    @PostMapping("/passwordCheck")
    public @ResponseBody String PasswordCheck(MemberDTO memberDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        MemberDTO passwordCheck = memberService.passwordCheck(memberDTO, principalDetails);
        if (passwordCheck == null) {
            return "no";
        } else {
            return "ok";
        }
    }

    @GetMapping("/mypage")
    public String mypage(@AuthenticationPrincipal PrincipalDetails principalDetails,
                         @RequestParam String loginId, Model model) {
        MemberDTO memberDTO = memberService.findByLoginId(loginId);
        List<FollowDTO> followDTOList = followService.findAllByMyId(loginId);
        List<FollowDTO> following = followService.findAllByYourId(loginId);
        String findByMyIdAndYourId=followService.findByMyIdAndYourId(principalDetails.getMemberDTO().getLoginId(),loginId);
        model.addAttribute("findByMyIdAndYourId",findByMyIdAndYourId);
        model.addAttribute("countFw", following.size());
        model.addAttribute("count", followDTOList.size());
        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("AuthenticationPrincipal", principalDetails);
        return "memberPages/myPage";
    }
}