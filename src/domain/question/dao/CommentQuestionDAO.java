package domain.question.dao;

import java.util.List;

import domain.question.vo.CommentQuestionVO;

public interface CommentQuestionDAO {
	public int commentQuestionInsert(CommentQuestionVO cqvo);
	public List<CommentQuestionVO> commentQuestionSelectAll(CommentQuestionVO cqvo);
	public int commentQuestionDelete(CommentQuestionVO cqvo);
}
