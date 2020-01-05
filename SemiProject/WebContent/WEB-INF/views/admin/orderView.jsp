<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="java.util.*, semi.kh.product.model.vo.*" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">


<%
List<Buy> list = (List<Buy>)request.getAttribute("list");
%>

<div id="container" style="width:940px;">

	<section id="tbl-container">
	<br />
	<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;배송관리</h2>
	<br />
	<table id="product" class="table table-striped" style="width:800px; background-color:white; margin:0 auto;">
		<thead>
			<tr>
				<th style="width:200px; text-align:center;">주문번호</th>
				<th style="width:100px; text-align:center;">주문자아이디</th>
				<th style="width:100px; text-align:center;">주문일</th>
				<th style="width:100px; text-align:center;">주문상태</th>
				<th style="width:150px; text-align:center;">송장번호</th>
				<th style="width:100px; text-align:center;"></th>
			</tr>
		</thead>
		<tbody>
		<%
			if(!list.isEmpty()){
				for(int i=0; i<list.size(); i++){
					Buy b = list.get(i);
		%>
			<tr>
				<td style="vertical-align:middle; text-align:center;">
					<a href="<%=request.getContextPath()%>/admin/productOrderNo?orderNo=<%=b.getOrderNo()%>">
						<%=b.getOrderNo() %>
					</a>
				</td>
				
				<td style="vertical-align:middle; text-align:center;">
					<a href="<%=request.getContextPath()%>/member/memberView?memberId=<%=b.getMemberId()%>">
						<%=b.getMemberId() %>
					</a>
				</td>
				<td style="vertical-align:middle; text-align:center;"><%=b.getOrderDate()%></td>
				<td style="vertical-align:middle; text-align:center;"><%=b.getShipStatus()%></td>
				<%if(b.getShipStatus().equals("배송준비중")) {%>
				<td style="vertical-align:middle; text-align:center;"><input type="text" style="width:120px; text-align:center" value="6092254226561" placeholder="6092254226561" /></td>
				<td style="vertical-align:middle; text-align:center;" value="테스트"><button class="btn btn-xs ship-btn" style="background-color:rgb(160,151,143); color:white">발송하기</button><input type="hidden" value=<%=b.getOrderNo() %> /></td>
				<%} 
				else{%>
					<td style="vertical-align:middle; text-align:center;" ><%=b.getShipNo() %></td>
					<td></td>
				<%} %>
			</tr>
		<%
				}%>
		<%	}
			else{
		%>
			<tr>
				<td colspan="7" style="text-align:center;">주문내역이 없습니다.</td>
			</tr>
		<%
			}
		%>
		</tbody>
	</table>
</div>

<script>
$(".ship-btn").on("click", function(){
	var orderNo = $(this).next().val();
	var shipNo = $(this).parent().prev().children().val();

	$.ajax({
		url : "<%=request.getContextPath()%>/admin/adminUpdateShipNo.do",
		data : "orderNo="+orderNo+"&shipNo="+shipNo, 
		success : function(data){
			location.href="<%=request.getContextPath()%>/admin/orderView";
		}
	})
	
});

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>	