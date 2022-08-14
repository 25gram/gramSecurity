package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.FollowDTO;
import com.its.gramsecurity.entity.FollowEntity;
import com.its.gramsecurity.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    //팔로우신청
    @PostMapping("/request")
    public @ResponseBody FollowDTO request_fw(@ModelAttribute  FollowDTO followDTO,
                                           @AuthenticationPrincipal PrincipalDetails principalDetails) {
        String myId = principalDetails.getMemberDTO().getLoginId();
        followService.save(followDTO, myId);
        return followDTO;
    }

    //전체 리스트
    @GetMapping("/list")
    public String findAll(Model model) {
        List<FollowDTO> followDTOList = followService.findAll();
        model.addAttribute("followList", followDTOList);
        return null;
    }

    //팔로우 리스트
    @PostMapping("/myList")
    public @ResponseBody List<FollowDTO> follow(@RequestParam("loginId") String loginId, Model model) {
        List<FollowDTO> followDTOList = followService.findAllByMyId(loginId);
        model.addAttribute("followList", followDTOList);
        return followDTOList;
    }


    //팔로잉 리스트
    @PostMapping("/yourList")
    public @ResponseBody List<FollowDTO> following(@RequestParam("loginId") String loginId, Model model) {
        List<FollowDTO> followDTOList = followService.findAllByYourId(loginId);
        model.addAttribute("followDTOList", followDTOList);
        return followDTOList;
    }

    //언팔로우
    @Transactional
    @DeleteMapping("/delete")
    public ResponseEntity UnFollow(@RequestParam("yourId") String yourId,
                                   @RequestParam("myId")String myId ) {
        followService.UnFollow(yourId, myId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
