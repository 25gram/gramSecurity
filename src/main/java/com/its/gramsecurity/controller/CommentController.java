package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.CommentDTO;
import com.its.gramsecurity.entity.CommentEntity;
import com.its.gramsecurity.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/commentSave")
    public @ResponseBody List<Optional<CommentEntity>> save(@ModelAttribute CommentDTO commentDTO){

        commentService.save(commentDTO);
        System.out.println("CommentController.save");
        System.out.println("commentDTO = " + commentDTO );
        List<Optional<CommentEntity> >commentDTOList=commentService.findAll(commentDTO.getBoardId());
        return null;
    }
}
