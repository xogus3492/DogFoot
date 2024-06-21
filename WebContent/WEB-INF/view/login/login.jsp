<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/53a8c415f1.js" crossorigin="anonymous"></script>
    <script  src="https://code.jquery.com/jquery-3.6.1.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){	
		$(document).on("click", "#btn", function(){
			let path = "login.p";
			let method = "POST";
			var data = {
                    email: $('#email').val(),
                    password: $('#password').val(),
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
					alert("로그인 성공");
					location.href = "index.jsp";
				} else {
					alert("로그인 실패");
				}
			}
			function whenError(e){
				alert("e >>> : " + e.responseText);
			}
		});
	});
</script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Noto Sans KR", sans-serif;
        }

        a {
            text-decoration: none;
            color: black;
        }

        li {
            list-style: none;
        }

        .wrap {
            width: 100%;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: rgba(0, 0, 0, 0.1);
        }

        .login {
            width: 30%;
            height: 600px;
            background: white;
            border-radius: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        h2 {
            color: rgba(0, 0, 0, 0.932);
            font-size: 2em;
        }
        .login_sns {
            padding: 20px;
            display: flex;
        }

        .login_sns li {
            padding: 0px 15px;
        }

        .login_sns a {
            width: 50px;
            height: 50px;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 10px;
            border-radius: 50px;
            background: white;
            font-size: 20px;
            box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.4), -3px -3px 5px rgba(0, 0, 0, 0.1);
        }

        .login_id {
            margin-top: 20px;
            width: 80%;
        }

        .login_id input {
            width: 100%;
            height: 50px;
            border-radius: 30px;
            margin-top: 10px;
            padding: 0px 20px;
            border: 1px solid lightgray;
            outline: none;
        }

        .login_pw {
            margin-top: 20px;
            width: 80%;
        }

        .login_pw input {
            width: 100%;
            height: 50px;
            border-radius: 30px;
            margin-top: 10px;
            padding: 0px 20px;
            border: 1px solid lightgray;
            outline: none;
        }
        .submit {
            margin-top: 50px;
            width: 80%;
        }
        .submit input {
            width: 100%;
            height: 50px;
            border: 0;
            outline: none;
            border-radius: 40px;
            background: linear-gradient(to left, rgb(0, 0, 0), rgb(0, 0, 0));
            color: white;
            font-size: 1.2em;
            letter-spacing: 2px;
        }
    </style>
</head>
<body>
<div class="wrap">
    <div class="login">
        <h2>Dog-Foot</h2>
        <div class="login_sns">
            <li><a href=""><i class="fab fa-instagram"></i></a></li>
            <li><a href=""><i class="fab fa-facebook-f"></i></a></li>
            <li><a href=""><i class="fab fa-twitter"></i></a></li>
        </div>
        <div class="login_id">
            <h4>ID</h4>
            <input type="email" name="email" id="email" placeholder="E-mail">
        </div>
        <div class="login_pw">
            <h4>Password</h4>
            <input type="password" name="password" id="password" placeholder="Password">
        </div>
        <div class="submit">
            <input type="submit" value="Login" id="btn">
        </div>
        <div class="submit">
            <a href="register.p">회원가입</a>
        </div>
    </div>
</div>
<script src="<c:url value='/resources/js/login/login.js'/>"></script> <!-- JavaScript 파일을 로드 -->
</body>
</html>
