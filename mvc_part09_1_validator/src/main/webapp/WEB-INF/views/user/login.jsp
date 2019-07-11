<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="container">
	<form id="loginForm" action="${path}/user/login" method="post">
		<table class="container table tabled-bordered">
			<tr>
				<th colspan=2><h1>LOGIN PAGE</h1></th>
			</tr>
			<tr>
				<td>
					아이디
				</td>
				<td>
					<input type="text" class="form-control" name="u_id" id="u_id"/>
				</td>
			</tr>
			<tr>
				<td>
					비밀번호
				</td>
				<td>
					<input type="password" class="form-control" name="u_pw" id="u_pw"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<label>
						<input type="checkbox" name="rememberme" id="rememberme"/>
						로그인정보 저장
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="form-control btn btn-primary" id="loginBtn" value="로그인"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" class="form-control btn btn-primary" onclick="location.href='${path}/user/join';" value="회원가입"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<script>

	$.validator.setDefaults({
		submitHandler : function() {
			$("#loginForm").submit();
		}
	});
	
	$("#loginForm").validate({
		rules : {
			u_id : {
				required : true,
				email : true
			},
			u_pw : {
				required : true
			}
		},
		messages : {
			u_id : {
				required : "이메일(아이디)를 작성해주세요.",
				email : "올바른 이메일 형식이 아닙니다."
			},
			u_pw : {
				required : "비밀번호를 확인해주세요."
			}
		}
	});
</script>
</body>
</html>