<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<%

	String writer = memberLoggedIn==null?"비회원":memberLoggedIn.getMemberId();
	String orderNo = (String)request.getAttribute("orderNo");
	String password = (String)request.getAttribute("password")==null?"":(String)request.getAttribute("password");
%>

<style>
#qna-container{
   margin-top: 97px;
   margin-left: 170px;
}

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
	}else{
		
	return true;
		}
	
	
}

function number_check(e){
	 var code = e.which?e.which:event.keyCode;
	    if(code < 48 || code > 57){
	        return false;
	    }
}
</script>
<section id="qna-container">
<h2>배송문의 작성</h2>
<form action="<%=request.getContextPath() %>/board/shipQnaBoardFormEnd" id="qnaBoardFrm"  method="post">
	<input type="hidden" name="password" value="<%=password%>">
	<table id="tbl-qnaboard-view" id="tbl-qna" class="table" style="width:600px;">
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">제 목</th>
		<td style="width:400px; background-color:white; vertical-align:middle;"><input class="form-control" type="text" name="title" required></td>
	</tr>
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">카테고리</th>
		<td style="width:400px; background-color:white; vertical-align:middle;"><input type="text" name="category" value="배송문의" readonly style='background :none; border : none;'/></td>
	</tr>
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">주문번호</th>
		<td style="width:400px; background-color:white; vertical-align:middle;"><input type="text" name="orderNo" value="<%=orderNo %>" readonly style='background :none; border : none;'/></td>
	</tr>
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">작성자</th>
		<td style="width:400px; background-color:white; vertical-align:middle;">
            <input type="text" name="writer" value="<%=writer %>" readonly style='background :none; border : none;'/>
		</td>
	</tr>
	
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">내 용</th>
		<td style="width:400px; background-color:white; vertical-align:middle;"><textarea class="form-control" rows="5" cols="50" name="content"></textarea></td>
	</tr>
	<tr>
		<th colspan="2" style="text-align:center;">
			<input type="submit" class="btn btn-primary btn-lg" value="등록하기" onclick="return validate();" >
		</th>
	</tr>
</table>
</form>
</section>
	
	

</body>
</html>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>	