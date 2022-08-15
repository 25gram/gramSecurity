package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.dto.StoryDTO;
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
    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        List<StoryDTO> storyDTOList = storyService.findByLoginId(loginId);
        System.out.println("storyDTOList = " + storyDTOList);
        model.addAttribute("storyList", storyDTOList);
        return "storyIndex";
    }
    @GetMapping("/myStory")
    public String findByLoginId(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        List<StoryDTO> storyDTOList = storyService.findByLoginId(loginId);
        model.addAttribute("storyList", storyDTOList);
        return "storyPages/myStory";
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

}
