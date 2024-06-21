package domain.question.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.question.dao.CommentQuestionDAO;
import domain.question.vo.CommentQuestionVO;

@Service
@Transactional
public class CommentQuestionServiceImpl implements CommentQuestionService {
	Logger logger = LogManager.getLogger(CommentQuestionServiceImpl.class);
	
	// 서비스에서 DAO 연결하기 
	// 필드 @Autowired 어노테이션으로  DI (의존성 주입하기)
	@Autowired(required=false)	
	private CommentQuestionDAO commentQuestionDAO;
	
	
	@Override
	public int commentQuestionInsert(CommentQuestionVO cqvo) {
		// TODO Auto-generated method stub
		logger.info("commentQuestionInsert 함수 진입 >>> : ");
		return commentQuestionDAO.commentQuestionInsert(cqvo);
	}

	@Override
	public List<CommentQuestionVO> commentQuestionSelectAll(CommentQuestionVO cqvo) {
		// TODO Auto-generated method stub
		logger.info("commentQuestionSelectAll 함수 진입 >>> : ");
		return commentQuestionDAO.commentQuestionSelectAll(cqvo);
	}
	
	@Override
	public int commentQuestionDelete(CommentQuestionVO cqvo) {
		// TODO Auto-generated method stub
		logger.info("commentQuestionDelete 함수 진입 >>> : ");
		return commentQuestionDAO.commentQuestionDelete(cqvo);
	}

}
