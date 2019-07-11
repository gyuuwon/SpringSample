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
	<h3>SIGN UP</h3>
	<form action="/user/signUpPost" id="signUpForm" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="uid" placeholder="USER ID" alt="아이디"/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="upw" placeholder="USER PW" alt="비밀번호"/></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td>
					<input type="password" name="repw" placeholder="USER REPW" alt="비밀번호 확인"/>
				</td>
			</tr>
			<tr>
				<td>사용자 이름</td>
				<td>
					<input type="text" name="uname" placeholder="USER NAME" alt="이름"/>
				</td>
			</tr>
			<tr>
				<td colspan=2>
					<input type="button" id="signUp" value="signUp"/>
					<input type="button" onclick="location.href='/user/signIn'" value="SIGN IN"/>
				</td>
			</tr>
		</table>	
	</form>
	<script>
		window.onload = function(){
			var bool = false;
			
			var input = document.getElementsByTagName('input');
			
			var signUpBtn = document.getElementById('signUp');
			
			signUpBtn.addEventListener('click',function(){
				console.log('input tag 개수 : ' + input.length);
				for(var i=0; i<input.length; i++){
					console.log(i+'번째 태그 : ' + input[i].value);
					if(input[i].value==""){
						alert(input[i].alt + "를 확인해주세요!");
						input[i].focus();
						bool = false;
						break;
					}else{
						bool = true;
					}
				}	
				
				if(bool){
					document.getElementById("signUpForm").submit();
				}
				
			});
		}
		
		var message = "${message}";
		if(message!=null && message != ""){
			alert(message);
		}
	</script>
</body>
</html>