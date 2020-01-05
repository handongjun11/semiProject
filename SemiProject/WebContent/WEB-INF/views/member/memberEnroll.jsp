<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "/WEB-INF/views/common/header.jsp"%>
<%@ page import = "java.util.*" %>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<style>
.table{
	width:500px;

}
.table th{
	text-align : center;
	vertical-align:middle;
}
#memberId_{
	display:inline-block;
}
#idcheck{
	display:inline-block;
}
#year {
	display:inline-block;
}
#month {
	display:inline-block;
}
#day {
	display:inline-block;
}
#addrFinder{
	/* 	position:absolute; */
	/* padding-top:4px; */
	vertical-align:middle;
	text-align:middle;
}
#address{
	display:inline-block;
}
.btn3{
	color:#9999ff;
	height:30px;
}

</style>
<script>

function enrollValidate(){

	if($("#memberId_").val().trim().length < 4){
		alert("wrong Id");		
		return false;
	}
	
	var idValid = $("#idValid").val();
	if(idValid == 0){
		alert("아이디 중복검사해주세요.");
		return false;
	}
	return true;
	
};
$(function(){
	
	$("#password__").blur(function(){
		var p1 = $("#password_").val();
		var p2 = $("#password__").val();
		if(p1 != p2){
			alert("패스워드가 일치하지 않습니다.");
			$("#password_").focus();
			return false;
		}
		return true;
		
	})

});
function checkIdDuplicate(){
	var memberId = $("#memberId_").val().trim();
	if(memberId.length < 4){
		alert("아이디는 4글자 이상가능");
		return;
	}
	
	//팝업창을 target으로 폼전송
	var target = "checkIdDuplicate";
	
	//첫번째 url은 생략, form의 action값이 이를 대신한다.
	var popup = open("",target,"left=300px, top=100px, height=200px, width=300px");
	
	checkIdDuplicateFrm.memberId.value = memberId;
	//폼의 대상을 작성한 popup을 가리키게한다. 
	checkIdDuplicateFrm.target=target;
	checkIdDuplicateFrm.submit();
	
}
function execDaumPostcode(){
	new daum.Postcode({
		oncomplete:function(data){
			var fullRoadAddr = data.roadAddress;
			var extraRoadAddr = '';
			
			if(data.bname !=='' &&/[동|로|가]$/g.test(data.bname)){
				extraRoadAddr += data.bname;
			}
			if(data.buildingName !=='' &&data.apartment === 'Y'){
				extraRoadAddr += (extraRoadAddr !== ''?','+data.buildingName : data.build);
			}
			if(extraRoadAddr !== ''){
				extraRoadAddr = '(' + extraRoadAddr +')';
			}
			if(fullRoadAddr !== ''){
				fullRoadAddr += extraRoadAddr;
			}
			document.getElementById("address").value = fullRoadAddr;
		}
	}).open();
}
</script>
<form action="<%=request.getContextPath()%>/member/checkIdDuplicate" method="post" name="checkIdDuplicateFrm">
	<input type="hidden" name="memberId" />
</form>

<section id="enroll-container">
	<h2>KH전자 회원 가입</h2>
	<form action="<%=request.getContextPath()%>/member/memberEnrollEnd" method="post" name="memberEnrollFrm" onsubmit="return enrollValidate();">
		<table class="table">
			<tr>
				<th style="width : 100px; text-align:center;">아이디</th>
				<td style="background-color:white; vertical-align:middle;">
					<input type="text" class="form-control" style="width:250px" name="memberId" id="memberId_" placeholder="4글자 이상 입력 하세요" required/>	 
					<input type="button" class="btn btn-info" id="idcheck" value="중복검사" onclick="checkIdDuplicate();" />
					<input type="hidden" name="idValid" id="idValid" value="0"/>
				</td>
			</tr>
			<tr>
				<th style="width : 100px; text-align:center;">비밀번호</th>
				<td style="background-color:white; vertical-align:middle;">
					<input type="password" class="form-control" name="password" id="password_" required/>
				</td>
			</tr>
			<tr>
				<th style="width : 100px; text-align:center;">비밀번호<br/>확인</th>
				<td style="background-color:white; vertical-align:middle;">
					<input type="password" class="form-control" id="password__" required/>
				</td>
			</tr>
			<tr>
				<th style="width : 100px; text-align:center;">이름</th>
				<td style="background-color:white; vertical-align:middle;">
					<input type="text" class="form-control"  name="memberName" id="memberName" required/>
				</td>
			</tr>
			<tr>
				<th style="width : 100px; text-align:center;">생년월일</th>
				<td style="background-color:white; vertical-align:middle;">
					<select name="year" id="year"  class="form-control" style="width:80px;">
					<script> var year = new Date().getFullYear();
							 for(var i = year-100;i<=year;i++) 
							 document.write("<option>"+i+"</option>");
					</script>
					</select>년도 &nbsp;
					<select name="month" id="month"  class="form-control" style="width:70px;"> 
					<script> for(var i =1;i<=12;i++) {
								if( 1<=i && i<=9){
									document.write("<option>"+"0"+i+"</option>");
								}else {document.write("<option>"+i+"</option>");}
							}
					</script>
					</select>월 &nbsp;
					<select name="day" id="day"  class="form-control" style="width:70px;"> 
					<script> for(var i =1;i<=31;i++){
							if( 1<=i && i<=9){
							document.write("<option>"+"0"+i+"</option>");
						}else {document.write("<option>"+i+"</option>");}
					}
						
					</script>
					</select>일
				</td>
				
			</tr>
			<tr>
				<th style="width : 100px; text-align:center;">이메일</th>
				<td style="background-color:white; vertical-align:middle;">
					<input type="email" name="email" class="form-control" id="email" placeholder="abc@xyz.com"/>
				</td>
			</tr>
			<tr>
				<th style="width : 100px; text-align:center;">휴대폰</th>
				<td style="background-color:white; vertical-align:middle;">
					<input type="tel" class="form-control" name="phone" id="phone" placeholder="(-없이)01012345678" required/>
				</td>
			</tr>
			<tr>
				<th style="width : 100px; text-align:center;">주소</th>
				<td style="background-color:white; vertical-align:middle;">
					<input type="text" class="form-control" name="address" id="address" style="width:300px" required />
					<input type="button" id="addrFinder" class="btn2 btn-link" value="검색" onclick="execDaumPostcode();" />
				</td>
			</tr>
			<tr style="height : 40px; ">
				<th style="width : 100px; text-align:center;">성별</th>
				<td style="background-color:white; vertical-align:middle;">
					<label for="gender0" class="radio-inline">남</label>
					<input type="radio" name="gender" id="gender0" value="M" checked/>
					<label for="gender1" class="radio-inline">여</label>
					<input type="radio" name="gender" id="gender1" value="F"/>
				</td>
			</tr>
			<tr>
				<th style="width : 100px; text-align:center;">선호하는<br />제품군</th>
				<td style="background-color:white; vertical-align:middle;">
					<label for="hobby0" class="checkbox-inline">스마트폰</label>
					<input type="checkbox" name="favorite" id="favorite0" value="스마트폰" />
					<label for="hobby1" class="checkbox-inline">태블릿</label> 	
					<input type="checkbox" name="favorite" id="favorite1" value=태블릿"/>
					<label for="hobby2" class="checkbox-inline">웨어러블</label>
					<input type="checkbox" name="favorite" id="favorite2" value="웨어러블"/>
					<br />
					<label for="hobby3" class="checkbox-inline">노트북</label>
					<input type="checkbox" name="favorite" id="favorite3" value="노트북" />
					<label for="hobby4" class="checkbox-inline">데스크탑</label>
					<input type="checkbox" name="favorite" id="favorite4" value="데스크탑" />
					<label for="hobby4" class="checkbox-inline">모니터</label>
					<input type="checkbox" name="favorite" id="favorite5" value="모니터"/>
				</td>
			</tr>
		</table>
		<input type="submit" class="btn3 btn-link" value="회원가입" />
		<input type="reset" class="btn3 btn-link" value="초기화"/>
	
	</form>

</section>
<%@ include file = "/WEB-INF/views/common/footer.jsp"%>