<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="java.util.*, semi.kh.product.model.vo.*, java.text.DecimalFormat" %>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<%
	Cart sc = (Cart)session.getAttribute("sc");
	
	int totalPrice = 0;
	if(sc==null){
		List<Cart> list = (List<Cart>)request.getAttribute("list");
		
		for(Cart c : list){
			totalPrice += (c.getAmount()*c.getpPrice());
		}
		
	}else{
		totalPrice = (sc.getAmount()*sc.getpPrice());
	}
	
	
%>

<style>
#enroll-container table{
	width:500px;
}


</style>


<section id="enroll-container">
	<h2 style="text-align:center;">비회원 주문/결제</h2>
	<hr />
	<form action="<%=request.getContextPath() %>/product/productBuyByNMEnd" name="showAddrFrm" method="post">
		<table id="table1" class="table">
			<h3 style="text-align:left; margin-left:200px;">구매자 정보</h3>
			<tr>
				<th style="width:135px; height:40px; vertical-align:middle;">이름</th>
				<td style="vertical-align:middle;">
					<input type="text" class="form-control" name="memberName" />
				</td>
			</tr>
			<tr>
				<th style="width:135px; height:40px; vertical-align:middle;">비밀번호</th>
				<td style="vertical-align:middle;">
					<input type="text" class="form-control" name="password" />
				</td>
			</tr>
			<tr>
				<th style="width:135px; height:40px; vertical-align:middle;">이메일</th>
				<td style="vertical-align:middle;">
					<input type="email" class="form-control" name="email" />
				</td>
			</tr>
			<tr>
				<th style="width:135px; height:40px; vertical-align:middle;">휴대폰 번호</th>
				<td style="vertical-align:middle;">
					<input type="text" class="form-control" name="phone"/>
				</td>
			</tr>
		</table>
		<hr />
		<table class="table">	
			<h3 style="text-align:left; margin-left:200px;">받는사람 정보</h3>
			<tr>
				<th style="width:135px; vertical-align:middle;">이름</th>
				<td>
					<input type="text" class="form-control" name="resName" />
				</td>
			</tr>
			<tr>
				<th style="width:135px; vertical-align:middle;">배송주소</th>
				<td>
					<input type="text" class="form-control" name="resAddress" id="resAddress"/>
				</td>
			</tr>
			<tr>
				<th style="width:135px; vertical-align:middle;">연락처</th>
				<td>
					<input type="text" class="form-control" name="resPhone" />
				</td>
			</tr>
			<tr>
				<th style="width:135px; height:30px; vertical-align:middle;">배송 요청사항</th>
				<td>					
					<select class="form-control" name="resRequirement">
					    <option>문앞</option>
					    <option>경비실</option>
					    <option>택배함</option>
					    <option>기타</option>
				  	</select>			
				  	<input type="text" class="form-control" name="resRequirementEtc" readonly/>		
				</td>
			</tr>
		</table>
		<hr />
		<table class="table">
			<h3 style="text-align:left; margin-left:200px;">결제 정보</h3>
			<tr>
				<th style="width:135px; height:40px; vertical-align:middle;">총 결제금액</th>
				<td style="background-color:white; vertical-align:middle; text-align:right">
					<input type="hidden" name="afterPrice" value="<%=(totalPrice) %>" />
					<%=new DecimalFormat().format(totalPrice) %>원
				</td>
			</tr>
		</table>
		<br />
		<button type="submit" id="submitBuy" class="btn btn-primary btn-lg">결제하기</button>
	</form>
</section>

<script>
$("[name=resRequirement]").on("change", function(){
	
	if($(this).val() == "기타"){
		$("[name=resRequirementEtc]").attr("readonly", false);
	}
	else{
		$("[name=resRequirementEtc]").attr("readonly", true).val("");
	}
});

$("#resAddress").on("click", function(){
	execDaumPostcode();
});

function execDaumPostcode(){

	  new daum.Postcode({
	        oncomplete:function(data){
	            var fullRoadAddr = data.roadAddress;
	            var extraRoadAddr = '';
	            
	            if(data.bname !=='' &&/[동|로|가]$/g.test(data.bname)){
	                extraRoadAddr += data.bname;
	            }
	            if(data.buildingName !=='' &&data.apartment === 'Y'){
	                extraRoadAddr += (extraRoadAddr !== ''?','+data.buildingName : data.build);
	            }
	            if(extraRoadAddr !== ''){
	                extraRoadAddr = '(' + extraRoadAddr +')';
	            }
	            if(fullRoadAddr !== ''){
	                fullRoadAddr += extraRoadAddr;
	            }
	            
	            document.getElementById("resAddress").value = fullRoadAddr;
	        }
	    }).open();
	  
}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>	