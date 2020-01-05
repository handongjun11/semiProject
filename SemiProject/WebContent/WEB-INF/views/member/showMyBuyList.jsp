<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, semi.kh.board.model.vo.* ,semi.kh.product.model.vo.*"  %>
<%
	List<Buy> list = (List<Buy>)request.getAttribute("list");
	List<BuyList> buyList =  (List<BuyList>)request.getAttribute("buyList"); 
	int cPage = (int)request.getAttribute("cPage");
	int numPerPage =(int)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");

%>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<style>
section #board-container{
	    margin: 100px;
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
form[name=reviewFrmgo]{
    margin: 6px 0;
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
 
    <table id="tbl-qna" class="table"  style ="border-collapse: collapse; width:800px; background-color:white; margin-top: 40px; margin-left: -69px;"> 
      <tr>
        <th style="border-bottom: 1px solid #a29990; vertical-align:middle; text-align:center;">주문번호</th>
        <th style="border-bottom: 1px solid #a29990; vertical-align:middle; text-align:center;">주문날짜</th>
        <th style="border-bottom: 1px solid #a29990; vertical-align:middle; text-align:center;">배송상태</th>
        <th style="border-bottom: 1px solid #a29990; vertical-align:middle; text-align:center;"></th>
     	 <!--<th style="border-bottom: 1px solid #a29990;">상품명</th>
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

        <!--  <tr>
         <th></th>
         <th>상품명</th>
         <th>수량</th>
         <th></th>
         </tr> -->
            	<% for(BuyList bList : buyList){
            	
         %>
       
		
		 <%if((b.getOrderNo()).equals(bList.getOrderNo())) {%>
		 
		<tr style="background-color:#f1f1f1; vertical-align:middle; text-align:center;">
		 <td style="vertical-align:middle; text-align:center;"></td>
		 <td style="vertical-align:middle; text-align:center;"><%=bList.getpName() %></td>
		 <td style="vertical-align:middle; text-align:center;">수량 : <%=bList.getAmount() %></td>
		 <td style="vertical-align:middle; text-align:center;">
			 <form name="reviewFrmgo" action="<%=request.getContextPath()%>/board/ReviewFrm">
			 <input type="hidden" name="pid" value="<%=bList.getpId()%>">
			 <input type="hidden" name="memberid" value="<%=bList.getMemberId()%>">
			 <%if(b.getShipStatus().equals("배송완료")) { %>
			 	<input type="submit" class="btn" id="btn-review" style="background-color:white; vertical-align:middle;" value="후기작성" />
			 <%} %>
			 </form>
		 </td>
		 <%} %>
			
		</tr>
		
		<% } 
           }
         } %>
 

     </table>
     	<div id="pageBar">
	<%=pageBar %>
	
	
	</div>
  </section>
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
	location.href = "<%=request.getContextPath()%>/board/shipQnaBoardForm?orderNo="+orderNo;
});


</script>

 <%@ include file="/WEB-INF/views/common/footer.jsp" %>	
