package domain.job.controller;

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

//import common.ChabunUtil;

import domain.job.service.BoardJobService;
import domain.job.service.CommentJobService;
import domain.job.vo.CommentJobVO;


@Controller
public class CommentJobController {
	Logger logger = LogManager.getLogger(CommentJobController.class);
	
	
	// 컨트롤러에서 채번 서비스 연결 
	//@Autowired(required=false)
	//private KosChabunService kosChabunService;
	
	// 컨트롤러에서 댓글 서비스 연결 
	@Autowired(required=false)
	private CommentJobService commentJobService;
	
	// 취업 게시판 댓글 글쓰기 폼
	@GetMapping("comment_job_form")
	public String commentJobForm() {
		logger.info("commentJobForm 함수 진입 >>> : ");	
		return "job/comment_job_form";
	}
	
	// 댓글 등록
	@PostMapping("commentJobInsert")
	@ResponseBody
	public String commentJobInsert(CommentJobVO cjvo) {	
		logger.info("commentJobInsert >>> : ");
		logger.info("commentJobInsert cjvo.getCommentjobid() >>> : " + cjvo.getCommentjobid());
		
		//String commentjobid = "3";
		//cjvo.setCommentjobid(commentjobid);
		logger.info("cjvo.getCommentjobid() >>> : " + cjvo.getCommentjobid());
		logger.info("cjvo.getCommentjobid() >>> : " + cjvo.getCommentjobid());
		logger.info("cjvo.getContent() >>> : " + cjvo.getContent());
		logger.info("cjvo.getUserid() >>> : " + cjvo.getUserid());
		logger.info("cjvo.getBoardjobid() >>> : " + cjvo.getBoardjobid());		
		
		
		int nCnt = commentJobService.commentJobInsert(cjvo);
		logger.info("commentJobInsert nCnt >>> : " + nCnt);
		
		if (1 == nCnt) { return "GOOD"; }
		else { return "BAD"; }
	}
	
	// 댓글 전체 조회	
	@PostMapping("commentJobSelectAll")
	@ResponseBody
	public String commentJobSelectAll(CommentJobVO cjvo) {	
		logger.info("commentJobSelectAll >>> : ");
		// rbvo.setSbnum("B0001");
		logger.info("commentJobSelectAll cjvo.getBoardjobid() >>> : " + cjvo.getBoardjobid());
		
		List<CommentJobVO> list  = commentJobService.commentJobSelectAll(cjvo);
		logger.info("commentJobSelectAll list >>> : " + list);
		
		for (CommentJobVO c : list) {
	    	System.out.println("작성자 : " + c.getCommentjobid());
	    	System.out.println("내용 : " + c.getContent());
	    	 try {
	             c.setUserid(URLEncoder.encode(c.getUserid(), "UTF-8").replace("+", "%20"));
	             c.setContent(URLEncoder.encode(c.getContent(), "UTF-8").replace("+", "%20"));
	         } catch (UnsupportedEncodingException e) {
	             e.printStackTrace();
	         }
	    }
		
		String ss = "";
		String listStr = "";
		for (int i=0; i < list.size(); i++) {
			CommentJobVO _cjvo = list.get(i);
			String s0 = _cjvo.getCommentjobid();
			String s1 = _cjvo.getUserid();
			String s2 = _cjvo.getContent();
			String s3 = _cjvo.getCreateddate();
			ss = s0+","+s1+","+s2+","+s3;
			listStr += ss+"&";
		}
		return listStr;
	}
	
	// 댓글 삭제	
	@PostMapping("commentJobDelete")
	@ResponseBody
	public String commentJobDelete(CommentJobVO cjvo) {	
		logger.info("commentJobDelete >>> : ");
		logger.info("commentJobDelete cjvo.getCommentjobid() >>> : " + cjvo.getCommentjobid());
	
		int nCnt  = commentJobService.commentJobDelete(cjvo);
		logger.info("commentJobDelete nCnt >>> : " + nCnt);
		
		if (1 == nCnt) { return "GOOD"; }
		else { return "BAD"; }
	}
}