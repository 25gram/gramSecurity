package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.FollowDTO;
import com.its.gramsecurity.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/request")
    public @ResponseBody String request_fw(FollowDTO followDTO){
        followService.save(followDTO);
        return null;
    }

    @GetMapping("list")
    public String findAll(Model model){
        List<FollowDTO>followDTOList=followService.findAll();
        model.addAttribute("followList",followDTOList);
        return "Contents/testList";
    }

//    @DeleteMapping("/")
//    public ResponseEntity UnFollow(@PathVariable Long id){
//        followService.UnFollow(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    @GetMapping("/findAll/{id}")
//    public String findList(Long id, Model model){
//        List<FollowDTO>followDTOList=followService.findById(id);
//        model.addAttribute("followList",followDTOList);
//        return "Contents/testList";
//    }
}
