package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.StoryDTO;
import com.its.gramsecurity.service.StoryService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/")
    public String findByMemberId(HttpSession session, Model model) {
        String memberId = (String)session.getAttribute("member_id");
        List<StoryDTO> storyDTOList = storyService.findByMemberId(memberId);
        model.addAttribute("storyList", storyDTOList);
        return "storyPages/main";
    }
    @GetMapping("/save-form")
    public String saveForm() {
        return "storyPages/save";
    }
}
