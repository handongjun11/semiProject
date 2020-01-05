<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "semi.kh.member.model.vo.*,
					semi.kh.member.model.service.*" %>
<%
	/* String memberId = request.getParameter("memberId");
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
	System.out.println("memberId@updatePassword = "+memberId);
	System.out.println("memberLoggedIn_password@updatePassword = "+memberLoggedIn.getPassword());
	Member m = new MemberService().selectOne(memberId);
	System.out.println("m_password@updatePassword = "+m.getPassword()); */
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>
div#findPassword-container{
	background:coral;
}
div#findPassword-container table{
	margin:0 auto;
	border-spacing:20px;
}
div#findPassword-container table tr:last-of-type td{
	text-align:center;
}

</style>
<script>
function authenticValidate(){
	
	var p1 = $("#phone").val().trim();
	var p2 = $("#memberId_").val().trim();
	if(p1.length ==0 || p2.length ==0){
		alert("공백은 입력할 수 없습니다.");
		return false;
	}
	return true;	
}

</script>
</head>
<body>
	<div id="findPassword-container">
		<form action="<%=request.getContextPath()%>/member/findPasswordEnd" name="findPasswordFrm" method="post">
			<table>
				<tr>
					<th>아이디</th>
					<td>
						<input type="text" name="memberId" id="memberId_" requeired/>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td>
						<input type="text" name="phone" id="phone" requeired/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="인증번호 받기" onclick="return authenticValidate();"/>
						<input type="button" onclick="self.close();" value="취소"/>
					</td>
				</tr>
				<%-- <input type="hidden" name="memberId" value="<%=memberId %>" /> --%>
			</table>
		
		</form>
	</div>
</body>
</html>