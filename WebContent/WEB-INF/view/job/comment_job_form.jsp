<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="domain.job.vo.BoardJobVO" %> 
<%@ page import="domain.user.vo.UserVO" %> 
<%@ page import="domain.job.vo.CommentJobVO" %> 
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="org.apache.log4j.LogManager" %>
<%@ page import="org.apache.log4j.Logger" %>

<%
   Cookie ck[] = request.getCookies();
	String userInfo = "";
	if (ck != null && ck.length > 0) {
	   // 쿠키 이름 중에서 "son"이 있으면 쿠키값 가져오기 
	   for (int i = 0; i < ck.length; i++) {
	
	      if (ck[i].getName().equals("userInfo")) {
	   userInfo = ck[i].getValue();
	   break;
	      }
	   }
	}
	
	Object objSession = session.getAttribute(userInfo);
	System.out.println("objSession : " + objSession);
	
	String userid = "";
	String email = "";
	String tel = "";
	String gender = "";
	String birthday = "";
	String jibunAddress = "";
	String info = "";
	String photo = "";
	String name = "";
	
	if (objSession != null) {
	   System.out.println("로그인 중");
	   UserVO uservo = (UserVO) objSession;
	   userid = uservo.getUserId();
	   email = uservo.getEmail();
	   tel = uservo.getTel();
	   gender = uservo.getGender();
	   birthday = uservo.getBirthDay();
	   jibunAddress = uservo.getJibunAddress();
	   info = uservo.getInfo();
	   photo = uservo.getPhoto();
	   name = uservo.getName();
	}
	
	System.out.println("name : " + name);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 기능</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	
        // 댓글 내용 길이 제한 200 바이트
        $("#content").keyup(function(){
            cut_200(this);
        });
        
	    selectAll();

        // 댓글 등록
        $(document).on("click", "#insertBtn", function(){
            console.log("insertBtn >>> : ");
            
            let insertURL = "commentJobInsert.p";        
            let method = "POST";
            let dataParam = {
                boardjobid: $("#boardjobid").val(),
                userid: $("#userid").val(),
                content: $("#content").val(),
            };
            dataParam = $("#commentjobform").serialize();
            console.log("dataParam >>> : " + dataParam);
            
            $.ajax({
                url: insertURL,
                type: method,
                data: dataParam,
                success: whenSuccess,
                error: whenError
            });
            
            function whenSuccess(resData){    
                alert("resData >>> : " + resData);    
                if ("GOOD" == resData){
                    // 입력 데이터 초기화 함수 호출 
                    commentjobformData();
                    location.reload();
                    selectAll();
                }
            }
            function whenError(e){
                alert("e >>> : " + e.responseText);
            }
        });

        // 댓글 삭제        
        $(document).on("click", ".deleteBtn", function(){
            console.log("D >>> : ");
            
            var commentjobidV = $(this).parents("li").attr("dataNum");
            alert("commentjobidV >>> : " + commentjobidV);
            var target = $(this).parents(".rbmemoItem");
            console.log("target >>> : " + target);
            
            let deleteURL = "commentJobDelete.p";
            let method = "POST";
            let dataParam = {
                rkcommentjobid: $('#commentjobid').val(commentjobidV),                
            };        
            dataParam = $("#commentjobid").serialize();
            console.log("dataParam >>> : " + dataParam);
            
            $.ajax({
                url: deleteURL,
                type: method,
                data: dataParam,
                success: whenSuccess,
                error: whenError
            });
            
            function whenSuccess(resData){                    
                console.log("resData >>> : " + resData);                
                if ("GOOD" == resData){
                    alert("댓글이 삭제되었습니다.");
                    target.remove();
                }    
            }
            function whenError(e){
                console.log("e >>> : " + e.responseText);
            }
        });
    });

    // 게시글 번호로 댓글 전체조회
    function selectAll(){
        console.log("SALL >>> : ");
        
        let selectAllURL = "commentJobSelectAll.p";
        let method = "POST";
        let dataParam = {
            boardjobid: $("#boardjobid").val(),                
        };        
        dataParam = $("#commentjobform").serialize();
        console.log("dataParam >>> : " + dataParam);
        
        $.ajax({
            url: selectAllURL,
            type: method,
            data: dataParam,
            success: whenSuccess,
            error: whenError
        });
        
        function whenSuccess(resData){    
            console.log("resData >>> : " + resData);
            console.log("whenSuccess commentJobSelectAll resData >>> : " + resData);
            
            if(isEmpty(resData)){
                return false;
            }
            
            let v = resData.split("&");
            for(let i=0; i < v.length; i++){
                console.log(v[i]);
                let vv = v[i].split(",");
                let j=0
                for (; j < vv.length-1; j++){
                    console.log("vv[0] >>> : " + vv[0]);
                    console.log("vv[1] >>> : " + vv[1]);
                    console.log("vv[2] >>> : " + vv[2]);
                    console.log("vv[3] >>> : " + vv[3]);                    
                }
                addNewItem(vv[0], vv[1], vv[2], vv[3]);
            }
        }
        function whenError(e){
            console.log("e >>> : " + e.responseText);
        }    
    }
    
    // 새로운 글 화면에 추가
    function addNewItem(num, writer, content, datetime){
        // 데이터 체크
        if(isEmpty(num)) return false;
        
        // 새로운 글이 추가될 li 태그 
        var newLi = $("<li>");
        newLi.attr("dataNum", num);
        newLi.addClass("rbmemoItem");
        // 작성자 정보가 지정될 <p> 태그 
        var writerP = $("<p>");
        writerP.addClass("writer");
        // 작성자 정보의 이름 
        var nameSpan = $("<span>");
        nameSpan.addClass("name");
        nameSpan.html(decodeURIComponent(writer) + "님");
        // 작성일시 
        var dateSpan = $("<span>");
        dateSpan.html(" / " + datetime + " ");
        // 삭제 버튼 
        var delInput = $("<input>");
        delInput.attr({"type":"button", "value":"삭제하기"});
        delInput.addClass("deleteBtn");
        // 내용
        var contentP = $("<p>");
        contentP.html(decodeURIComponent(content));
        
        // 조립하기
        writerP.append(nameSpan).append(dateSpan).append(delInput);
        newLi.append(writerP).append(contentP);
        $("#commentjoblist").append(newLi);
    }
    
    // 댓글 길이 체크 
    function getTextLength(s){
        var len = 0;
        for(var i=0; i < s.length; i++){
            if(escape(s.charAt(i)).length == 6){
                len++;
            }
            len++;
        }
        return len;
    }
    function cut_200(obj){
        var t = $(obj).val();
        var l = t.length;
        while(getTextLength(t) > 200){
            l--;
            t= t.substring(0, l);
        }
        $(obj).val(t);
        $('.bytes').text(getTextLength(t));
    }
    
    // 댓글 등록 후 입력창 초기화
    function commentjobformData(){
        $("#userid").val("");
        $("#content").val("");
    }
    
    // 데이터 체크
    function isEmpty(val){
        if(typeof val=="undefined" || val==null || val==""){
            return true;
        }else{
            return false;
        }
    }
</script>
<style>
    .post-actions input[type="button"],
         .comment-form input[type="submit"] {
             position: relative;
             border: 0;
             padding: 15px 25px;
             display: inline-block;
             text-align: center;
             color: black;
             background-color: #05050515;
             box-shadow: 0px 4px 0px #000000;
             text-decoration: none;
             border-radius: 5px;
             cursor: pointer;
             font-family: "Silkscreen", sans-serif;
             font-weight: 700;
             font-style: normal;
             display: inline-flex;
             align-items: center;
             justify-content: center;
         }
 
         .post-actions input[type="button"]:active,
         .comment-form input[type="submit"]:active {
             top: 4px;
             box-shadow: 0 0 #541686e0;
             background-color: #0dde64;
         }
 
         .comments {
             margin-top: 30px;
         }
 
         .comments h3 {
             font-size: 20px;
             margin-bottom: 15px;
             color: #007bff;
         }
 
         .comment {
             padding: 10px;
             border: 1px solid #ddd;
             border-radius: 5px;
             margin-bottom: 10px;
             background-color: #fff;
         }
 
         .comment .author {
             font-weight: bold;
             color: #007bff;
         }
 
         .comment .date {
             font-size: 12px;
             color: #777;
         }
 
         .comment .content {
             margin-top: 5px;
         }
 
         .comment-form {
             margin-top: 20px;
             display: flex;
             flex-direction: column;
             gap: 10px;
             align-items: center;
             font-family: "Black Han Sans", sans-serif;
             font-weight: 400;
             font-style: normal;
         }
 
         .comment-form textarea {
             width: 600px;
             max-width: 100%;
             padding: 10px;
             border: 1px solid #ddd;
             border-radius: 15px;
             resize: none;
             font-family: "Black Han Sans", sans-serif;
             font-weight: 400;
             font-style: normal;
         }
  
 </style>
</head>
<body>
<hr>
<% request.setCharacterEncoding("UTF-8"); %>
<%
    Logger logger = LogManager.getLogger(this.getClass());
    logger.info("comment_job_form.jsp 페이지 >>> : ");

    String boardjobid = request.getParameter("boardjobid");
    // boardjobid = "B0004"; // 필요에 따라 하드코딩 가능
    logger.info("comment_job_form.jsp boardjobid >>> : " + boardjobid);
%>
<div id="rbwriterdiv">
<form name="commentjobform" id="commentjobform">
<div class="comments">
<h3 style="text-align:center">Comments</h3>
<div class="comment">   
<ul name="commentjoblist" id="commentjoblist">
       <!-- 여기에 동적 생성 요소가 들어옵니다. -->
</ul>  
</div>
</div>   
    <table id="comment_form">
	<%
		if (objSession != null) {
         
	%>
        	 <tr>
	            <td>user</td>
	            <td>
	                <input type="text" id="userid" name="userid" value=<%= name %> required readonly>
	                <input type="hidden" name="boardjobid" id="boardjobid" value="<%=boardjobid%>">
	                <input type="hidden" name="commentjobid" id="commentjobid">
	            </td>
	        </tr>
	        <div class="comment-form">
	        <tr>
	            <td><h3>Comment</h3></td>
	            <td>
	                <textarea name="content" id="content" placeholder="댓글을 입력하세요..." rows="5" cols="50" style="resize: none"></textarea>    
	                <div><span class="bytes">0</span>bytes</div>
	                <input type="button" value="submit" id="insertBtn">
	            </td>    
	        </tr>
	        </div>
	<%  
		}else{
	%>
           <tr>
           <td>로그인 필요</td>
           </tr>
	<%
		}
	%>
    </table>
    <hr>
</form>
</div>
</body>
</html>