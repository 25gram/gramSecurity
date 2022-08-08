package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.BoardDTO;
import com.its.gramsecurity.dto.BoardFileDTO;
import com.its.gramsecurity.dto.LikesDTO;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.service.BoardService;
import com.its.gramsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final BoardService boardService;

    //메인피드
    @GetMapping("/main")
    public String main(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        MemberDTO memberDTO = memberService.findByLoginId(loginId);
        List<MemberDTO> findAll = memberService.findAll();
        List<BoardDTO> boardList = boardService.findAll();
        List<BoardFileDTO> boardFileList = boardService.fileFindAll();
//        List<LikesDTO> likesList = boardService.likesFindAll();
        List<LikesDTO> likesList = boardService.qqq(loginId);
        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("findAll", findAll);
        model.addAttribute("boardList",boardList);
        model.addAttribute("boardFile",boardFileList);
        model.addAttribute("likes",likesList);
        return "main";
    }



}
