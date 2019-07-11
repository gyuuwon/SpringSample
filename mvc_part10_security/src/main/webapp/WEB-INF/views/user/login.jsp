<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/login">
		<div>
			<input type="text" name="username" />
		</div>
		<div>
			<input type="password" name="password" />
		</div>	
		<div>
			<label>
				<input type="checkbox" name="remember-me" />
				Remember Me
			</label>
		</div>
		<div>
			<input type="submit"/>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</body>
</html>