package domain.job.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.job.dao.BoardJobDAO;
import domain.job.vo.BoardJobVO;


@Service
@Transactional
public class BoardJobServiceImpl implements BoardJobService {
	Logger logger = LogManager.getLogger(BoardJobServiceImpl.class);
	
	
	// 서비스에서 DAO 연결하기
	// 필드 @Autowired 어노테이션으로 DI (의존성 주입하기)
	@Autowired(required=false)
	private BoardJobDAO boardJobDAO;
	
	@Override
	public int boardJobInsert(BoardJobVO bjvo) {
		// TODO Auto-generated method stub
		logger.info("boardJobInsert 함수 진입 >>> : ");	
		
		return boardJobDAO.boardJobInsert(bjvo);
	}

	@Override
	public List<BoardJobVO> boardJobSelectAll(BoardJobVO bjvo) {
		// TODO Auto-generated method stub
		logger.info("boardJobSelectAll 함수 진입 >>> : ");	
		
		return boardJobDAO.boardJobSelectAll(bjvo);
	}

	@Override
	public List<BoardJobVO> boardJobSelect(BoardJobVO bjvo) {
		// TODO Auto-generated method stub
		logger.info("boardJobSelect 함수 진입 >>> : ");	
		
		return boardJobDAO.boardJobSelect(bjvo);
	}

	@Override
	public int boardJobViewcount(BoardJobVO bjvo) {
		// TODO Auto-generated method stub
		logger.info("boardJobViewcount 함수 진입 >>> : ");
		
		return boardJobDAO.boardJobViewcount(bjvo);
	}

	@Override
	public int boardJobUpdate(BoardJobVO bjvo) {
		// TODO Auto-generated method stub
		logger.info("boardJobUpdate 함수 진입 >>> : ");	
		
		return boardJobDAO.boardJobUpdate(bjvo);
	}

	@Override
	public int boardJobDelete(BoardJobVO bjvo) {
		// TODO Auto-generated method stub
		logger.info("boardJobDelete 함수 진입 >>> : ");
		
		return boardJobDAO.boardJobDelete(bjvo);
	}

}
