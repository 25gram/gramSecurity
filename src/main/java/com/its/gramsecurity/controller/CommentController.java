package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.CommentDTO;
import com.its.gramsecurity.entity.CommentEntity;
import com.its.gramsecurity.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글저장
    @PostMapping("/commentSave")
    public @ResponseBody List<Optional<CommentEntity>> save(@ModelAttribute CommentDTO commentDTO,Principal principal){
        String memberId= principal.getName();
        commentService.save(commentDTO,memberId);
        List<Optional<CommentEntity>>commentDTOList=commentService.findAll(commentDTO.getBoardId());
        System.out.println("CommentController.save");
        System.out.println("commentDTOList"+commentDTOList);
        return null;
    }

    //댓글 리스트 출력
    @GetMapping("/list")
    public @ResponseBody String findComment(@RequestParam("boardId")Long boardId, Model model){
        List<Optional<CommentEntity> >commentDTOList=commentService.findAll(boardId);
        model.addAttribute("commentDTOList",commentDTOList);
        return null;
    }
}
