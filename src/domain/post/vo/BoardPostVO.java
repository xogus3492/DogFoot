package domain.post.vo;

public class BoardPostVO {
    private int postid;
    private String title;
    private String userId;
    private String createdDate;
    private int viewCount;
    private String tableName;
 
    public BoardPostVO() {
		
    }
    
	public BoardPostVO(int postid, String title, String userId, String createdDate, int viewCount, String tableName) {
		
		this.postid = postid;
		this.title = title;
		this.userId = userId;
		this.createdDate = createdDate;
		this.viewCount = viewCount;
		this.tableName = tableName;
	}


	// Getters and setters
    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
