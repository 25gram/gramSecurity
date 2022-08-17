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
    public List<StoryDTO> findStoryList(String id) {
        List<FollowDTO> followDTOList = storyIdList(id);
        List<StoryDTO> storyDTOList = storyService.findStoryList(followDTOList);

        return storyDTOList;
    }
    @GetMapping("/")
    public String myStory(@AuthenticationPrincipal PrincipalDetails principalDetails){
        String loginId = principalDetails.getMemberDTO().getLoginId();
        List<StoryDTO> storyDTOList = storyService.findByLoginId(loginId);
        if(storyDTOList.isEmpty()){
            return "redirect:/storyBoard/save-form";
        } else {
            return "redirect:/storyBoard/stories?loginId="+loginId;
        }
    }

    @GetMapping("/stories/{id}")
    public String stories(@PathVariable("id") String loginId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<StoryDTO> storyDTOList = storyService.findByLoginId(loginId);
        String myId = principalDetails.getMemberDTO().getLoginId();
        storyViewService.storyViewSave(loginId);
        model.addAttribute("storyList", storyDTOList);
        model.addAttribute("myId", myId);
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
        String loginid = storyService.saveFile(storyDTO);
        return "redirect:/storyBoard/stories/"+loginid;
    }

    public List<FollowDTO> storyIdList(String id){
        List<FollowDTO> myList = followService.findAllByMyId(id);
        List<FollowDTO> yourList=followService.findAllByYourId(id);

        return yourList;
    }
    @GetMapping("/storyViewCheck")
    public boolean findByStoryIdAndLoginId(Long id, String loginId){
        boolean result = storyViewService.findByStoryIdAndLoginId(id, loginId);
        return result;
    }
}
