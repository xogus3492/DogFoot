package domain.free.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.free.vo.CommentFreeVO;

@Repository
public class CommentFreeDAOImpl implements CommentFreeDAO {
	Logger logger = LogManager.getLogger(CommentFreeDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;
	
	
	@Override
	public int commentFreeInsert(CommentFreeVO cfvo) {
		// TODO Auto-generated method stub
		logger.info("CommentFreeInsert 함수 진입 >>> : ");
		
		return sqlSession.insert("commentFreeInsert", cfvo);
	}

	@Override
	public List<CommentFreeVO> commentFreeSelectAll(CommentFreeVO cfvo) {
		// TODO Auto-generated method stub
		logger.info("commentFreeSelectAll 함수 진입 >>> : ");
		
		return sqlSession.selectList("commentFreeSelectAll", cfvo);
	}

	@Override
	public int commentFreeDelete(CommentFreeVO cfvo) {
		// TODO Auto-generated method stub
		logger.info("commentFreeDelete 함수 진입 >>> : ");
		
		return sqlSession.delete("commentFreeDelete", cfvo); 
	}

}
