package domain.free.service;

import java.util.List;

import domain.free.vo.CommentFreeVO;

public interface CommentFreeService {
	
	public int commentFreeInsert(CommentFreeVO cfvo);
	public List<CommentFreeVO> commentFreeSelectAll(CommentFreeVO cfvo);
	public int commentFreeDelete(CommentFreeVO cfvo);
}	
