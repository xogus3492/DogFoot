package domain.question.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.question.dao.BoardQuestionDAO;
import domain.question.vo.BoardQuestionVO;

@Service
@Transactional
public class BoardQuestionServiceImpl implements BoardQuestionService {
	Logger logger = LogManager.getLogger(BoardQuestionServiceImpl.class);
	
	// 서비스에서 DAO 연결하기 
	// 필드 @Autowired 어노테이션으로  DI (의존성 주입하기)
	@Autowired(required=false)		
	private BoardQuestionDAO boardQuestionDAO;

	@Override
	public int boardQuestionInsert(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionInsert 함수 진입 >>> : ");
		return boardQuestionDAO.boardQuestionInsert(bqvo);
	}

	@Override
	public List<BoardQuestionVO> boardQuestionSelectAll(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionSelectAll 함수 진입 >>> : ");
		return boardQuestionDAO.boardQuestionSelectAll(bqvo);
	}

	@Override
	public List<BoardQuestionVO> boardQuestionSelect(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionSelect 함수 진입 >>> : ");
		return boardQuestionDAO.boardQuestionSelect(bqvo);
	}

	@Override
	public List<BoardQuestionVO> boardQuestionUserid(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionUserid 함수 진입 >>> : ");
		return boardQuestionDAO.boardQuestionUserid(bqvo);
	}

	@Override
	public int boardQuestionViewcount(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionViewcount 함수 진입 >>> : ");
		return boardQuestionDAO.boardQuestionViewcount(bqvo);
	}

	@Override
	public int boardQuestionUpdate(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionUpdate 함수 진입 >>> : ");
		return boardQuestionDAO.boardQuestionUpdate(bqvo);
	}

	@Override
	public int boardQuestionDelete(BoardQuestionVO bqvo) {
		// TODO Auto-generated method stub
		logger.info("boardQuestionDelete 함수 진입 >>> : ");
		return boardQuestionDAO.boardQuestionDelete(bqvo);
	}

}
