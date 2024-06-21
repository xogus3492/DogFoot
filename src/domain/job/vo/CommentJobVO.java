package domain.job.vo;

public class CommentJobVO {
	private String commentjobid; 
	private String content;
	private String userid;
	private String boardjobid;		
	private String deleteyn;
	private String createddate;
	private String modifieddate;
	
	
	// constructors
	public CommentJobVO() {
	}

	public CommentJobVO(String commentjobid, String content, String userid, String boardjobid, String deleteyn,
			String createddate, String modifieddate) {
		this.commentjobid = commentjobid;
		this.content = content;
		this.userid = userid;
		this.boardjobid = boardjobid;
		this.deleteyn = deleteyn;
		this.createddate = createddate;
		this.modifieddate = modifieddate;
	}
	
	// getters / setters
	public String getCommentjobid() {
		return commentjobid;
	}
	public String getContent() {
		return content;
	}
	public String getUserid() {
		return userid;
	}
	public String getBoardjobid() {
		return boardjobid;
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
	public void setCommentjobid(String commentjobid) {
		this.commentjobid = commentjobid;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setBoardjobid(String boardjobid) {
		this.boardjobid = boardjobid;
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
