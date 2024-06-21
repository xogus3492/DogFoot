package domain.question.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import domain.user.vo.UserVO;
import domain.question.service.BoardQuestionService;
import domain.question.vo.BoardQuestionVO;

import global.util.ChabunUtil;
import global.util.CommonUtils;
import global.util.FileUploadUtil;


@Controller
public class BoardQuestionController {
	Logger logger = LogManager.getLogger(BoardQuestionController.class);
	
	@Autowired(required=false)
	private  BoardQuestionService boardQuestionService;
	
	@GetMapping("insert_question_form")
	public String insertForm(HttpServletRequest request,  HttpSession session) {
		logger.info("boardForm �븿�닔 吏꾩엯 >>>  : ");
			
		
		   Cookie ck[] = request.getCookies();
		String userInfo = "";
		if (ck != null && ck.length > 0) {
		   // 쿠키 이름 중에서 "son"아 있으면 쿠키값 가져오기 
		   for (int i = 0; i < ck.length; i++) {

		      if (ck[i].getName().equals("userInfo")) {
		   userInfo = ck[i].getValue();
		   break;
		      }
		   }
		}

		Object objSession = session.getAttribute(userInfo);
		System.out.println("objSession : " + objSession);

		String userid = "";
		String email = "";
		String tel = "";
		String gender = "";
		String birthday = "";
		String jibunAddress = "";
		String info = "";
		String photo = "";
		String name = "";

		if (objSession != null) {
		   System.out.println("로그인 중");
		   UserVO uservo = (UserVO) objSession;
		   userid = uservo.getUserId();
		   email = uservo.getEmail();
		   tel = uservo.getTel();
		   gender = uservo.getGender();
		   birthday = uservo.getBirthDay();
		   jibunAddress = uservo.getJibunAddress();
		   info = uservo.getInfo();
		   photo = uservo.getPhoto();
		   name = uservo.getName();
		}

		System.out.println("name : " + name);
		
		if (objSession == null) {
			
			return "redirect:/login.p"; // 로그인되지 않은 경우 로그인 페이지로 리디렉션
		}
		
		return "insert/insert_form";
	}
	
	
	@PostMapping("board_question_insert")
	public String boardQuestionInsert(HttpServletRequest req) {
		logger.info("boardQuestionInsert �븿�닔 吏꾩엯 >>> : ");
		
		String viewcount = "0";
		//�씠誘몄� �뾽濡쒕뱶
		FileUploadUtil fu = new FileUploadUtil(	 CommonUtils.BOARD_IMG_UPLOAD_PATH
                								,CommonUtils.BOARD_IMG_FILE_SIZE
                								,CommonUtils.BOARD_EN_CODE);
		
		// �씠誘몄� �뙆�씪 �썝蹂� �궗�씠利� 
		boolean bool = fu.imgfileUpload(req);
		logger.info("kosBoardInsert bool >>> : " + bool);
		
		String num = "15";//kosChabunService.getKosBoardChabun().getBnum();
		logger.info("kosBoardInsert num >>> : " + num);
		String boardquestionid = ChabunUtil.getBoardChabun("N", num);
		logger.info("kosBoardInsert bnum >>> : " + boardquestionid);
		
		BoardQuestionVO _bqvo = null;
		_bqvo = new BoardQuestionVO();
		
		_bqvo.setBoardquestionid(boardquestionid);
		_bqvo.setTitle(fu.getParameter("title"));
		_bqvo.setContent(fu.getParameter("content"));
		_bqvo.setViewcount(viewcount);		
		_bqvo.setPicturefile(fu.getFileName("picturefile"));
		_bqvo.setUserid(fu.getParameter("userid"));
		
		logger.info("_bqvo.getBoardquestionid" + _bqvo.getBoardquestionid());
		logger.info("_bqvo.getTitle" + _bqvo.getTitle());
		logger.info("_bqvo.getContent" + _bqvo.getContent());
		logger.info("_bqvo.getViewcount" + _bqvo.getViewcount());
		logger.info("_bqvo.getPicturefile" + _bqvo.getPicturefile());
		logger.info("_bqvo.getUserid" + _bqvo.getUserid());
		
		//�꽌鍮꾩뒪 �샇異�
		int nCnt = boardQuestionService.boardQuestionInsert(_bqvo);
		if(nCnt > 0) {
			logger.info("boardQuestionInsert nCnt >>> : " + nCnt);
			
			return "question/board_question_insert";
		}
		return "insert/insert_form";
	}

	
	@GetMapping("board_question_selectAll")
	public String boardQuestionSelectAll(BoardQuestionVO bqvo, Model model) {
		logger.info("boardQuestionSelectAll �븿�닔 吏꾩엯 >>> : ");
		
		
		int pageSize = CommonUtils.BOARD_PAGE_SIZE;
		int groupSize = CommonUtils.BOARD_GROUP_SIZE;
		int curPage = CommonUtils.BOARD_CUR_PAGE;
		int totalCount = CommonUtils.BOARD_TOTAL_COUNT;
		
		if (bqvo.getCurPage() !=null){
			curPage = Integer.parseInt(bqvo.getCurPage());
		}
		
		bqvo.setPageSize(String.valueOf(pageSize));
		bqvo.setGroupSize(String.valueOf(groupSize));
		bqvo.setCurPage(String.valueOf(curPage));
		bqvo.setTotalCount(String.valueOf(totalCount));
		
		logger.info("boardFreeFileUploadSelectAll bfvo.getPageSize() >>> : 	" + bqvo.getPageSize());
		logger.info("boardFreeFileUploadSelectAll bfvo.getGroupSize() >>> : 	" + bqvo.getGroupSize());
		logger.info("boardFreeFileUploadSelectAll bfvo.getCurPage() >>> : 	" + bqvo.getCurPage());
		logger.info("boardFreeFileUploadSelectAll bfvo.getTotalCount() >>> : " + bqvo.getTotalCount());
		
		//�꽌鍮꾩뒪 �샇異�
		List<BoardQuestionVO> listAll = boardQuestionService.boardQuestionSelectAll(bqvo);
		if(listAll.size() > 0) {
			logger.info("boardFreeSelectAll listAll.size() >>> : " + listAll.size()); 
			model.addAttribute("pagingBQVO", bqvo);
			model.addAttribute("listAll", listAll);
			return "question/board_question_selectAll";
		}
		return "question/board_question_selectAll";
	}
	
	//寃뚯떆湲� 議고쉶
	@GetMapping("board_question_select")
	public String boardQuestionSelect(BoardQuestionVO bqvo, Model model) {
		logger.info("boardQuestionSelect �븿�닔 吏꾩엯 >>> : ");
		
		logger.info("boardQuestionSelect �븿�닔 吏꾩엯 bfvo.getBoardquestionid() >>> : " + bqvo.getBoardquestionid());
		
		//�꽌鍮꾩뒪 �샇異�
		List<BoardQuestionVO> listS = boardQuestionService.boardQuestionSelect(bqvo);
		if(listS.size() > 0) {
			logger.info("boardQuestionSelect listS.size() >>> : " + listS.size()); 
			
			
			int viewCnt = boardQuestionService.boardQuestionViewcount(bqvo);
			logger.info("boardQuestionSelect viewCnt" + viewCnt);
			
			
			model.addAttribute("listS", listS);
			return "question/board_question_select";
		}
		return "question/board_question_SelectAll";
	}
	
	@GetMapping("board_question_selectContents")
	public String boardQuestionSelectContents(BoardQuestionVO bqvo, Model model) {
		logger.info("boardQuestionSelectContents �븿�닔 吏꾩엯 >>> : ");
		
		logger.info("boardQuestionSelectContents �븿�닔 吏꾩엯 bqvo.getBoardquestionid() >>> : " + bqvo.getBoardquestionid());
		
		//�꽌鍮꾩뒪 �샇異�
		List<BoardQuestionVO> listS = boardQuestionService.boardQuestionSelect(bqvo);
		if(listS.size() > 0) {
			logger.info("boardFreeSelect listS.size() >>> : " + listS.size()); 
			
			int viewCnt = boardQuestionService.boardQuestionViewcount(bqvo);
			logger.info("boardQuestionSelect viewCnt" + viewCnt);
			
			model.addAttribute("listS", listS);
			return "question/board_question_selectContents";
		}
		return "question/board_question_selectAll";
	}
	
	@GetMapping("board_question_update")
	public String boardQuestionUpdate(BoardQuestionVO bqvo, Model model) {
		logger.info("boardQuestionUpdate �븿�닔 吏꾩엯 >>> : ");
		logger.info("boardQuestionUpdate bqvo.getBoardquestionid() �븿�닔 吏꾩엯 >>> : " + bqvo.getBoardquestionid());
		
		int nCnt = boardQuestionService.boardQuestionUpdate(bqvo);
		
		if(nCnt > 0) {
			logger.info("boardQuestionUpdate nCnt >>> : " + nCnt);
			return "question/board_question_update";
		}
		return "#";
	} 
	@GetMapping("board_question_delete")
	public String boardQuestionDelete(BoardQuestionVO bqvo, Model model) {
		logger.info("boardQuestionDelete �븿�닔 吏꾩엯 >>> : ");
		logger.info("boardQuestionDelete  bqvo.getBoardquestionid() �븿�닔 吏꾩엯 >>> : " +  bqvo.getBoardquestionid());
		
		int nCnt = boardQuestionService.boardQuestionDelete(bqvo);
		
		if(nCnt > 0) {
			logger.info("boardQuestionDelete nCnt >>> : " + nCnt);
			return "questoin/board_question_delete";
		}
		return "#";
	}
	
}
