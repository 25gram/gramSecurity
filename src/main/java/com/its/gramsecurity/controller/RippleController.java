package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.CommentDTO;
import com.its.gramsecurity.dto.RippleDTO;
import com.its.gramsecurity.service.RippleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ripple")
public class RippleController {
    private final RippleService rippleService;

    @PostMapping("/save")
    public @ResponseBody List<RippleDTO> save(@ModelAttribute RippleDTO rippleDTO,
                                              @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println(rippleDTO);
        String loginId = principalDetails.getMemberDTO().getLoginId();
        rippleService.save(rippleDTO);
        List<RippleDTO> rippleList = rippleService.findAll();
        System.out.println("rippleList" + rippleList);
        return rippleList;
    }

//    댓글 리스트 출력
    @PostMapping("/list")
    public @ResponseBody List<RippleDTO> findAll(Model model){
        List<RippleDTO> rippleList = rippleService.findAll();
        model.addAttribute("rippleList",rippleList);
        return rippleList;
    }
}
