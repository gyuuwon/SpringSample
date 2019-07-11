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
				<div class="col-lg-2">
					<select id="searchType" name="searchType" class="form-control">
						<option value="n" ${cri.searchType == null ? 'selected' : ''}>-----------</option>
						<option value="t" ${cri.searchType == 't' ? 'selected' : ''}>TITLE</option>
						<option value="c" ${cri.searchType == 'c' ? 'selected' : ''}>CONTENT</option>
						<option value="w" ${cri.searchType == 'w' ? 'selected' : ''}>WRITER</option>
						<option value="tc" ${cri.searchType == 'tc' ? 'selected' : ''}>TITLE&CONTENT</option>
						<option value="cw" ${cri.searchType == 'cw' ? 'selected' : ''}>CONTENT&WRITER</option>
						<option value="tcw" ${cri.searchType == 'tcw' ? 'selected' : ''}>TITLE&CONTENT&WRITER</option>
					</select>
				</div>
				<div class="col-lg-3">
					<input id="keyword" type="text" class="form-control" name="keyword" value="${cri.keyword}"/>	
				</div>
				<div>
					<input id="searchBtn" type="button" class="btn btn-warning" value="SEARCH"/>
					<input id="newBtn" type="button" class="btn btn-primary" value="NEW BOARD"/>
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
								<td><a href="/sboard/readPage${pageMaker.makeSearchQuery(pageMaker.cri.page)}&bno=${board.bno}">${board.title}</a></td>
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
								<%-- <li><a href="/board/listPage?page=${pageMaker.startPage-1}">&laquo;</a></li> --%>
								<li><a href="/sboard/list${pageMaker.makeSearchQuery(pageMaker.startPage -1)}">&laquo;</a></li>
							</c:if>
							<c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
								<li ${pageMaker.cri.page == i ? 'class=active' : ''}>
									<%-- <a href="/sboard/list?page=${i}&searchType=${cri.searchType}&keyword=${cri.keyword}">${i}</a> --%>
									<a href="/sboard/list${pageMaker.makeSearchQuery(i)}">${i}</a>
								</li>
							</c:forEach>
							<c:if test="${pageMaker.next}">
								<%-- <li><a href="/board/listPage?page=${pageMaker.endPage+1}">&raquo;</a></li> --%>
								<li><a href="/sboard/list${pageMaker.makeSearchQuery(pageMaker.endPage+1)}">&raquo;</a></li>
							</c:if>
						</ul>
					</div>
					
				</div>
			</div>		
		</div>
	</div>
</section>
<script>
	$(function(){
		$("#searchBtn").click(function(){
			var keywordVal = $("#keyword").val();
			var searchTypeVal = $("#searchType").val();
			location.href="/sboard/list?searchType="+searchTypeVal+"&keyword="+keywordVal;
		});	
		
		$("#newBtn").click(function(){
			location.href="/sboard/register";
		});
		
	});
</script>



<jsp:include page="../include/footer.jsp"/>








