package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.FollowDTO;
import com.its.gramsecurity.entity.FollowEntity;
import com.its.gramsecurity.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public @ResponseBody List<FollowDTO> request_fw(FollowDTO followDTO, Principal principal,Model model) {
        String myId=principal.getName();
        Long id= followService.save(followDTO,myId);
        return null;
    }

    //전체 리스트
    @GetMapping("/list")
    public String findAll(Model model) {
        List<FollowDTO> followDTOList = followService.findAll();
        model.addAttribute("followList", followDTOList);
        return null;
    }

    //팔로우 리스트
    @GetMapping("/myList")
    public @ResponseBody String follow(@RequestParam("myId") String myId, Model model) {
        List<Optional<FollowEntity>> followDTOList = followService.findAllByMyId(myId);
        model.addAttribute("followList", followDTOList);
        System.out.println("myId = " + myId + ", model = " + model);
        return null;
    }

    //팔로잉 리스트
    @GetMapping("/yourList")
    public @ResponseBody String following(@RequestParam("yourId") String yourId, Model model) {
        List<Optional<FollowEntity>> followingDTOList = followService.findAllByYourId(yourId);
        model.addAttribute("followingList", followingDTOList);
        System.out.println("yourId = " + yourId + ", model = " + model);
        return null;
    }

    //언팔로우
    @DeleteMapping("/")   public ResponseEntity UnFollow(@PathVariable Long id) {
        followService.UnFollow(id);
//        return new ResponseEntity<>(HttpStatus.OK);
        return null;
    }

}
