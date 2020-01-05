<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "semi.kh.board.model.vo.* "%>
<%@ page import = "java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<%
	Board b = (Board)request.getAttribute("b");
	List<BoardComment> commentList = (List<BoardComment>)request.getAttribute("commentList");	
%>
<style>
#board-container{
    margin-top: 97px;
    margin-left: 170px;
}
/*
#tbl-qnaboard1-view th,td{
	border : 1px solid ;
}
#tbl-qnaboard1-view th{
	width : 100px;
	height : 40px;
}
#tbl-qnaboard1-view td{
	width : 300px;
}
#tbl-qnaboard1-view{
	border-collapse: collapse;
}
#tbl-qnaboard1-view tr:nth-of-type(5){
	height: 150px;
}
*/

/*댓글 테이블*/
#btn-insert, #boardCommentContent{
	display:inline-block;
}

#btn-insert{
	margin-left:7px;
	margin-bottom:60px;
}


table#tbl-comment{
	width : 600px;
	margin-left:10px;
	border-collapse: collapse;
}
table#tbl-comment tr td{
	width:580px;
	padding:5px;
	text-align : left;
	line-height: 120%;
}
/*
table#tbl-comment tr td:first-of-type{
	padding : 5px 5px 5px 50px;
	    border: none;
   border-bottom: 1px solid;
	
}
table#tbl-comment tr td:last-of-type{
	text-align : right;
	width:100px;
	    border: none;
    border-bottom: 1px solid;
}
*/
table#tbl-comment button.btn-reply{
	display : none;
}
table#tbl-comment tr{
	display: inline-block;
}
table#tbl-comment tr:hover button.btn-reply{
	display : inline;
}
table#tbl-comment sub.comment-writer{
	color: navy;
	font-size:14px;
	
}
table#tbl-comment sub.comment-date{
	color: tomato;
	font-size:10px;
	
}





</style>
<script>
function updateBoard(){
	location.href="<%=request.getContextPath()%>/board/QnaboardUpdate?boardNo=<%=b.getBoardNo()%>";
}
function deleteBoard(){
	if(!confirm("이 게시글을 정말 삭제하시겠습니까?")){
		return;
	}
	$("[name=boardDelFrm]").submit();
}
</script>
</head>
<body>
<section id="board-container">
	<h2>게시판</h2>
	<table id="tbl-qnaboard1-view" class="table" style="width:600px;">
		
		<tr>
			<th style="width:100px; vertical-align:middle; text-align:center;">제 목</th>
			<td style="width:400px; background-color:white; vertical-align:middle;"><%=b.getBoardTitle() %></td>
		</tr>
		<tr>
			<th style="width:100px; vertical-align:middle; text-align:center;">카테고리</th>
			<td style="width:400px; background-color:white; vertical-align:middle;"><%=b.getBcategory() %></td>
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
			<td style="width:400px; background-color:white; vertical-align:middle;"><%=b.getbWriter() %></td>
		</tr>
		
		<%if(!b.getBcategory().equals("배송문의")) {%>
		<tr>
			<th style="width:100px; vertical-align:middle; text-align:center;">비밀글</th>
         <td style="width:400px; background-color:white; vertical-align:middle;">&nbsp;&nbsp;
         	<%if(b.getbLockFlag().equals("N")){ %>
            <input type="checkbox" name="lockflag" value="N" onclick="return false;">
            <% }else{%>
            <input type="checkbox" name="lockflag" value="Y" checked onclick="return false;">
			<!-- 비회원일때 보이기  -->            
            <input type="password" size='5' name="password" value="<%=b.getBoardPwd()%>" readonly> 
            <% }%>
         </td>
		</tr>
		<%} %>
		
		
		<tr>
			<th style="width:100px; vertical-align:middle; text-align:center;">내용</th>
			<td style="width:400px; background-color:white; vertical-align:middle;"><%=b.getbConetent()%></td>
		</tr>
		<!-- 글작성자/관리자인경우에만 표시되어야함. -->
		<% if( memberLoggedIn != null 
		&&( "admin".equals(memberLoggedIn.getMemberId()) 
				||  b.getbWriter().equals(memberLoggedIn.getMemberId()))){ %>
				<!-- &&와 || 를 잘 알자 ! true || false => 뒤에를 검사를 안한다! -->

		<tr>
			<th colspan="2" style="text-align:center;">
				<input type="button" class="btn btn-primary" value="수정하기" onclick="updateBoard();" />
				&nbsp;&nbsp;&nbsp;
				<input type="button" class="btn btn-danger" value="삭제하기" onclick="deleteBoard();"  />
			</th>
			
		</tr>

	<% }else{ %>
	
	
	<%} %>
	</table>
	
	<hr style="margin-top : 30px;"/>
	<!-- 댓글 시작-->
	<!-- admin일때만 보이도록하기 -->
	<div id="comment-container">
		
		
		<table id="tbl-comment">
		<% for (BoardComment bc : commentList) {
	
		%>
			<tr class="level1">
				<td style="width:530px;">
					<sub class="comment-writer">
						<%=bc.getcWriter() %>
					</sub>
					<sub class="comment-date">
						<%=bc.getcDate() %>
					</sub>
					<br/><br />
					<%=bc.getcContent() %>
				</td>
				<td style="width:70px; text-align:right;">
					<form action="<%=request.getContextPath()%>/board/boardCommentDelete">
						<input type="hidden" name="boardNo" value="<%=b.getBoardNo() %>"/>
						<input type="hidden" name="boardCommentLevel" value="1"/>
						<input type="hidden" name="commentNo" value="<%=bc.getCommentNo() %>" />
						<%if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())){ %>
						<button type="submit" class="btn-delete btn btn-default" onclick="return validate();">삭제</button>
						<%} %>
					</form>
					<%} %>
				</td>
			</tr>
		
				
			
		</table>
		<div class="comment-editor">
			<form action="<%=request.getContextPath()%>/board/boardCommentInsert" method="post" name="boardCommentFrm" >
				<input type="hidden" name="boardRef" value="<%=b.getBoardNo()%>" />
				<!-- value="memberLoggedIn!=null?memberLoggedIn.getMemberId():" -->
				<input type="hidden" name="boardCommentWriter" value="[답변]" />
				<%if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())){ %>
				<textarea class="form-control" style="width:530px;" id="boardCommentContent" name="boardCommentContent" cols="60" rows="3"></textarea>
				<button type="submit" id="btn-insert" class="btn btn-primary" >등록</button>
				<%} %>
			</form>
		</div><!-- end of . comment-editor -->
	</div> <!-- end of . comment-container -->
	

</section>
	
<form action="<%=request.getContextPath()%>/board/boardQnaDelete" name="boardDelFrm" method="post">
<input type="hidden" name="boardNo" value="<%=b.getBoardNo()%>" />
<!-- 트리거 del을 db에 만들어서 삭제하면 바로 들어가게 처리함. -->
</form>
<script>
$("[name=boardCommentFrm]").submit(function(e){
		
	
		//boardCommentContent 유효성 검사
		var len = $("[name=boardCommentContent]").val().trim().length;
		
		if(len==0){
			alert("댓글을 작성하세요");
			e.preventDefault();
		}
		
	});
	

function validate(){
	if(confirm("이 댓글을 정말 삭제하시겠습니까?")){
		return true;
	}
	else{
		return false;
	}
}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>	