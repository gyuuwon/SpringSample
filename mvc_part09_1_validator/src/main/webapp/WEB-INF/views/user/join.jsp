<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<style>
	.maxWidth{
		max-width:500px;
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
					<div class="result"></div>					
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" class="form-control" name="u_pw" id="u_pw"/>
					<div class="result"></div>		
				</td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td>
					<input type="password" class="form-control" name="u_repw" id="u_repw"/>
					<div class="result"></div>
				</td>
			</tr>
			<tr>
				<td>이름(2~6자이내)</td>
				<td>
					<input type="text" name="u_name" class="form-control" id="u_name"/>
					<div class="result"></div>
				</td>
			</tr>
			<tr>
				<td>전화번호(-제외 숫자만)</td>
				<td>
					<input type="text" name="u_phone" class="form-control" id="u_phone"/>
					<div class="result"></div>
				</td>
			</tr>
			<tr>
				<td>생년월일(ex-19820607)</td>
				<td>
					<input type="text" name="u_birth" class="form-control" id="u_birth"/>
					<div class="result"></div>
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
					<input type="button" id="joinBtn" class="form-control btn btn-primary" value="회원가입" />
				</td>
			</tr>
		</table>
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
	
	$(function(){
		$("#u_id").focus();
		
		var boolUId = false;
		var boolUPassword = false;
		var boolUPasswordCheck = false;
		var boolUPhone = false;
		var boolUName = false;
		var boolUBirth = false;
		var boolUAddress = false;
		var boolUInfo = false;
		
		var regexEmail =/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;       // 이메일
		var regexPass = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;			// 영문,숫자를 혼합하여 6~20자 이내
		var regexMobile = /^[0-9]{2,3}?[0-9]{3,4}?[0-9]{4}$/;					// mobile -표시 없이 숫자만
		var regexBirth = /^[0-9]{4}[0-9]{2}[0-9]{2}$/;							// 생년월일  19820607
		var regexName = /^[\uac00-\ud7a3]{2,6}$/;								// 한글 영문 포함 2~6자 이내
		// 유효성 검사
		function checkRegex(elP,valP,regexP,messageP,ajaxP){
			if(regexP.test(valP) === false){
				showErrorMessage(elP,messageP,false);
				return false;
			}else if(regexP.test(valP) !== false && ajaxP === null){
				showErrorMessage(elP,'사용가능 합니다.',true);
				return true;
			} else{
				if(ajaxP !== null){
					ajaxP(elP);
				}
			}
		}
		// 메시지를 보여줄 요소, 보여줄 error message, 성공/실패 여부
		function showErrorMessage(elP,messageP,isChecked){
			// true 
			//<span style="margin-left:5px;font-size:12px;color:green;>사용가능 합니다.</span>
			// false
			//<span style="margin-left:5px;font-size:12px;color:red;>messageP</span>
			var html = '<span style="margin-left:5px;font-size:12px;';
			html += isChecked ? 'color:green;' : 'color:red;';
			html += '">';
			html += isChecked ? '사용 가능합니다.' : messageP;
			html += '</span>';
			
			$(elP).html(html);
		}
		
		$("#u_id").on("input",function(){
			var tempVal = $(this).val();
			console.log(tempVal);
			var elP = $(this).parent().find(".result");
			//elP.html(tempVal);
			var message = "올바른 이메일 형식이 아닙니다.";
			boolUId = checkRegex(elP,tempVal,regexEmail,message,checkUidAjax); 
		});
		
		function checkUidAjax(elP){
			$.ajax({
				type : "post",
				url : '${path}/user/uIdCheck',
				dataType : 'json',
				data : {
					u_id : $("#u_id").val()
				},
				success : function(data){
					console.log("isChecked : " + data);
					if(data){
						showErrorMessage(elP,"사용가능합니다.",true);
						boolUId = true;
					}else{
						showErrorMessage(elP,"존재하는 아이디 입니다.",false);
						boolUId = false;
					}
				}
			});
		}
		
		$("#u_pw").on("input",function(){
			var tempVal = $(this).val();
			var elP = $(this).parent().find(".result");
			var message = "영문/숫자 조합하여 6~20자 이내 작성";
			boolUPassword = checkRegex(elP,tempVal,regexPass,message,null);
		});
		
		$("#u_repw").on("input",function(){
			var tempVal = $(this).val();
			var originVal = $("#u_pw").val();
			var elP = $(this).parent().find(".result");
			var message = "";
			
			if(boolUPassword){
				if(tempVal == originVal){
					boolUPasswordCheck = true;
					message = "비밀번호가 일치 합니다.";
				}else{
					boolUPasswordCheck = false;
					message = "비밀번호가 일치 하지 않습니다.";
				}
			}else{
				message ="비밀번호를 확인해주세요";
				boolUPasswordCheck = false;
			}
			showErrorMessage(elP,message,boolUPasswordCheck);
		});
		
		$("#u_name").on("input",function(){
			var tempVal = $(this).val();
			var elP = $(this).parent().find(".result");
			var message = "한글 2~6자 이내 작성";
			boolUName = checkRegex(elP,tempVal,regexName,message,null);
		});
		
		$("#u_phone").on("input",function(){
			var tempVal = $(this).val();
			var elP = $(this).parent().find(".result");
			var message = "-제외 숫자만 작성";
			boolUPhone = checkRegex(elP,tempVal,regexMobile,message,null);
		});
		
		$("#u_birth").on("input",function(){
			var tempVal = $(this).val();
			var elP = $(this).parent().find(".result");
			var message = "숫자만 입력 ex)19990101";
			boolUBirth = checkRegex(elP,tempVal,regexBirth,message,null);
		});
	
		function checkAddr(){
			if(
				($("#u_addr_post").val() === null || $("#u_addr_post").val() === '') 
				&&
				($("#u_addr").val() === null || $("#u_addr").val() === '')
				&& 
				($("#u_addr_detail").val() === null || $("#u_addr_detail").val() === '')
			){
				boolUAddress = false;	
			}else{
				boolUAddress = true;
			}
			
			console.log(boolUAddress);
		}
		
		$("#u_info").on("change",function(){
			var isChecked = $(this).is(":checked");
			console.log("isCheck u_info : " + isChecked);
			if(isChecked){
				boolUInfo = true;
			}else{
				boolUInfo = false;
			}
		});
		
		$("#joinBtn").click(function(){
			checkAddr();	
			
			 if(!boolUId){
				alert("아이디를 확인해 주세요.");
				$("#u_id").focus();
			}else if(!boolUPassword){
				alert("비밀번호를 확인해주세요.");
				$("#u_pw").focus();
			}else if(!boolUPasswordCheck){
				alert("비밀번호가 일치하지 않습니다.");
				$("#u_repw").focus();
			}else if(!boolUName){
				alert("이름 정보를 확인해주세요.");
				$("#u_name").focus();
			}else if(!boolUPhone){
				alert("전화번호를 확인해주세요.");
				$("#u_phone").focus();
			}else if(!boolUBirth){
				alert("생년월일을 확인해주세요.");
				$("#u_birth").focus();
			}else if(!boolUAddress){
				console.log(boolUAddress);
				alert("주소를 기입해주세요.");
				$("#u_addr_post").focus();
			}else if(!boolUInfo){
				alert("개인정보이용약관에 동의해주세요.");
			}else{
				$("#joinForm").submit();
			}
		});
	});
	
	
	
	
	
	
	
</script>
</body>
</html>