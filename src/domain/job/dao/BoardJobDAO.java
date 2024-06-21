package domain.job.dao;

import java.util.List;

import domain.job.vo.BoardJobVO;

public interface BoardJobDAO {
	public int boardJobInsert(BoardJobVO bjvo);
	public List<BoardJobVO> boardJobSelectAll(BoardJobVO bjvo);
	public List<BoardJobVO> boardJobSelect(BoardJobVO bjvo);	
	
	//public List<BoardJobVO> boardJobPwCheck(BoardJobVO bjvo);
	
	// 게시글 조회 수 
	public int boardJobViewcount(BoardJobVO bjvo);
	
	public int boardJobUpdate(BoardJobVO bjvo);
	public int boardJobDelete(BoardJobVO bjvo);
}
