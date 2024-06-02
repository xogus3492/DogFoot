<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Main</title>
 	<link href="/resources/css/main/main1.css" rel="stylesheet" type ="text/css">
</head>
<body>
<nav>
    <div class="left">
        <div class="logo">
            <a href="#"><img src="<c:url value='/resources/img/logo.png'/>" alt=""></a>
        </div>
        <div class="mobile-menu">커뮤니티</div>
        <ul class="menu-list">
            <li>
                <a href="<c:url value='/board_job'/>">취업게시판</a>
            </li>
            <li>
                <a href="<c:url value='/board_popularity'/>">인기게시판</a>
            </li>
            <li>
                <a href="<c:url value='/board_free'/>">자유게시판</a>
            </li>
            <li>
                <a href="<c:url value='/board_question'/>">질문게시판</a>
            </li>
            <li>
                <a href="<c:url value='/register'/>">회원가입</a>
            </li>
        </ul>
    </div>
    <div class="right">
        <div class="icon search">
            <div class="search-bar">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" placeholder="찿고싶은키워드"/>
            </div>
        </div>
        <div class="icon kids">
            <a href="<c:url value='/login'/>">로그인</a>
        </div>
        <div class="icon bell">
            <a href="#"><i class="fa-solid fa-bell"></i></a>
        </div>
    </div>
</nav>
<main>
    <div class="video">
        <video id="mainVideo" src="<c:url value='/resources/video/메인.mp4'/>" autoplay muted loop></video>
    </div>
    <div class="description">
        <h1>이번주의 추천 개발자 유투브</h1>
        <h3>비전공자로서 살아가기</h3>
        <p>건설업 종사자가 개발자로 성공하기</p>
        <div class="buttons">
            <button class="play" id="playPauseBtn"><i class="fa-solid fa-play"></i><span> 재생</span></button>
            <button class="detail"><i class="fa-solid fa-circle-info"></i> 상세 정보</button>
        </div>
    </div>
</main>
<section>
    <div class="content-list">
        <h1>현재 뜨는 개발자 정보</h1>
        <div class="slider" id="slider1"></div>
        <div class="prev" data-slider="slider1"><i class="fa-solid fa-angle-left prev-arrow"></i></div>
        <div class="next" data-slider="slider1"><i class="fa-solid fa-angle-right"></i></div>
    </div>
    <div class="content-list">
        <h1>개발기술 패치 내용</h1>
        <div class="slider" id="slider2"></div>
        <div class="prev" data-slider="slider2"><i class="fa-solid fa-angle-left prev-arrow"></i></div>
        <div class="next" data-slider="slider2"><i class="fa-solid fa-angle-right"></i></div>
    </div>
    <div class="content-list">
        <h1>현재 개발자 취업 시장은?</h1>
        <div class="slider" id="slider3"></div>
        <div class="prev" data-slider="slider3"><i class="fa-solid fa-angle-left prev-arrow"></i></div>
        <div class="next" data-slider="slider3"><i class="fa-solid fa-angle-right"></i></div>
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
<script src="<c:url value='/resources/js/script.js'/>"></script>
</body>
</html>
