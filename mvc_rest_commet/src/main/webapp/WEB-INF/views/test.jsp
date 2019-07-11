<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Comment Test</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style type="text/css">
	ul li{
		list-style: none;
	}
	
	.pagination{
		width: 100%;
		margin-bottom: 50px;
	}
	
	.pagination li{
		float: left;
		padding: 3px;
		border: 1px solid skyblue;
		margin: 3px;
	}
	
	.pagination li a{
		margin: 3px;
		text-decoration: none;
		color: black;
	}
	
	.active{
		background: skyblue;
	}
	
	.pagination > .active > a{
		color: white;
	}
</style>
</head>
<body>
	<div id="modDiv" style="display: none;">
		<div class="mod-title"></div>
		<div>
			<input type="text" id="commentText">
		</div>
		<div>
			<button id="commentModBtn">MODIFY</button>
			<button id="commentDelBtn">DELETE</button>
			<button id="closeBtn">CLOSE</button>
		</div>
	</div>
	<h1>AJAX-REST TEST PAGE</h1>
	<div>
		<div>
			commentAuth <input type="text" id="newCommentAuth">
		</div>
		<div>
			commentText <input type="text" id="newCommentText">
		</div>
		<button id="commentAddBtn">ADD COMMENT</button>
		<button id="commentListBtn">List ALL</button>
	</div>
	<ul id="comments"></ul>
	<ul id="pagination" class="pagination"></ul>
	<script>
		var bno = 1;
		var commentPage = 1;
		
		$("#commentAddBtn").click(function(){
			var commentText = $("#newCommentText").val();
			var commentAuth = $("#newCommentAuth").val();
			alert(commentText +" / "+commentAuth);
			$.ajax({
				type : "post",
				url : "comments",
				headers : {
					"Content-type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : "text",
				data : JSON.stringify({
					bno : bno,
					commentText : commentText,
					commentAuth : commentAuth
				}),
				success : function(data){
					getPageList(1);
				},
				error : function(res,error){
					alert(error);
				}
			});
		});
		
		function getAllList(){
			$.getJSON("comments/all/"+bno,function(data){
				console.log(data);
				var str = "";
				$(data).each(function(){
					str += "<li data-cno='"+this.cno+"' data-text='"+this.commentText+"' class='commentLi'>"+this.cno+" : "+this.commentText+"<button>MODIFY</button></li>"
				});
				$("#comments").html(str);
			});
		}
		
		$("#commentListBtn").click(function(){
			getAllList();
		});
		
		$("#comments").on("click",".commentLi button",function(){
			var comment = $(this).parent();
			var cno = comment.attr("data-cno");
			var commentText = comment.attr("data-text");
			$(".mod-title").html(cno);
			$("#commentText").val(commentText);
			$("#modDiv").show("slow");
		});
		
		$("#closeBtn").click(function(){
			$("#modDiv").hide("slow");
		});
		
		$("#commentModBtn").click(function(){
			var cno = $(".mod-title").html();
			var commentText = $("#commentText").val();
			console.log(cno + " / " + commentText);
			
			$.ajax({
				type : "patch",
				url : "comments/"+cno,
				headers : {
					"Content-type" : "application/json",
					"X-HTTP-Method-Override" : "PATCH"
				},
				data : JSON.stringify({commentText : commentText}),
				dateType : "text",
				success : function(data){
					if(date == "SUCCESS"){
						alert("수정이 완료되었습니다.");
						$("#modDiv").hide("slow");
						getPageList(commentPage);
					}
				}
			});
		});
		
		$("#commentDelBtn").click(function(){
			var cno = $(".mod-title").html();
			
			$.ajax({
				type : "delete",
				url : "comments/"+cno,
				headers : {
					"X-HTTP-Method-Override" : "DELETE"
				},
				dataType : "text",
				success : function(data){
					alert(data);
					$("#modDiv").hide("slow");
					getPageList(commentPage);
				}
			});
		});
		
		getPageList(1);
		
		function getPageList(page){
			commentPage = page;
			$.getJSON("comment/"+bno+"/")
		}
		
		
	</script>
</body>
</html>