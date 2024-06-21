package domain.post.controller;

import java.util.List;

import domain.post.service.BoardPostService;
import domain.post.vo.BoardPostVO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardPostController {
    Logger logger = LogManager.getLogger(BoardPostController.class);

    @Autowired(required = false)
    private BoardPostService boardpostService;

    @GetMapping("board_post_selectAll")
    public String boardPostSelectAll(BoardPostVO bpvo, Model model) {
        logger.info("boardPostSelectAll 함수 진입 >>>");
        
        List<BoardPostVO> listAll = boardpostService.boardPostSelectAll(bpvo);
        if(listAll.size() > 0) {
        model.addAttribute("listAll", listAll);
        return "post/board_post_selectAll";
    
    } return "post/board_post_selectAll";

    }
    
    
}

