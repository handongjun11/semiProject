<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="semi.kh.board.model.vo.*"%>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<%  Board b = (Board)request.getAttribute("board"); %>
<style>
#qna-container{
    margin-top: 97px;
    margin-left: 170px;
}
/*
#qnaBoardFrm th,td{
	border : 1px solid ;
}
#tbl-qnaboard-view{
	border-collapse: collapse;
}
*/
</style>
<title>Q&A 글 작성</title>
</head>
<body>
<script>
function validate(){
	//내용을 작성하지 않은 경우에 대한 유효성 검사하세요.
	//공백만 작성한 경우도 폼이 제출되어서는 안됨.

	var title = $("textarea[name=title]");
	var content = $("textarea[name=content]");
		
	if(title.val().trim().length  ==  0 ){
		alert("제목을 입력하세요");
		return false;
	}else{
		
	return true;
		}


	if(content.val().trim().length  ==  0 ){
		alert("내용을 입력하세요");
		return false;
	}
	return true;
}
function check(){
	
	if($("input[name='lockflag']").is(':checked') ){
		 $("input[name='lockflag']").val("Y");
		 $("input[name='password']").prop("required", true);
		 $("input[name='password']").prop("readonly", false);
		}else{
			$("input[name='lockflag']").val("N");
			$("input[name='password']").val("");
			$("input[name='password']").prop("required", false);
			$("input[name='password']").prop("readonly", true);
		}
	
}
$(function(){
	console.log($("input[name='lockflag']").val());
	
	if($("input[name='lockflag']").val() == "Y"){
		$("input[name='lockflag']").prop("checked" , true);
		$("input[name='password']").prop("required", true);
	}
	else{
		$("input[name='lockflag']").prop("checked" , false);
		$("input[name='password']").prop("required", false);
		$("input[name='password']").val("");

	}
})
function number_check(e){
		 var code = e.which?e.which:event.keyCode;
		    if(code < 48 || code > 57){
		        return false;
		    }
	}
	
</script>
<section id="qna-container">
<h2>글 수정</h2>
<form action="<%=request.getContextPath() %>/board/qnaBoardUpdateEnd" id="qnaBoardFrm"  method="post">
	<input type="hidden" name="boardNo" value="<%=b.getBoardNo()%>">
	<table id="tbl-qnaboard-view" class="table" style="width:600px;">
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">제 목</th>
		<td style="width:400px; background-color:white; vertical-align:middle;"><input type="text" name="title" class="form-control" value="<%=b.getBoardTitle()%>" required></td>
	</tr>
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">카테고리</th>
		<td style="width:400px; background-color:white; vertical-align:middle;"><input type="text" name="category" value="<%=b.getBcategory() %>" readonly style='background :none; border : none;'/></td>
	</tr>
	<%if(b.getbPid()!=null) {%>
		<tr>
			<th style="width:100px; vertical-align:middle; text-align:center;">상품아이디</th>
			<td style="width:400px; background-color:white; vertical-align:middle;"><%=b.getbPid() %></td>
		</tr>
		<%} %>
		<%if(b.getbOdrderNo()!=null) {%>
		<tr>
			<th style="width:100px; vertical-align:middle; text-align:center;">주문번호</th>
			<td style="width:400px; background-color:white; vertical-align:middle;"><%=b.getbOdrderNo() %></td>
		</tr>
		<%} %>
	
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">작성자</th>
		<td style="width:400px; background-color:white; vertical-align:middle;">
			<!--로그인한 회원명출력-->
               <%-- =memberLoggedIn.getMemberName() --%>  
               <!--  input type="hidden" 
                     name="writer"
                     value="<%-- =memberLoggedIn.getMemberId() --%> "
                     readonly /-->
               <input type="text" name="writer" value="<%=b.getbWriter() %>" readonly style='background :none; border : none;' requried>
		</td>
	</tr>
	<%if(!b.getBcategory().equals("배송문의")) {%>
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">비밀글</th>
		<td style="width:400px; background-color:white; vertical-align:middle;"><input type="checkbox" name="lockflag" value="<%=b.getbLockFlag() %>"  onclick="check();">
			<!-- 비호원일 경우 보이게 하기 -->
		     <input type="password" size='5' name="password" value="<%=b.getBoardPwd() %>" placeholder="비밀번호" onkeypress="return number_check(event)"  > (숫자 4자리 입력)
		</td>
	</tr>
	<%} %>
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">내 용</th>
		<td style="width:400px; background-color:white; vertical-align:middle;" ><textarea rows="5" cols="50" name="content" class="form-control"><%=b.getbConetent()%></textarea></td>
	</tr>
	<tr>
		<th colspan="2" style="width:100px; vertical-align:middle; text-align:center;">
			<input type="submit" class="btn btn-primary btn-lg" value="등록하기" onclick="return validate();">
		</th>
	</tr>
</table>
</form>
</section>
	
	

</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>	