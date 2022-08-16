package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.FollowDTO;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.dto.StoryDTO;
import com.its.gramsecurity.service.FollowService;
import com.its.gramsecurity.service.MemberService;
import com.its.gramsecurity.service.StoryService;
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
    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        StoryDTO storyDTO = storyService.findByLoginId(loginId);
        model.addAttribute("storyDTO", storyDTO);
        return "storyIndex";
    }
    @GetMapping("/storyView/{id}")
    public String storyView(Model model, @PathVariable("id") String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<FollowDTO> followDTOList = storyIdList(principalDetails);
        List<StoryDTO> storyDTOList = storyService.storyView(followDTOList);
        model.addAttribute("storyList", storyDTOList);
        return "storyPages/myStory";
    }
    @GetMapping("/findByLoginId")
    public String findByLoginId(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        StoryDTO storyDTO = storyService.findByLoginId(loginId);
        if(storyDTO==null){
            String result = "no";
            return result;
        } else {
            model.addAttribute("storyDTO", storyDTO);
            String result = "ok";
            return result;
        }
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
        storyService.saveFile(storyDTO);
        return "redirect:/storyBoard/myStory";
    }
    @GetMapping("/storyIdList")
    public List<FollowDTO> storyIdList(@AuthenticationPrincipal PrincipalDetails principalDetails){
        String id = principalDetails.getMemberDTO().getLoginId();
        List<FollowDTO> myList = followService.findAllByMyId(id);
        List<FollowDTO> yourlist=followService.findAllByYourId(id);
        return myList;
    }
}
