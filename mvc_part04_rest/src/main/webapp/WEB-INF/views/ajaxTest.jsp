<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax Test</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<h1>Ajax Test</h1>
	<div>
		<input type="text" id="name" name="name"/><br>
		<input type="number" id="age" name="age"/><br>
		<input type="button" id="submit" value="전송"/>
		<input type="button" id="post" value="POST"/>
		<input type="button" id="put" value="PUT"/>
	</div>
	<div id="result" style="border: 1px solid black; padding: 10px; margin-top: 10px;">
	</div>
	<script>
		$("#submit").click(function(){
			var input_name = $("#name").val();
			var input_age = $("#age").val();
			
			$.ajax({
				type : "GET",
				url : "getSample",
				data : {
					name : input_name,
					age : input_age
				},
				dataType : 'JSON',
				success : function(result){ // function(result) = 변수(result)이름은 아무거나 사용가능 
					$("#result").html("이름 : "+ result.name + " / 나이 : " + result.age);
				},
				error : function(response){
					alert("error"+response)
				}
			});
		});
		
		$("#post").click(function(){
			var name = $("#name").val();
			var age = $("#age").val();
			
			alert(name+ age);
			$.ajax({
				type : "POST",
				url : "getSample",
				headers : {
					'Content-type' : 'application/json',
					'X-HTTP-Method-Override' : "POST"
				},
				dataType : "json",
				data : JSON.stringify({
					name : name,
					age : age
				}),
				success : function(data){
					console.log(data);
					
					var html ="<table border=1>";
					html += "<tr>";
					html += "<th>이름</th>";
					html += "<th>나이</th>";
					html == "</tr>";
					for(var i = 0; i < data.length; i++){
						html += "<tr>";
						html += "<td>"+data[i].name+"</td>";
						html += "<td>"+data[i].age+"</td>";
						html += "</tr>";
					}
					html +="</table>";
					$("#result").html(html);
					
				},error : function(response){
					alert("error : " + response);
				}
			});
		});
		
		$("#put").click(function(){
			
			var name = $("#name").val();
			var age = $("#age").val();
			console.log(name + age);
			$.ajax({
				type : "PUT",
				url : "getSample2",
				dataType : "json",
				headers : {
					'Content-type' : 'application/json',
					'X-HTTP-Method-Override' : "PUT"
				},
				data : JSON.stringify({
					name : name,
					age : age
				}),
				success : function(data){
					var html ="<table border=1>";
					html += "<tr>";
					html += "<th>이름</th>";
					html += "<th>나이</th>";
					html == "</tr>";
					for(var i = 0; i < data.length; i++){
						html += "<tr>";
						html += "<td>"+data[i].name+"</td>";
						html += "<td>"+data[i].age+"</td>";
						html += "</tr>";
					}
						html +="</table>";	
						$("#result").html(html);
					
					
				}
			});
		});
		
		
		
	</script>
</body>
</html>