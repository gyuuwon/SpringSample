<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
<sec:authorize access="isAuthenticated()">
	<sec:authentication var="member" property="principal.member"/>
</sec:authorize>

	<div class="container">
		<h1> CHAT PAGE</h1>
		<textarea id="data" class="form-control" rows="4" cols="50" readonly></textarea>
		<br/>
		<div class="row">
			<div class="col-md-10">
				<input type="text" id="message" class="form-control"/>
			</div>
			<div class="col-md-2">
				<input type="button" id="sendBtn" 
				class="form-control btn btn-primary" value="SEND"/>
			</div>
		</div>
	</div>
	<script>
		$(function(){
			$("#sendBtn").click(function(){
				// 메세지 전송
				sendMessage();
				$("#message").val('');
			});
			
			$("#message").keydown(function(key){
				// 인풋에 들어온 키 값이 enter 일때
				if(key.keyCode == 13){
					// 메세지 전송
					sendMessage();
					$(this).val('');
				}
			});
		});
		
		var sock = new SockJS("/echo");
		sock.onmessage = onmessage;
		sock.onclose = onclose;
		
		function sendMessage(){
			var userName = "${member.u_name}";
			var message = $("#message").val();
			
			sock.send(userName+","+message);
		}
		
		function onmessage(message){
			var data = message.data;
			$("#data").append(data+"\r\n");
			$("#data").focus();
			$("#message").focus();
		}
		
		function onclose(){
			$("#data").append("연결 끊김");
		}
		
	</script>
</body>
</html>





