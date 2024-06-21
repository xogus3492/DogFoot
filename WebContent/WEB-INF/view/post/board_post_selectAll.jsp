<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="domain.post.vo.BoardPostVO" %>


<%@ page import="org.apache.log4j.LogManager" %>  
<%@ page import="org.apache.log4j.Logger" %>   

<% request.setCharacterEncoding("UTF-8");%>
<%
Logger logger = LogManager.getLogger(this.getClass());
logger.info("boardFreeSelectAll.jsp >>> : ");

Object obj = request.getAttribute("listAll");
List<BoardPostVO> list = (List<BoardPostVO>)obj;

int nCnt =list.size();
logger.info("nCnt >>> : " + nCnt);
%>
<!DOCTYPE html>
<html>
<head>
<meta 
name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="<c:url value='/resources/css/board_free/style.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
 <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Silkscreen:wght@400;700&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
function openNav() {
    document.getElementById("myNav").style.width = "100%";
}

function closeNav() {
    document.getElementById("myNav").style.width = "0%";
}

$(document).ready(function() {
    //목록 버튼
    $(document).on("click", "#selectAllBtn", function() {
        $("#boardList").attr({
            "method": "GET",
            "action": "board_post_selectAll.p"
        }).submit();
    });
		
});
</script>
<style>
body {
            font-family: 'Lato', sans-serif;
            line-height: 1.6;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        
        
 .title {
            text-align: center;
            margin-top: 20px;
            color: #000000;
            font-family: "Black Han Sans", sans-serif;
        }        

.overlay {
    height: 100%;
    width: 0;
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    background-color: rgb(0, 0, 0);
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
        font-size: 20px
    }

    .overlay .closebtn {
        font-size: 40px;
        top: 15px;
        right: 35px;
    }
}

footer {
    width: 100%;
    background-color: #333;
    margin-top: 15rem;
    margin-bottom: 2rem;
    display: flex;
    justify-content: center;
    align-items: center;

    .wrap {
        width: 80%;
        margin-bottom: 2rem;
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
        row-gap: 2em;

        .social-icons {
            i {
                font-size: 1.5rem;
                margin: 0 0.3em;
            }
        }

        .options {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            grid-gap: 1em;

            .option {
                color: #6f6f6f;
            }
        }
    }
}

h2 {
    font-weight: border;
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

  .scrollmenu {
            overflow: auto;
            white-space: nowrap;
            background-color: #333;
            font-family: "Black Han Sans", sans-serif;
        }

div.scrollmenu a {
    display: inline-block;
    color: white;
    text-align: center;
    padding: 14px;
    text-decoration: none;
}

div.scrollmenu a:hover {
    background-color: #777;
}

</style>
</head>
<body>
<div class="scrollmenu">
    <a href="/dogFoot/index.jsp">Home</a>
        <a href="board_post_selectAll.p">인기게시판</a>
		<a href="board_free_selectAll.p">자유게시판</a>
		<a href="board_job_selectAll.p">취업게시판</a>
		<a href="board_question_selectAll.p">질문게시판</a>
        <a href="https://www.hrd.go.kr/hrdp/ma/pmmao/newIndexRenewal.do">국비교육</a>
        <a href="https://boottent.com/camps">부트캠프</a>
        <a href="https://dongurit.notion.site/3449d2e155dc454598f1a0f523edb2e9">취업준비</a>
</div>
<h1 class="title">인기게시판</h1>
<div id="myNav" class="overlay">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <div class="overlay-content">
        <a href="board_post_selectAll.p">인기게시판</a>
        <a href="board_free_selectAll.p">자유게시판</a>
		<a href="board_job_selectAll.p">취업게시판</a>
		<a href="board_question_selectAll.p">질문게시판</a>
    </div>
</div>
<span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>

<form id="boardList" name="boardList" >
    <br>     
<br>
<table>    
    <tr>
        <th>순위</th>
        <th>내용</th>
        <th>글쓴이</th>
        <th>일시</th>
        <th>조회수</th>
        <th>게시판</th>
    </tr>
    <%
    for(int i=0; i<nCnt; i++){        
        BoardPostVO _pvo = list.get(i); 
        
        String url = "";

        if (_pvo.getTableName().equals("BOARDFREE")) {
            url = "board_free_selectContents.p?boardfreeid=" + _pvo.getPostid();
        } else if (_pvo.getTableName().equals("BOARDJOB")) {
            url = "board_job_selectContents.p?boardjobid=" + _pvo.getPostid();
        } else if (_pvo.getTableName().equals("BOARDQUESTION")) {
            url = "board_question_selectContents.p?boardquestionid=" + _pvo.getPostid();
        }
        System.out.println(url);
    %>
    <tbody>
    <tr>        
        <td class="left"><%= i + 1 %></td>
        <td class="left">
            <a href="<%=url%>"><%= _pvo.getTitle() %></a>
        </td>
        <td class="left"><%= _pvo.getUserId() %></td>         
        <td class="left"><%= _pvo.getCreatedDate() %></td>    
        <td class="left"><%= _pvo.getViewCount() %></td>                
        <td class="left"><%= _pvo.getTableName() %></td>
    </tr>
    <input type="hidden" name="postid" value="<%= _pvo.getPostid() %>">
    <%
    }
    %>
    </tbody>
</table>

<span class="right">
    <input type="button" value="목록" class="greylist" id="selectAllBtn">
</span>
</form>
<footer>
    <div class="wrap">
        <div class="social-icons">
            <i class="fa-brands fa-facebook-square"></i>
            <i class="fa-brands fa-instagram"></i>
            <i class="fa-brands fa-twitter"></i>
            <i class="fa-brands fa-youtube"></i>
        </div>
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
<!--script src="<c:url value='/resources/js/board_free/scripts.js'/>"></script> -->
</body>
</html>
