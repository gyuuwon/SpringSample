<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>MASTER</h1>
	<sec:authorize access="isAuthenticated()">
		<p>principal : <sec:authentication property="principal"/>
		<p>ID : <sec:authentication property="principal.username"/>
		<p>MemberVO : <sec:authentication property="principal.member"/>
		<p>MemberVO uname : <sec:authentication property="principal.member.uname"/>
		<p>MemberVO authList : <sec:authentication property="principal.member.authList"/>
		<sec:authentication var="mem" property="principal.member"/>
		
		${mem.uid}<br>
		${mem.upw}<br>
		${mem.uname}<br>
		${mem.regdate}<br>
		${mem.updatedate}<br>
		
	</sec:authorize>
</body>
</html>