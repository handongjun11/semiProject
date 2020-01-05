<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/memberManagement.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<%
/*쇼핑몰할일  */

int totalQnA = (int)request.getAttribute("totalQnA"); //총 누적게시물 수
int noAnswerQnA = (int)request.getAttribute("noAnswerQnA"); //답변안된 게시물
int notShipped = (int)request.getAttribute("notShipped");
int deliveryCompleted = (int)request.getAttribute("deliveryCompleted");
int answerCompletedBoardListCount = (int)request.getAttribute("answerCompletedBoardListCount");



/*쇼핑몰현황  */
int todayOrder = (int)request.getAttribute("todayOrder"); //오늘 들어온 주문건수
int todaySellTotalCost  = (int)request.getAttribute("todaySellTotalCost"); //오늘 판매한 총 금액
int todayMember = (int)request.getAttribute("todayMember"); //오늘 가입한 신규 회원 수

int totalOrder = (int)request.getAttribute("totalOrder"); //누적 주문건수
int totalSellCost = (int)request.getAttribute("totalSellCost"); //누적 판매금액
int totalCountMember = (int)request.getAttribute("totalCountMember"); //누적 가입회원수
%>



<%@ include file="/WEB-INF/views/common/header.jsp"%>
<h2 id="managementDo">오늘의 할일</h2>


<table cellspacing='0' id="tbl-admin12">
	<thead>
		<tr>
			<th><i class="fas fa-shipping-fast"></i><br />미배송체크</th>
			<th><i class="fas fa-truck"></i><br />배송완료체크</th>
			<th><i class="fas fa-clipboard-list"></i><br />미답변 게시물</th>
			<th><i class="fas fa-clipboard-check"></i><br />답변 게시물</th>
			<th><i class="far fa-clipboard"></i><br />총 게시물</th>
		</tr>
	</thead>
    <tbody>
		<tr>
			<td><a href="<%=request.getContextPath()%>/admin/notShipped"><%=notShipped %></a>건</td>
			<td><a href="<%=request.getContextPath()%>/admin/deliveryCompleted"><%=deliveryCompleted %>건</a></td>
			<td>
				<a href="<%=request.getContextPath()%>/admin/noAnswerList"><%=noAnswerQnA%>건</a>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/admin/AdminAnswerCompletedBoardList"><%=answerCompletedBoardListCount%>건</a>
			</td>
			<td>
				<a href="<%=request.getContextPath()%>/admin/boardList?category=all&cPage=1"><%=totalQnA %>건</a>
			</td>
			
		</tr>
	</tbody>
</table>


<h2 id="managementStatus">쇼핑몰현황</h2>
<table cellspacing='0' id="tbl-admin123">
	<thead>
		<tr>
			<th>쇼핑몰현황</th>
			<th><i class="fas fa-cart-arrow-down"></i><br />주문</th>
			<th><i class="fas fa-hand-holding-usd"></i><br />주문금액</th>
			<th><i class="fas fa-user"></i><br />가입회원수</th>
		</tr>
	</thead>
    <tbody>
		<tr>
			<td>오늘</td>
			<td><a href="<%=request.getContextPath()%>/admin/dailyOrderView"><%=todayOrder %>건</a></td>
			<td><%=todaySellTotalCost %>원</td>
			<td><a href="<%=request.getContextPath()%>/admin/dailyMemberList"><%=todayMember %>명</a></td>
		</tr>
		<tr>
			<td>총 누적</td>
			<td><a href="<%=request.getContextPath()%>/admin/orderView"><%=totalOrder %>건</a></td>
			<td><%=totalSellCost %>원</td>
			<td><a href="<%=request.getContextPath()%>/admin/memberList"><%=totalCountMember %>명</a></td>
		</tr>
	</tbody>
</table>



<%@ include file="/WEB-INF/views/common/footer.jsp"%>