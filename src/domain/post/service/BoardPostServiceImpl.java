package domain.post.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.post.dao.BoardPostDAO;
import domain.post.vo.BoardPostVO;

@Service
public class BoardPostServiceImpl implements BoardPostService {
	Logger logger = LogManager.getLogger(BoardPostServiceImpl.class);
	
	@Autowired(required = false)
	private BoardPostDAO boardPostDAO;
	
	@Override
	public List<BoardPostVO> boardPostSelectAll(BoardPostVO bpvo) {
		// TODO Auto-generated method stub
		logger.info("boardPostSelectAll 함수 진입 >>> : ");
		
		return boardPostDAO.boardPostSelectAll(bpvo);
	}

}
