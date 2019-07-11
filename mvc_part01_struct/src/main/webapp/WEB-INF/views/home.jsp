<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<meta charset="utf-8">
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="doA">doA</a>
<a href="doB">doB</a>
<a href="doC">doC</a>
<form action="doD" method="post">
	<input type="text" name="msg">
	<input type="submit" value="전송">
</form>
<a href="doE">doE</a>
<a href="doF">doF</a>
</body>
</html>
