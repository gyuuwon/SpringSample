<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.fileDrop{
		width:100%;
		height:150px;
		border:1px solid gray;
		background-color:lightslategray;
		margin:auto;
	}
	
	.uploadList{
		width:100%;
	}
	
	.uploadList li{
		float:left;
		padding:20px;
	}
	
	ul li{
		list-style:none;
	}
	
	.clear{
		clear:both;
	}
	
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/upload.js"></script>
<script src="${pageContext.request.contextPath}/resources/editor/js/service/HuskyEZCreator.js"></script>
</head>
<body>
	<h1>MODIFY BOARD</h1>
<form id="modifyForm" action="/sboard/modifyPage" method="post">
	<table border=1 style="width:100%;">
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" value="${boardVO.title}" required/></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer" value="${userInfo.uname}" readonly required/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea style="width:100%;" name="content" id="content" rows=3>${boardVO.content}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<label>FILE DROP HERE</label>
				<div class="fileDrop"></div>
			</td>
		</tr>
	</table>
	<div>
		<ul class="uploadList">
			
		</ul>
	</div>
	<hr class="clear"/>
	<div>
		<input type="hidden" name="bno" value="${boardVO.bno}"/>
		<input type="button" id="saveBtn" value="MODIFY"/>
		<input type="button" id="cancelBtn" value="CANCEL"/>
	</div>
</form>

<script>
	var bno = ${boardVO.bno};
	
	$.getJSON("/sboard/getAttach/"+bno,function(list){
		$(list).each(function(){
			// this == fullName
			var fileInfo = getFileInfo(this);
			var html = "<li>";
				html +=  "<img src='"+fileInfo.imgSrc+"' alt='첨부파일'/>";
				html +=  "<div>";
				html +=  "<a href='"+fileInfo.getLink+"'>"+fileInfo.fileName+"</a>";
				html +=  "</div>";
				html +=  "<div>";
				html +=  "<a href='"+fileInfo.fullName+"' class='delBtn' >X</a>";
				html +=  "</div>";
				html +=  "</li>";
			$(".uploadList").append(html);
		});	
	});

	// 첨부파일 삭제
	$(".uploadList").on("click",".delBtn",function(event){
		event.preventDefault();
	});




	/*
		첨부파일 업로드 & 삭제
	*/

	$(".fileDrop").on("dragenter dragover",function(event){
		event.preventDefault();
		var fileLink = $(this).attr("href");
		
		$.ajax({
			type : "delete",
			url : "/deleteFile",
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			data : JSON.stringify ({
				fileName : fileLink
			}),
			dataType : "text",
			success : function(result){
				alert(result);
				$(this).closest("li").remove();
			}
		});
	});
	
	$(".fileDrop").on("drop",function(event){
		event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;
		
		var maxSize = 10485760;
		
		var formData = new FormData();
		
		for(var i=0; i<files.length;i++){
			if(files[i].size > maxSize){
				alert("업로드 할 수 없는 파일이 포함되어 있습니다."+files[i].size);
				return;
			}
			console.log(files[i]);
			formData.append("file",files[i]);
		}
		
		$.ajax({
			type : "POST",
			data : formData,
			url : "/uploadFile",
			dataType : "json",
			processData : false,
			contentType : false,
			success : function(data){
				console.log(data.length);
				for(var i =0; i< data.length; i++){
					console.log(data[i]);
					var fileInfo = getFileInfo(data[i]);
					console.log(fileInfo.fileName);
					console.log(fileInfo.imgSrc);
					console.log(fileInfo.fullName);
					console.log(fileInfo.getLink);
					
					var html = "<li>";
						html += "<img src='"+fileInfo.imgSrc+"' alt='attachment'/>";
						html += "<div>";
						html += "<a href='"+fileInfo.getLink+"' target='_blank'>";
						html += fileInfo.fileName;
						html += "</a>";
						html += "</div>";
						html += "<div>";
						html += "<a href='"+fileInfo.fullName+"' class='delBtn'>X</a>";
						html += "</div>";
						html += "</li>";
					$(".uploadList").append(html);
				}
			}
		});
	});	
	
	$(".uploadList").on("click",".delBtn",function(event){
		event.preventDefault();
		var target = $(this);
		
		$.ajax({
			type : "DELETE",
			url : "/deleteFile",
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			data : JSON.stringify({ fileName : target.attr("href")}),
			dataType : "text",
			success : function(data){
				// 가장 근접한 1개의 부모 요소(li type)를 반환 
				console.log(data);
				target.closest("li").remove();
			}
		});
	});



	/* 수정 등록 */
	var path = "${pageContext.request.contextPath}/resources/editor/SmartEditor2Skin.html";
	
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame(
			oEditors,
			"content",
			path,
			"createSEditor2"
	);
	
	$("#saveBtn").click(function(){
		
		var str = "";
		var fileList = $(".uploadList .delBtn");
		
		$(fileList).each(function(index){
			str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href")+"'/>";
		});
		
		$("#modifyForm").append(str);
		
		oEditors[0].exec("UPDATE_CONTENTS_FIELD",[]);
		
		$("#modifyForm").submit();
	});
	
	$("#cancelBtn").click(function(){
		$("#modifyForm").attr("action","/sboard/read");
		$("#modifyForm").attr("method","get");
		$("#modifyForm").submit();
	});
</script>
</body>
</html>












