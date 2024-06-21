package domain.free.vo;

public class CommentFreeVO {
   
   private String commentfreeid;
   private String content;
   private String userid;
   private String boardfreeid;
   private String deleteyn;
   private String createddate;
   private String modifieddate;

   public CommentFreeVO() {
      
   }

   public CommentFreeVO(String commentfreeid, String content, String userid, String boardfreeid, String createddate) {
      super();
      this.commentfreeid = commentfreeid;
      this.content = content;
      this.userid = userid;
      this.boardfreeid = boardfreeid;
      this.createddate = createddate;
   }

   public CommentFreeVO(String commentfreeid, String content, String userid, String boardfreeid, String deleteyn,
         String createddate, String modifieddate) {
      super();
      this.commentfreeid = commentfreeid;
      this.content = content;
      this.userid = userid;
      this.boardfreeid = boardfreeid;
      this.deleteyn = deleteyn;
      this.createddate = createddate;
      this.modifieddate = modifieddate;
   }

   public String getCommentfreeid() {
      return commentfreeid;
   }

   public void setCommentfreeid(String commentfreeid) {
      this.commentfreeid = commentfreeid;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getUserid() {
      return userid;
   }

   public void setUserid(String userid) {
      this.userid = userid;
   }

   public String getBoardfreeid() {
      return boardfreeid;
   }

   public void setBoardfreeid(String boardfreeid) {
      this.boardfreeid = boardfreeid;
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
}
