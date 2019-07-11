<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>UPLOAD RESULT</h1>
	<h2>${savedName}</h2>
	<c:forEach var="n" items="${savedNames}">
		${n}<br>
	</c:forEach>
</body>
</html>