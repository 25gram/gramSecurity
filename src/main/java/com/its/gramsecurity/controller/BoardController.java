package com.its.gramsecurity.controller;

import com.its.gramsecurity.config.auth.PrincipalDetails;
import com.its.gramsecurity.dto.BoardDTO;
import com.its.gramsecurity.dto.BoardFileDTO;
import com.its.gramsecurity.dto.LikesDTO;
import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.service.BoardService;
import com.its.gramsecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    @PostMapping("/fileSave")
    public String fileSave(@ModelAttribute BoardDTO boardDTO,
                           @RequestParam("boardFilter") String boardFilter,
                           MultipartHttpServletRequest mp,
                           @AuthenticationPrincipal PrincipalDetails principalDetails,
                           Model model) throws IOException {
        BoardDTO saveDTO = boardService.fileSave(boardDTO, principalDetails);
        List<MultipartFile> multipartFileList = mp.getFiles("boardFile");
        List<BoardFileDTO> fileDTOList = new ArrayList<>();
        int a = 0;
//        System.out.println("="+boardFilter);
        List<String> list = Arrays.asList(boardFilter.split(","));
        String[] list2 = list.toArray(new String[list.size()]);
        System.out.println(list2[a]);
        for (MultipartFile m: multipartFileList) {
            BoardFileDTO fileDTO = new BoardFileDTO();
            fileDTO.setId(saveDTO.getId());
            fileDTO.setBoardId(saveDTO.getId());
            fileDTO.setBoardFile(m);
            fileDTOList.add(boardService.save(fileDTO,list2[a]));
            a++;
        }
        saveDTO.setBoardFileList(fileDTOList);
        model.addAttribute("boardDTO", saveDTO);
        model.addAttribute("fileDTOList", fileDTOList);
        return "redirect:/main/main";
    }
    @PostMapping("/likes")
    public @ResponseBody String likes(@ModelAttribute LikesDTO likesDTO,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        String likes = boardService.likes(likesDTO,principalDetails);
        return likes;
    }
    @PostMapping("/detail")
    public @ResponseBody List<BoardFileDTO> detail(@RequestParam("boardId") Long id) {
        List<BoardFileDTO> boardFile = boardService.detail(id);
        return boardFile;
    }
    @PostMapping("/find")
    public @ResponseBody List<BoardDTO> board2() {
        List<BoardDTO> boardFile = boardService.boardFind();
        return boardFile;
    }
    @PostMapping("/count")
    public @ResponseBody List<LikesDTO> count() {
        List<LikesDTO> list = boardService.count();
        return list;
    }

    @GetMapping("/search")
    public @ResponseBody List<MemberDTO> search(@RequestParam String  search, Model model){
        System.out.println("BoardController.search");
        System.out.println("search = " + search);
        List<MemberDTO>memberDTOList=memberService.search(search);
        model.addAttribute("searchList",memberDTOList);
        System.out.println("memberDTOList = " + memberDTOList);
        return memberDTOList;
    }
}
