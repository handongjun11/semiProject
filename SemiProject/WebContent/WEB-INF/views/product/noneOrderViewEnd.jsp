<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, semi.kh.board.model.vo.* ,semi.kh.product.model.vo.*"  %>
<%
	List<Buy> list = (List<Buy>)request.getAttribute("list");
	List<BuyList> buyList =  (List<BuyList>)request.getAttribute("buyList"); 
	List<Board> blist = (List<Board>)request.getAttribute("blist");
	List<BoardReview> rlist = (List<BoardReview>)request.getAttribute("rlist");
	String password = (String)request.getAttribute("password");
	//System.out.println("password###"+password);
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
 	<h3>주문 내역</h3>
    <table id="tbl-qna" style ="border-collapse: collapse;" class="table" style='width:800px; background-color:white; margin:0 auto;'> 
      <tr>
        <th style="border-bottom: 1px solid #a29990; vertical-align:middle; text-align:center;">주문번호</th>
        <th style="border-bottom: 1px solid #a29990; vertical-align:middle; text-align:center;">주문날짜</th>
        <th style="border-bottom: 1px solid #a29990; vertical-align:middle; text-align:center;">배송상태</th>
         <th style="border-bottom: 1px solid #a29990; vertical-align:middle; text-align:center;"></th>
      <!--   <th style="border-bottom: 1px solid #a29990;">상품명</th>
        <th style="border-bottom: 1px solid #a29990;">후기작성</th> -->
      
      </tr>
      
      <!-- 스크립틀릿 처리요먕 -->
		<% if(list == null || list.isEmpty()) { %>
            <tr>
               <td colspan="4" align="center" style="vertical-align:middle; text-align:center;">검색결과가 없습니다.</td>
            </tr>
         <%} else { 
            for(Buy b:list) { %>
            <tr style="background-color:white; vertical-align:middle; text-align:center;">
		 <td style="vertical-align:middle; text-align:center;"><%=b.getOrderNo() %></td>
		 <td style="vertical-align:middle; text-align:center;"><%=b.getOrderDate() %></td>
		 <%if(b.getShipStatus().equals("배송준비중")){ %>
		 	<td style="vertical-align:middle; text-align:center;"><%=b.getShipStatus()%></td>
		 <%}
		   else{ %>
		   	<td style="vertical-align:middle; text-align:center;"><%=b.getShipStatus()%> <br /> <button class="btn btn-link" onclick="showTracker('<%=b.getShipNo()%>');"><%=b.getShipNo() %></button></td>
		   
		 <%} %>
		 
		 <td style="vertical-align:middle; text-align:center;"> <input type="button" class="btn shipQna" style="background-color:rgb(236,235,236)" value="배송문의" /></td>
            </tr>

            	<% for(BuyList bList : buyList){
            	
         %>
       
		
		 <%if((b.getOrderNo()).equals(bList.getOrderNo())) {%>

		 <td style="vertical-align:middle; text-align:center;"></td>
		 <td style="vertical-align:middle; text-align:center;"><%=bList.getpName() %></td>
		 <td style="vertical-align:middle; text-align:center;">수량 : <%=bList.getAmount() %></td>
		 <td style="vertical-align:middle; text-align:center;">
		  <form name="reviewFrmgo" action="<%=request.getContextPath()%>/board/ReviewFrm">
		<input type="hidden" name="pid" value="<%=bList.getpId()%>">
		 <input type="hidden" name="memberid" value="<%=bList.getMemberId()%>">
		 <input type="hidden" name="password" value="<%=password%>">
		 <%if("배송완료".equals(b.getShipStatus())) { %>
		 <input type="submit" class="btn" id="btn-review" style="background-color:white;" value="후기작성" />
		 <%}
		 else{ %>
		 <td></td>
		 	  <%} %>
		 </form>
		 </td>
		 <%} %>
			
		</tr>
		</form>
		<% } 
           }
         } %>
 

     </table>
     <div id="pageBar"></div>
     
     <!-- ------------------------------------------------------------------------------------- -->
     <h3>문의 내역</h3>
     <table id="tbl-qna" class="table table-striped" style="width:800px; background-color:white; margin:0 auto; border-collapse: collapse;"> 
      <tr>
        <th style="width:100px; vertical-align:middle; text-align:center;">답변여부</th>
        <th style="width:100px; vertical-align:middle; text-align:center;">카테고리</th>
        <th style="width:200px; vertical-align:middle; text-align:center;">제목</th>
        <th style="width:100px; vertical-align:middle; text-align:center;">작성자</th>
        <th style="width:100px; vertical-align:middle; text-align:center;">등록일</th>
      </tr>
      
      <!-- 스크립틀릿 처리요먕 -->
		<% if(blist == null || blist.isEmpty()) { %>
            <tr>
               <td colspan="6" align="center">검색결과가 없습니다.</td>
            </tr>
         <%} else { 
            for(Board b:blist) {
         
         %>
		
		<tr>
		
		 <% if(b.getBoardCommentCnt() <= 0){  %>
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

			
		</tr>
		<%}
         } %>
     </table>
     
     
     <br><br>
     <!-- ------------------------------------------------------------------------------------- -->
     <h3>후기 내역</h3>
     <table id="tab-review" class="table table-striped" style="width:800px; background-color:white; margin:0 auto; text-align:center; border-collapse: collapse; "> 
      <tr>
         <th style="width : 100px; text-align:center;">첨부파일</th>
         <th style="width : 200px; text-align:center;">작성내용</th>
         <th style="width : 80px; text-align:center;">작성일</th>
         <th style="width : 80px; text-align:center;">평점</th>
         <th style="width : 100px; text-align:center;"></th>
      </tr>

		<% if(rlist == null || rlist.isEmpty()) { %>
            <tr>
               <td colspan="3" align="center">검색결과가 없습니다.</td>
            </tr>
         <%} else { 
            for(BoardReview br :rlist) {  %>
			<tr>
			<%if(br.getRfile() == null || br.getRfile().equals("null")){ %>
				<td></td>
			<%}else{ %>
				<td><img src='<%=request.getContextPath()%>/upload/board/<%=br.getRfile()%>'></td>
			<%} %>
				<td><%=br.getContent() %></td>
			
				<td><%=br.getBdate() %></td>
	
				<td> <span style="color:red;"><%for(int i=1 ; i <= br.getRate() ; i++) {%>
				              ★ <% }%></span>
				       <span style="color:lightgray;"><%for(int i=1 ; i <= (5-br.getRate()) ; i++) {%>
				              ★<% }%></span>
				</td>
				<td>
				<form>
				<input type="button" value="수정" class="btn btn-default" onclick="modify('<%=br.getbNo() %>');">
				<input type="button" value="삭제" class="btn btn-primary" onclick="return del('<%=br.getbNo() %>');">
				</form>
				</td>
			</tr>	
			<%} 
          }%>
 
     </table>

     
</section>
 <% if(rlist == null || rlist.isEmpty()) { %>
            <tr>
            
            </tr>
         <%} else { 
            for(BoardReview br :rlist) {  %>
 				<form id="<%=br.getbNo() %>" name="deleteFrm" action="<%=request.getContextPath()%>/review/deleteReview">
 				<input type="hidden" name="boardno" value="<%=br.getbNo()%>">
 				<input type="hidden" name="rfile" value="<%=br.getRfile()%>">
 				<input type="hidden" name="password" value="<%=password%>">
 				<input type="hidden" name="orderno" value="<%=br.getWriter() %>" >
 				<input  type="hidden" name="NM" value="비회원">
 				</form>
 					<%} 
          }%>
</div>
  
<script>
$(function(){
	var shipStatus = $(".shiptStatus").val();
	if(!shipStatus=="배송준비중"){
		$(this).on("click", function(){
			
		});
	}
});


function showTracker(shipNo){
	var url = "https://tracker.delivery/#/kr.epost/"+shipNo;
    var title ="배송현황 보기";
    var status = "left=500px,top=200px,width=400px,height=600px";
    open(url,title,status);
};

$(".shipQna").on("click", function(){
	var orderNo = $(this).parent().prev().prev().prev().html();
	location.href = "<%=request.getContextPath()%>/board/shipQnaBoardForm?orderNo="+orderNo+"&password="+<%=password%>;
});
$("#btn-review").on("click", function(){
	
	//비회원 후기작성  폼으로 옮기기 !!!!!!!!! url주소 바꾸기 !!!
	 location.href = "<%=request.getContextPath()%>/board/reviewForm" ;
	
	
	
});

function modify(boardno){
	
	location.href="<%=request.getContextPath()%>/review/modify?boardno="+boardno+"&password="+<%=password%> ;
	
}
function del(boardno){
	
	if(!confirm("이 게시글을 정말 삭제하시겠습니까?")){
		return;
	}
	
	$("#"+boardno).submit();
	
}
</script>

 <%@ include file="/WEB-INF/views/common/footer.jsp" %>