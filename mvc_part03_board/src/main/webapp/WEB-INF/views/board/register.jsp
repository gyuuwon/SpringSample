<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp"/>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">REGISTER BOARD</h3>
				</div>
				<form method="post">
					<div class="box-body">
						<!-- content -->
						<div class="form-group">
							<label>TITLE</label>
							<input type="text" name="title" class="form-control" placeholder="ENTER TITLE">
						</div>
						<div class="form-group">
							<label>CONTENT</label>
							<textarea name="content" class="form-control" rows="3" placeholder="ENTER.... CONTENT"></textarea>
						</div>
						<div class="form-group">
							<label>WRITER</label>
							<input type="text" name="writer" class="form-control" placeholder="ENTER WRITER" required>
						</div>
					</div>
					<div class="box-footer">
						<!-- bottom -->
						<input type="submit" class="btn btn-warning" value="등록">
					</div>
				</form>	
			</div>
		</div>
	</div>
</section>
<jsp:include page="../include/footer.jsp"/>