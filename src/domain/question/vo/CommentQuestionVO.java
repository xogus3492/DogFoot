package domain.question.vo;

public class CommentQuestionVO {
	private String commentquestionid;
	private String content;
	private String userid;
	private String boardquestionid;
	private String deleteyn;
	private String createddate;
	private String modifieddate;
	
	

	public CommentQuestionVO() {
	}

	public CommentQuestionVO(String commentquestionid, String content, String userid, String boardquestionid, String deleteyn,
			String createddate, String modifieddate) {
		this.commentquestionid = commentquestionid;
		this.content = content;
		this.userid = userid;
		this.boardquestionid = boardquestionid;
		this.deleteyn = deleteyn;
		this.createddate = createddate;
		this.modifieddate = modifieddate;
	}
	
	// getters / setters
	public String getCommentquestionid() {
		return commentquestionid;
	}
	public String getContent() {
		return content;
	}
	public String getUserid() {
		return userid;
	}
	public String getBoardquestionid() {
		return boardquestionid;
	}
	public String getDeleteyn() {
		return deleteyn;
	}
	public String getCreateddate() {
		return createddate;
	}
	public String getModifieddate() {
		return modifieddate;
	}
	public void setCommentquestionid(String commentquestionid) {
		this.commentquestionid = commentquestionid;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setBoardquestionid(String boardquestionid) {
		this.boardquestionid = boardquestionid;
	}
	public void setDeleteyn(String deleteyn) {
		this.deleteyn = deleteyn;
	}
	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	public void setModifieddate(String modifieddate) {
		this.modifieddate = modifieddate;
	}
	
	
}
