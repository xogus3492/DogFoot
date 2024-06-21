package domain.job.service;

import java.util.List;

import domain.job.vo.CommentJobVO;

public interface CommentJobService {

	public int commentJobInsert(CommentJobVO cjvo);
	public List<CommentJobVO> commentJobSelectAll(CommentJobVO cjvo);
	public int commentJobDelete(CommentJobVO cjvo);
}
