<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>로그인 채팅앱</title>
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
<div style="margin-bottom:20px;">로그인</div>

<form method="post">
<input class="id" type="text" name="id" placeholder="이름"/> <br/>
<input class="pw" type="password" name="password" placeholder="비밀번호"/> <br/>
<input type="submit" value="로그인"/>
</form>
<br/>
<a href="./signup">회원가입</a>


</div>
 <script src="http://code.jquery.com/jquery-3.1.1.js"></script>
  
    <script>
    var ex = "<c:out value="${ex}" />"
    if(ex=="wrong"){
    	alert("아이디와 비밀번호가 일치하지 않습니다.")
    }else if(ex=='success'){
    	alert("회원가입이 완료되었습니다. 로그인해주세요.")
    }
    </script>
</body>
</html>