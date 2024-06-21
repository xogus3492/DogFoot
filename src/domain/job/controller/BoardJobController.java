package domain.job.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//ChabunUtil;
//CommonUtils;
//FileUploadUtil;
//chabun.service.KosChabunService;

import domain.job.service.BoardJobService;
import domain.job.vo.BoardJobVO;
import domain.user.vo.UserVO;
import global.util.CommonUtils;
import global.util.FileUploadUtil;
import global.util.SessionKeyEncryption;
import global.util.chabun.service.KosChabunService;



@Controller
public class BoardJobController {
	Logger logger = LogManager.getLogger(BoardJobController.class);
	
	
	
	// 컨트롤러에서 채번 서비스 연결
	@Autowired(required=false)
	private KosChabunService kosChabunService;
	
	// 컨트롤러에서 회원 서비스 연결 
	@Autowired(required=false)
	private BoardJobService boardJobService;
	
	
	@GetMapping("insert_job_form")
	public String insertForm(HttpServletRequest request, HttpSession session) {
		logger.info("컨트롤러 insertForm 함수 진입 >>> : ");
		
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
	
	
	// 게시판 글쓰기 
	@PostMapping("board_job_insert")
	public String boardJobInsert(HttpServletRequest req) {
		logger.info("boardJobInsert 함수 진입 >>> : ");	
		
		// 채번 구하기 X -> 시퀀스 사용
		//String num = "7"; //kosChabunService.getKosBoardChabun().getBoardjobid();
		//logger.info("boardJobInsert num >>> : " + num);
		//String boardjobid = ChabunUtil.getBoardChabun("N", num);
		//logger.info("boardJobInsert boardjobid >>> : " + boardjobid);
		
		System.out.println("title : " + req.getParameter("title"));
		System.out.println("picturefile : " + req.getParameter("picturefile"));
		
		// 기본 조회수 설정 
		String viewcount = "0";
		
		// 이미지 업로드 
		FileUploadUtil fu = new FileUploadUtil(	 CommonUtils.BOARD_IMG_UPLOAD_PATH
								                ,CommonUtils.BOARD_IMG_FILE_SIZE
								                ,CommonUtils.BOARD_EN_CODE);
		
		// 이미지 파일 원본 사이즈 
		boolean bool = fu.imgfileUpload(req);
		logger.info("boardJobInsert bool >>> : " + bool);

		if (bool) {
			BoardJobVO _bjvo = null;
			_bjvo = new BoardJobVO();
			
			System.out.println(fu.getFileName("picturefile"));
				
			//_bjvo.setBoardjobid(boardjobid); -> 시퀀스 사용
			_bjvo.setTitle(fu.getParameter("title"));
			_bjvo.setContent(fu.getParameter("content"));
			_bjvo.setViewcount(viewcount);
			//_bjvo.setViewcount(fu.getParameter("viewcount"));
			_bjvo.setPicturefile(fu.getFileName("picturefile"));
			_bjvo.setUserid(fu.getParameter("userid"));
			
			logger.info("_bjvo.getBoardjobid() >>> : "	+ _bjvo.getBoardjobid());
			logger.info("_bjvo.getTitle() >>> : "		+ _bjvo.getTitle());
			logger.info("_bjvo.getContent() >>> : "		+ _bjvo.getContent());
			logger.info("_bjvo.getViewcount() >>> : " 	+ _bjvo.getViewcount());
			logger.info("_bjvo.getPicturefile() >>> : " + _bjvo.getPicturefile());
			logger.info("_bjvo.getUserid() >>> : "		+ _bjvo.getUserid());
			logger.info("_bjvo.getDeleteyn() >>> : "	+ _bjvo.getDeleteyn());
			logger.info("_bjvo.getCreateddate() >>> : "	+ _bjvo.getCreateddate());
			logger.info("_bjvo.getModifieddate() >>> : "+ _bjvo.getModifieddate());
			
			// 서비스 호출
			int nCnt = boardJobService.boardJobInsert(_bjvo);
			if (nCnt > 0) { 
				logger.info("boardJobInsert nCnt >>> : " + nCnt);
				return "job/board_job_insert";
			}
		}
		return "insert/insert_form";
	}
		
	// 게시판 전체 조회 
	@GetMapping("board_job_selectAll")
	public String boardJobSelectAll(HttpServletRequest request, BoardJobVO bjvo, Model model) {
		logger.info("boardJobSelectAll 함수 진입 >>> : ");
		//logger.info("user : " + request.getSession().getAttribute());
		
		// 페이징 처리 ====================================================================
		int pageSize = 10;
		int groupSize = CommonUtils.BOARD_GROUP_SIZE;
		int curPage = CommonUtils.BOARD_CUR_PAGE;
		int totalCount = CommonUtils.BOARD_TOTAL_COUNT;
		
		if (bjvo.getCurPage() !=null){
			curPage = Integer.parseInt(bjvo.getCurPage());
		}
		
		bjvo.setPageSize(String.valueOf(pageSize));
		bjvo.setGroupSize(String.valueOf(groupSize));
		bjvo.setCurPage(String.valueOf(curPage));
		bjvo.setTotalCount(String.valueOf(totalCount));

		logger.info("JobSpringFileUploadSelectAll bjvo.getPageSize() >>> : 	" + bjvo.getPageSize());
		logger.info("JobSpringFileUploadSelectAll bjvo.getGroupSize() >>> : 	" + bjvo.getGroupSize());
		logger.info("JobSpringFileUploadSelectAll bjvo.getCurPage() >>> : 	" + bjvo.getCurPage());
		logger.info("JobSpringFileUploadSelectAll bjvo.getTotalCount() >>> : " + bjvo.getTotalCount());
		// 페이징 처리 ======================================================================================
		
		// 서비스 호출
		List<BoardJobVO> listAll = boardJobService.boardJobSelectAll(bjvo);		
		if (listAll.size() > 0) { 
			logger.info("boardJobSelectAll listAll.size() >>> : " + listAll.size());
			
			model.addAttribute("pagingBJVO", bjvo);				
			model.addAttribute("listAll", listAll);
			model.addAttribute("search_bjvo", bjvo);
			return "job/board_job_selectAll";
		}		
		return "job/board_job_selectAll";
	}
	
	// 게시글 조회 
	@GetMapping("board_job_select")
	public String boardJobSelect(BoardJobVO bjvo, Model model) {
		logger.info("boardJobSelect 함수 진입 >>> : ");
		
		logger.info("boardJobSelect 함수 진입  bjvo.getBoardjobid() >>> : " + bjvo.getBoardjobid());
		
		// 서비스 호출
		List<BoardJobVO> listS = boardJobService.boardJobSelect(bjvo);		
		if (listS.size() == 1) { 
			logger.info("boardJobSelect listS.size() >>> : " + listS.size());
			
			int viewcountCnt = boardJobService.boardJobViewcount(bjvo);
			logger.info("boardJobSelect viewcountCnt >>> : " + viewcountCnt);
					
			model.addAttribute("listS", listS);
			return "job/board_job_select";
		}		
		return "job/board_job_selectAll";
	}
		
	// 게시글 내용보기  
	@GetMapping("board_job_selectContents")
	public String boardJobSelecContents(BoardJobVO bjvo, Model model) {
		logger.info("boardJobSelecContents 함수 진입 >>> : ");
		
		logger.info("boardJobSelecContents 함수 진입  bjvo.getBoardjobid() >>> : " + bjvo.getBoardjobid());
		
		// 서비스 호출
		List<BoardJobVO> listS = boardJobService.boardJobSelect(bjvo);		
		if (listS.size() == 1) { 
			logger.info("boardJobSelect listS.size() >>> : " + listS.size());
			
			int viewcountCnt = boardJobService.boardJobViewcount(bjvo);
			logger.info("boardJobSelect viewcountCnt >>> : " + viewcountCnt);
					
			model.addAttribute("listS", listS);
			return "job/board_job_selectContents";
		}		
		return "job/board_job_SelectAll";
	}
	
	// 게시글 수정 
	@GetMapping("board_job_update")
	public String boardJobUpdate(BoardJobVO bjvo, Model model) {
		logger.info("boardJobUpdate 함수 진입 >>> : ");
		logger.info("boardJobUpdate 함수 진입 bjvo.getBoardjobid() >>> : " + bjvo.getBoardjobid());
		
		int nCnt = boardJobService.boardJobUpdate(bjvo);
		
		if (nCnt > 0) { 
			logger.info("boardJobUpdate nCnt >>> : " + nCnt);
			return "job/board_job_update";
		}
		return "#";		
	}
	
	// 게시글 삭제
	@GetMapping("board_job_delete")
	public String boardJobDelete(BoardJobVO bjvo, Model model) {
		logger.info("boardJobDelete 함수 진입 >>> : ");
		logger.info("boardJobDelete 함수 진입 kbvo.getBoardjobid() >>> : " + bjvo.getBoardjobid());
		
		int nCnt = boardJobService.boardJobDelete(bjvo);
		
		if (nCnt > 0) { 
			logger.info("boardJobDelete nCnt >>> : " + nCnt);
			return "job/board_job_delete";
		}
		return "#";		
	}
}
