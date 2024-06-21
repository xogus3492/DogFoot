package domain.free.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.free.dao.BoardFreeDAO;
import domain.free.vo.BoardFreeVO;

@Service
public class BoardFreeServiceImpl implements BoardFreeService {
	Logger logger = LogManager.getLogger(BoardFreeServiceImpl.class);
	
	@Autowired(required = false)
	private BoardFreeDAO boardFreeDAO;
	
	@Override
	public int boardFreeInsert(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreeInsert 함수 진입 >>> : ");
		
		return boardFreeDAO.boardFreeInsert(bfvo);
	}

	@Override
	public List<BoardFreeVO> boardFreeSelectAll(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreeSelectAll 함수 진입 >>> : ");
		
		return boardFreeDAO.boardFreeSelectAll(bfvo);
	}

	@Override
	public List<BoardFreeVO> boardFreeSelect(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreeSelect 함수 진입 >>> : ");
		
		return boardFreeDAO.boardFreeSelect(bfvo);
	}

	@Override
	public List<BoardFreeVO> boardFreePwCheck(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreePwCheck 함수 진입 >>> : ");
		
		return boardFreeDAO.boardFreePwCheck(bfvo);
	}

	@Override
	public int boardFreeViewCount(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreeViewCount 함수 진입 >>> : ");
		
		return boardFreeDAO.boardFreeViewCount(bfvo);
	}

	@Override
	public int boardFreeUpdate(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreeUpdate 함수 진입 >>> : ");
		
		return boardFreeDAO.boardFreeUpdate(bfvo);
	}

	@Override
	public int boardFreeDelete(BoardFreeVO bfvo) {
		// TODO Auto-generated method stub
		logger.info("boardFreeDelete 함수 진입 >>> : ");
		
		return boardFreeDAO.boardFreeDelete(bfvo);
	}

}
