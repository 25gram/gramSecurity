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
//    @GetMapping("/")
//    public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//        String loginId = principalDetails.getMemberDTO().getLoginId();
//        StoryDTO storyDTO = storyService.findByLoginId(loginId);
//        model.addAttribute("storyDTO", storyDTO);
//        return "storyIndex";
//    }
    @GetMapping("/storyView")
    public List<StoryDTO> storyView(String id) {
        List<FollowDTO> followDTOList = storyIdList(id);
        List<StoryDTO> storyDTOList = storyService.storyView(followDTOList);
        System.out.println("StoryController.storyView");
        System.out.println("id = " + id);
        System.out.println("storyDTOList = " + storyDTOList);
        return storyDTOList;
    }
    @GetMapping("/")
    public String storyIndex(){
        Long id = 1L;
        return "redirect:/storyBoard/stories/"+id;
    }
    @GetMapping("/stories/{id}")
    public String stories(@PathVariable("id") Long id, Model model){
        StoryDTO storyDTO = storyService.findById(id);
        System.out.println("stories---------------------------------------");
        System.out.println("id" + id);
        System.out.println("storyDTO = " + storyDTO);
        model.addAttribute("storyDTO", storyDTO);
        return "storyPages/stories";
    }
    @GetMapping("/findByLoginId")
    public StoryDTO findByLoginId(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String loginId = principalDetails.getMemberDTO().getLoginId();
        StoryDTO storyDTO = storyService.findByLoginId(loginId);
        return storyDTO;
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
        Long id = storyService.saveFile(storyDTO);
        return "redirect:/storyBoard/stories/"+id;
    }
//    @GetMapping("/storyIdList")
    public List<FollowDTO> storyIdList(String id){
        List<FollowDTO> myList = followService.findAllByMyId(id);
        List<FollowDTO> yourlist=followService.findAllByYourId(id);
        System.out.println("StoryController.storyIdList");
        System.out.println("myList = " + myList);
        return yourlist;
    }
}
