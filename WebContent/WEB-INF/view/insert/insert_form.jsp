<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 글쓰기</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/insert/insert.css'/>">
    
    <script src="https://cdn.ckeditor.com/4.16.2/standard/ckeditor.js"></script>
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">		
		$(document).ready(function(){
			
			$(document).on("click", "#savePost", function(){
				
				// 선택된 게시판 타입 가져오기
	   			var selectedBoardType = $('#board_type').val();
	   			
	   			// 선택된 게시판 타입에 따라 action 경로 설정
			    var actionPath;
			    switch (selectedBoardType) {
			      case 'free':
			        actionPath = 'board_free_insert.p';
			        break;
			      case 'job':
			        actionPath = 'board_job_insert.p';
			        break;
			      case 'question':
			        actionPath = 'board_question_insert.p';
			        break;
			      default:
			        actionPath = '';
			    }
			    
			    var data = {
			    		title: $('#title').val(),
			    		userid: $('#userid').val(),
			    		content: $('#content').val(),
			    		picturefile: $('#picturefile').val(),
			    }
			    
	   			// board_job_insert.p
				$('#insert_form').attr({
					'action': actionPath,
					'method': 'POST',
					'data': data,
					'enctype': 'multipart/form-data'
				}).submit();
			});
			CKEDITOR.replace('content');
		});	
	</script>
    
    <style>
        .preview-container {
            display: flex;
            flex-direction: column;
            gap: 20px;
            margin-top: 20px;
        }
        .preview-container img, .preview-container video {
            max-width: 100%;
            max-height: 300px;
       }
        body {
          font-family: Arial, sans-serif;
          margin: 0;
          padding: 0;
      }
      .container {
          width: 80%;
          margin: 0 auto;
      }
      .header {
          text-align: center;
             margin: 20px 0;
      }
      .form-group {
          margin-bottom: 20px;
      }
      .form-group label {
         margin-bottom: 5px;
      }
      .form-group input, .form-group textarea {
          width: 100%;
             padding: 10px;
          box-sizing: border-box;
      }
      .form-actions {
             text-align: center;
             
      }
      .post-actions input[type="button"],
.comment-form input[type="submit"],
.form-actions input[type="button"],
.form-actions button {
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
.comment-form input[type="submit"]:active,
.form-actions input[type="button"]:active,
.form-actions button:active {
    top: 4px;
    box-shadow: 0 0 #541686e0;
    background-color: #0dde64;
}
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>게시판 글쓰기</h1>
    </div>
	
	<div class ="board_type">
		게시판 종류
    	<select name="board_type" id="board_type">
            <option value="free">자유게시판</option>
            <option value="job">직업게시판</option>
            <option value="question">질문게시판</option>
        </select>
	</div>
	
    <form name="insert_form" id="insert_form">
        <div class="form-group">
            <label for="title">제목:</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div class="form-group">
            <label for="userid">작성자:</label>
             <input type="text" id="userid" name="userid" value=<%= name %> required readonly>
        </div>
        <div class="form-group">
            <label for="content">내용:</label>
            <textarea id="content" name="content" rows="10" required></textarea>
        </div>
        <div class="form-group">
            <label for="picturefile">사진:</label>
            <input type="file" id="picturefile" name="picturefile" >
        </div>
        <div class="preview-container" id="preview-container">

        </div>
        <div class="form-actions">
        	<input type="button" value="등록" id="savePost">
            <button type="reset">초기화</button>
        </div>
    </form>
</div>
<script src="<c:url value='/res/js/insert/insert.js'/>"></script>
</body>
</html>
