<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>채팅</title>
 <script src="http://code.jquery.com/jquery-3.1.1.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
<script src="http://cdn.bootcss.com/stomp.js/2.3.3/stomp.js"></script>
<script type="text/javascript">
	
	var sender = "<c:out value="${sender}" />";
	var receiver =  "<c:out value="${receiver}" />";
	var chatroom_id =  "<c:out value="${chatroom_id}" />";
	
	function checkoutUserlist(){

        var url = "http://localhost:8080/websocketTest/feed "; 
        $.post(url,{
                greeting:"hello seoul" 
            },
            function(data){ 
                alert(data);
            },"json");
    }

    var stompClient = null;


    //this line.
    function connect() {
        var socket = new SockJS("http://localhost:8080/websocketTest/hello");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            stompClient.subscribe('/queue/message-'+chatroom_id,function(greeting){
            	appendMessage(greeting);
            });
        });
    }
    
    
    function send() {
    	var msg = $("#message").val();
    	
    	if(msg != ""){
			  var message = {};
			  message.message_content = $("#message").val()
		  	  message.message_sender = sender
		  	  message.message_receiver = receiver
		  	  message.chatroom_id = chatroom_id
		  }
		 
		  
        stompClient.send("/app/message", {atytopic:"message", name: chatroom_id}, JSON.stringify(message));
    
        $("#message").val("");
    }
	
		  
	function appendMessage(msg) {
		if(JSON.parse(msg.body).message_sender==sender){
			$("#chatMessageArea").append("<div style='text-align:right'>"+JSON.parse(msg.body).message_content+"</div>");
			var chatAreaHeight = $("#chatArea").height();
			var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
			$("#chatArea").scrollTop(maxScroll);
		}else{
			$("#chatMessageArea").append("<div style='text-align:left'>"+JSON.parse(msg.body).message_content+"</div>");
			var chatAreaHeight = $("#chatArea").height();
			var maxScroll = $("#chatMessageArea").height() - chatAreaHeight;
			$("#chatArea").scrollTop(maxScroll);
		}
		
	}
	
	$(document).ready(function(){
		connect(); 
		$('#message').keypress(function(event){
			
			var keycode = (event.keyCode ? event.keyCode : event.which);
			if(keycode == '13'){
				send();	
			}
			event.stopPropagation();
		});
		$('#sendBtn').click(function() { send(); });
		$('#exitBtn').click(function() { disconnect(); });
		
		$("#chatMessageArea").append($('.conversation').html());
	});
</script>
<style>
#chatArea {
	width: 600px; height: 400px; overflow-y: auto; border: 1px solid black;
}
</style>
</head>
<body>
	<input type="button" id="exitBtn" value="나가기">
	<br/>
	${chatroom_id}
    
    <h1>대화 영역</h1>
    <div id="chatArea">
    <div id="chatMessageArea"></div>
    </div>
    <br/>
    <input type="text" id="message">
    <input type="button" id="sendBtn" value="전송">
    
    <div style="display:none;">
    <div class="conversation">
    <c:forEach var="post" items="${mesList}">
    
    <c:choose>
   		<c:when test="${post.message_sender==sender}">
   		<div style="text-align:right">
   		${post.message_sender}:${post.message_content}
   		</div>
    	</c:when>
    	<c:otherwise>
    	<div style="text-align:left">
   		${post.message_sender}:${post.message_content}
   		</div>
    	</c:otherwise>
    </c:choose>
    </c:forEach>
    </div>
    </div>
    
</body>

</html>