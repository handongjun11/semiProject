<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, semi.kh.board.model.vo.* "  %>
<%
	int cPage = 1;
	int numPerPage = 7;
	String pageBar = "";	
	List<Board> list = null;
	
	list = (List<Board>)request.getAttribute("list");
	cPage = (int)request.getAttribute("cPage");
	pageBar = (String)request.getAttribute("pageBar");
%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<style>
section #board-container{
	 margin-top: 50px;
	 /* margin-right:400px; */
}

#tab-qna {
border-collapse: collapse;
}

a{
	text-decoration: none;
    color: black;
}
#tbl-qna tr:hover{
	background-color: #f0f0f0;
}
/*페이지바*/
div#pageBar{margin-top:10px; text-align:center;}
div#pageBar span.cPage{
	color: red;
	margin: 3px;
}

</style>
</head>
<body>
<nav id="view">
<ul class="View-nav">
			<li><a href="<%=request.getContextPath()%>/member/memberView?memberId=<%=memberLoggedIn.getMemberId() %>">회원 정보 보기</a></li>
					
			<li><a href="<%=request.getContextPath()%>/member/showMyBuyList?memberId=<%=memberLoggedIn.getMemberId() %>">주문 내역 보기</a></li>
	
			<li><a href="<%=request.getContextPath()%>/member/showMyReviewList?memberId=<%=memberLoggedIn.getMemberId() %>">나의 후기 보기</a></li>
					
			<li><a href="<%=request.getContextPath()%>/member/showMyQNAList?memberId=<%=memberLoggedIn.getMemberId() %>">문의 내역 보기</a></li>
</ul>						
</nav>
 <div class="area">
 <section id="board-container">

 <br><br>
 <div id="list"></div>
 
 <form >
    <table id="tbl-qna" class="table table-striped" style="background-color:white;  margin-top: 40px; border-collapse: collapse;"> 
      <tr>
        <th style="width:100px; vertical-align:middle; text-align:center;">답변여부</th>
        <th style="width:100px; vertical-align:middle; text-align:center;">카테고리</th>
        <th style="width:200px; vertical-align:middle; text-align:center;">제목</th>
        <th style="width:100px; vertical-align:middle; text-align:center;">작성자</th>
        <th style="width:100px; vertical-align:middle; text-align:center;">등록일</th>
      </tr>
      
      <!-- 스크립틀릿 처리요먕 -->

		<% if(list == null || list.isEmpty()) { %>
            <tr>
               <td colspan="6" align="center">검색결과가 없습니다.</td>
            </tr>
         <%} else { 
            for(Board b:list) {

         %>
		
		<tr>
		
		 <% if(b.getBoardCommentCnt() <= 0){ %>
			<td td style="vertical-align:middle; text-align:center;">미답변</td>
			<%} else {%>
				<td td style="vertical-align:middle; text-align:center;">답변완료</td>
			<% }%>
			<td td style="vertical-align:middle; text-align:center;"><%=b.getBcategory()%></td>
				<td td style="vertical-align:middle; text-align:center;">
				<a href="<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>">
				<%=b.getBoardTitle() %>
				</a>
			</td>
		
			<td td style="vertical-align:middle; text-align:center;"><%=b.getbWriter() %></td>
			<td td style="vertical-align:middle; text-align:center;"><%=b.getBoardDate() %></td>
			
		</tr>
		<%} }%>
     </table>
		</form>
     	<div id="pageBar">
			<%=pageBar %>
		</div>
	
	
	

  </section>
  </div>
  <script>


  
 </script>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>	