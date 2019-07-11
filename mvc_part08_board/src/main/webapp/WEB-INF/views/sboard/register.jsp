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
	<h1>REGISTER BOARD</h1>
<form id="registForm" action="/sboard/register" method="post">
	<input type="hidden" name="uno" value="${userInfo.uno}"/>
	<table border=1 style="width:100%;">
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" required/></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer" value="${userInfo.uname}" readonly required/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea style="width:100%;" name="content" id="content" rows=3></textarea>
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
		<input type="button" id="saveBtn" value="SAVE"/>
	</div>
</form>

<script>
	var path = "${pageContext.request.contextPath}/resources/editor/SmartEditor2Skin.html";
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame(
			oEditors,
			"content",
			path,
			"createSEditor2"
	);
	

	
	
	
	$(".fileDrop").on("dragenter dragover",function(event){
		event.preventDefault();
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
				alert(data);
				target.closest("li").remove();
			}
		});
	});
	
	$("#saveBtn").click(function(){
		
		var str = "";
		var fileList = $(".uploadList .delBtn");
		
		$(fileList).each(function(index){
			str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href")+"'/>";
		});
		
		$("#registForm").append(str);
		
		oEditors[0].exec("UPDATE_CONTENTS_FIELD",[]);
		
		$("#registForm").submit();
	});
	
	
</script>
</body>
</html>
