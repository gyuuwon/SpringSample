<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<form action="/board/modify" method="post">
					<div class="box-body">
						<!-- content -->
						<div>
							<label>TITLE</label> <input type="text" name="title"
								class="form-control" value="${board.title}" />
						</div>
						<div>
							<label>CONTENT</label>
							<textarea name="content" rows="4" class="form-control">${board.content}</textarea>
						</div>
						<div>
							<label>WRITER</label> <input type="text" class="form-control" name="writer" value="${board.writer}" />
						</div>
					</div>
					<div class="box-footer">
						<!-- bottom -->
						<input type="submit" class="btn btn-warning" value="SAVE">
						<input type="submit" class="btn btn-warning" value="CANCEL">
						<input type="hidden" name="bno" value="${board.bno}"> 
						<input type="hidden" name="page" value="${page}">
					</div>
				</form>
			</div>
		</div>
	</div>
</section>
