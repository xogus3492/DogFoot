package domain.free.controller;

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

import domain.free.service.BoardFreeService;
import domain.free.vo.BoardFreeVO;
import domain.user.vo.UserVO;
import global.util.CommonUtils;
import global.util.FileUploadUtil;

@Controller
public class BoardFreeController {
   Logger logger = LogManager.getLogger(BoardFreeController.class);
   
   @Autowired(required=false)
   private  BoardFreeService boardFreeService;
   
   @GetMapping("insert_form")
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
   
   @PostMapping("board_free_insert")
   public String boardFreeInsert(HttpServletRequest req) {
      logger.info("boardFreeInsert 함수 진입 >>> : ");
      //이미지 업로드
      FileUploadUtil fu = new FileUploadUtil(    CommonUtils.BOARD_IMG_UPLOAD_PATH
                                        ,CommonUtils.BOARD_IMG_FILE_SIZE
                                        ,CommonUtils.BOARD_EN_CODE);
      
      String viewCount = "0";
      
      // 이미지 파일 원본 사이즈 
      boolean bool = fu.imgfileUpload(req);
      logger.info("kosBoardInsert bool >>> : " + bool);
      
      BoardFreeVO _bfvo = null;
      _bfvo = new BoardFreeVO();
      
      _bfvo.setBoardfreeid(fu.getParameter("boardfreeid"));
      _bfvo.setTitle(fu.getParameter("title"));
      _bfvo.setContent(fu.getParameter("content"));
      _bfvo.setViewcount(viewCount);      
      _bfvo.setPicturefile(fu.getFileName("picturefile"));
      _bfvo.setUserid(fu.getParameter("userid"));
      
      logger.info("_bfvo.getBoardfreeid" + _bfvo.getBoardfreeid());
      logger.info("_bfvo.getTitle" + _bfvo.getTitle());
      logger.info("_bfvo.getContent" + _bfvo.getContent());
      logger.info("_bfvo.getViewcount" + _bfvo.getViewcount());
      logger.info("_bfvo.getPicturefile" + _bfvo.getPicturefile());
      logger.info("_bfvo.getUserid" + _bfvo.getUserid());
      
      //서비스 호출
      int nCnt = boardFreeService.boardFreeInsert(_bfvo);
      if(nCnt > 0) {
         logger.info("boardFreeInsert nCnt >>> : " + nCnt);
         return "free/board_free_insert";
      }
      return "insert/insert_form";
   }

   
   @GetMapping("board_free_selectAll")
   public String boardFreeSelectAll(BoardFreeVO bfvo, Model model) {
      logger.info("boardFreeSelectAll 함수 진입 >>> : ");
      
      
      int pageSize = CommonUtils.BOARD_PAGE_SIZE;
      int groupSize = CommonUtils.BOARD_GROUP_SIZE;
      int curPage = CommonUtils.BOARD_CUR_PAGE;
      int totalCount = CommonUtils.BOARD_TOTAL_COUNT;
      
      if (bfvo.getCurPage() !=null){
         curPage = Integer.parseInt(bfvo.getCurPage());
      }
      
      bfvo.setPageSize(String.valueOf(pageSize));
      bfvo.setGroupSize(String.valueOf(groupSize));
      bfvo.setCurPage(String.valueOf(curPage));
      bfvo.setTotalCount(String.valueOf(totalCount));
      
      logger.info("boardFreeFileUploadSelectAll bfvo.getPageSize() >>> :    " + bfvo.getPageSize());
      logger.info("boardFreeFileUploadSelectAll bfvo.getGroupSize() >>> :    " + bfvo.getGroupSize());
      logger.info("boardFreeFileUploadSelectAll bfvo.getCurPage() >>> :    " + bfvo.getCurPage());
      logger.info("boardFreeFileUploadSelectAll bfvo.getTotalCount() >>> : " + bfvo.getTotalCount());
      
      //서비스 호출
      List<BoardFreeVO> listAll = boardFreeService.boardFreeSelectAll(bfvo);
      if(listAll.size() > 0) {
         logger.info("boardFreeSelectAll listAll.size() >>> : " + listAll.size()); 
         model.addAttribute("pagingBFVO", bfvo);
         model.addAttribute("listAll", listAll);
         model.addAttribute("search_bfvo", bfvo);
         return "free/board_free_selectAll";
      }
      return "free/board_free_selectAll";
   }
   
   //게시글 조회
   @GetMapping("board_free_select")
   public String boardFreeSelect(BoardFreeVO bfvo, Model model) {
      logger.info("boardFreeSelect 함수 진입 >>> : ");
      
      logger.info("boardFreeSelect 함수 진입 bfvo.getBoardfreeid() >>> : " + bfvo.getBoardfreeid());
      
      //서비스 호출
      List<BoardFreeVO> listS = boardFreeService.boardFreeSelect(bfvo);
      if(listS.size() > 0) {
         logger.info("boardFreeSelect listS.size() >>> : " + listS.size()); 
         
         
         int viewCnt = boardFreeService.boardFreeViewCount(bfvo);
         logger.info("boardFreeSelect viewCnt" + viewCnt);
         
         
         model.addAttribute("listS", listS);
         return "free/board_free_select";
      }
      return "free/board_free_SelectAll";
   }
   
   @GetMapping("board_free_selectContents")
   public String boardFreeSelectContents(BoardFreeVO bfvo, Model model) {
      logger.info("boardFreeSelectContents 함수 진입 >>> : ");
      
      logger.info("boardFreeSelectContents 함수 진입 bfvo.getBoardfreeid() >>> : " + bfvo.getBoardfreeid());
      
      //서비스 호출
      List<BoardFreeVO> listS = boardFreeService.boardFreeSelect(bfvo);
      if(listS.size() > 0) {
         logger.info("boardFreeSelect listS.size() >>> : " + listS.size()); 
         
         int viewCnt = boardFreeService.boardFreeViewCount(bfvo);
         logger.info("boardFreeSelect viewCnt" + viewCnt);
         
         model.addAttribute("listS", listS);
         return "free/board_free_selectContents";
      }
      return "free/board_free_selectAll";
   }
   
   @GetMapping("board_free_update")
   public String boardFreeUpdate(BoardFreeVO bfvo, Model model) {
      logger.info("boardFreeUpdate 함수 진입 >>> : ");
      logger.info("boardFreeUpdate bfvo.getBoardfreeid() 함수 진입 >>> : " + bfvo.getBoardfreeid());
      
      int nCnt = boardFreeService.boardFreeUpdate(bfvo);
      
      if(nCnt > 0) {
         logger.info("boardFreeUpdate nCnt >>> : " + nCnt);
         return "free/board_free_update";
      }
      return "#";
   } 
   @GetMapping("board_free_delete")
   public String boardFreeDelete(BoardFreeVO bfvo, Model model) {
      logger.info("boardFreeDelete 함수 진입 >>> : ");
      logger.info("boardFreeDelete bfvo.getBoardfreeid() 함수 진입 >>> : " + bfvo.getBoardfreeid());
      
      int nCnt = boardFreeService.boardFreeDelete(bfvo);
      
      if(nCnt > 0) {
         logger.info("boardFreeDelete nCnt >>> : " + nCnt);
         return "free/board_free_delete";
      }
      return "#";
   }
   
}
