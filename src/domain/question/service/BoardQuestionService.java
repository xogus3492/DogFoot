package domain.question.service;

import java.util.List;

import domain.question.vo.BoardQuestionVO;

public interface BoardQuestionService {

		public int boardQuestionInsert(BoardQuestionVO bqvo);
		public List<BoardQuestionVO> boardQuestionSelectAll(BoardQuestionVO bqvo);
		public List<BoardQuestionVO> boardQuestionSelect(BoardQuestionVO bqvo);
		public List<BoardQuestionVO> boardQuestionUserid(BoardQuestionVO bqvo);

		public int boardQuestionViewcount(BoardQuestionVO bqvo);
		public int boardQuestionUpdate(BoardQuestionVO bqvo);
		public int boardQuestionDelete(BoardQuestionVO bqvo);
	}