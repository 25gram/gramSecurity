package com.its.gramsecurity.controller;

import com.its.gramsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/msg")
public class MsgController {
@Autowired
    MemberService ms;


    @GetMapping("msgpage")
    String msgpage(@RequestParam("loginId")String loginId, Model model){
        model.addAttribute("mem",ms.findByLoginId(loginId));
        return "/msg";
    }
}
