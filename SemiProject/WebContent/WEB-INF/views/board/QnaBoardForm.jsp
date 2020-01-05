<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
#qna-container{
   margin-top: 97px;
   margin-left: 170px;
}
</style>
<title>Q&A 글 작성</title>

<script>
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
function number_check(e){
     var code = e.which?e.which:event.keyCode;
        if(code < 48 || code > 57){
            return false;
        }
}

$(window).on("beforeunload", function() {
   return "내용을 잃어버릴 수도 있습니다. 그래도 나가시겠습니까?";
});

$(document).ready(function() {
   // 어떤 폼의 제출 이벤트가 터지면
   $("#qnaBoardFrm").on("submit", function (e) {
       // 그 폼을 언제 나가도 괜찮도록 window.beforeunload 바인딩을 풀어주고
       $(window).off("beforeunload");

       var content = $("textarea[name='content']");
        

       if(content.val().length  ==  0 ){
           alert("내용을 입력하세요");
           return false;
       }else{
           //폼제출
       return true;
           }
       
   });
});

</script>

<section id="qna-container">
<h2>게시판 작성</h2>
<form action="<%=request.getContextPath() %>/board/qnaBoardFormEnd" id="qnaBoardFrm"  method="post">
	
	<table id="tbl-qnaboard-view" id="tbl-qna" class="table" style="width:600px;">
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">제 목</th>
		<td style="width:400px; background-color:white; vertical-align:middle;"><input type="text" name="title" class="form-control" required></td>
	</tr>
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">카테고리</th>
		<td style="width:400px; background-color:white; vertical-align:middle;"><input type="text" name="category" value="일반문의" readonly style='background :none; border : none;'/></td>
	</tr>
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">작성자</th>
		<td style="width:400px; background-color:white; vertical-align:middle;">
			<!--로그인한 회원명출력-->
               <% if(memberLoggedIn != null){%>  
               <input type="text" 
                     name="writer"
                     value="<%=memberLoggedIn.getMemberId()%>"
                     readonly style='background :none; border : none;'/>
                <%}else{ %>
               <input type="text" class="form-control" name="writer" requried>
               <%} %>
		</td>
	</tr>
	<tr>
		<th style="width:100px; vertical-align:middle; text-align:center;">비밀글</th>
		<td style="width:400px; background-color:white; vertical-align:middle;"><input type="checkbox" name="lockflag" value="N" onclick="check();">
			<!-- 비호원일 경우 보이게 하기 -->
			<%if(memberLoggedIn != null){ %>
				
			<%}else{ %>
		     <input type="password" size='5' name="password" placeholder="비밀번호" onkeypress="return number_check(event)" readonly> (숫자 4자리 입력)
		     <%} %>
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

<%@ include file="/WEB-INF/views/common/footer.jsp" %>	