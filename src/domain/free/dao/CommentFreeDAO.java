package domain.free.dao;

import java.util.List;

import domain.free.vo.CommentFreeVO;

public interface CommentFreeDAO {
	
	public int commentFreeInsert(CommentFreeVO cfvo);
	public List<CommentFreeVO> commentFreeSelectAll(CommentFreeVO cfvo);
	public int commentFreeDelete(CommentFreeVO cfvo);
}

