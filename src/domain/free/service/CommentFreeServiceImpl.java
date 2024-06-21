package domain.free.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.free.dao.CommentFreeDAO;
import domain.free.vo.CommentFreeVO;

@Service
@Transactional
public class CommentFreeServiceImpl implements CommentFreeService {
	Logger logger = LogManager.getLogger(CommentFreeServiceImpl.class);
	
	@Autowired(required=false)
	private CommentFreeDAO commentFreeDAO;
	
	@Override
	public int commentFreeInsert(CommentFreeVO cfvo) {
		// TODO Auto-generated method stub
		logger.info("commentFreeInsert 함수 진입 >>> : ");
		
		return commentFreeDAO.commentFreeInsert(cfvo);
	}

	@Override
	public List<CommentFreeVO> commentFreeSelectAll(CommentFreeVO cfvo) {
		// TODO Auto-generated method stub
		logger.info("commentFreeSelectAll 함수 진입 >>> : ");
		
		return commentFreeDAO.commentFreeSelectAll(cfvo);
	}

	@Override
	public int commentFreeDelete(CommentFreeVO cfvo) {
		// TODO Auto-generated method stub
		logger.info("commentFreeDelete 함수 진입 >>> : ");
		
		return commentFreeDAO.commentFreeDelete(cfvo);
	}

}
