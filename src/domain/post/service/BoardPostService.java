package domain.post.service;

import java.util.List;

import domain.post.vo.BoardPostVO;

public interface BoardPostService {
	
	 public List<BoardPostVO> boardPostSelectAll(BoardPostVO bpvo);
}
