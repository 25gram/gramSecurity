package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.StoryDTO;
import com.its.gramsecurity.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/storyBoard")

@Controller
public class StoryController {
    private final StoryService storyService;
    String memberName = "admin";

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long memberId = principalDetails.getMemberDTO().getId();
        List<StoryDTO> storyDTOList = storyService.findByMemberId(memberId);
        model.addAttribute("storyList", storyDTOList);
        return "storyIndex";
    }
    @GetMapping("/myStory")
    public String findByMemberId(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long memberId = principalDetails.getMemberDTO().getId();
        List<StoryDTO> storyDTOList = storyService.findByMemberId(memberId);
        model.addAttribute("storyList", storyDTOList);
        return "storyPages/main";
    }
    @GetMapping("/save-form")
    public String saveForm() {
        return "storyPages/save";
    }

    @GetMapping("/findByLocation")
    public @ResponseBody List<StoryDTO> findByStoryLocationTag(@ModelAttribute String storyLocationTag){
        List<StoryDTO> storyDTOList = storyService.findByStoryLocationTag(storyLocationTag);
        return storyDTOList;
    }

    @PostMapping("/save")
    public @ResponseBody String save(@ModelAttribute StoryDTO storyDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        System.out.println("storyDTO1 = " + storyDTO);

        System.out.println("1" + principalDetails);
        storyService.save(storyDTO,principalDetails);

        System.out.println("storyDTO2 = " + storyDTO);
        System.out.println(storyDTO.getStoryFile());
        System.out.println(storyDTO.getStoryImgFile());
        return "ok";
    }

}
