package domain.post.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.post.vo.BoardPostVO;

@Repository
public class BoardPostDAOImpl implements BoardPostDAO {
	Logger logger = LogManager.getLogger(BoardPostDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BoardPostVO> boardPostSelectAll(BoardPostVO bpvo) {
		// TODO Auto-generated method stub
		logger.info("boardPostSelectAll 함수 진입 >>> : ");
		
		return sqlSession.selectList("boardPostSelectAll", bpvo);
	}

}
