<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.user.vo.UserVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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

System.out.println("sessionKey : " + userInfo);
Object obj = session.getAttribute(userInfo);


String userid = "";
String email = "";
String tel = "";
String gender = "";
String birthday = "";
String jibunAddress = "";
String info = "";
String photo = "";
String name = "";

if (obj != null) {
   UserVO uservo = (UserVO) obj;
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
%>
</body>
</html>