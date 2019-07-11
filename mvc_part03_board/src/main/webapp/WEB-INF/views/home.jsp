<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="./include/header.jsp"/>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header width-border">
					<h3 class="box-title">HOME PAGE</h3>
				</div>
				<a href="/board/register" class="btn btn-primary">글쓰기</a>
				<a href="/board/listAll" class="btn btn-primary">글목록</a>
				<a href="/board/listCri" class="btn btn-primary">글목록 CRI</a>
				<a href="/board/listPage" class="btn btn-primary">paging</a>
				<a href="/sboard/list" class="btn btn-primary">검색게시판</a>
			</div>
		</div>
	</div>
</section>
<jsp:include page="./include/footer.jsp"/>