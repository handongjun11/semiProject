<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="semi.kh.board.model.vo.*"%>

<%
		Board b = (Board)request.getAttribute("board");

 %>
<!DOCTYPE html>
<html style="background :  #F5F5F5;">
<head>
<meta charset="UTF-8">
<title>비밀글 비밀번호 확인</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<style>

div#updatePassword-container{
	background : #F5F5F5;

}
div#updatePassword-container table{

	margin:0 auto;
	border-spacing : 20px;
}
div#updatePassword-container table tr:last-of-type td{
	text-align : center;
}
#tbl-checkPwd{
    width: 320px;
    height: 110px;


}

</style>
<script>

 function check(){

	
	
	 var p1= $.trim($("#password").val());
		var p2 = $("#boardPwd").val()==null?" ":$("#boardPwd").val();
		

	if(p1 != p2){
		alert("비밀번호가 일치하지 않습니다.");
		return false;
	
	}else{
	  
	    alert("비밀번호 확인 완료");
	    opener.location.href= "<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>";
        window.close();
	return true;

	}
	   

 }

</script>
</head>

<body>
	<div id="updatePassword-container">
		<form action="<%=request.getContextPath()%>/board/ChkQnaBoardPwdEnd"
				name="updatePasswordFrm"
				method="post">
				<input type="hidden" name="boardNo" id="boardNo" value="<%=b.getBoardNo()%>"/>
				<table id="tbl-checkPwd" style="margin: 35px;">
					<tr>
						<th>비밀번호를 입력하세요</th>
						<td><input type="password" name="password" id="password" required/></td>
					</tr>
					
					<tr>
						<td>
						<input type="hidden" name="boardPwd" id="boardPwd" value="<%=b.getBoardPwd()%>"/>
					
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" onclick="return check();" value="확인"/>
							<input type="button"  onclick="self.close();" value="취소"/>
						</td>
					</tr>
				</table>
	</form>
	
	</div>
</body>
</html>