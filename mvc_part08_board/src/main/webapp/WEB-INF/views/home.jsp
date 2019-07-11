<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>MAIN PAGE</h1>
	<c:choose>
		<c:when test="${!empty userInfo}">
			<span>${userInfo.uname} 님 반갑습니다.</span>
			<a href="sboard/register">글작성</a>
			<a href="user/signOut">로그아웃</a>
		</c:when>
		<c:otherwise>
			<a href="user/signIn">로그인</a>
			<a href="user/signUp">회원가입</a>
		</c:otherwise>
	</c:choose>
	<br>
	<a href="sboard/listReply">답변형검색게시판</a>
</body>
</html>