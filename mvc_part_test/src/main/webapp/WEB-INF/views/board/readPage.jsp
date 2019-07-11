<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<section class="content">
	<div class="row">
		<div class="col-md-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">READ BOARD</h3>
				</div>
				<div class="box-body">
					<!-- content -->
					<div class="form-group">
						<label>TITLE</label>
						<input type="text" name="title" value="${board.title}" readonly/>
					</div>
					<div>
						<label>CONTENT</label>
						<textarea rows=4 readonly>${board.content}</textarea>
					</div>
					<div>
						<label>WRITER</label>
						<input type="text" value="${board.writer}" readonly/>
					</div>
				</div>
				<div class="box-footer">
					<!-- bottom -->
					<input type="button" class="btn btn-warning" value="MODIFY"/>
					<input type="button" class="btn btn-danger" value="DELETE"/>
					<input type="button" class="btn btn-primary" value="LIST"/>
					<form id="readForm" method="post">
						<input type="hidden" name="bno" value="${board.bno}"/>
						<input type="hidden" name="page" value="${page}"/>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<script>
	// 문서가 준비되었을때
	$(function(){
		var msg = '${result}';
		
		if(msg == 'SUCCESS'){
			alert('수정처리가 완료되었습니다.');
		}
		
		if(msg == 'FAIL'){
			alert('수정처리가 정상적으로 완료되지 않았습니다.');
		}
		
		var readForm = $("#readForm");
		
		$(".btn-warning").click(function(){
			//alert('click');
			readForm.attr("action","/board/modify");
			readForm.attr("method","GET");
			readForm.submit();
		});
		
		$(".btn-danger").click(function(){
			readForm.attr("action","/board/remove").submit();
		});
		
		$(".btn-primary").click(function(){
			readForm.attr("action","/board/listPage");
			readForm.attr("method","get");
			readForm.submit();
		});
	});
</script>
