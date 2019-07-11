<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp"/>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<form action="/sboard/modifyPage" method="post">
				<div class="box-body">
					<!-- ${board} -->
					<div class="form-group">
						<label>TITLE</label>
						<input type="text" 
								name="title" 
								class="form-control"
								 value="${board.title}"/>
					</div>
					<div class="form-group">
						<label>CONTENT</label>
						<textarea name="content" class="form-control" rows=4>${board.content}</textarea>
					</div>
					<div class="form-group">
						<label>WRITER</label>
						<input type="text" 
								name="writer"
								class="form-control"
								 value="${board.writer}"/>
					</div>
				</div>
				<div class="box-footer">
					<!-- bottom -->
					<input type="submit" class="btn btn-warning" value="MODIFY"/>
					<input type="hidden" name="bno" value="${board.bno}"/>
					<input type="hidden" name="page" value="${cri.page}"/>
					<input type="hidden" name="perPageNum" value="${cri.perPageNum}"/>
					<input type="hidden" name="searchType" value="${cri.searchType}"/>
					<input type="hidden" name="keyword" value="${cri.keyword}"/>
				</div>
				</form>
			</div>		
		</div>
	</div>
</section>
<jsp:include page="../include/footer.jsp"/>







