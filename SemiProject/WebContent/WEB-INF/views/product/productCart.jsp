<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="java.util.*, semi.kh.product.model.vo.*, java.text.DecimalFormat, javax.servlet.http.HttpSession" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">


<%
List<Cart> list = (List<Cart>)request.getAttribute("list");
session.setAttribute("sc", null);
%>

<div id="container" style="width:940px;">

	<section id="tbl-container">
	<br />
	<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;장바구니</h2>
	<br />
	<table id="product" class="table table-striped" style="width:800px; background-color:white; margin:0 auto;">
		<thead>
			<tr>
				<th style="width:80px; vertical-align:middle; text-align:center;">사진</th>
				<th style="width:170px; vertical-align:middle; text-align:center;">상품아이디</th>
				<th style="width:170px; vertical-align:middle; text-align:center;">상품명</th>
				<th style="width:100px; vertical-align:middle; "></th>
				<th style="width:100px; vertical-align:middle; text-align:center;">수량</th>
				<th style="width:100px; vertical-align:middle; text-align:center;">상품가격</th>
				<th><button class="btn btn-default btn-sm" id="allDelete">전체삭제</button></th>
			</tr>
		</thead>
		<tbody>
		<%
			int totalPrice = 0;
		
			if(!list.isEmpty()){
				for(int i=0; i<list.size(); i++){
					Cart c = list.get(i);
					totalPrice += (c.getAmount()*c.getpPrice());
		%>
			<tr>
				<td><img src="<%=request.getContextPath()%>/images/product/<%=c.getpName() %>_thumb1.png" onError="javascript:this.src='<%=request.getContextPath()%>/images/error_thumb.jpg'" style="width:70px"/></td>
				<td style="vertical-align:middle; text-align:center;"><%=c.getpId() %></td>
				<td style="vertical-align:middle; text-align:center;"><%=c.getpName() %></td>
				<td style="vertical-align:middle; text-align:right;"><%= new DecimalFormat().format(c.getpPrice()) + "원"%></td>
				<td style="vertical-align:middle; text-align:center;"><input type="number" class="changeAmount form-control" min=1 value="<%=c.getAmount() %>" placeholder=<%=c.getAmount() %> /><input type="hidden" id="changePId" value=<%=c.getpId()%> /> <input type="hidden" id="changePrice" value=<%=c.getpPrice() %> /></td>
				<td style="vertical-align:middle; text-align:center;"><%=new DecimalFormat().format(c.getpPrice() * c.getAmount())  +"원" %></td>
				<td style="vertical-align:middle; text-align:center;"><button class="deleteItem btn btn-default" value=<%=c.getpId() %>>삭제</button></td>
			</tr>
		<%
				}%>
			<tr>
				<td colspan="5" style="vertical-align:middle; text-align:right; height:78px;" ><h3>총가격</h3></td>
				<td colspan="2" style="vertical-align:middle; text-align:center;"><h3 id="tp" value="<%=totalPrice%>"><%=new DecimalFormat().format(totalPrice)%>원</h3></td>
			</tr>	
		<%	}
			else{
		%>
			<tr>
				<td colspan="7" style="text-align:center;">장바구니에 담은 상품이 없습니다.</td>
			</tr>
		<%
			}
		%>
		</tbody>
	</table>
	
	<br />

	<form style="text-align:center;" action="<%=request.getContextPath()%>/product/productBuy">
		<input type="hidden" name="cartList" value=<%=list %>/>
		&nbsp;&nbsp;&nbsp;
		<% if(!list.isEmpty()){ %>
			<button type="submit" class="btn btn-lg btn-primary">상품주문</button>
		<%} %>	
	</form>
</div>

<script>
$(".deleteItem").on("click", function(){
	if(!confirm("정말 삭제하시겠습니까?")) return;

	var pId = $(this).val();
	$.ajax({
		url : "<%=request.getContextPath()%>/product/productDeleteItem.do",
		data : "pId="+pId,
		success : function(data){
			location.href="<%=request.getContextPath()%>/product/productCart";
		}
	})
});


var oldAmount = "";
$(".changeAmount").on("mousedown", function(){
	oldAmount = $(this).val();
}).on("mouseup", function(event){
	var pId = $(this).next().val();
	var amount = $(this).val();
	var price = $(this).next().next().val();
	var itemTotalPrice = $(this).parent().next();
	var oldTotalPrice = (($("#tp").text()).replace(/[^0-9]/g,'') - (oldAmount*price));
	
	$.ajax({
		url : "<%=request.getContextPath()%>/product/productUpdateAmount.do",
		data : "pId="+pId+"&amount="+amount, 
		success : function(data){
			$(this).val(amount);
			itemTotalPrice.text(Comma(amount*price)+'원');
			$("#tp").text(Comma(oldTotalPrice + (amount*price))+'원');
		}
	})
}).on("keydown", function(){
	alert("수량은 마우스로 입력하세요.");
	event.preventDefault();
});


function Comma(Num) { //function to add commas to textboxes
    Num += '';
    Num = Num.replace(',', ''); Num = Num.replace(',', ''); Num = Num.replace(',', '');
    Num = Num.replace(',', ''); Num = Num.replace(',', ''); Num = Num.replace(',', '');
    x = Num.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1))
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    return x1 + x2 ;
}

$("#allDelete").on("click", function(){
	if(!confirm("정말 삭제하시겠습니까?")) return;
	
	$.ajax({
		url : "<%=request.getContextPath()%>/product/productDeleteAllItem.do",
		success : function(data){
			location.href="<%=request.getContextPath()%>/product/productCart";
		}
	})
});

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>	