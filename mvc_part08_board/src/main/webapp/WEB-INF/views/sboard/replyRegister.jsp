<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/upload.js"></script>
<script src="${pageContext.request.contextPath}/resources/editor/js/service/HuskyEZCreator.js"></script>
</head>
<body>
<h1><a href="/">KOREATE</a></h1>
<h1>REGISTER BOARD</h1>
<form id="registForm" action="/sboard/replyRegister" method="post">
	<table border=1 style="width:100%;">
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" required/></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer" value="${userInfo.uname}" readonly required/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea style="width:100%;" name="content" id="content" rows=3></textarea>
			</td>
		</tr>
	</table>
	<hr/>
	<div>
		<input type="button" id="saveBtn" value="SAVE"/>
		<input type="hidden" name="uno" value="${userInfo.uno}"/>
		<input type="hidden" name="origin" value="${boardVO.origin}"/>
		<input type="hidden" name="depth" value="${boardVO.depth}"/>
		<input type="hidden" name="seq" value="${boardVO.seq}"/>
		<input type="hidden" name="page" value="${cri.page}"/>
		<input type="hidden" name="perPageNum" value="${cri.perPageNum}"/>
		<input type="hidden" name="searchType" value="${cri.searchType}"/>
		<input type="hidden" name="keyword" value="${cri.keyword}"/>
	</div>
</form>

<script>
	var path = "${pageContext.request.contextPath}/resources/editor/SmartEditor2Skin.html";
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame(
			oEditors,
			"content",
			path,
			"createSEditor2"
	);
	
	$("#saveBtn").click(function(){
		var str = "";
		$("#registForm").append(str);
		oEditors[0].exec("UPDATE_CONTENTS_FIELD",[]);
		$("#registForm").submit();
	});
	
	
</script>
</body>
</html>












