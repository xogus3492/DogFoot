package domain.free.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.free.service.CommentFreeService;
import domain.free.vo.CommentFreeVO;

@Controller
public class CommentFreeController {
    Logger logger = LogManager.getLogger(CommentFreeController.class);
    
    @Autowired(required=false)
    private CommentFreeService commentFreeService;
    
    //댓글 쓰기 폼
    @GetMapping("comment_free_form")
    public String commentFreeForm() {
        logger.info("commentFreeForm 함수 진입 >>> : ");
        return "insert/comment_free_form";
    }
    
    @PostMapping("comment_free_insert")
    @ResponseBody
    public String commentFreeInsert(CommentFreeVO cfvo) {
        logger.info("commentFreeInsert 함수 진입 >>> : ");
        logger.info("commentFreeInsert cfvo.Commentfreeid() >>> : " + cfvo.getCommentfreeid());
        
        logger.info("cfvo.getCommentfreeid() >>> : " +cfvo.getCommentfreeid());
        logger.info("cfvo.getContent() >>> : " + cfvo.getContent());
        logger.info("cfvo.getUserid() >>> : " + cfvo.getUserid());
        logger.info("cfvo.getBoardfreeid() >>> : " + cfvo.getBoardfreeid());    
        
        //서비스 호출
        int nCnt = commentFreeService.commentFreeInsert(cfvo);
        logger.info("commentFreeInsert nCnt >>> : " + nCnt);
        
        if(1 == nCnt) {return "GOOD";}
        else {return "BAD";}
    }
    
    //댓글 전체 조회
    @PostMapping("comment_free_selectAll")
    @ResponseBody
    public String commentFreeSelectAll(CommentFreeVO cfvo) {
        logger.info("commentFreeSelectAll 함수 진입 >>> : ");
        logger.info("commentFreeSelectAll cfvo.Commentfreeid() >>> : " + cfvo.getCommentfreeid());
        
        //서비스 호출
        List<CommentFreeVO> list = commentFreeService.commentFreeSelectAll(cfvo);
        logger.info("commentFreeSelectAll list >>> : " + list);
        
        for (CommentFreeVO vo : list) {
        	System.out.println("id : " + vo.getUserid());
        	System.out.println("content : " + vo.getContent());
        }
        
        //댓글 한글 인코딩
        if (list != null) {
            for(CommentFreeVO c : list) {
                if (c != null) {
                    try {
                        if (c.getUserid() != null) {
                            c.setUserid(URLEncoder.encode(c.getUserid(), "UTF-8").replace("+", "%20"));
                        }
                        if (c.getContent() != null) {
                            c.setContent(URLEncoder.encode(c.getContent(), "UTF-8").replace("+", "%20"));
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        StringBuilder listStr = new StringBuilder();
        
        if (list != null) {
            for (CommentFreeVO _cfvo : list) {
                String s0 = _cfvo.getCommentfreeid();
                String s1 = _cfvo.getUserid();
                String s2 = _cfvo.getContent();
                String s3 = _cfvo.getBoardfreeid();
                String s4 = _cfvo.getCreateddate();
                
                String ss = String.join(",", s0, s1, s2, s3, s4);
                listStr.append(ss).append("&");
                
                logger.info("ss >>> : " + ss);
                logger.info("listStr >>> : " + listStr.toString());
            }
        }
        
        return listStr.toString();
    }
    
    @PostMapping("comment_free_delete")
    @ResponseBody
    public String commentFreeDelete(CommentFreeVO cfvo) {    
        logger.info("commentFreeDelete >>> : ");
        logger.info("commentFreeDelete cfvo.getCommentfreeid() >>> : " + cfvo.getCommentfreeid());
        
        //서비스 호출
        int nCnt  = commentFreeService.commentFreeDelete(cfvo);
        logger.info("commentFreeDelete nCnt >>> : " + nCnt);
        
        if (1 == nCnt) { return "GOOD"; }
        else { return "BAD"; }
    }
}
