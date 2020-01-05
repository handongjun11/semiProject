<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, semi.kh.board.model.vo.* "  %>
<%
	List<Board> list = (List<Board>)request.getAttribute("list");
	List<BoardComment> clist =  (List<BoardComment>)request.getAttribute("list");
	List<Board> noticeList = (List<Board>)request.getAttribute("noticeList");
	int cPage = (int)request.getAttribute("cPage");
	int numPerPage =(int)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
	String searchKeyword = request.getParameter("searchKeyword")==null?"":request.getParameter("searchKeyword");
	String searchType = request.getParameter("searchType")==null?"title":request.getParameter("searchType");

%>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
div#search-title{
	display:<%="title".equals(searchType)?"inline-block":"none"%>;
}
div#search-content {
	display:<%="content".equals(searchType)?"inline-block":"none"%>;
}
#btn-add{
	position:absolute;
	margin-top:20px;
	margin-left: 575px;
}
.pagination>.active>span:hover{
	background-color : #EFEDEB;
}

</style>
<script>
$(function(){
	var stitle = $("#search-title");
	var scontent = $("#search-content");

	$("select#searchType").change(function(){
		stitle.hide();
		scontent.hide();
		$("#search-"+$(this).val()).css("display","inline-block");
	});
	
});
</script>


<style>
section #board-container{
	    margin: 100px;
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
#searchType{
	display:inline-block;
}
#search-title{

	display:inline-block;
}
#search-content{

	display:inline-block;
}
.bbttnn{
	display:inline-block;
	position:absolute;
	left:215px;
	top:1px;
}

.iinput{
	display:inline-block;
}

.form1{
	position:relative;
}
.noticeClass td{
	background-color :#E6E1DB;
}
.noticeClass:hover > td{
	background : #EFEDEB;

}



</style>
</head>
<body>
 <div class="area">
 <section id="board-container">

<input type="button" value="글쓰기" id="btn-add" class="btn btn-default" onclick="goToBoardForm();" />



 <div id="search-container">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 검색 타입 : 
		<select id="searchType" class="form-control input-sm" style="width:75px;">
			<option value="title" <%="title".equals(searchType)?"selected":"" %> >제목</option>
			<option value="content" <%="content".equals(searchType)?"selected":"" %> >내용</option>
		</select>
		<div id="search-title">
			<form action="<%=request.getContextPath()%>/board/boardList" class="form1">
				<input type="hidden" name="numPerPage" value="<%=numPerPage %>" />
				<input type="hidden" name="searchType" value="title"/>
				<input type="search" name="searchKeyword" class="form-control input-sm iinput" size="25" value="<%="title".equals(searchType)?searchKeyword:"" %>" />
				<button type="submit" class="btn btn-default bbttnn"><i class="glyphicon glyphicon-search"></i></button>
			</form>
		</div>
		<div id="search-content">
			<form action="<%=request.getContextPath()%>/board/boardList" class="form1"> 
				<input type="hidden" name="numPerPage" value="<%=numPerPage %>" />
				<input type="hidden" name="searchType" value="content"/>
				<input type="search" name="searchKeyword" class="form-control input-sm iinput" size="25" value="<%="content".equals(searchType)?searchKeyword:"" %>" />
				<button type="submit" class="btn btn-default bbttnn"><i class="glyphicon glyphicon-search"></i></button>
			</form>
		</div>	
	</div>
	
	
 <form id="QnaBoardListFrm">
    <table id="tbl-qna" class="table table-striped" style="width:600px; background-color:white; margin:0 auto; border-collapse: collapse;"> 
      <tr>
        <th style="width:100px; vertical-align:middle; text-align:center;">답변여부</th>
        <th style="width:100px; vertical-align:middle; text-align:center;">카테고리</th>
        <th style="width:200px; vertical-align:middle; text-align:center;">제목</th>
        <th style="width:100px; vertical-align:middle; text-align:center;">작성자</th>
        <th style="width:100px; vertical-align:middle; text-align:center;">등록일</th>
      </tr>
      
       <!-- 공지사항 -->
    <% if(noticeList == null || noticeList.isEmpty()) { %>
           
         <%} else { 
            for(Board b:noticeList) {
         
         %>
		
		<tr class="noticeClass">
		
		
			<td style="vertical-align:middle; text-align:center;"><i class="fas fa-volume-up"></i></td>
			
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

			
		</tr>
		<%}
         } %>
      
      <!-- 일반문의 -->
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
            
                <td style="vertical-align:middle; text-align:center;">
                <a href="<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>">
                <%=b.getBoardTitle() %>
                </a>
            </td>
            
             <% }else{ %>
                 <%if(b.getbLockFlag().equals("Y")){ %>
             <td style="vertical-align:middle; cursor:pointer; text-align:center;" onclick="check('<%=b.getBoardNo()%>');" style="cursor:pointer;"><i class="fas fa-lock" style="font-size:1px;"></i>&nbsp;<%=b.getBoardTitle()%></td>
                 <%}else {%>
                 <td style="vertical-align:middle; text-align:center; cursor:pointer;">
                <a href="<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>">
                <%=b.getBoardTitle() %>
                </a>
            </td>
            
            <%}
                 }%>
			<td style="vertical-align:middle; text-align:center;"><%=b.getbWriter() %></td>
			<td style="vertical-align:middle; text-align:center;"><%=b.getBoardDate() %></td>

			
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