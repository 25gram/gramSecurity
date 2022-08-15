package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.dto.MsgDTO;
import com.its.gramsecurity.entity.MsgEntity;
import com.its.gramsecurity.service.MemberService;
import com.its.gramsecurity.service.MsgService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/msg")
public class MsgController {
@Autowired
    MemberService ms;
@Autowired
    MsgService msgs;


    @GetMapping("msgpage")
    String msgpage(@RequestParam("loginId")String loginId, Model model){
        model.addAttribute("mem",ms.findByLoginId(loginId));
        return "/msg";
    }
//    @GetMapping("gomsgpage")
//    @ResponseBody
//    List<MsgDTO> gomsg(@ModelAttribute MsgDTO mem){
//        return msgs.findByFriendId(mem);
//    }

    @PostMapping("save")
    @ResponseBody
    String save(@ModelAttribute MsgDTO mem){
        System.out.println("MsgController.save");
        msgs.save(mem);
        return "ok";
    }

}
