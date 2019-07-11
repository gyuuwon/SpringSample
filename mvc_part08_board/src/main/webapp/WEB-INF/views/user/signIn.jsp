<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><a href="/">KOREATE</a></h1>
	<h3>SIGN IN</h3>
	<form action="/user/signInPost" id="signInForm" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="uid" placeholder="USER ID" alt="아이디"></td>
			</tr>
			
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="upw" placeholder="USER PW" alt="비밀번호"></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<label>
						<input type="checkbox" name="userCookie">로그인 정보 저장
					</label>
				</td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="button" id="signIn" value="signIn">
					<input type="button" onclick="location.href='/user/signUp'" value="signUp">
				</td>
			</tr>
		</table>
	</form>
	<script>
		window.onload = function(){
			var bool = false;
			
			var input = document.getElementsByTagName("input");
			
			var signBtn = document.getElementById("signIn");
			
			signBtn.addEventListener("click",function(){
				for(var i = 0; i < input.length; i++){
					if(!(input[i].getAttribute("type") == "checkbox")){
						if(input[i].value == ""){
							alert(input[i].alt+"를 확인해주세요");
							input[i].focus();
							bool = false;
							break;
						}else{
							bool = true;
						}
					}
				}
				
				if(bool){
					document.getElementById("signInForm").submit();
				}
			});
		};
		
		var message = "${message}";
		if(message != null && message != ""){
			alert(message);
		}
	</script>
	
	
	
	
</body>
</html>