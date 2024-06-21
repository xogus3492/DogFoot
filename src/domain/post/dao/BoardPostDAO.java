package domain.post.dao;

import java.util.List;

import domain.post.vo.BoardPostVO;

public interface BoardPostDAO {
	
	public List<BoardPostVO> boardPostSelectAll(BoardPostVO bpvo);
}
