package domain.question.service;

import java.util.List;

import domain.question.vo.CommentQuestionVO;

public interface CommentQuestionService {

	public int commentQuestionInsert(CommentQuestionVO cqvo);
	public List<CommentQuestionVO> commentQuestionSelectAll(CommentQuestionVO cqvo);
	public int commentQuestionDelete(CommentQuestionVO cqvo);
}
