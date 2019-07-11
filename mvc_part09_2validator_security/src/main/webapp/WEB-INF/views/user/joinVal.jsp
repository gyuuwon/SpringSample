<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
	.maxWidth{
		max-width:500px;
	}
	.error {
		color:red;
	}
</style>
<div class="container">
	
	<form id="joinForm" action="${path}/user/joinPost" method="post">
		<table class="container table table-bordered maxWidth">
			<tr>
				<th colspan="2" class="text-center"><h1>JOIN PAGE</h1></th>
			</tr>
			<tr>
				<td>아이디(email)</td>
				<td>
					<input type="text" class="form-control" name="u_id" id="u_id"/>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" class="form-control" name="u_pw" id="u_pw"/>
				</td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td>
					<input type="password" class="form-control" name="u_repw" id="u_repw"/>
				</td>
			</tr>
			<tr>
				<td>이름(2~6자이내)</td>
				<td>
					<input type="text" name="u_name" class="form-control" id="u_name"/>
				</td>
			</tr>
			<tr>
				<td>전화번호(-제외 숫자만)</td>
				<td>
					<input type="text" name="u_phone" class="form-control" id="u_phone"/>
				</td>
			</tr>
			<tr>
				<td>생년월일(ex-19820607)</td>
				<td>
					<input type="text" name="u_birth" class="form-control" id="u_birth"/>
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<div class="row">
						<div class="col-md-8">
							<input type="text" class="form-control" name="u_addr_post" id="u_addr_post"/>
						</div>
						<div class="col-md-4">
							<input type="button" class="form-control btn btn-default" onclick="sample6_execDaumPostcode();" value="주소찾기"/>
						</div>
					</div>
					<br/>
					<input type="text" class="form-control" name="u_addr" id="u_addr"/>
					<br/>
					<input type="text" class="form-control" name="u_addr_detail" id="u_addr_detail"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<h4>이용약관</h4>
					<textarea class="form-control" readonly>당신의 개인정보는 언제든지 회사에서 사용할수 있으며... 10원에 팔아먹을수도 있어요... 그래도 이 사이트를 이용하시겠습니까??</textarea>
					<label>
						<input type="checkbox" name="u_info" id="u_info" value="y"/>
						개인정보 이용동의					
					</label>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" id="joinBtn" class="form-control btn btn-primary" value="회원가입" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</div>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function sample6_execDaumPostcode(){
		new daum.Postcode({
			oncomplete : function(data){
				// 주소 검색 결과
				console.log(data);
				
				var fullAddr='';	// 최종 주소
				var extraAddr = ''; // 조합형 주소
				
				// 선택한 주소 타입에 따라 주소값을 가져온다
				if(data.userSelectedType === 'R'){ // 도로명 주소
					fullAddr = data.roadAddress;
				}else{
					// 지번 주소
					fullAddr = data.jibunAddress;
				}
				
				// 도로명 주소 타입 조합
				if(data.userSelectedType === 'R'){
					// 법정동명이 있을때 법정동명 추가
					if(data.bname !== ''){
						extraAddr += data.bname;
					}
					
					// 건물명이 존재 한다면..건물명 추가
					if(data.buildingName !== ''){
						extraAddr += (extraAddr !== '' ?','+data.buildingName : data.buildingName);
					}
					
					fullAddr += (extraAddr !== '' ? ' ('+extraAddr+')' : '');
				}
				// 우편번호 삽입
				$("#u_addr_post").val(data.zonecode);
				// 전체 주소 삽입
				$("#u_addr").val(fullAddr);
				// 상세주소 작성
				$("#u_addr_detail").focus();
			}
		}).open();
	}
	
	// mobile -표시 없이 숫자만
	var regexMobile = /^[0-9]{2,3}?[0-9]{3,4}?[0-9]{4}$/;					
	
	$.validator.setDefaults({
		submitHandler : function(){
			$("#joinForm").submit();
		}
	});
	
	$.validator.addMethod("regx",function(value,element,regexpr){
		return regexpr.test(value);
	});
	
	$("#joinForm").validate({
		rules : {
			u_id : {
				required : true,
				email : true,
				remote : {
					type :"post",
					url : "${path}/user/uIdCheck",
					data : {
						'${_csrf.parameterName}' : function(){
							return '${_csrf.token}';
						}
					}
				}
			},
			u_pw : {
				required : true,
				minlength : 6,
				maxlength : 20
			},
			u_repw : {
				required : true,
				minlength : 6,
				maxlength : 20,
				equalTo : "#u_pw"
			},
			u_name : {
				required : true,
				rangelength : [2,6]
			}, 
			u_phone : {
				required : true,
				regx : regexMobile
			},
			u_birth : {
				required : true
			},
			u_addr_post : {
				required : true
			},
			u_addr : {
				required : true
			},
			u_addr_detail : {
				required : true
			},
			u_info : {
				required : true
			}
		},
		messages : {
			u_id : {
				required : "이메일(아이디)를 작성해주세요",
				email : "올바른 이메일 형식이 아닙니다.",
				remote : "이미 존재하는 ID입니다."
			},
			u_pw : {
				required : "비밀번호를 확인해주세요",
				minlength : "비밀번호는 최소 6자리 이상입니다.",
				maxlength : "비밀번호는 최대 20자리만 가능합니다."
			},
			u_repw : {
				required : "비밀번호 확인을 작성해주세요",
				minlength : "비밀번호는 최소 6자리 이상입니다.",
				maxlength : "비밀번호는 최대 20자리만 가능합니다.",
				equalTo : "비밀번호가 일치 하지 않습니다."
			},
			u_name : {
				required : "이름을 확인해주세요",
				rangelength : $.validator.format("문자 길이가 {0} 에서 {1} 사이의 값을 입력하세요")
			}, 
			u_phone : {
				required : "전화번호를 입력해주세요",
				regx : "올바른 전화번호 형식이 아닙니다."
			},
			u_birth : {
				required : "생년월일을 입력해주세요"
			},
			u_addr_post : {
				required : "우편번호를 확인해주세요"
			},
			u_addr : {
				required : "주소를 확인해 주세요"
			},
			u_addr_detail : {
				required : "상세주소를 입력해 주세요"
			},
			u_info : {
				required : "개인정보이용동의를 확인해주세요"
			}
		}
	});
	
	$(function(){
		$("#u_birth").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : "yymmdd",
			dayNames : ['월요일','화요일','수요일','목요일','금요일','토요일','일요일'],
			dayNamesMin : ['월','화','수','목','금','토','일'],
			monthNamesShort : ['1','2','3','4','5','6','7','8','9','10','11','12'],
			monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] 	
		});
	});
	
</script>
</body>
</html>