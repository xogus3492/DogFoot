package domain.question.controller;

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

import domain.question.service.CommentQuestionService;
import domain.question.vo.CommentQuestionVO;

@Controller
public class CommentQuestionController {
	Logger logger = LogManager.getLogger(CommentQuestionController.class);
	
	@Autowired(required=false)
	private CommentQuestionService commentQuestionService;
	
	//?���? ?���? ?��
	@GetMapping("comment_question_form")
	public String commentQuestionForm() {
		logger.info("commentQuestionForm ?��?�� 진입 >>> : ");
		return "question/comment_question_form";
	}
	
	@PostMapping("commentQuestionInsert")
	@ResponseBody
	public String commentQuestionInsert(CommentQuestionVO cqvo) {
		logger.info("commentQuestionInsert ?��?�� 진입 >>> : ");
		logger.info("commentQuestionInsert cqvo.Commentquestionid() >>> : " + cqvo.getCommentquestionid());
		
		logger.info("cqvo.getCommentquestionid() >>> : " +cqvo.getCommentquestionid());
		logger.info("cqvo.getContent() >>> : " + cqvo.getContent());
		logger.info("cqvo.getUserid() >>> : " + cqvo.getUserid());
		logger.info("cqvo.getBoardfreeid() >>> : " + cqvo.getBoardquestionid());	
		
		int nCnt = commentQuestionService.commentQuestionInsert(cqvo);
		logger.info("commentQuestionInsert nCnt >>> : " + nCnt);
		
		if(1 == nCnt) {return "GOOD";}
		else {return "BAD";}
	}
	
	//?���? ?���? 조회
	@PostMapping("commentQuestionSelectAll")
	@ResponseBody
	public String commentQuestionSelectAll(CommentQuestionVO cqvo) {
		logger.info("commentQuestionSelectAll ?��?�� 진입 >>> : ");
		logger.info("commentQuestionSelectAll cqvo.Boardquestionid() >>> : " + cqvo.getBoardquestionid());
		
		List<CommentQuestionVO> list = commentQuestionService.commentQuestionSelectAll(cqvo);
		logger.info("commentFreeSelectAll list >>> : " + list);
		
		if (list != null) {
            for(CommentQuestionVO c : list) {
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
		
		String ss = "";
		String listStr = "";
		for (int i=0; i < list.size(); i++) {
			CommentQuestionVO _cqvo = list.get(i);
			String s0 = _cqvo.getCommentquestionid();
			String s1 = _cqvo.getUserid();
			String s2 = _cqvo.getContent();
			String s3 = _cqvo.getBoardquestionid();
			String s4 = _cqvo.getCreateddate();
			ss = s0+","+s1+","+s2+","+s3+","+s4;
			listStr += ss+"&";
		}
		return listStr;
	}
	@PostMapping("commentQuestionDelete")
	@ResponseBody
	public String commentQuestionDelete(CommentQuestionVO cqvo) {	
		logger.info("commentQuestionDelete >>> : ");
		logger.info("commentQuestionDelete cqvo.getCommentquestionid() >>> : " + cqvo.getCommentquestionid());
	
		int nCnt  = commentQuestionService.commentQuestionDelete(cqvo);
		logger.info("commentQuestionDelete nCnt >>> : " + nCnt);
		
		if (1 == nCnt) { return "GOOD"; }
		else { return "BAD"; }
	}

}
