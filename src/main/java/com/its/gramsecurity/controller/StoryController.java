package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.StoryDTO;
import com.its.gramsecurity.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/storyBoard")

@Controller
public class StoryController {
    private final StoryService storyService;
    String memberName = "admin";

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("id", 1);
        model.addAttribute("memberName", memberName);
        List<StoryDTO> storyDTOList = storyService.findByMemberName(memberName);
        model.addAttribute("storyList", storyDTOList);
        return "storyPages/test3";
    }
    @GetMapping("/myStory")
    public String findByMemberId(Model model) {
        List<StoryDTO> storyDTOList = storyService.findByMemberName(memberName);
        model.addAttribute("storyList", storyDTOList);
        return "storyPages/main";
    }
    @GetMapping("/save-form")
    public String saveForm() {
        return "storyPages/save";
    }

}
