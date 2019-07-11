<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/resources/bootstrap/css/bootstrap.css" rel="stylesheet"/>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${path}/resources/bootstrap/js/bootstrap.js"></script>
<script src="${path}/resources/js/jquery.validate.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.js"></script>  
</head>
<body>
	<header class="container">
		<nav class="navbar navbar-default" role="navigation">
			<div class="navbar-hedaer">
				<button type="button" class="navbar-toggle" 
						data-toggle="collapse" 
						data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle Navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${path}">
					<span class="glyphicon glyphicon-leaf"></span>
				</a>
			</div>
			<div class="collapse navbar-collapse navbar-right navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							게시물
						</a>
						<ul class="dropdown-menu dropdown-menu-left">
							<li><a href="#">공지사항</a></li>
							<li><a href="#">질문답변</a></li>
							<li><a href="#">문의사항</a></li>
						</ul>
					</li>
					<sec:authorize access="isAuthenticated()">
						<sec:authentication var="member" property="principal.member"/>
						<li><a href="#">${member.u_name}</a></li>
						<li><a href="/mngt/main">MANAGEMENT</a></li>
						<li><a href="/user/logout">logout</a></li>
					</sec:authorize>
					<sec:authorize access="isAnonymous()">
						<li><a href="${path}/user/login">로그인</a></li>
						<li><a href="${path}/user/join">회원가입</a></li>
						<li><a href="${path}/user/joinVal">회원가입(validation)</a></li>
					</sec:authorize>
					<li><a href="/chat">chat</a></li>
				</ul>			
			</div>
		</nav>	
	</header>