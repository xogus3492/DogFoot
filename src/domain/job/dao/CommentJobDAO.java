package domain.job.dao;

import java.util.List;

import domain.job.vo.CommentJobVO;

public interface CommentJobDAO {
	public int commentJobInsert(CommentJobVO cjvo);
	public List<CommentJobVO> commentJobSelectAll(CommentJobVO cjvo);
	public int commentJobDelete(CommentJobVO cjvo);
}
