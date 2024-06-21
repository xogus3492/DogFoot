package domain.user.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.user.dao.UserDAO;
import domain.user.vo.UserVO;
import global.util.SessionKeyEncryption;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public String insert(UserVO user) {
		int result = userDAO.insert(user);
		String response = "FAIL";
		if (result == 1) {
			response = "SUCCESS";
		}
		return response;
	}

	@Override
	public String login(UserVO userVO, HttpServletRequest request, HttpServletResponse response) {
		UserVO result = userDAO.selectByEmailOrPassword(userVO);
		
		String r = null;
		if (result != null) {
			String sessionKey = SessionKeyEncryption.hashKey(result.getUserId()); // 암호화 필요
			Cookie ck = new Cookie("userInfo", sessionKey);
			ck.setMaxAge(60*60*24); 
			response.addCookie(ck);
			
			HttpSession session = request.getSession();
			session.setAttribute(sessionKey, result);
			request.setAttribute("user", result);
			r = "SUCCESS";
		} else {
			r = "FAIL";
		}
		return r;
	}

	@Override
	public String checkDuplicateEmail(UserVO userVO) {
		UserVO result = userDAO.selectByEmail(userVO);
		String response = null;
		if (result != null) {
			response = "DUPLICATED";
		} else {
			response = "NON_DUPLICATED";
		}
		return response;
	}

	@Override
	public String update(UserVO userVO) {
		int result = userDAO.update(userVO);
		String response = "FAIL";
		if (result == 1) {
			response = "SUCCESS";
		}
		return response;
	}

}
