<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="domain.free.vo.BoardFreeVO" %>
<%@ page import="domain.user.vo.UserVO" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.log4j.LogManager" %>  
<%@ page import="org.apache.log4j.Logger" %>   

<% request.setCharacterEncoding("UTF-8");%>
<%
Logger logger = LogManager.getLogger(this.getClass());
logger.info("boardFreeSelectAll.jsp >>> : ");


//페이징 변수 세팅
	int pageSize = 0;
	int groupSize = 0;
	int curPage = 0;
	int totalCount = 0;
	
	
Object objPaging = request.getAttribute("pagingBFVO");
BoardFreeVO pagingBFVO = (BoardFreeVO)objPaging;	

BoardFreeVO search_bfvo = (BoardFreeVO)request.getAttribute("search_bfvo");
System.out.println(search_bfvo);
String searchFilter = search_bfvo.getSearchFilter();
String keyword = search_bfvo.getKeyword();

Object obj = request.getAttribute("listAll");
List<BoardFreeVO> list = (List<BoardFreeVO>)obj;

int nCnt =list.size();
logger.info("nCnt >>> : " + nCnt);

%>
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
   System.out.println("obj : " + objSession);
   
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
$(document).ready(function() {

    //I
    $(document).on("click", "#insertBtn", function() {
        location.href = "insert_form.p";
    });

    //목록 버튼
    $(document).on("click", "#selectAllBtn", function() {
        $("#boardList").attr({
            "method": "GET",
            "action": "board_free_selectAll.p"
        }).submit();
    });
	
	// 강사님 코드지만 안쓰는 것들 =====================================================
   /* 수정하기 
    $(document).on("click", "#selectBtn", function() {
        $("#boardList").attr({
            "method": "GET",
            "action": "board_free_select.p"
        }).submit();
    });

       글 내용보기
    $(document).on("click", "#contentsBtn", function() {
        if ($('.boardfreeid:checked').length == 0) {
            alert("글번호 하나를 선택하세요!!");
            return;
        }
        $("#boardList").attr({
            "method": "GET",
            "action": "board_free_selectContents.p"
        }).submit();
    });

       삭제하기 버튼
    $(document).on("click", "#deletetBtn", function() {
        if ($('.boardfreeid:checked').length == 0) {
            alert("삭제할 글번호 하나를 선택하세요!!");
            return;
        }
        alert("1건의 게시글을 삭제합니다!!");
        $("#boardList").attr({
            "method": "GET",
            "action": "board_free_delete.p"
        }).submit();
    });
    */
   // 강사님 코드지만 안쓰는 것들 =====================================================
      
   // 검색버튼
	// 공통 함수: 값이 null, "null", 빈 문자열인지 확인
	function isEmptyOrNullOrUndefined(value) {
    return value == null || value === "null" || value === "";
	}

	// 검색 버튼 클릭 이벤트
	$("#searchBtn").click(function() {
    //alert("searchBtn >>> : ");
    $("#boardList").attr({ "method": "GET", "action": "board_free_selectAll.p" }).submit();
	});

	// 초기화: searchFilter와 keyword 값 설정
	var searchFilter = "<%=searchFilter%>";
	var keyword = "<%=keyword%>";

	if (isEmptyOrNullOrUndefined(searchFilter)) {
    $("#searchFilter").val("key1");
	} else {
    $("#searchFilter").val(searchFilter);
	}

	if (isEmptyOrNullOrUndefined(keyword)) {
    $("#keyword").val("");
	} else {
    $("#keyword").val(keyword);
	}
	// 검색버튼 ======================
});
function openNav() {
    document.getElementById("myNav").style.width = "100%";
}

function closeNav() {
    document.getElementById("myNav").style.width = "0%";
}
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
<h1 class="title">자유게시판</h1>
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
    <span> ▷ 총 <%= nCnt %>개의 게시물이 있습니다. </span>
    <span class="right">
      <span class="grey" id="strong">SELECT</span>
      <select name="searchFilter">
    	<option value="key2">제목</option>
   	 	<option value="key3">글쓴이</option>
	</select>
	<input type="text" name="keyword">
	<input type="button" class="gradient" value="검색" id="searchBtn">
  </span>
<br>
<table>	
    <tr>
        <th>사진</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>일시</th>
        <th>조회수</th>
    </tr>
    <%
    for(int i=0; i<nCnt; i++){		
        BoardFreeVO _bfvo = list.get(i);
        
        pageSize = Integer.parseInt(pagingBFVO.getPageSize());
    	groupSize = Integer.parseInt(pagingBFVO.getGroupSize());
    	curPage = Integer.parseInt(pagingBFVO.getCurPage());
    	totalCount = Integer.parseInt(_bfvo.getTotalCount());
    %>
    <tbody>
    <tr>
    	<td>
    	<img src="/dogFoot/fileupload/board/<%= _bfvo.getPicturefile() %>" border="1" width="25" height="25" alt="image"><br></td>
      	 <td class="left">
    	<a href="board_free_selectContents.p?boardfreeid=<%= _bfvo.getBoardfreeid() %>"><%= _bfvo.getTitle() %></a>
		</td>
        <td class="left"><%= _bfvo.getUserid() %> </td>	
        <td class="left"><%= _bfvo.getCreateddate() %></td>
        <td class="left"><%= _bfvo.getViewcount() %></td>				
    </tr>	    
    <%
    }
    %> 
  <tr>
		<jsp:include page="board_free_paging.jsp" flush="true">
			<jsp:param name="url" value="board_free_selectAll.p"/>
			<jsp:param name="str" value=""/>
			<jsp:param name="pageSize" value="<%=pageSize%>"/>
			<jsp:param name="groupSize" value="<%=groupSize%>"/>
			<jsp:param name="curPage" value="<%=curPage%>"/>
			<jsp:param name="totalCount" value="<%=totalCount%>"/>
		</jsp:include>
</tr>
</table>
<span class="right">
    <input type="button" value="목록" class="greylist" id="selectAllBtn">
    <input type="button" value="글쓰기" class="gradient"  id="insertBtn">
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
