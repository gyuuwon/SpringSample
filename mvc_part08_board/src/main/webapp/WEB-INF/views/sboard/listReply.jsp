<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>REPLY BOARD LIST</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<h1><a href="/">KOREATE</a></h1>
	<div>
		<select name="searchType">
			<option value="n" ${cri.searchType == null ? 'selected' : ''}>-------------------</option>
			<option value="t" ${cri.searchType == 't' ? 'selected' : ''}>TITLE</option>
			<option value="c" ${cri.searchType == 'c' ? 'selected' : ''}>CONTENT</option>
			<option value="w" ${cri.searchType == 'w' ? 'selected' : ''}>WRITER</option>
			<option value="tc" ${cri.searchType == 'tc' ? 'selected' : ''}>TITLE&CONTENT</option>
			<option value="cw" ${cri.searchType == 'cw' ? 'selected' : ''}>CONTENT&WRITER</option>
			<option value="tcw" ${cri.searchType == 'tcw' ? 'selected' : ''}>TITLE*CONTENT&WRITER</option>
		</select>
		<input type="text" name="keyword" id="keyword" value="${cri.keyword}"/>
		<input type="button" id="searchBtn" value="검색"/>
		<input type="button" id="newBtn" value="글작성"/>
	</div>
	<br/>
	<table border=1>
		<tr>
			<th>BNO</th>
			<th>TITLE</th>
			<th>WRITER</th>
			<th>REGDATE</th>
			<th>VIEWCNT</th>
		</tr>
		<!-- 게시글 항목 출력 -->
		<c:forEach var="board" items="${list}">
			<c:choose>
				<c:when test="${board.showboard == 'y'}">
					<tr>
						<td>${board.bno}</td>
						<td>
							<a style="text-decoration:none;" href="/sboard/readPage${pageMaker.makeSearchQuery(pageMaker.cri.page)}&bno=${board.bno}">
								<c:if test="${board.depth != 0}">
									<c:forEach var = "i" begin="1" end="${board.depth}">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:forEach>	
									└ <!-- ㅂ - 한자 - 6 --> 
								</c:if>	
								${board.title}[${board.commentCnt}]
							</a>
							</td>
						<td>${board.writer}</td>
						<td><f:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regdate}"/></td>
						<td>${board.viewcnt}</td>
					</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td></td>
					<td>삭제된 게시물입니다.</td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</c:otherwise>
			</c:choose>
		</c:forEach>
	</table>
	<!-- paging 블럭 -->
	<c:if test="${pageMaker.prev}">
		<a href="/sboard/listReply${pageMaker.makeSearchQuery(1)}">&laquo;&laquo;</a>
		<a href="/sboard/listReply${pageMaker.makeSearchQuery(pageMaker.startPage-1)}">&laquo;</a>
	</c:if>
	<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
		<a href="/sboard/listReply${pageMaker.makeSearchQuery(i)}">${i}</a>
	</c:forEach>
	<c:if test="${pageMaker.next}">
		<a href="/sboard/listReply${pageMaker.makeSearchQuery(pageMaker.endPage+1)}">&raquo;</a>
		<a href="/sboard/listReply${pageMaker.makeSearchQuery(pageMaker.maxPage)}">&raquo;&raquo;</a>
		
	</c:if>
	
	<script>
		$("#searchBtn").click(function(){
			var searchValue = $("select option:selected").val();
			var keywordValue = $("#keyword").val();
			console.log(searchValue +"  /  " + keywordValue);
			location.href="/sboard/listReply?searchType="+searchValue+"&keyword="+keywordValue;
		});
		
		$("#newBtn").click(function(){
			location.href="/sboard/register";
		});
		
		
	</script>
</body>
</html>









