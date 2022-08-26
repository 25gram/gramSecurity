package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.*;
import com.its.gramsecurity.service.BoardService;
import com.its.gramsecurity.service.CommentService;
import com.its.gramsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final CommentService commentService;
    private final StoryController storyController;

    //메인피드
    @GetMapping("/main")
    public String main(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        List<MemberDTO> findAll = memberService.findAll();
        List<BoardDTO> boardList = boardService.findAll();
        MemberDTO memberDTO = memberService.findByLoginId(loginId);
        List<BoardFileDTO> boardFileList = boardService.fileFindAll();
        boardService.likesFindAll(memberDTO.getMemberName());
        commentService.likesFindAll(memberDTO.getMemberName());
        model.addAttribute("AuthenticationPrincipal",principalDetails);
        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("findAll", findAll);
        model.addAttribute("boardList",boardList);
        model.addAttribute("boardFile",boardFileList);
        List<StoryDTO> myStoryList = storyController.storyCheck(loginId); // 내 스토리 리스트
        List<StoryDTO> followingStoryList = storyController.findStoryList(loginId); //내가 팔로우한 사람들의 스토리 리스트
        model.addAttribute("myStoryList", myStoryList);
        List<StoryDTO> neverSeenStoryList = new ArrayList<>();
        for(int i=0; i<followingStoryList.size(); i++) {
            StoryDTO myFollowStory = followingStoryList.get(i); //내가 팔로우한 사람의 스토리
            Long storyId = myFollowStory.getId();  // 내가 팔로우한 사람의 스토리 번호
           boolean result = storyController.findByStoryIdAndLoginId(storyId, loginId);
            System.out.println("==============================MainController/main/result================================"+result);
           if(!result){ // 내가 팔로우한 사람의 스토리를 본적이 없으면
               neverSeenStoryList.add(myFollowStory); // 내가 안본 팔로우스토리 리스트에 추가
           }
        }
        model.addAttribute("neverSeenStoryList", neverSeenStoryList);
        System.out.println("=============================MainController/main/neverSeenStoryList========================="+neverSeenStoryList);
        return "main";
    }

    @GetMapping("modal")
//    String modal(@AuthenticationPrincipal PrincipalDetails principalDetails,Model model){
    String modal(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model,@RequestParam("bid")Long bid){
        String loginId = principalDetails.getMemberDTO().getLoginId();
        List<MemberDTO> findAll = memberService.findAll();
        List<BoardDTO> boardList = boardService.findAll();
        MemberDTO memberDTO = memberService.findByLoginId(loginId);
        List<BoardFileDTO> boardFileList = boardService.fileFindAll();
        boardService.likesFindAll(memberDTO.getMemberName());
        commentService.likesFindAll(memberDTO.getMemberName());
        model.addAttribute("AuthenticationPrincipal",principalDetails);
        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("findAll", findAll);
        model.addAttribute("boardList",boardList);
        model.addAttribute("boardFile",boardFileList);
        model.addAttribute("bid",bid);
        List<StoryDTO> myStoryList = storyController.storyCheck(loginId); // 내 스토리 리스트
        List<StoryDTO> followingStoryList = storyController.findStoryList(loginId); //내가 팔로우한 사람들의 스토리 리스트
        model.addAttribute("myStoryList", myStoryList);
        List<StoryDTO> neverSeenStoryList = new ArrayList<>();
        for(int i=0; i<followingStoryList.size(); i++) {
            StoryDTO myFollowStory = followingStoryList.get(i); //내가 팔로우한 사람의 스토리
            Long storyId = myFollowStory.getId();  // 내가 팔로우한 사람의 스토리 번호
            boolean result = storyController.findByStoryIdAndLoginId(storyId, loginId);
            System.out.println("==============================MainController/main/result================================"+result);
            if(!result){ // 내가 팔로우한 사람의 스토리를 본적이 없으면
                neverSeenStoryList.add(myFollowStory); // 내가 안본 팔로우스토리 리스트에 추가
            }
        }
        model.addAttribute("neverSeenStoryList", neverSeenStoryList);
        System.out.println("=============================MainController/main/neverSeenStoryList========================="+neverSeenStoryList);
        return "main";
    }
    @GetMapping("/story")
    public String story(){
        return "storyPages/save";
    }


}
