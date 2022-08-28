package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.*;
import com.its.gramsecurity.service.FollowService;
import com.its.gramsecurity.service.MemberService;
import com.its.gramsecurity.service.StoryService;
import com.its.gramsecurity.service.StoryViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/storyBoard")

@Controller
public class StoryController {
    private final StoryService storyService;
    String memberName = "admin";
    private final MemberService memberService;
    private final FollowService followService;
    private final StoryViewService storyViewService;
    @GetMapping("/findStoryList")
    public List<StoryDTO> findStoryList(String loginId) {
        List<FollowDTO> followDTOList = storyIdList(loginId);
        List<StoryDTO> storyDTOList = storyService.findStoryList(followDTOList);
//        System.out.println("========================StoryController/findStoryList/yourStoryList========================="+storyDTOList);
        return storyDTOList;
    }
    @GetMapping("/")
    public String myStory(@AuthenticationPrincipal PrincipalDetails principalDetails){
        List<StoryDTO> myStoryList = new ArrayList<>();
        StoryDTO myNewStory = new StoryDTO();
        String loginId = principalDetails.getMemberDTO().getLoginId();
        List<StoryDTO> storyDTOList = storyService.findByLoginId(loginId);
        if(storyDTOList.isEmpty()){
            return "redirect:/storyBoard/save-form";
        } else {
            for(StoryDTO storyDTO: storyDTOList){
                myStoryList.add(storyDTO);
                int i = myStoryList.size();
                myNewStory = myStoryList.get(i-1);
            }
            Long storyId = myNewStory.getId();
            return "redirect:/storyBoard/stories?id="+storyId+"&loginId="+loginId;
        }
    }
    public List<StoryDTO> storyCheck(String loginId) {
        List<StoryDTO> storyDTOList = storyService.findByLoginId(loginId);
        return storyDTOList;
    }

    @GetMapping("/stories")
    public String stories(@RequestParam("id") Long storyId, @RequestParam("loginId") String loginId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<StoryDTO> storyDTOList = storyService.findByLoginId(loginId); //본적없는 팔로우스토리 아이디 받아서 다시찾기
        StoryDTO storyDTO = storyService.findById(storyId); //본적없는 팔로우스토리 아이디 받아서 다시찾기
        String myId = principalDetails.getMemberDTO().getLoginId();
        Long countStoryView = storyViewService.storyViewSave(storyId, myId);
        model.addAttribute("storyList", storyDTOList);
        model.addAttribute("storyDTO", storyDTO);
        model.addAttribute("countStoryView", countStoryView);
        return "storyPages/stories";
    }

    @GetMapping("/findByLoginId")
    public List<StoryDTO> findByLoginId(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        List<StoryDTO> storyDTOList = storyService.findByLoginId(loginId);
        return storyDTOList;
    }
    @GetMapping("/save-form")
    public String saveForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        MemberDTO memberDTO = principalDetails.getMemberDTO();
        model.addAttribute("memberDTO", memberDTO);
        return "storyPages/save";
    }

    @GetMapping("/findByLocation")
    public @ResponseBody List<StoryDTO> findByStoryLocationTag(@ModelAttribute String storyLocationTag){
        List<StoryDTO> storyDTOList = storyService.findByStoryLocationTag(storyLocationTag);
        return storyDTOList;
    }

    @PostMapping("/save")
    public @ResponseBody Long save(@ModelAttribute StoryDTO storyDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        String loginId = principalDetails.getUsername();
        Long id = storyService.save(storyDTO,loginId);
        return id;
    }
    @PostMapping("/saveFile")
    public String saveFile(@ModelAttribute StoryDTO storyDTO) throws IOException {
        Long storyId = storyService.saveFile(storyDTO);
        StoryDTO storySaved = storyService.findById(storyId);
        String loginId = storySaved.getLoginId();
        return "redirect:/storyBoard/stories?id="+storyId+"&loginId="+loginId;
    }

    public List<FollowDTO> storyIdList(String id){
        List<FollowDTO> myList = followService.findAllByMyId(id);
        List<FollowDTO> yourList=followService.findAllByYourId(id);
//        System.out.println("============================StoryController/storyIdList/myList=============================="+myList);
        return myList;
    }
    @GetMapping("/storyViewCheck")
    public boolean findByStoryIdAndLoginId(Long storyId, String storyWriter){
//        System.out.println("======================StoryController/findByStoryIdAndLoginId/storyId======================="+storyId);
//        System.out.println("====================StoryController/findByStoryIdAndLoginId/storyWriter====================="+storyId);

        boolean result = storyViewService.findByStoryIdAndLoginId(storyId, storyWriter);
        return result;
    }
}
