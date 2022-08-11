package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.CommentDTO;
import com.its.gramsecurity.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글저장
    @PostMapping("/commentSave")
    public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO commentDTO,
                                               @AuthenticationPrincipal PrincipalDetails principalDetails){
        String loginId= principalDetails.getMemberDTO().getLoginId();
        commentService.save(commentDTO,loginId);
        List<CommentDTO>commentDTOList=commentService.findAll();
        System.out.println("CommentController.save");
        System.out.println("commentDTOList"+commentDTOList);
        return commentDTOList;
    }

    //댓글 리스트 출력
    @PostMapping("/list")
    public @ResponseBody List<CommentDTO> findComment(Model model){
        List<CommentDTO>commentDTOList=commentService.findAll();
        model.addAttribute("commentDTOList",commentDTOList);
        return commentDTOList;
    }
}
