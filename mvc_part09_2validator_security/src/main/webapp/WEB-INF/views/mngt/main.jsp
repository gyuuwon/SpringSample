<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="container">
	<table class="container table tabled-bordered">
		<tr>
			<th>회원번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>권한</th>
			<th>회원가입일</th>
			<th>최종방문일</th>
			<th>탈퇴여부</th>
			<th>권한선택</th>
		</tr>
		
		<c:forEach var="members" items="${memberList}">
			<tr>
				<td>${members.u_no}</td>
				<td id="userid">${members.u_id}</td>
				<td>${members.u_name}</td>
				<td id="memberAuth">
					<c:forEach var="memberAuth" items="${members.authList}">
						<c:if test="${memberAuth.u_auth eq 'ROLE_USER'}">
							일반 사용자
						</c:if>
						<c:if test="${memberAuth.u_auth eq 'ROLE_MEMBERSHIP'}">
							매니저
						</c:if>
						<c:if test="${memberAuth.u_auth eq 'ROLE_MASTER'}">
							관리자
						</c:if>
						&nbsp;
					</c:forEach>
				</td>
				<td>
					<f:formatDate pattern="yyyy-MM-dd HH:mm" value="${members.u_date}"/>
				</td>
				<td>
					<f:formatDate pattern="yyyy-MM-dd HH:mm" value="${members.u_visit_date}"/>
				</td>
				<td>
					<select class="deleteMember">
						<option value="n" <c:out value="${members.u_withdraw == 'n' ? 'selected' : ''}"/>>
						활성화
						</option>
						<option value="y" <c:out value="${members.u_withdraw == 'y' ? 'selected' : ''}"/>>
						비활성화
						</option>
					</select>
					<input type="button" class="deleteBtn" value="DELETEYN"/>
				</td>
				<td>
					<select class="changeAuth">
						<option disabled selected>권한선택</option>
						<option value="ROLE_USER">일반사용자</option>
						<option value="ROLE_MEMBERSHIP">매니저</option>
						<option value="ROLE_MASTER">관리자</option>
					</select>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
	<script>
		$(".changeAuth").on("change",function(){
			var parentTr = $(this).parent().parent();
			var uid = parentTr.find("#userid").text();
			var changeAuthValue = $(this).val();
			
			console.log(uid+" // " + changeAuthValue);
			
			$.post("/mngt/user/changeAuth",
					{	u_id:uid ,
						u_auth:changeAuthValue,
						'${_csrf.parameterName}' : '${_csrf.token}'
					},
					function(data){
						console.log(data);
						
						var str = "";
						$(data).each(function(){
							if(this.u_auth == 'ROLE_USER'){
								str+=" 일반사용자 &nbsp;";
							}else if(this.u_auth == 'ROLE_MEMBERSHIP'){
								str+=" 매니저 &nbsp;";
							}else if(this.u_auth == 'ROLE_MASTER'){
								str+=" 관리자 &nbsp;";
							}			
						});
						parentTr.find("#memberAuth").html(str);						
					});
			
		});	
		
		
		$(".deleteBtn").on("click",function(){
			// u_delete == y(탈퇴,비활성화) n(가입,활성화)
			var u_delete = $(this).parent().find(".deleteMember").val();
			alert(u_delete);
			var parentTr = $(this).parent().parent();
			var u_id = parentTr.find("#userid").text();
			$.post(
				"/mngt/user/delete",
				{	
					u_id : u_id,
					u_withdraw : u_delete,
					'${_csrf.parameterName}' : '${_csrf.token}'
				},
				function(data){
				console.log(data);
			});
			
		});
	</script>
</body>
</html>






