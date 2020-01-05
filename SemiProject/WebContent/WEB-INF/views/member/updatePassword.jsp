<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "semi.kh.member.model.vo.*,
					semi.kh.member.model.service.*" %>
<%
	String memberId = request.getParameter("memberId");
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
	Member m = new MemberService().selectOne(memberId);

	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>
div#updatePassword-container{
	background:white;
}
div#updatePassword-container table{
	margin:0 auto;
	border-spacing:20px;
}
div#updatePassword-container table tr:last-of-type td{
	text-align:center;
}

</style>
<script>
function passwordValidate(){
	
	var p1 = $("#password_new").val();
	var p2 = $("#password_check").val();
	if(p1 != p2){
		alert("패스워드가 일치하지 않습니다.");
		$("#password_new").focus();
		return false;
	}
	return true;
	
}

</script>
</head>
<body>
	<div id="updatePassword-container">
		<form action="<%=request.getContextPath()%>/member/updatePasswordEnd" name="updatePasswordFrm" method="post">
			<table>
				<tr>
					<th>아이디</th> <!-- 기존 비밀번호 확인 : 틀렸다면 변경 불가-->
					<td>
						<input type="text" name="memberId" id="memberId_" requeired/>
						
					</td>
				</tr>
				<tr>
					<th>현재비밀번호</th> <!-- 기존 비밀번호 확인 : 틀렸다면 변경 불가-->
					<td>
						<input type="password" name="password" id="password" requeired/>
						
					</td>
				</tr>
				<tr>
					<th>새 비밀번호</th> <!-- 새 비밀번호 -->
					<td><input type="password" name="password_new" id="password_new" requeired/></td>
				</tr>
				<tr>
					<th>새 비밀번호 확인</th>
					<td><input type="password" id="password_check" requeired /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="변경" onclick="return passwordValidate();"/>
						<input type="button" onclick="self.close();" value="취소"/>
					</td>
				</tr>
				<%-- <input type="hidden" name="memberId" value="<%=memberId %>" /> --%>
			</table>
		
		</form>
	</div>
</body>
</html>