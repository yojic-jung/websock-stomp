<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입 채팅앱</title>
<style>
.center{width:500px;margin:200px auto;text-align:center; }
.id, .pw{
margin:10px;
width:150px;
padding:5px;
font-size:15px;
}
</style>
  <% if(session.getAttribute("id")!=null) {
%>
<script>
window.location="./main";
</script>
<%
}
%>
</head>
<body>

<div class="center">
<div style="margin-bottom:20px;">회원가입</div>

<form method="post">
<input class="id" type="text" name="id" placeholder="이름"/> <br/>
<input class="pw" type="password" name="password" placeholder="비밀번호"/> <br/>
<input type="submit" value="회원가입"/>
</form>
<br/>


</div>
 <script src="http://code.jquery.com/jquery-3.1.1.js"></script>
  
    <script>
    var ex = "<c:out value="${ex}" />"
    if(ex=="wrong")
    	alert("이미 가입된 이름입니다.")
    </script>
</body>
</html>