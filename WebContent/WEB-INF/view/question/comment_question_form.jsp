<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ page import="domain.user.vo.UserVO" %>
<%@ page import="domain.question.vo.BoardQuestionVO" %>
<%@ page import="domain.question.vo.CommentQuestionVO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@ page import="org.apache.log4j.LogManager" %>
<%@ page import="org.apache.log4j.Logger" %>

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
			// 댓글 내용 길이 제한 200 바이트
			$("#content").keyup(function(){
				cut_200(this);
			});

			selectAll();

			$(document).on("click", "#insertBtn", function(){
				console.log("insertBtn >>> : ");

				let insertURL = "commentQuestionInsert.p";
				let method = "POST";
				let dataParam = {
					boardquestionid: $("#boardquestionid").val(),
					userid: $("#userid").val(),
					content: $("#content").val(),
				};
				dataParam = $("#commentquestionform").serialize();
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
						commentquestionformData();
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

				var commentquestionidV = $(this).parents("li").attr("dataNum");
				alert("commentquestionidV >>> : " + commentquestionidV);
				var target = $(this).parents(".rbmemoItem");
				console.log("target >>> : " + target);

				let deleteURL = "commentQuestionDelete.p";
				let method = "POST";
				let dataParam = {
					rkcommentquestionid: $('#commentquestionid').val(commentquestionidV),
				};
				dataParam = $("#commentquestionid").serialize();
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

			let selectAllURL = "commentQuestionSelectAll.p";
			let method = "POST";
			let dataParam = {
				boardquestionid: $("#boardquestionid").val(),
			};
			dataParam = $("#commentquestionform").serialize();
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
				console.log("whenSuccess commentQuestionSelectAll resData >>> : " + resData);

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
					addNewItem(vv[0], decodeURIComponent(vv[1]), decodeURIComponent(vv[2]), vv[3]);
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

			userid = decodeURIComponent(userid);
			content = decodeURIComponent(content);
			
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
			$("#commentquestionlist").append(newLi);
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
		function commentquestionformData(){
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
</head>
<body>
<hr>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	Logger logger = LogManager.getLogger(this.getClass());
	logger.info("comment_question_form.jsp 페이지 >>> : ");

	String boardquestionid = request.getParameter("boardquestionid");
	logger.info("comment_question_form.jsp boardquestionid >>> : " + boardquestionid);
%>
<div id="rbwriterdiv">
	<form name="commentquestionform" id="commentquestionform">
		<div class="comments">
			<h3 style="text-align:center">Comments</h3>
			<div class="comment">
				<ul name="commentquestionlist" id="commentquestionlist">
					<!-- 여기에 동적 생성 요소가 들어옵니다. -->
				</ul>
			</div>
		</div>
		<table>
		<%
			if (objSession !=null) {
		%>
			<tr>
				<td>userid</td>
				<td>
					<input type="text" name="userid" id="userid" value=<%= name %> required readonly/>
					<input type="hidden" name="boardquestionid" id="boardquestionid" value="<%=boardquestionid%>">
					<input type="hidden" name="commentquestionid" id="commentquestionid">

				</td>
			</tr>
			<div class="comment-form">
				<tr>
					<td><h3>Comment</h3></td>
					<td>
						<textarea name="content" id="content" placeholder="댓글을 입력하세요..." rows="5" cols="50" style="resize: none"></textarea>
						<div><span class="bytes">0</span>bytes</div>
						<input type="button" value="작성" id="insertBtn">
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