package domain.free.vo;

public class BoardFreeVO {
   
   private String boardfreeid;
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
   
   private String keyword;         // 검색어
   private String searchFilter;   // 검색조건
      
   
   public BoardFreeVO() {
      
   }
   
   public BoardFreeVO(String boardfreeid, String title, String content, String viewcount, String picturefile,
         String userid, String deleteyn, String createddate, String modifieddate) {
      super();
      this.boardfreeid = boardfreeid;
      this.title = title;
      this.content = content;
      this.viewcount = viewcount;
      this.picturefile = picturefile;
      this.userid = userid;
      this.deleteyn = deleteyn;
      this.createddate = createddate;
      this.modifieddate = modifieddate;
   }
   
   public BoardFreeVO(String boardfreeid, String title, String content, String viewcount, String picturefile,
         String userid, String deleteyn, String createddate, String modifieddate, String pageSize, String groupSize,
         String curPage, String totalCount, String keyword, String searchFilter) {
      super();
      this.boardfreeid = boardfreeid;
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

   public String getBoardfreeid() {
      return boardfreeid;
   }

   public void setBoardfreeid(String boardfreeid) {
      this.boardfreeid = boardfreeid;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getViewcount() {
      return viewcount;
   }

   public void setViewcount(String viewcount) {
      this.viewcount = viewcount;
   }

   public String getPicturefile() {
      return picturefile;
   }

   public void setPicturefile(String picturefile) {
      this.picturefile = picturefile;
   }

   public String getUserid() {
      return userid;
   }

   public void setUserid(String userid) {
      this.userid = userid;
   }

   public String getDeleteyn() {
      return deleteyn;
   }

   public void setDeleteyn(String deleteyn) {
      this.deleteyn = deleteyn;
   }

   public String getCreateddate() {
      return createddate;
   }

   public void setCreateddate(String createddate) {
      this.createddate = createddate;
   }

   public String getModifieddate() {
      return modifieddate;
   }

   public void setModifieddate(String modifieddate) {
      this.modifieddate = modifieddate;
   }

   public String getPageSize() {
      return pageSize;
   }

   public void setPageSize(String pageSize) {
      this.pageSize = pageSize;
   }

   public String getGroupSize() {
      return groupSize;
   }

   public void setGroupSize(String groupSize) {
      this.groupSize = groupSize;
   }

   public String getCurPage() {
      return curPage;
   }

   public void setCurPage(String curPage) {
      this.curPage = curPage;
   }

   public String getTotalCount() {
      return totalCount;
   }

   public void setTotalCount(String totalCount) {
      this.totalCount = totalCount;
   }

   public String getKeyword() {
      return keyword;
   }

   public void setKeyword(String keyword) {
      this.keyword = keyword;
   }

   public String getSearchFilter() {
      return searchFilter;
   }

   public void setSearchFilter(String searchFilter) {
      this.searchFilter = searchFilter;
   }


}