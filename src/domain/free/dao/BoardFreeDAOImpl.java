package domain.free.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.free.vo.BoardFreeVO;

@Repository
public class BoardFreeDAOImpl implements BoardFreeDAO {
	Logger logger = LogManager.getLogger(BoardFreeDAOImpl.class);

	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int boardFreeInsert(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreeInsert 함수 진입 >>> : ");
		
		return sqlSession.insert("boardFreeInsert", bfvo);
	}

	@Override
	public List<BoardFreeVO> boardFreeSelectAll(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreeSelectAll 함수 진입 >>> : ");
		
		return sqlSession.selectList("boardFreeSelectAll", bfvo);
	}

	@Override
	public List<BoardFreeVO> boardFreeSelect(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreeSelect 함수 진입 >>> : ");
		
		return sqlSession.selectList("boardFreeSelect", bfvo);
	}

	@Override
	public List<BoardFreeVO> boardFreePwCheck(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreePwCheck 함수 진입 >>> : ");
		
		return sqlSession.selectList("boardFreePwCheck", bfvo);
	}

	@Override
	public int boardFreeViewCount(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreeViewCount 함수 진입 >>> : ");
		
		return sqlSession.update("boardFreeViewCount", bfvo);
	}

	@Override
	public int boardFreeUpdate(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreeUpdate 함수 진입 >>> : ");
		
		return sqlSession.update("boardFreeUpdate", bfvo);
	}

	@Override
	public int boardFreeDelete(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub	
		logger.info("boardFreeDelete 함수 진입 >>> : ");
		
		return sqlSession.delete("boardFreeDelete", bfvo);
	}

}
