<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ include file = "/WEB-INF/views/common/header.jsp" %>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<%
	Member m =(Member)request.getAttribute("member");
	String memberId_ = m.getMemberId();
	String password = m.getPassword();
	String memberName = m.getMemberName();
	int birth = m.getBirth()!=0?m.getBirth():0;
	String gender = m.getGender();
	String email = m.getEmail() !=null?m.getEmail():"";
	String address = m.getAddress() !=null?m.getAddress():"";
	String phone = m.getPhone();
	String favorite = m.getFavorite() !=null?m.getFavorite():"";
	String grade = m.getGrade();
	
	String[] favoriteArr = null;
	String[] favoriteChecked = new String[5];
	
	if(favorite != null){	
		favoriteArr = favorite.split(",");
		
		for(int i=0;i<favoriteArr.length;i++){
			switch(favoriteArr[i]){
			case "운동" :favoriteChecked[0] = "checked";break;
			case "등산" :favoriteChecked[1] = "checked";break;
			case "독서" :favoriteChecked[2] = "checked";break;
			case "게임" :favoriteChecked[3] = "checked";break;
			case "여행" :favoriteChecked[4] = "checked";break;
			}
		}
	}
	String url = (String)request.getRequestURI()+"?memberId="+memberId_;
	String u = (String)request.getRequestURI();

	String birth_st = String.valueOf(birth);
	String year = birth_st.substring(0,4);
	String month = birth_st.substring(4,6);
	String day = birth_st.substring(6,8);
	

%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>
.btn3{
	color:#9999ff;
	height:30px;
}

.btn2{
	position:absolute;
}
.table{
	width:500px;

}
.table th{
	text-align : center;
	vertical-align:middle;
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
	padding-top:5px;
	vertical-align:middle;
	text-align:middle;
}
</style>

<script>
function updateValidate(){

	return true;
}

function deleteMember(){
	var bool = confirm("정말로 탈퇴하시겠습니까?ㅠㅠ");
	if(bool){
		var frm = document.memberUpdateFrm;
		frm.action = "<%=request.getContextPath()%>/member/memberDelete";
		frm.submit();
	}
}

//비밀번호 변경 팝업 요청
function updatePassword(){
	var url = "<%=request.getContextPath() %>/member/updatePassword?memberId=<%=memberId_%>";
	var title ="updatePassword";
	var status = "left=500px,top=200px,width=400px,height=250px";
	open(url,title,status);
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
<!-- <form name="memberDeleteFrm"> <input type="hidden" name="memberId" /> </form> -->
<nav id="view">
<ul class="View-nav">
			<li><a href="<%=request.getContextPath()%>/member/memberView?memberId=<%=memberId_ %>">회원 정보 보기</a></li>
					
			<li><a href="<%=request.getContextPath()%>/member/showMyBuyList?memberId=<%=memberId_ %>">주문 내역 보기</a></li>
	
			<li><a href="<%=request.getContextPath()%>/member/showMyReviewList?memberId=<%=memberId_ %>">나의 후기 보기</a></li>
					
			<li><a href="<%=request.getContextPath()%>/member/showMyQNAList?memberId=<%=memberId_ %>">문의 내역 보기</a></li>
										
		</nav>
<section id="memberView-container">
	<h2>내 정보</h2>
	 
		
	
	<form action="<%=request.getContextPath()%>/member/memberUpdateEnd" method="post" name="memberUpdateFrm" onsubmit="return updateValidate();">
		<table class="table" style="margin-bottom: 12px;">
			<tr>
				<th style="width : 100px; text-align:center;">아이디</th>
				<td style="background-color:white; vertical-align:middle;">
					<input type="text" name="memberId" class="form-control" id="memberId_" value="<%=memberId_%>" required readonly/>	

				</td>
			</tr>
			<tr >
				<th style="width : 100px; text-align:center;" > 이름</th>
				<td style="background-color:white; vertical-align:middle;"><input type="text" class="form-control" name="memberName" id="memberName" value="<%=memberName%>" required/></td>
			</tr>
			<tr style="height : 30px; ">
				<th style="width : 100px; text-align:center;">나이</th>
				<td style="background-color:white; vertical-align:middle;">
					<select name="year" id="year" class="form-control" style="width:80px";> 
					<script>
							var year = new Date().getFullYear();
							 document.write("<option>"+<%=year%>+"</option>");
							 for(var i = year-100;i<=year;i++) {
								document.write("<option>"+i+"</option>");
							 }
					 </script>
					</select>년도 &nbsp;
					<select name="month" id="month" class="form-control" style="width:70px";>
					 <script> 
					 if( 1<= <%=Integer.parseInt(month)%> && <%=Integer.parseInt(month)%> <=9 ){
							document.write("<option>"+"0"+<%=Integer.parseInt(month)%>+"</option>");
						}else{
							document.write("<option>"+<%=Integer.parseInt(month)%>+"</option>");
						}
						for(var i =1;i<=12;i++){
							if( 1<=i && i<=9){
								document.write("<option>"+"0"+i+"</option>");
							}else {document.write("<option>"+i+"</option>");}
						}
					</script>
					</select>월 &nbsp;
					<select name="day" id="day" class="form-control" style="width:70px";>
					<script> 
					if(1<= <%=Integer.parseInt(day)%> && <%=Integer.parseInt(day)%> <=9 ){
						document.write("<option>"+"0"+<%=Integer.parseInt(day)%>+"</option>");
					}else{
						document.write("<option>"+<%=Integer.parseInt(day)%>+"</option>");
					}
						for(var i =1;i<=31;i++){
							if( 1<=i && i<=9){
								document.write("<option>"+"0"+i+"</option>");
							}else {document.write("<option>"+i+"</option>");}
						}
					</script>
					</select>일</td>
			</tr>
			<tr>
				<th style="width : 100px; text-align:center;">이메일</th>
				<td style="background-color:white; vertical-align:middle;" ><input type="email" class="form-control" name="email" id="email"  value="<%=email%>"/></td>
			</tr>
			<tr >
				<th style="width : 100px; text-align:center;">휴대폰</th>
				<td style="background-color:white; vertical-align:middle;"><input type="tel" class="form-control" name="phone" id="phone" value="<%=phone%>" required/></td>
			</tr>
			<tr>
				<th style="width : 100px; text-align:center;">주소</th>
				<td style="background-color:white; vertical-align:middle;">
					<input type="text" class="form-control" style="width:300px; display:inline-block;" name="address" id="address" value="<%=address%>"/>
					<input type="button" id="addrFinder" class="btn2 btn-link" value="검색" onclick="execDaumPostcode();" />
				</td>
			</tr>
			<tr style="height : 30px; ">
				<th style="width : 100px; text-align:center;">성별</th>
				<td style="background-color:white; vertical-align:middle;">
					<label for="gender0" class="radio-inline">남</label>
					<input type="radio" name="gender"  id="gender0" value="M"  <%="M".equals(gender)?"checked":"" %>/>
					<label for="gender1" class="radio-inline">여</label>
					<input type="radio" name="gender" id="gender1" value="F" <%="F".equals(gender)?"checked":""%>/>
					
				</td>
			</tr>
			<tr style="height : 30px; ">
				<th style="width :100px; text-align:center;">등급</th>
				<td style="background-color:white; vertical-align:middle;">
					<input type="text" name="grade" class="form-control" id="grade" value="<%=grade %>" required readonly/>	
				</td>
			</tr>
	
			<tr >
				<th style="width : 100px; height : 50px; text-align:center;">선호하는<br />제품군</th>
				<td style="background-color:white; vertical-align:middle;">
					<label for="hobby0" class="checkbox-inline">스마트폰</label>
					<input type="checkbox" name="favorite" id="favorite0" value="스마트폰" <%=favorite.contains("스마트폰")?"checked":"" %>/>
					<label for="hobby1" class="checkbox-inline">태블릿</label> 	
					<input type="checkbox" name="favorite" id="favorite1" value=태블릿" <%=favorite.contains("태블릿")?"checked":"" %>/>
					<label for="hobby2" class="checkbox-inline">웨어러블</label>
					<input type="checkbox" name="favorite" id="favorite2" value="웨어러블" <%=favorite.contains("웨어러블")?"checked":"" %>/>
					<br />
					<label for="hobby3" class="checkbox-inline">노트북</label>
					<input type="checkbox" name="favorite" id="favorite3" value="노트북" <%=favorite.contains("노트북")?"checked":"" %>/>
					<label for="hobby4" class="checkbox-inline">데스크탑</label>
					<input type="checkbox" name="favorite" id="favorite4" value="데스크탑" <%=favorite.contains("데스크탑")?"checked":"" %>/>
					<label for="hobby4" class="checkbox-inline">모니터</label>
					<input type="checkbox" name="favorite" id="favorite5" value="모니터" <%=favorite.contains("모니터")?"checked":"" %>/>
				</td>
			</tr>
		</table>
		<input type="submit" class="btn3 btn-link"  value="회원정보변경" />
		<input type="button" class="btn3 btn-link" value="비밀번호수정" onclick="updatePassword();"/>
		<input type="button" class="btn3 btn-link"  value="탈퇴" onclick="deleteMember();"/>
	
	</form>

</section>

<%@ include file = "/WEB-INF/views/common/footer.jsp" %>