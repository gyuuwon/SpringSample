<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script> 
<script>
	var result = '${result}';
	
	if(result == 'SUCCESS'){
		alert('요청 처리 완료');
	}
	
	if(result == 'FAIL'){
		alert('요청 처리 실패');
	}
</script>
<div>
	<h1>Board List</h1>
	<input type="submit" value="New Board" onclick="location.href='/board/register'"/>
</div>
<div>
	<h1>LIST PAGING</h1>
	<table border="1">
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<!-- 내용 -->
		<c:forEach var="board" items="${boardList}">
			<tr>
				<td>${board.bno}</td>
				<td><a
					href="/board/readPage?bno=${board.bno}&page=${pageMaker.cri.page}">${board.title}</a></td>
				<td>${board.writer}</td>
				<td><f:formatDate pattern="yyyy-MM-dd HH:mm"
						value="${board.regdate}" /></td>
				<td><span class="badge bg-red">${board.viewcnt}</span></td>
			</tr>
		</c:forEach>
	</table>
</div>
<div>
	<div>
		<c:if test="${pageMaker.prev}">
			<a href="/board/listPage?page=${pageMaker.startPage-1}">&laquo;</a>
		</c:if>

		<c:forEach var="i" begin="${pageMaker.startPage}"
			end="${pageMaker.endPage}">
			<a href="/board/listPage?page=${i}">${i}</a>
		</c:forEach>

		<c:if test="${pageMaker.next}">
			<a href="/board/listPage?page=${pageMaker.endPage+1}">&raquo;</a>
		</c:if>
	</div>
</div>
