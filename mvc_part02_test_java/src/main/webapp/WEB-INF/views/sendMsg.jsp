<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>send Message</h1>
	
	<h2>${productVO.name}</h2>
	<h2>${productVO.price}</h2>
	
	<form action="sendMsg" method="post">
		<input type="text" name="msg">
		<input type="submit">
	</form>
</body>
</html>