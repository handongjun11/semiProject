<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ page import="java.util.*, semi.kh.product.model.vo.*"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<%
	List<Buy> list = (List<Buy>)request.getAttribute("list");
	List<BuyList> buyList = (List<BuyList>)request.getAttribute("buyList");
	/* System.out.printf("list값아 제대로 왓니~~ = %s", list); */
%>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<style>
#enroll-container table {
	width: 500px;
}
</style>



<section id="enroll-container">
	<h2 style="text-align: center;">상품 결제정보</h2>
	<hr />
	<form action="<%=request.getContextPath()%>/admin/productUpdateEnd" id="table1" method="post" name="productUpdateFrm" onsubmit="return updateValidate();">
	<table class="table">
		<h3 style="text-align: left; margin-left: 200px;">구매자 정보</h3>
		<% if(list == null || list.isEmpty()){ %>
			<tr>
				<td colspan="6" align="center">
					검색결과가 없습니다.
				</td>
			</tr>
		<%}
		else {
			for(Buy b : list){
		%>
		<tr>
			<th style="width: 135px; height: 40px; vertical-align: middle;">주문번호</th>
			<td style="background-color: white; vertical-align: middle;">
				<%=b.getOrderNo()%> <input type="hidden" name="orderNo" value=<%=b.getOrderNo()%> />
			</td>
		</tr>
		<tr>
			<th style="width: 135px; height: 40px; vertical-align: middle;">주문일</th>
			<td style="background-color: white; vertical-align: middle;"><%=b.getOrderDate() %>
			</td>
		</tr>
		
		<tr>
			<th style="width: 135px; height: 40px; vertical-align: middle;">아이디</th>
			<td style="background-color: white; vertical-align: middle;">
			<a href="<%=request.getContextPath()%>/member/memberView?memberId=<%=b.getMemberId()%>"><%=b.getMemberId() %></a>
			</td>
		</tr>
		<tr>
			<th style="width: 135px; height: 40px; vertical-align: middle;">이름</th>
			<td style="background-color: white; vertical-align: middle;"><%=b.getResName() %>
			</td>
		</tr>
		<tr>
			<th style="width: 135px; height: 40px; vertical-align: middle;">휴대폰번호</th>
			<td style="background-color: white; vertical-align: middle;">
			<input type="text" class="form-control" style="width:300px; display:inline-block;" name="resPhone" id="resPhone" value="<%=b.getResPhone()%>"/>
			</td>
		</tr>
		
		<tr>
			<th style="width: 135px; height: 40px; vertical-align: middle;">배송요청사항</th>
			<td style="background-color: white; vertical-align: middle;" value="<%=b.getResRequirement()%>">
			<input type="text" class="form-control" style="width:300px; display:inline-block;" name="resRequirement" id="resRequirement" value="<%=b.getResRequirement()%>"/>
			</td>
		</tr>
		<tr>
			<th style="width: 135px; height: 40px; vertical-align: middle;">배송지</th>
			<td style="background-color: white; vertical-align: middle;">
			<input type="text" class="form-control" style="width:300px; display:inline-block;" name="resAddress" id="resAddress" value="<%=b.getResAddress()%>"/>
			</td>
		</tr>
		<tr>
			<th style="width: 135px; height: 40px; vertical-align: middle;">배송상태</th>
			<td style="background-color: white; vertical-align: middle;"><%=b.getShipStatus() %>
			</td>
		</tr>
		<tr>
			<th style="width: 135px; height: 40px; vertical-align: middle;">상품가격</th>
			<td style="background-color: white; vertical-align: middle;">
				<%=b.getTotalPrice()%>
			</td>
		</tr>
		<tr>
			<th style="width: 135px; height: 40px; vertical-align: middle;">송장번호</th>
			<td style="background-color: white; vertical-align: middle;"><%=b.getShipNo().equals("0000000000000")?"-":b.getShipNo() %>
			</td>
		</tr>
		<% } 
		} %>
	</table>
	
	<hr />
	
	<table class="table table-striped">
		<h3 style="text-align: left; margin-left: 200px;">구매리스트</h3>
		<tr>
	        <th style="vertical-align:middle; text-align:center; height: 40px; ">상품아이디</th>
	        <th style="vertical-align:middle; text-align:center; height: 40px; ">상품명</th>
	        <th style="vertical-align:middle; text-align:center; height: 40px; ">수량</th>
      	</tr>
			<% if(buyList == null || buyList.isEmpty()){ %>
			<tr>
				<td colspan="3" align="center">
					검색결과가 없습니다.
				</td>
			</tr>
		<%}
		else {
			for(BuyList bl : buyList){
		%>
		<tr>
			<td style="vertical-align: middle; text-align:center; height: 40px; "><%=bl.getpId()%></td>
			<td style="vertical-align: middle; text-align:center; height: 40px; "><%=bl.getpName()%></td>
			<td style="vertical-align: middle; text-align:center; height: 40px; "><%=bl.getAmount()%></td>
		</tr>
		<% } 
		} %>
		
	</table>
	<br />
	<div>
		<button class="btn btn-xs ship-btn" style="background-color:rgb(160,151,143); color:white" onclick="deleteProduct();">주문취소</button>&nbsp;&nbsp;&nbsp;
		<input type="submit" class="btn btn-xs ship-btn" style="background-color:rgb(160,151,143); color:white" value="상품결제정보변경" />
	</div>
	</form>
	
	<hr />
	
	
</section>


<script>

function updateValidate(){
	return true;
}

function deleteProduct(){
	var bool = confirm("정말로 주문취소하시겠습니까?ㅠㅠ");
	if(bool){
		var frm = document.productUpdateFrm;
		frm.action = "<%=request.getContextPath()%>/admin/deleteProduct";
		frm.submit();
	}
}
</script>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>
