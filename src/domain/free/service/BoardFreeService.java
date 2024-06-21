package domain.free.service;

import java.util.List;

import domain.free.vo.BoardFreeVO;

public interface BoardFreeService {
	
	public int boardFreeInsert(BoardFreeVO bfvo);
	public List<BoardFreeVO> boardFreeSelectAll(BoardFreeVO bfvo);
	public List<BoardFreeVO> boardFreeSelect(BoardFreeVO bfvo);
	public List<BoardFreeVO> boardFreePwCheck(BoardFreeVO bfvo);
	
	public int boardFreeViewCount(BoardFreeVO bfvo);
	
	public int boardFreeUpdate(BoardFreeVO bfvo);
	public int boardFreeDelete(BoardFreeVO bfvo);
}
