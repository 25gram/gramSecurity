package com.its.gramsecurity.controller;
import com.its.gramsecurity.dto.StoryDTO;
import com.its.gramsecurity.service.StoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final StoryService storyService;
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        session.setAttribute("id", 1);
        session.setAttribute("memberId", "admin");
        String memberId = (String)session.getAttribute("memberId");
        List<StoryDTO> storyDTOList = storyService.findByMemberId(memberId);
        model.addAttribute("storyList", storyDTOList);
        return "storyIndex";
    }
}

