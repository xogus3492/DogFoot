package domain.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.user.vo.UserVO;

public interface UserService {

	public String insert(UserVO userVO);

	public String login(UserVO userVO, HttpServletRequest request, HttpServletResponse response);

	public String checkDuplicateEmail(UserVO userVO);
	
	public String update(UserVO userVO);
}
