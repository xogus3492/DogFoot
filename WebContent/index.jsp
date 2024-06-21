<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="domain.user.vo.UserVO"%>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Main</title>
    <link rel="stylesheet" href="<c:url value='/css/main.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script  src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

	<%@ include file="/util/session.jsp" %>

<script type="text/javascript">
   window.onload = function() {
	  if ('<%=name%>' != '') {
      var beforeLogin = document.getElementById('beforeLogin');
      var afterLogin = document.getElementById('afterLogin');
      var profilePicture = document.getElementById('profile-picture');
      beforeLogin.style.display = 'none';
      afterLogin.style.display = 'block';
      profilePicture.style.display = 'block';
      
      // 사용자 정보
       var userInfo = {
           userid: '<%=userid%>',
           email: '<%=email%>',
           gender: '<%=gender%>',
           birthday: '<%=birthday%>',
           jibunAddress: '<%=jibunAddress%>',
           info: '<%=info%>'
         };

         // 사용자 정보를 HTML에 삽입
         document.getElementById("user-id").innerText = userInfo.userid;
         document.getElementById("user-email").innerText = userInfo.email;
         document.getElementById("user-gender").innerText = userInfo.gender;
         document.getElementById("user-birthday").innerText = userInfo.birthday;
         document.getElementById("user-jibunAddress").innerText = userInfo.jibunAddress;
         document.getElementById("user-info").innerText = userInfo.info;
        }
   };
</script>

<script type="text/javascript">
$(document).ready(function(){
	$(document).on("click", "#save-btn", function(){
		let path = "update.p";
		let method = "POST";
		var data = {
				userid: '<%=userid%>',
                email: $('#1').val(),
                gender: $('#2').val(),
                birthday: $('#3').val(),
                jibunAddress: $('#4').val(),
                info: $('#5').val(),
            };
		
		$.ajax({
			url: path,
			type: method,
			data: data,
			success: whenSuccess,
			error: whenError
		});
		
		function whenSuccess(resData){
			if (resData == "SUCCESS") {
				alert("정보 수정를 수정하셨습니다");
				var editBtn = document.getElementById('edit-btn');
			    var saveBtn = document.getElementById('save-btn');
			    var infoInputs = document.querySelectorAll('.info input');
			    var cancelBtn = document.getElementById('cancelBtn');
				infoInputs.forEach(function(input) {
		            var span = input.previousElementSibling;
		            span.innerText = input.value;
		            span.style.display = 'block';
		            input.remove();
		        });
		        editBtn.style.display = 'block';
		        saveBtn.style.display = 'none';
		        cancelBtn.style.display = 'none';
			} else {
				alert("정보 수정 실패");
			}
		}
		function whenError(e){
			alert("e >>> : " + e.responseText);
		}
	});
	
	$(document).on("click", "#logout-btn", function(){
		alert('로그아웃 버튼 확인');
		let path = "logout.p";
		let method = "GET";
		var data = {
				userid: '<%=userid%>',
            };
		
		$.ajax({
			url: path,
			type: method,
			data: data,
			success: whenSuccess,
			error: whenError
		});
		
		function whenSuccess(resData){
			if (resData == "SUCCESS") {
				alert("로그아웃 하셨습니다");
				location.href = "index.jsp";
			} else {
				alert("로그아웃 실패");
			}
		}
		function whenError(e){
			alert("e >>> : " + e.responseText);
		}
	});
});
</script>
    
</head>
<body>
    <nav>
        <div class="left">
            <div class="logo">
                <a href="#"><img src="resources/img/logo.png" alt="Logo"></a>
            </div>
            <div class="mobile-menu">커뮤니티</div>
            <ul class="menu-list">
            	<li><a href="board_post_selectAll.p">인기게시판</a></li>
            	<li><a href="board_free_selectAll.p">자유게시판</a></li>
                <li><a href="board_job_selectAll.p">취업게시판</a></li>
                <li><a href="board_question_selectAll.p">질문게시판</a></li>
            </ul>
        </div>
        <div class="right">
            <div class="icon search">
                <div class="search-bar">
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <input type="text" placeholder="찾고 싶은 키워드" />
                </div>
            </div>
            <div class="icon kids">
                <a href="login.p" id="beforeLogin">로그인</a>
                <a id="afterLogin" style="display: none;"><%=name%>님 반갑습니다</a>
            </div>
            <div class="icon profile">
                <div class="profile-container">
                    <img src="/dogFoot/fileupload/user/<%=photo%>" alt="프로필 사진" class="profile-icon" id="profile-picture" onclick="toggleProfilePanel()" style="display:none">
                    <div id="profile-panel" class="profile-panel">
                        <span class="close" id="cancelBtn" onclick="cancelEditMode()" style="display:none">X</span>
                        <h2 id="user-id"></h2>
                        <div class="info">
                            <strong>EMAIL</strong> <span id="user-email"></span>
                        </div>
                        <div class="info">
                            <strong>GENDER</strong> <span id="user-gender"></span>
                        </div>
                        <div class="info">
                            <strong>BIRTHDAY</strong> <span id="user-birthday"></span>
                        </div>
                        <div class="info">
                            <strong>JIBUNADDRESS</strong> <span id="user-jibunAddress"></span>
                        </div>
                        <div class="info">
                            <strong>INFO</strong> <span id="user-info"></span>
                        </div>
                        <div class="button-container">
                            <button id="edit-btn" class="edit-btn" onclick="toggleEditMode()">전체 수정</button>
                            <button id="save-btn" class="save-btn" >저장</button>
                            <button id="logout-btn" class="logout-btn">로그아웃</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </nav>
    <main>
        <div class="video">
            <video id="mainVideo" src="<c:url value='/video/메인.mp4'/>" autoplay muted loop></video>
        </div>
        <div class="description">
            <h1>이번주의 추천 개발자 유투브</h1>
            <h3>비전공자로서 살아가기</h3>
            <p>건설업 종사자가 개발자로 성공하기</p>
            <div class="buttons">
                <button class="play" id="playPauseBtn">
                    <i class="fa-solid fa-play"></i><span> 재생</span>
                </button>
                <button class="detail">
                    <i class="fa-solid fa-circle-info"></i> 상세 정보
                </button>
            </div>
        </div>
    </main>
    <section>
        <div class="content-list">
            <h1>현재 뜨는 개발자 정보</h1>
            <div class="slider" id="slider1"></div>
            <div class="prev" data-slider="slider1">
                <i class="fa-solid fa-angle-left prev-arrow"></i>
            </div>
            <div class="next" data-slider="slider1">
                <i class="fa-solid fa-angle-right"></i>
            </div>
        </div>
        <div class="content-list">
            <h1>개발기술 패치 내용</h1>
            <div class="slider" id="slider2"></div>
            <div class="prev" data-slider="slider2">
                <i class="fa-solid fa-angle-left prev-arrow"></i>
            </div>
            <div class="next" data-slider="slider2">
                <i class="fa-solid fa-angle-right"></i>
            </div>
        </div>
        <div class="content-list">
            <h1>현재 개발자 취업 시장은?</h1>
            <div class="slider" id="slider3"></div>
            <div class="prev" data-slider="slider3">
                <i class="fa-solid fa-angle-left prev-arrow"></i>
            </div>
            <div class="next" data-slider="slider3">
                <i class="fa-solid fa-angle-right"></i>
            </div>
        </div>
    </section>
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
                <div class="option">기프트카드</div>
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
    <script src="<c:url value='/js/main.js'/>"></script>
</body>
</html>