package domain.job.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.job.vo.BoardJobVO;

@Repository
public class BoardJobDAOImpl implements BoardJobDAO {
	Logger logger = LogManager.getLogger(BoardJobDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int boardJobInsert(BoardJobVO bjvo) {
		// TODO Auto-generated method stub
		logger.info("BoardJobInsert 함수 진입 >>> : ");	
		
		return sqlSession.insert("BoardJobInsert", bjvo);
	}

	@Override
	public List<BoardJobVO> boardJobSelectAll(BoardJobVO bjvo) {
		// TODO Auto-generated method stub
		logger.info("BoardJobSelectAll 함수 진입 >>> : ");	
		
		return sqlSession.selectList("BoardJobSelectAll", bjvo);
	}

	@Override
	public List<BoardJobVO> boardJobSelect(BoardJobVO bjvo) {
		// TODO Auto-generated method stub
		logger.info("BoardJobSelect 함수 진입 >>> : ");	
		
		return sqlSession.selectList("BoardJobSelect", bjvo);
	}

	@Override
	public int boardJobViewcount(BoardJobVO bjvo) {
		// TODO Auto-generated method stub
		logger.info("BoardJobViewcount 함수 진입 >>> : ");	
		
		return sqlSession.update("BoardJobViewcount", bjvo);
	}

	@Override
	public int boardJobUpdate(BoardJobVO bjvo) {
		// TODO Auto-generated method stub
		logger.info("BoardJobUpdate 함수 진입 >>> : ");	
		
		return sqlSession.update("BoardJobUpdate", bjvo);
	}

	@Override
	public int boardJobDelete(BoardJobVO bjvo) {
		// TODO Auto-generated method stub
		logger.info("BoardJobDelete 함수 진입 >>> : ");	
		
		return sqlSession.update("BoardJobDelete", bjvo);
	}

}
