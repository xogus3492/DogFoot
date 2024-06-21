package domain.question.dao;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.question.vo.CommentQuestionVO;

@Repository
public class CommentQuestionDAOImpl implements CommentQuestionDAO {
	Logger logger = LogManager.getLogger(CommentQuestionDAOImpl.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int commentQuestionInsert(CommentQuestionVO cqvo) {
		// TODO Auto-generated method stub
		logger.info("commentJobInsert 함수 진입 >>> : ");	
		
		return sqlSession.insert("commentQuestionInsert", cqvo);
	}

	@Override
	public List<CommentQuestionVO> commentQuestionSelectAll(CommentQuestionVO cqvo) {
		// TODO Auto-generated method stub
		logger.info("commentQuestionSelectAll 함수 진입 >>> : ");
		
		return sqlSession.selectList("commentQuestionSelectAll", cqvo);
	}

	@Override
	public int commentQuestionDelete(CommentQuestionVO cqvo) {
		// TODO Auto-generated method stub
		logger.info("commentQuestionDelete 함수 진입 >>> : ");
		
		return sqlSession.update("commentQuestionDelete", cqvo);
	}

}
