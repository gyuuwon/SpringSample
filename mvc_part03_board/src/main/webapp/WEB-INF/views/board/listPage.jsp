<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../include/header.jsp"/>
<script>
	var result = '${result}';
	
	if(result == 'SUCCESS'){
		alert('요청 처리 완료');
	}
	
	if(result == 'FAIL'){
		alert('요청 처리 실패');
	}
</script>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">게시글 목록</h3>
				</div>
				<div class="box-body">
					<table class="table table-bordered">
						<tr>
							<th>BNO</th>
							<th>TITLE</th>
							<th>WRITER</th>
							<th>REGDATE</th>
							<th>VIEWCNT</th>
						</tr>
						<!-- 내용 -->
						<c:forEach var="board" items="${boardList}">
							<tr>
								<td>${board.bno}</td>
								<td><a href="/board/readPage?bno=${board.bno}&page=${pageMaker.cri.page}">${board.title}</a></td>
								<td>${board.writer}</td>
								<td><f:formatDate pattern="yyyy-MM-dd HH:mm" value="${board.regdate}"/></td>
								<td><span class="badge bg-red">${board.viewcnt}</span></td>								
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="box-footer">
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li><a href="/board/listPage?page=${pageMaker.startPage-1}">&laquo;</a></li>
							</c:if>
							
							<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
								<li ${pageMaker.cri.page == i ? 'class=active' : ''}>
									<a href="/board/listPage?page=${i}">${i}</a>
								</li>
							</c:forEach>
							
							<c:if test="${pageMaker.next}">
								<li><a href="/board/listPage?page=${pageMaker.endPage+1}">&raquo;</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>		
		</div>
	</div>
</section>
<jsp:include page="../include/footer.jsp"/>