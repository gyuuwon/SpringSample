<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>LOGOUT PAGE</h1>
	<form id="logOutForm" action="/logout" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	</form>
	<script type="text/javascript">
		window.onload = function(){
			document.getElementById("logOutForm").submit();
		}
	</script>
</body>
</html>