<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, semi.kh.board.model.vo.* "  %>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/showMyReviewList.css" />

<%
	
	int cPage = 1;
	int numPerPage = 5;
	String pageBar = "";	
	List<BoardReview> list = null;
	
	list = (List<BoardReview>)request.getAttribute("list");
	cPage = (int)request.getAttribute("cPage");
	pageBar = (String)request.getAttribute("pageBar");

%>
<script>
function modify(boardno){
	
	location.href="<%=request.getContextPath()%>/review/modify?boardno="+boardno ;
	
}
function del(boardno){
	
	if(!confirm("이 게시글을 정말 삭제하시겠습니까?")){
		return;
	}
	
	$("#"+boardno).submit();
	
}



</script>

<nav id="view">
<ul class="View-nav">
			<li><a href="<%=request.getContextPath()%>/member/memberView?memberId=<%=memberLoggedIn.getMemberId() %>">회원 정보 보기</a></li>
					
			<li><a href="<%=request.getContextPath()%>/member/showMyBuyList?memberId=<%=memberLoggedIn.getMemberId() %>">주문 내역 보기</a></li>
	
			<li><a href="<%=request.getContextPath()%>/member/showMyReviewList?memberId=<%=memberLoggedIn.getMemberId() %>">나의 후기 보기</a></li>
					
			<li><a href="<%=request.getContextPath()%>/member/showMyQNAList?memberId=<%=memberLoggedIn.getMemberId() %>">문의 내역 보기</a></li>
</ul>						
</nav>

 <section id="board-container" style="margin-top:50px;">

 <br><br>
 <div id="list"></div>

    <table id="tab-review" class="table table-striped" style="border-collapse: collapse;  background-color:white; margin-top: 40px;"> 
      <tr>
         <th style="width : 100px;">첨부파일</th>
         <th style="width : 200px;">작성내용</th>
         <th style="width : 100px;">작성일</th>
         <th style="width : 100px;">평점</th>
         <th style="width : 100px;"></th>
      </tr>

		<% if(list == null || list.isEmpty()) { %>
            <tr>
               <td colspan="3" align="center">검색결과가 없습니다.</td>
            </tr>
         <%} else { 
            for(BoardReview br :list) {  %>
			<tr>
				<td><img src='<%=request.getContextPath()%>/upload/board/<%=br.getRfile()%>'></td>
		
				<td><%=br.getContent() %></td>
			
				<td><%=br.getBdate() %></td>
	
				<td> <span style="color:red;"><%for(int i=1 ; i <= br.getRate() ; i++) {%>
				              ★ <% }%></span>
				       <span style="color:lightgray;"><%for(int i=1 ; i <= (5-br.getRate()) ; i++) {%>
				              ★<% }%></span>
				</td>
				<td>
				<form>
				<input type="button" value="수정" class="btn btn-default" onclick="modify(<%=br.getbNo() %>);">
				<input type="button" value="삭제" class="btn btn-primary" onclick="return del(<%=br.getbNo() %>);">
				</form>
				</td>
			</tr>	
			<%} 
          }%>
 
     </table>
    <div id="review-pageBar"><%=pageBar %></div>
  </section>
  <% if(list == null || list.isEmpty()) { %>
            
         <%} else { 
            for(BoardReview br :list) {  %>
 				<form id="<%=br.getbNo() %>" name="deleteFrm" action="<%=request.getContextPath()%>/review/deleteReview">
 				<input type="hidden" name="boardno" value="<%=br.getbNo()%>">
 				<input type="hidden" name="rfile" value="<%=br.getRfile()%>">
 				<input type="hidden" name="memberId" value="<%=memberLoggedIn.getMemberId()%>">
 				</form>
 					<%} 
          }%>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>	