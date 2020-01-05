<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, semi.kh.board.model.vo.* "  %>
<%
	List<Board> list = (List<Board>)request.getAttribute("list");
	List<BoardComment> clist =  (List<BoardComment>)request.getAttribute("list");
	int cPage = (int)request.getAttribute("cPage");
	int numPerPage =(int)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");

%>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<style>
section #board-container{
	    margin: 50px;
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
 <form>
<%if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())){ %>

 <%}else{%>
 <input type="button" value="글쓰기" id="btn-add" onclick="goToBoardForm();" />
 
 <%} %>
 </form>
 <br><br>
 
 <form id="QnaBoardListFrm">
    <table id="tbl-qna" class="table table-striped" style="width:750px; background-color:white; margin:0 auto; border-collapse: collapse;"> 
      <tr>
        <th style="width:100px; vertical-align:middle; text-align:center;">답변여부</th>
        <th style="width:100px; vertical-align:middle; text-align:center;">카테고리</th>
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
			<%if( memberLoggedIn != null && ((memberLoggedIn.getMemberId().equals(b.getbWriter()))||"admin".equals(memberLoggedIn.getMemberId()))){ %>
			
				<td style="vertical-align:middle; text-align:left;">
				<a href="<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>">
				<%=b.getBoardTitle() %>
				</a>
			</td>
			
			 <% }else{ %>
			 	<%if(b.getbLockFlag().equals("Y")){ %>
			 <td style="vertical-align:middle; text-align:left;" onclick="check('<%=b.getBoardNo()%>');" style="cursor:pointer;"><%=b.getBoardTitle()%></td>
			 	<%}else {%>
			 	<td style="vertical-align:middle; text-align:left;">
				<a href="<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>">
				<%=b.getBoardTitle() %>
				</a>
			</td>
			 
			<%}
			 	}%>
			<td style="vertical-align:middle; text-align:center;"><%=b.getbWriter() %></td>
			<td style="vertical-align:middle; text-align:center;"><%=b.getBoardDate() %></td>
			<%if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getMemberId())){ %>
			<%  if(b.getBoardCommentCnt() <= 0){ %>
			<td style="vertical-align:middle; text-align:center;"> 
			<a href="<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>">
			<button type="button" id="btn_comment" name="btn_comment" class="btn" style="background-color:rgb(236,235,236)">답변</button></a> </td>
			<%} else {%>
			<td> </td>
			<%} }%>
			
		</tr>
		<%}
         } %>
     </table>
		</form>
     	<div id="pageBar">
	<%=pageBar %>
	
	
	</div>
  </section>
  </div>
  <script>
  function goToBoardForm(){

      location.href = "<%=request.getContextPath()%>/board/qnaBoardForm" ;


  }
  function check(boardNo){
	  console.log(boardNo);
	  var url = "<%=request.getContextPath() %>/board/ChkQnaBoardPwd?boardNo="+boardNo;
		var title = "checkQnaPwd";
		var status = "left=500px, top=200px, width=400px, height=210px"
		open(url,title,status)
   
	  
  }

  
 </script>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>	