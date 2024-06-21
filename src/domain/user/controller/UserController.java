package domain.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import domain.job.controller.BoardJobController;
import domain.job.vo.BoardJobVO;
import domain.user.service.UserService;
import domain.user.vo.UserVO;
import global.util.CommonUtils;
import global.util.FileUploadUtil;
import global.util.PasswordEncryption;
import global.util.SessionKeyEncryption;

@Controller
public class UserController {
	Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired(required=false)
	private UserService userService;

	// view 요청
	@GetMapping("/register")
	public String registerForm(Model model) {
		System.out.println("register GET 접근");
		return "register/register";
	}
	
	@GetMapping("/login")
    public String loginForm(Model model) {
        return "login/login";
    }
	
	/*@GetMapping("/login-check")
    public String loginCheck(HttpServletRequest request, Model model) {
		System.out.println("로그인 체크 컨트롤러 확인");
		Cookie[] ck = request.getCookies();
		String userInfo = "";
		if (ck != null && ck.length > 0) {
		    for (int i = 0; i < ck.length; i++) {
		        if (ck[i].getName().equals("userInfo")) {
				     userInfo = ck[i].getValue();
				     break;
		        }
		    }
		}
		if (request.getSession().getAttribute("session" + userInfo) != null) {
			model.addAttribute("sessionKey", userInfo);
			return request.getParameter("url");
		}
        return "login/login";
    }*/

	// ajax 요청
	@PostMapping("/register")
	@ResponseBody
    public Object register(HttpServletRequest request) {
		System.out.println("register post 접근");
		
		// 파일 이미지 업로드 
		FileUploadUtil fu = new FileUploadUtil(	 CommonUtils.USER_IMG_UPLOAD_PATH
										         ,CommonUtils.USER_IMG_FILE_SIZE
										         ,CommonUtils.USER_EN_CODE);
				
		
		// 이미지 파일 원본 사이즈 
		boolean bool = fu.imgfileUpload(request);
		System.out.println("bool : " + bool);
		
		String result = "FAIL";
		if (bool) {
			String email = fu.getParameter("email");
	        String password = PasswordEncryption.hashPassword(fu.getParameter("password"));
	        String name = fu.getParameter("name");
	        String tel = fu.getParameter("tel");
	        String gender = fu.getParameter("gender");
	        String info = fu.getParameter("info");
	        String birthYear = fu.getParameter("birth_year");
	        String birthMonth = fu.getParameter("birth_month");
	        String birthDay = fu.getParameter("birth_day");
	        String zoneCode = fu.getParameter("zone_code");
	        String roadAddress = fu.getParameter("road_address");
	        String detailAddress = fu.getParameter("detail_address");
	        String jibunAddress = fu.getParameter("jibun_address");
	        String photo = fu.getFileName("photo"); // 파일 업로드 처리가 필요할 수 있음
	        
	        System.out.println("E-MAIL: " + email);
	        System.out.println("PASSWORD (hashed): " + password);
	        System.out.println("NAME: " + name);
	        System.out.println("TEL: " + tel);
	        System.out.println("GENDER: " + gender);
	        System.out.println("INFO: " + info);
	        System.out.println("birth_year: " + birthYear);
	        System.out.println("ZONE CODE: " + zoneCode);
	        System.out.println("ROAD ADDRESS: " + roadAddress);
	        System.out.println("DETAIL ADDRESS: " + detailAddress);
	        System.out.println("JIBUN ADDRESS: " + jibunAddress);
	        System.out.println("PHOTO FILE NAME: " + photo);
	        
			result = userService.insert(UserVO.of(fu));
		}
		
		return result;
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response) {
    	UserVO userVO = new UserVO();
    	userVO.setEmail(request.getParameter("email"));
    	userVO.setPassword(PasswordEncryption.hashPassword(request.getParameter("password")));
        return userService.login(userVO, request, response);
    }
    
    @GetMapping("/email-check")
    @ResponseBody
    public String checkDuplicateEmail(HttpServletRequest request) {
    	UserVO userVO = new UserVO();
    	userVO.setEmail(request.getParameter("email"));
        return userService.checkDuplicateEmail(userVO);
    }
    
    @PostMapping("/update")
    @ResponseBody
    public String update(HttpServletRequest request) {
    	System.out.println("업데이트 컨트롤러 확인");
    	UserVO userVO = new UserVO();
    	userVO.setUserId(request.getParameter("userid"));
    	userVO.setEmail(request.getParameter("email"));
    	userVO.setGender(request.getParameter("gender"));
    	userVO.setBirthDay(request.getParameter("birthday"));
    	userVO.setJibunAddress(request.getParameter("jibunAddress"));
    	userVO.setInfo(request.getParameter("info"));
        return userService.update(userVO);
    }
	
    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	String sessionKey = SessionKeyEncryption.hashKey(request.getParameter("userid"));
    	session.removeAttribute(sessionKey);
    	System.out.println("로그아웃 세션 제거 후 : " + sessionKey);
    	
    	String result = "FAIL";
    	if (session.getAttribute(sessionKey) == null) {
    		result = "SUCCESS";
    	}
        return result;
    }
}
