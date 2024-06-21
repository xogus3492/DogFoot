package domain.job.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.job.vo.CommentJobVO;

@Repository
public class CommentJobDAOImpl implements CommentJobDAO {
	Logger logger = LogManager.getLogger(CommentJobDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int commentJobInsert(CommentJobVO cjvo) {
		// TODO Auto-generated method stub
		logger.info("commentJobInsert 함수 진입 >>> : ");	
		
		return sqlSession.insert("commentJobInsert", cjvo);
	}

	@Override
	public List<CommentJobVO> commentJobSelectAll(CommentJobVO cjvo) {
		// TODO Auto-generated method stub
		logger.info("commentJobSelectAll 함수 진입 >>> : ");	
		
		return sqlSession.selectList("commentJobSelectAll", cjvo);
	}

	@Override
	public int commentJobDelete(CommentJobVO cjvo) {
		// TODO Auto-generated method stub
		logger.info("commentJobDelete 함수 진입 >>> : ");
		
		return sqlSession.update("commentJobDelete", cjvo);
	}

}
