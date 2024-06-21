<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up Form</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/register/style.css'/>">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
    <script type="text/javascript">
		$(document).ready(function(){
			// 회원가입 이벤트
			$(document).on("click", "#submit", function(){
				if ($("#requestCheck").val() == 'false') {
					alert('본인확인이 필요합니다');
					$('#tel').focus();
					return;
				}
				
				var form = $('#register-form')[0];
                var formData = new FormData(form);
                
                $.ajax({
    				url: 'register.p',
    				type: 'POST',
    				data: formData,
    				contentType : false,
    				processData : false,
    				success: whenSuccess,
    				error: whenError
    			});
    			
    			function whenSuccess(resData){
    				if (resData == "SUCCESS") {
    					alert("회원가입 되셨습니다");
    					location.href = "login.p";
    				} else {
    					alert("회원가입 실패");
    				}
    			}
    			function whenError(e){
    				alert("e >>> : " + e.responseText);
    			}
				
			});
			
			// 이메일 중복 확인 이벤트
			$(document).on("click", "#emailCheckBtn", function() {

	            if ($('#email').val() == '') {
	                alert("이메일을 입력해주세요");
	                $('#email').focus();
	                return;
	            }

	            let path = "email-check.p";
	            let method = "GET";
	            var data = {
	                email: $('#email').val(),
	            };

	            $.ajax({
	                url: path,
	                type: method,
	                data: data,
	                success: whenSuccess,
	                error: whenError
	            });

	            function whenSuccess(resData) {
	                if (resData == "DUPLICATED") {
	                    alert("이메일이 중복됐습니다");
	                } else {
	                    alert("사용 가능한 이메일입니다");
	                }
	            }
	            function whenError(e) {
	                alert("e >>> : " + e.responseText);
	            }
	        });
			
			// 인증번호 요청 이벤트
			$(document).on("click", "#verify-btn", function() {

	            if ($('#tel').val() == '') {
	                alert("전화번호를 입력해주세요");
	                $('#tel').focus();
	                return;
	            }

	            let path = "send-one.p";
	            let method = "POST";
	            var data = {
	                phone: $('#tel').val(),
	            };

	            $.ajax({
	                url: path,
	                type: method,
	                data: data,
	                success: whenSuccess,
	                error: whenError
	            });

	            function whenSuccess(resData) {
	            	$("#verification-code").show();
	                $("#verify-code-btn").show();
	                if (resData == 'FAIL') {
	                	alert('인증번호 요청에 실패했습니다.');
	                	return;
	                }
	                $("#requestCheck").val('true');
	                $("#veificationNumber").val(resData);
	                $("#verification-result").val('');
	            }
	            function whenError(e) {
	                alert("e >>> : " + e.responseText);
	            }
	        });
			
			// 인증번호 확인 이벤트
			$(document).on("click", "#verify-code-btn", function() {
				if ($("#verification-code").val() == '') {
					alert('인증번호를 입력해 주세요');
					$('#verification-code').focus();
					return;
				}
				if ($("#verification-code").val() != $('#veificationNumber').val()) {
					$("#verification-result").text('인증번호가 틀렸습니다');
                    $("#verification-result").css('color', 'red');
					return;
				}
				$("#verification-result").text('인증되었습니다');
                $("#verification-result").css('color', 'green');
                $("#requestCheck").val('true');
			});
		});	
	</script>
    
    <script>
    	
       document.addEventListener('DOMContentLoaded', function() {
            // 인증번호 관련 기능
            const verifyBtn = document.getElementById('verify-btn');
            const verificationInput = document.getElementById('verification-code');
            const verifyCodeBtn = document.getElementById('verify-code-btn');
            const verificationResult = document.getElementById('verification-result');

            // Initialize birth year select options
            const birthYearSelect = document.getElementById('birth_year');
            const birthMonthSelect = document.getElementById('birth_month');
            const birthDaySelect = document.getElementById('birth_day');
            const currentYear = new Date().getFullYear();

            for (let year = 1950; year <= currentYear; year++) {
                const option = document.createElement('option');
                option.value = year;
                option.textContent = year;
                birthYearSelect.appendChild(option);
            }

            // Initialize birth month select options
            for (let month = 1; month <= 12; month++) {
                const option = document.createElement('option');
                option.value = month;
                option.textContent = month;
                birthMonthSelect.appendChild(option);
            }

            // Initialize birth day select options
            for (let day = 1; day <= 31; day++) {
                const option = document.createElement('option');
                option.value = day;
                option.textContent = day;
                birthDaySelect.appendChild(option);
            }

            // Profile photo preview
            document.getElementById('photo').addEventListener('change', function(event) {
                const file = event.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = function(e) {
                        document.getElementById('image-preview').src = e.target.result;
                    }
                    reader.readAsDataURL(file);
                }
            });
        });

        // Daum 우편번호 검색
        function sample6_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    var addr = '';
                    var extraAddr = '';

                    if (data.userSelectedType === 'R') {
                        addr = data.roadAddress;
                    } else {
                        addr = data.jibunAddress;
                    }

                    if (data.userSelectedType === 'R') {
                        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                            extraAddr += data.bname;
                        }
                        if (data.buildingName !== '' && data.apartment === 'Y') {
                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        if (extraAddr !== '') {
                            extraAddr = ' (' + extraAddr + ')';
                        }
                        document.getElementById("jibun_address").value = extraAddr;
                    } else {
                        document.getElementById("jibun_address").value = '';
                    }

                    document.getElementById('zone_code').value = data.zonecode;
                    document.getElementById("road_address").value = addr;
                    document.getElementById("detail_address").focus();
                }
            }).open();
        }
    </script>
    <style>
        html, body {
            height: 100%;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            height: 100%; /* Ensures the container takes full height */
        }

        .signup-form {
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: flex-start;
        }

        form {
            width: 60%; /* Adjust width if necessary */
            padding: 20px;
            background: #fff;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            border-radius: 4px;
        }

        .image-preview-container {
            width: 20%; /* Adjust width if necessary */
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 10px;
            background: #fff;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            border-radius: 1px;
            margin-right: 20px; /* Add margin to create space between logo and preview */
        }

        .image-preview-container img {
            max-width: 100%;
            max-height: 300px;
            object-fit: contain;
        }

        .row {
            display: flex;
            flex-wrap: wrap;
            margin: 0 -10px;
            width: 100%; /* Ensures rows take full width of the form */
        }

        h4 {
            width: 100%;
            margin: 0 0 10px;
            font-size: 18px;
            text-align: center; /* Center aligns the heading */
        }

        .input-group {
            display: flex;
            flex-direction: column;
            margin-bottom: 15px;
            position: relative;
            width: 100%; /* Ensures input group takes full width of the form */
        }

        .input-group-icon {
            position: relative;
            width: 100%; /* Ensures input group icon takes full width */
        }

        .input-group-icon input {
            padding-left: 40px;
            width: calc(100% - 40px); /* Adjusts width to account for padding */
        }

        .input-icon {
            position: absolute;
            top: 50%;
            left: 10px;
            transform: translateY(-50%);
        }

        .input-icon i {
            font-size: 16px;
            color: #999;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box; /* Ensures padding is included in width */
        }

        input[type="radio"],
        input[type="checkbox"] {
            margin-right: 10px;
        }

        label {
            font-size: 14px;
            color: #333;
        }

        span {
            display: flex;
            align-items: center;
        }

        i.fa {
            margin-right: 5px;
            font-size: 16px;
        }

        input[type="radio"]:checked + label span i,
        input[type="checkbox"]:checked + label span i {
            color: #000;
        }

        /* SECTION - BIRTH */
        .info#info__birth {
            display: flex;
            width: 100%; /* Ensures birth info section takes full width */
        }

        .info#info__birth select {
            margin-left: 7px;
        }

        .info#info__birth select:first-child {
            margin-left: 0;
        }
        
        .info#info__birth {
            display: flex;
            width: 100%; /* Ensures birth info section takes full width */
        }

        .info#info__birth input {
            margin-left: 7px;
        }

        .info#info__birth input:first-child {
            margin-left: 0;
        }
        
        a,
        a:visited {
            text-decoration: none;
            color: #00AE68;
        }

        a,
        a:visited {
            text-decoration: none;
            color: #00AE68;
        }

        a.button {
            display: block;
            position: relative;
            float: left;
            width: auto; /* 기본 크기로 설정 */
            padding: 10px 20px; /* 패딩 추가 */
            margin: 10px 20px 10px 0;
            font-weight: 600;
            text-align: center;
            color: #FFF;
            border-radius: 5px;
            transition: all 0.2s;
            line-height: normal; /* 기본 line-height로 설정 */
        }

        .btnBlueGreen {
            background: #00AE68;
        }

        .btnLightBlue {
            background: #5DC8CD;
        }

        .btnOrange {
            background: #FFAA40;
        }

        .btnPurple {
            background: #A74982;
        }

        /* 3D */
        .btnBlueGreen.btnPush {
            box-shadow: 0px 5px 0px 0px #007144;
        }

        .btnPush:hover {
            margin-top: 15px;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
     <div class="main">
        <div class="container">
            <div class="signup-form">
                <div class="image-preview-container">
                    <img id="image-preview" src="" alt="프로필 사진" style="display: block;">
                </div>
<form class="register-form" id="register-form" method="POST" enctype="multipart/form-data" action="register.p">
    <div class="form-row">
        <div class="form-group">
            <div class="form-input">
                <label for="email" class="required">E-MAIL</label>
                <input type="email" name="email" id="email" required />
            </div>
            <div>
                <input type="button" value="이메일 중복 확인" class="emailCheckBtn" id="emailCheckBtn" name="emailCheckBtn" style="float: right;">
            </div>
            <div class="form-input">
                <label for="password" class="required">PASSWORD</label>
                <input type="password" name="password" id="password" required />
            </div>
            <div class="form-input">
                <label for="name" class="required">NAME</label>
                <input type="text" name="name" id="name" required />
            </div>
            <div class="form-input">
                <label for="tel" class="required">Phone number</label>
                <div class="verification-container">
                    <input type="text" name="tel" id="tel" required />
                    <button type="button" id="verify-btn">인증</button>
                </div>
                <input type="text" id="verification-code" placeholder="인증번호 입력" style="display:none;">
                <button type="button" id="verify-code-btn" style="display:none;">확인</button>
                <div id="verification-result"></div>
            </div>
        </div>
        
        <br>
        <select name="gender" id="gender" aria-label="Gender">
            <option value="M">남</option>
            <option value="W">여</option>
        </select>
        <br><br>
        
        <div class="form-radio">
            <div class="label-flex">
                <a href="#" class="form-link"></a>
            </div>
            <div class="form-radio-group">
                <div class="form-radio-item">
                    <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
                    <input type="text" id="zone_code" name="zone_code" placeholder="우편번호">
                    <input type="text" id="road_address" name="road_address" placeholder="주소">
                    <span id="guide" style="color:#999;display:none"></span>
                    <input type="text" id="detail_address" name="detail_address" placeholder="상세주소">
                    <input type="text" id="jibun_address" name="jibun_address" placeholder="참고항목">
                </div>
            </div>
        </div>
        
        <br>
        <div class="info" id="info__birth">
            <input type="text" class="box" id="birth_year" name="birth_year" aria-label="Birth Year" placeholder="연도" style="margin-right: 10px;">
            <input type="text" class="box" id="birth_month" name="birth_month" aria-label="Birth Month" placeholder="월" style="margin-right: 10px;">
            <input type="text" class="box" id="birth_day" name="birth_day" aria-label="Birth Day" placeholder="일">
        </div>
        
        <div class="form-input">
            <label for="info">소개</label>
            <input type="text" name="info" id="info" />
        </div>
        <div class="form-input">
            <label for="photo">프로필사진</label>
            <input type="file" id="photo" name="photo" accept="image/png, image/jpeg">
        </div>
        <div class="form-submit">
            <a title="Button push blue/green" id="submit" class="button btnPush btnBlueGreen">회원가입</a>
        </div>
    </div>
</form>

            </div>
        </div>
    </div>
    
<input type="text" id="requestCheck" value="false" style="display:none;"/>
<input type="text" id="veificationNumber" value="fxjghfcjyhtrcytcdtresxrtysxrewstjuifytgukfyktdyr" style="display:none;"/>
<input type="text" id="verifyCheck" value="false" style="display:none;"/>
</body>
</html>
