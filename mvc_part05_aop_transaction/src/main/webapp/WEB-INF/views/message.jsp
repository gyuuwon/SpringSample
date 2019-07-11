<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="text" id="targetid" placeholder="targetid"/> <br/>
	<input type="text" id="sender" placeholder="sender"/> <br/>
	<textarea id="message" placeholder="message"></textarea><br/>
	<input type="button" id="add" value="MESSAGE 전송"/>
	<ul id="messageList">
	
	</ul>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	messageList();


	$("#add").click(function(){
		alert('click');
		$.ajax({
			type : "post",
			url : "messages/add",
			dataType : "text",
			data : {
				targetid : $("#targetid").val(),
				sender : $("#sender").val(),
				message : $("#message").val()
			},
			success : function(data){
				
				alert(data);
				$("#targetid").val("");
				$("#sender").val("");
				$("#message").val("");
			},
			error : function(res,status,error){
				alert("code : " + res.code+"\n"+"message : " + res.responseText);
			}
		});
		alert('end');
	});
	
	function readMessage(mno, uid){
		alert(mno+"  /  " + uid);
		
		/* $.ajax({
			type : "patch",
			url : "messages/read/"+mno+"/"+uid,
			headers : {
				"X-HTTP-Method-Override" : "PATCH"
			},
			dataType : "json",
			success : function(data){
				// data == MessageVO
				console.log(data.message+"/"+data.sender+"/"+data.targetid);
			}
		}); */
		
		$.ajax({
			type : "patch",
			url : "messages/read/"+mno,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PATCH"
			},
			data : JSON.stringify({
				uid : uid
			}),
			dataType : "json",
			success : function(data){
				// data == MessageVO
				console.log(data.message+"/"+data.sender+"/"+data.targetid);
			}
		});
		
		
	}	
	
	
	function messageList(){
		$.getJSON("messages/list",function(data){
			// data == List<MessageVO>
			console.log(data);
			
			var str = "";
			
			$(data).each(function(){
				var opendate = "";
				if(this.opendate > 0){
					var date = new Date(this.opendate);
					opendate = date.getFullYear()+ " 년 " +
							   (date.getMonth()+1) +" 월 " +
							   date.getDate() +" 일 " +
							   date.getHours()+ " 시" +
							   date.getMinutes()+ " 분";
				}else{
					opendate = " 미확인 ";
				}
				
				
				str += "<li onclick='readMessage("+this.mno+",\""+this.targetid+"\")'>";
				str += this.mno + "  |  ";
				str += this.targetid + "  |  ";
				str += this.sender + "  |  ";
				str += this.message + "  |  ";
				str += opendate + "  |  ";
				str += new Date(this.senddate)+"  |  ";
				str += "</li>";
			});
			
			$("#messageList").html(str);
			
		});
		
		
		
	}

</script>
</body>
</html>