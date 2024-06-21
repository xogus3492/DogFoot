package domain.job.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.job.dao.CommentJobDAO;
import domain.job.vo.CommentJobVO;

@Service
@Transactional
public class CommentJobServiceImpl implements CommentJobService {
	Logger logger = LogManager.getLogger(CommentJobServiceImpl.class);
	
	// 서비스에서 DAO 연결하기 
	// 필드 @Autowired 어노테이션으로  DI (의존성 주입하기)
	@Autowired(required=false)	
	private CommentJobDAO commentJobDAO;
	
	
	@Override
	public int commentJobInsert(CommentJobVO cjvo) {
		// TODO Auto-generated method stub
		logger.info("commentJobInsert 함수 진입 >>> : ");			
		return commentJobDAO.commentJobInsert(cjvo);
	}

	@Override
	public List<CommentJobVO> commentJobSelectAll(CommentJobVO cjvo) {
		// TODO Auto-generated method stub
		logger.info("commentJobSelectAll 함수 진입 >>> : ");			
		return commentJobDAO.commentJobSelectAll(cjvo);
	}
	
	@Override
	public int commentJobDelete(CommentJobVO cjvo) {
		// TODO Auto-generated method stub
		logger.info("commentJobDelete 함수 진입 >>> : ");
		return commentJobDAO.commentJobDelete(cjvo);
	}

}
