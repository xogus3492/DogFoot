<%@ page language="java" contentType="text/html;"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="domain.free.vo.BoardFreeVO" %> 
<%@ page import="java.util.List" %> 

<% request.setCharacterEncoding("UTF-8"); %>
<%
    Object obj = request.getAttribute("listS");
    if (obj == null) return;

    List<BoardFreeVO> list = (List<BoardFreeVO>) obj;    
    int nCnt = list.size();
    System.out.println(nCnt);

    BoardFreeVO _bfvo = null;
    if (nCnt == 1){
        _bfvo = list.get(0);
    }
    
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글보기</title>
    <link rel="stylesheet" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Silkscreen:wght@400;700&display=swap" rel="stylesheet">

    <style>
       body {
            font-family: 'Lato', sans-serif;
            line-height: 1.6;
            color: #333;
        }

        .scrollmenu {
            overflow: auto;
            white-space: nowrap;
            background-color: #333;
            font-family: "Black Han Sans", sans-serif;
            font-weight: 400;
            font-style: normal;
        }

        .scrollmenu a {
            display: inline-block;
            color: white;
            text-align: center;
            padding: 14px;
            text-decoration: none;
        }

        .scrollmenu a:hover {
            background-color: #777;
        }

        .title {
            text-align: center;
            margin-top: 20px;
            color: #000000;
            font-family: "Black Han Sans", sans-serif;
            font-weight: 400;
            font-style: normal;
        }

        .overlay {
            height: 100%;
            width: 0;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: rgba(0, 0, 0, 0.9);
            overflow-x: hidden;
            transition: 0.5s;
        }

        .overlay-content {
            position: relative;
            top: 25%;
            width: 100%;
            text-align: center;
            margin-top: 30px;
        }

        .overlay a {
            padding: 8px;
            text-decoration: none;
            font-size: 36px;
            color: #818181;
            display: block;
            transition: 0.3s;
        }

        .overlay a:hover, .overlay a:focus {
            color: #f1f1f1;
        }

        .overlay .closebtn {
            position: absolute;
            top: 20px;
            right: 45px;
            font-size: 60px;
        }

        @media screen and (max-height: 450px) {
            .overlay a {
                font-size: 20px;
            }

            .overlay .closebtn {
                font-size: 40px;
                top: 15px;
                right: 35px;
            }
        }

        footer {
            width: 100%;
            margin-bottom: 2rem;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .wrap {
            width: 80%;
            margin: 0 auto;
            display: flex;
            flex-direction: column;
            align-items: center;
            text-align: center;
            row-gap: 2em;
        }

        .social-icons i {
            font-size: 1.5rem;
            margin: 0 0.3em;
        }

        .options {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            grid-gap: 1em;
        }

        .option {
            color: #6f6f6f;
        }

        h2 {
            font-weight: bold;
        }

        .hr1 {
            border: 0;
            height: 2px;
            background: #d3d3d3;
        }

        .grey {
            color: #727272;
        }

        #strong {
            font-weight: 900;
        }

        table {
            width: 100%;
            border-top: 1px solid #d3d3d3;
            border-collapse: collapse;
        }

        th {
            background-color: #d3d3d3;
            border-top: 3px solid #727272;
        }

        th, td {
            border-bottom: 1px solid #d3d3d3;
            padding: 10px;
        }

        .greylist {
            width: 50px;
            height: 30px;
            font-weight: 900;
            color: white;
            text-align: center;
            background: grey;
            border: solid 2px white;
            border-radius: 5px;
        }

        .gradient {
            width: 80px;
            height: 30px;
            font-weight: 900;
            color: white;
            text-align: center;
            background: linear-gradient(to bottom, grey, black);
            border: solid 2px white;
            border-radius: 5px;
        }

        .left {
            text-align: left;
        }

        .right {
            float: right;
        }

        .center {
            text-align: center;
        }

        a {
            color: black;
            text-decoration-line: none;
        }

        .post-detail {
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            max-width: 800px;
            background-color: #f9f9f9;
        }

        .post-detail h2 {
            font-size: 24px;
            margin-bottom: 10px;
            color: #007bff;
        }

        .post-detail p {
            font-size: 16px;
            margin: 5px 0;
        }

        .post-detail .post-content {
            margin: 20px 0;
            padding: 15px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            word-wrap: break-word;
            white-space: pre-wrap; /* 줄바꿈을 유지합니다 */
        }

        .post-detail .post-actions {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
        }

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

        footer {
            margin-top: 20px;
            background-color: #333;
            color: white;
            padding: 10px 0;
        }

        .footer .wrap {
            display: flex;
            justify-content: space-between;
            align-items: center;
            max-width: 800px;
            margin: 0 auto;
        }

        .footer .social-icons i {
            margin: 0 5px;
        }

        .footer .options {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
        }

        .footer .option {
            cursor: pointer;
        }

        .footer .option:hover {
            text-decoration: underline;
        }
    </style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    function openNav() {
		document.getElementById("myNav").style.width = "100%";
	}

	function closeNav() {
		document.getElementById("myNav").style.width = "0%";
	}

	$(document).ready(function() { 
	
		$(document).on("click", "#selectAllBtn", function() {
        	$("#selectContents").attr({
            	"method": "GET",
            	"action": "board_free_selectAll.p"
        	}).submit();
    	});
	
		$(document).on("click", "#updateBtn", function() {
        	$("#selectContents").attr({
            	"method": "GET",
            	"action": "board_free_select.p"
        	}).submit();
    	});
	
		$(document).on("click", "#deleteBtn", function() {
        	$("#selectContents").attr({
            	"method": "GET",
            	"action": "board_free_delete.p"
        	}).submit();
    	});

	});
</script>    
</head>
<body>
<div class="scrollmenu">
    <a href="<c:url value='/main'/>">Home</a>
    <a href="<c:url value='/login'/>">login</a>
    <a href="<c:url value='/board_free'/>">자유게시판</a>
    <a href="<c:url value='/board_popularity'/>">인기게시판</a>
    <a href="<c:url value='/board_job'/>">취업게시판</a>
    <a href="<c:url value='/board_question'/>">질문게시판</a>
    <a href="#base">남는게 시팜</a>
    <a href="#custom">Custom</a>
    <a href="#more">More</a>
    <a href="#logo">Logo</a>
    <a href="#friends">Friends</a>
    <a href="#partners">Partners</a>
    <a href="#people">People</a>
    <a href="#work">Work</a>
</div>
<h1 class="title">게시글 상세보기</h1>
<div id="myNav" class="overlay">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <div class="overlay-content">
        <a href="<c:url value='/board_free'/>">자유게시판</a>
        <a href="<c:url value='/board_popularity'/>">인기게시판</a>
        <a href="<c:url value='/board_job'/>">취업게시판</a>
        <a href="<c:url value='/board_question'/>">질문게시판</a>
    </div>
</div>
<form id="selectContents">
<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
<div class="post-detail">
	<input type="hidden" id="boardfreeid" name="boardfreeid" class="boardfreeid" value=<%= _bfvo.getBoardfreeid() %>>
    <h2><%= _bfvo.getTitle() %></h2>
    <p>작성자: <%= _bfvo.getUserid() %></p>
    <p>작성일: <%= _bfvo.getModifieddate() %></p>
    <p>조회수:<%= _bfvo.getViewcount() %></p>
    <div class="post-content">
        <%= _bfvo.getContent() %><br>
       <img src="/dogFoot/fileupload/board/<%= _bfvo.getPicturefile() %>" border="1" width="100" height="100" alt="image"><br>
    </div>
    <div class="post-actions">
        <input type="button" value="목록" id="selectAllBtn">
        <input type="button" value="글수정" id="updateBtn">
       <input type="button" value="글삭제" id="deleteBtn">

    </div>
</div>
</form>
<jsp:include page="/comment_free_form.p">
    <jsp:param name="boardfreeid" value="<%= _bfvo.getBoardfreeid() %>"/>
</jsp:include>	

<footer>
    <div class="wrap">
        <div class="options">
            <div class="option">자막 및 음성</div>
            <div class="option">음성 지원</div>
            <div class="option">고객 센터</div>
            <div class="option">미디어 센터</div>
            <div class="option">투자 정보(IR)</div>
            <div class="option">입사 정보</div>
            <div class="option">이용 약관</div>
            <div class="option">개인 정보</div>
            <div class="option">법적 고지</div>
            <div class="option">쿠키 설정</div>
            <div class="option">회사 정보</div>
            <div class="option">문의하기</div>
        </div>
    </div>
</footer>
</body>
</html>
