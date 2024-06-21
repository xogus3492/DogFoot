package domain.job.vo;

public class BoardJobVO {
	private String boardjobid;
	private String title;
	private String content;
	private String viewcount;
	private String picturefile;
	private String userid;
	private String deleteyn;
	private String createddate;
	private String modifieddate;
	
	// 페이징 이동 필드
	private String pageSize;
	private String groupSize;
	private String curPage;
	private String totalCount;
	
	private String keyword;
	private String searchFilter;
	
	
	// constructors
	public BoardJobVO() {
	}
	
	
	public BoardJobVO(String boardjobid, String title, String content, String viewcount, String picturefile,
			String userid, String deleteyn, String createddate, String modifieddate) {
		this.boardjobid = boardjobid;
		this.title = title;
		this.content = content;
		this.viewcount = viewcount;
		this.picturefile = picturefile;
		this.userid = userid;
		this.deleteyn = deleteyn;
		this.createddate = createddate;
		this.modifieddate = modifieddate;
	}
	
	// + 페이징 이동 필드 생성자
	public BoardJobVO(String boardjobid, String title, String content, String viewcount, String picturefile,
			String userid, String deleteyn, String createddate, String modifieddate, String pageSize, String groupSize,
			String curPage, String totalCount) {
		this.boardjobid = boardjobid;
		this.title = title;
		this.content = content;
		this.viewcount = viewcount;
		this.picturefile = picturefile;
		this.userid = userid;
		this.deleteyn = deleteyn;
		this.createddate = createddate;
		this.modifieddate = modifieddate;
		this.pageSize = pageSize;
		this.groupSize = groupSize;
		this.curPage = curPage;
		this.totalCount = totalCount;
	}

	public BoardJobVO(String boardjobid, String title, String content, String viewcount, String picturefile,
			String userid, String deleteyn, String createddate, String modifieddate, String pageSize, String groupSize,
			String curPage, String totalCount, String keyword, String searchFilter) {
		super();
		this.boardjobid = boardjobid;
		this.title = title;
		this.content = content;
		this.viewcount = viewcount;
		this.picturefile = picturefile;
		this.userid = userid;
		this.deleteyn = deleteyn;
		this.createddate = createddate;
		this.modifieddate = modifieddate;
		this.pageSize = pageSize;
		this.groupSize = groupSize;
		this.curPage = curPage;
		this.totalCount = totalCount;
		this.keyword = keyword;
		this.searchFilter = searchFilter;
	}

	



	// getters / setters
	public String getBoardjobid() {
		return boardjobid;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getViewcount() {
		return viewcount;
	}
	public String getPicturefile() {
		return picturefile;
	}
	public String getUserid() {
		return userid;
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
	public String getPageSize() {
		return pageSize;
	}
	public String getGroupSize() {
		return groupSize;
	}
	public String getCurPage() {
		return curPage;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setBoardjobid(String boardjobid) {
		this.boardjobid = boardjobid;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setViewcount(String viewcount) {
		this.viewcount = viewcount;
	}
	public void setPicturefile(String picturefile) {
		this.picturefile = picturefile;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public void setGroupSize(String groupSize) {
		this.groupSize = groupSize;
	}
	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	
	public String getKeyword() {
		return keyword;
	}

	public String getSearchFilter() {
		return searchFilter;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}
	
}
