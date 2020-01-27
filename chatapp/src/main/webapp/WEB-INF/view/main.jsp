<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>메인 채팅앱</title>
  <% if(session.getAttribute("id")==null) {
%>
<script>
window.location="./login";
</script>
<%
}
%>
<style>

.left{
	float:left;
	width:400px;
	margin-right:50px;;
	padding:20px;
}

.right{
	width:400px;
	padding:20px;
	display:inline-block;
}

.box1,.box2{
border:1px solid black;
padding:20px;
}
</style>
</head>
<body>
<a href="./logout">로그아웃</a>
<br/>


<div class="left">
<div>대화상대</div>
<div class="box1">

<c:forEach var="post" items="${user}" varStatus="status">

<table style="border-spacing:0px;">
<tr>
<td class="user" style="width:300px;background-color:lightgray;padding:10px; border:1px solid black;">
${post}
</td>
</tr>
</table>

</c:forEach>
</div>
</div>


<div class="right">
<div>대화방</div>
<div class="box2"></div>
</div>

<script src="http://code.jquery.com/jquery-3.1.1.js"></script>
<script>
$(document).ready(function(){
	$('.user').dblclick(function(){
		window.open('./chatroom?user='+$(this).text(),"","width=600, height=600");
	});
	
	$('.user').hover(function() {
		  $(this).css("background-color", "gray");
		}, function(){
		  $(this).css("background-color", "lightgray");
		});
});
</script>
</body>
</html>