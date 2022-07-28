package com.its.gramsecurity.controller;

import com.its.gramsecurity.dto.BoardDTO;
import com.its.gramsecurity.dto.BoardFileDTO;
import com.its.gramsecurity.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/")
    public String aa(){
        return "contents";
    }

    @PostMapping("/fileSave")
    public String fileSave(@ModelAttribute BoardDTO boardDTO,
                           @RequestParam("boardFilter") String boardFilter,
                           MultipartHttpServletRequest mp,
                           Model model) throws IOException {
        BoardDTO saveDTO = boardService.fileSave(boardDTO);
        List<MultipartFile> multipartFileList = mp.getFiles("boardFile");
        List<BoardFileDTO> fileDTOList = new ArrayList<>();
        int a = 0;
        System.out.println("="+boardFilter);
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
        return "detail";
    }
}
