package domain.question.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.question.vo.BoardQuestionVO;


@Repository
public class BoardQuestionDAOImpl implements BoardQuestionDAO {
	Logger logger = LogManager.getLogger(BoardQuestionDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;

	@Override
	public int boardQuestionInsert(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionInsert �븿�닔 吏꾩엯 >>> : ");
		
		return sqlSession.insert("boardQuestionInsert", bqvo);
	}

	@Override
	public List<BoardQuestionVO> boardQuestionSelectAll(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionSelectAll �븿�닔 吏꾩엯 >>> : ");
		
		return sqlSession.selectList("boardQuestionSelectAll", bqvo);
	}

	@Override
	public List<BoardQuestionVO> boardQuestionSelect(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionSelect �븿�닔 吏꾩엯 >>> : ");
		
		return sqlSession.selectList("boardQuestionSelect", bqvo);
	}

	@Override
	public List<BoardQuestionVO> boardQuestionUserid(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionUserid �븿�닔 吏꾩엯 >>> : ");
		
		return sqlSession.selectList("boardQuestionUserid", bqvo);
	}

	@Override
	public int boardQuestionViewcount(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionViewcount �븿�닔 吏꾩엯 >>> : ");
		
		return sqlSession.update("boardQuestionViewcount", bqvo);
	}

	@Override
	public int boardQuestionUpdate(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionUpdate �븿�닔 吏꾩엯 >>> : ");
		
		return sqlSession.update("boardQuestionUpdate", bqvo);
	}

	@Override
	public int boardQuestionDelete(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionDelete �븿�닔 吏꾩엯 >>> : ");
		
		return sqlSession.update("boardQuestionDelete", bqvo);
	}
}
