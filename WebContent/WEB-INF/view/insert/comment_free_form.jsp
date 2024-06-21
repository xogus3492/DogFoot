<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="domain.free.vo.BoardFreeVO" %> 
<%@ page import="domain.free.vo.CommentFreeVO" %> 
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.log4j.LogManager" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="domain.user.vo.UserVO"%>
 <%
   Cookie ck[] = request.getCookies();
   String userInfo = "";
   if (ck != null && ck.length > 0) {
      // 쿠키 이름 중에서 "son"아 있으면 쿠키값 가져오기 
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
        $("#content").keyup(function(){
            cut_200(this);
        });

        selectAll();

        $(document).on("click", "#insertBtn", function(){
            console.log("insertBtn >>> : ");
            
            let insertURL = "comment_free_insert.p";        
            let method = "POST";
            let dataParam = {
                boardfreeid: $("#boardfreeid").val(),
                userid: $("#userid").val(),
                content: $("#content").val(),
            };
            dataParam = $("#commentfreeform").serialize();
            console.log("dataParam >>> : " + dataParam);
            
            $.ajax({
                url: insertURL,
                type: method,
                data: dataParam,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
                success: whenSuccess,
                error: whenError
            });
            
            function whenSuccess(resData){
                if ("GOOD" == resData){ 
                    commentfreeformData();
                    location.reload();
                    selectAll();
                }
            }
            function whenError(e){
                //alert("e >>> : " + e.responseText);
            }
        });

        $(document).on("click", ".deleteBtn", function(){
            console.log("D >>> : ");
            
            var commentfreeidV = $(this).parents("li").attr("dataNum");
           //alert("commentfreeidV >>> : " + commentfreeidV);
            var target = $(this).parents(".rbmemoItem");
            console.log("target >>> : " + target);
            
            let deleteURL = "comment_free_delete.p";
            let method = "POST";
            let dataParam = {
                rkcommentfreeid: $('#commentfreeid').val(commentfreeidV),                
            };        
            dataParam = $("#commentfreeid").serialize();
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

    function selectAll(){
        console.log("SALL >>> : ");
        
        let selectAllURL = "comment_free_selectAll.p";
        let method = "POST";
        let dataParam = {
            boardfreeid: $("#boardfreeid").val(),                
        };        
        dataParam = $("#commentfreeform").serialize();
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
            console.log("whenSuccess rboardSelectAll resData >>> : " + resData);
            
            if(isEmpty(resData)){
                return false;
            }
            
            let v = resData.split("&");
            for(let i=0; i < v.length; i++){
                console.log(v[i]);
                let vv = v[i].split(",");
                for (let j=0; j < vv.length-1; j++){
                    vv[j] = decodeURIComponent(vv[j]);
                    console.log("vv[" + j + "] >>> : " + vv[j]);
                }
                addNewItem(vv[0], vv[1], vv[2], vv[3]);
            }
        }
        function whenError(e){
            console.log("e >>> : " + e.responseText);
        }    
    }
    
    function addNewItem(num, writer, content, datetime){
        if(isEmpty(num)) return false;
        
        var newLi = $("<li>");
        newLi.attr("dataNum", num);
        newLi.addClass("rbmemoItem");
        var writerP = $("<p>");
        writerP.addClass("writer");
        var nameSpan = $("<span>");
        nameSpan.addClass("name");
        nameSpan.html(decodeURIComponent(writer) + "님");
        var dateSpan = $("<span>");
        dateSpan.html(" / " + datetime + " ");
        var delInput = $("<input>");
        delInput.attr({"type":"button", "value":"삭제하기"});
        delInput.addClass("deleteBtn");
        var contentP = $("<p>");
        contentP.html(decodeURIComponent(content));
        
        writerP.append(nameSpan).append(dateSpan).append(delInput);
        newLi.append(writerP).append(contentP);
        $("#commentfreelist").append(newLi);
    }
    
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
    
    function commentfreeformData(){
        $("#userid").val("");
        $("#content").val("");
    }
    
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
    logger.info("comment_free_form.jsp 페이지 >>> : ");

    String boardfreeid = request.getParameter("boardfreeid");
    logger.info("comment_free_form.jsp boardfreeid >>> : " + boardfreeid);
%>
<div id="rbwriterdiv">
<form name="commentfreeform" id="commentfreeform" accept-charset="UTF-8">
<div class="comments">
<h3 style="text-align:center">댓글</h3>
<div class="comment">
     <ul name="commentfreelist" id="commentfreelist">
   </ul>
   </div>
   </div>
    <div class="comment-form">
    <table>
     <%
      if (objSession != null) {
         
        %>
       <tr>
            <td>작성자</td>
            <td>
                <input type="text" id="userid" name="userid" value=<%= name %> required readonly>
                <input type="hidden" name="boardfreeid" id="boardfreeid" value="<%= boardfreeid%>">
                <input type="hidden" name="commentfreeid" id="commentfreeid">
            </td>
        </tr>
        <tr>
            <td>댓글</td>
            <td>
                <textarea name="content" id="content" rows="6" placeholder="댓글을 입력하세요..."></textarea>
                <div><span class="bytes">0</span>bytes</div>
                <input type="button" value="저장하기" id="insertBtn">
            </td>    
        </tr>
     </table>
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
 </form>
</div>
</body>
</html>
