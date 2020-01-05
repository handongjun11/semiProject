<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, semi.kh.board.model.vo.* "  %>
<%
	String category = (String)request.getAttribute("category");
	int cPage = (int)request.getAttribute("cPage");
	int numPerPage = 5;
	String pageBar = (String)request.getAttribute("pageBar");
	List<Board> list = (List<Board>)request.getAttribute("list");
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<style>
section #board-container{
	    margin: 30px;
}
#btn-add{
	margin-left: 700px;
    margin-bottom: 10px;
    background :none;

}
#tab-qna {
	
border-collapse: collapse;
}
#tab-qna th, td{
	/*border-bottom : 1px solid #a29990 ;
	width : 1000px;
	text-align : center;*/
}
#tbl-qna{
 /*border-bottom : 1px solid #a29990 ;*/

}
#tbl-qna th{
/* background :#A299B7; */
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
 <div class="area">
 <section id="board-container">

<script>
$(function(){
	
	var category = "<%=category%>";
	switch (category){
	case "모아보기" : $("#category").val("all"); category="all"; break;
	case "일반문의" : $("#category").val("nomalQnA"); category="nomalQnA"; break;
	case "배송문의" : $("#category").val("delQnA"); category="delQnA"; break;
	case "상품문의" : $("#category").val("prdQnA"); category="prdQnA"; break;
	}
	
	$("#category").change(function(){
		var category = $(this).val();
		location.href = "<%=request.getContextPath()%>/admin/boardList?category="+category+"&cPage=1";
		
	});

});



 </script>
 <br><br>
 <div id="list"></div>
 
 <form >
    <table id="tbl-qna" class="table table-striped" style="width:800px; background-color:white; margin:0 auto; border-collapse: collapse;"> 
      <tr>
        <th style="width:100px; vertical-align:middle; text-align:center;">답변여부</th>
         <th style="width:104px; vertical-align:middle; text-align:center;">
         	<select id="category" name="category" class="form-control">
			  <option value="all">모아보기</option>
			  <option value="nomalQnA">일반문의</option>
			  <option value="delQnA">배송문의</option>
			  <option value="prdQnA">상품문의</option>
		  	</select>
         </th>
        <th style="width:200px; vertical-align:middle; text-align:center;">제목</th>
        <th style="width:100px; vertical-align:middle; text-align:center;">작성자</th>
        <th style="width:100px; vertical-align:middle; text-align:center;">등록일</th>
        <th style="width:100px; vertical-align:middle; text-align:center;"></th>
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
			<td style="vertical-align:middle; text-align:center;">미답변</td>
			<%} else {%>
				<td style="vertical-align:middle; text-align:center;">답변완료</td>
			<% }%>
			<td style="vertical-align:middle; text-align:center;"><%=b.getBcategory()%></td>
			<td style="vertical-align:middle; text-align:left;">
				<a href="<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>">
				<%=b.getBoardTitle() %>
			</a>
			</td>
		
			<td style="vertical-align:middle; text-align:center;"><%=b.getbWriter() %></td>
			<td style="vertical-align:middle; text-align:center;"><%=b.getBoardDate() %></td>
			<%  if(b.getBoardCommentCnt() <= 0){ %>
			<td style="vertical-align:middle; text-align:center;"> 
			<a href="<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>">
			<button type="button"  name="btn_comment" >답변</button></a> </td>
			<%} else {%>
			<td style="vertical-align:middle; text-align:center;"> </td>
			<%}%>
			
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