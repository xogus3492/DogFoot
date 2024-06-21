package domain.user.dao;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.user.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	Logger logger = Logger.getLogger(UserDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(UserVO userVO) {
		return sqlSession.insert("insert", userVO);
	}

	@Override
	public UserVO selectByEmailOrPassword(UserVO userVO) {
		return sqlSession.selectOne("selectByEmailOrPassword", userVO);
	}

	@Override
	public UserVO selectByEmail(UserVO userVO) {
		return sqlSession.selectOne("selectByEmail", userVO);
	}

	@Override
	public int update(UserVO userVO) {
		return sqlSession.insert("update", userVO);
	}
}
