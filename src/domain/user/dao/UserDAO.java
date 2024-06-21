package domain.user.dao;

import domain.user.vo.UserVO;

public interface UserDAO {

	public int insert(UserVO userVO);

	public UserVO selectByEmailOrPassword(UserVO userVO);

	public UserVO selectByEmail(UserVO userVO);

	public int update(UserVO userVO);
}
