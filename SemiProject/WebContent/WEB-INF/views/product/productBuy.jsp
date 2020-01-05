<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ page import="java.util.*, semi.kh.product.model.vo.*,semi.kh.member.model.service.*, java.text.DecimalFormat" %>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<%

	Member m = new MemberService().selectOne(memberLoggedIn.getMemberId());
	String grade = m.getGrade();

	double sale = 0;
	switch(grade){
	case "VVIP" : grade = "VVIP(5%할인)"; sale = 0.05; break;
	case "VIP" : grade = "VIP(3%할인)"; sale = 0.03; break;
	case "GOLD" : grade = "GOLD(1%할인)"; sale = 0.01; break;
	case "일반" : grade = "일반(할인X)"; sale = 0; break;
	}
	
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
	<h2 style="text-align:center;">회원 주문/결제</h2>
	<hr />
	<form action="<%=request.getContextPath() %>/product/productBuyEnd" name="showAddrFrm" method="post">
		<table id="table1" class="table">
			<h3 style="text-align:left; margin-left:200px;">구매자 정보</h3>
			<tr>
				<th style="width:135px; height:40px; vertical-align:middle;">이름</th>
				<td style="background-color:white; vertical-align:middle;">
					<%=memberLoggedIn.getMemberName() %>
					<input type="hidden" name="memberId" value="<%=memberLoggedIn.getMemberId() %>" />
				</td>
			</tr>
			<tr>
				<th style="width:135px; height:40px; vertical-align:middle;">회원등급</th>
				<td style="background-color:white; vertical-align:middle;">
					<%=grade %>
				</td>
			</tr>
			<tr>
				<th style="width:135px; height:40px; vertical-align:middle;">이메일</th>
				<td style="background-color:white; vertical-align:middle;">
					<%=memberLoggedIn.getEmail() %>
				</td>
			</tr>
			<tr>
				<th style="width:135px; height:40px; vertical-align:middle;">휴대폰 번호</th>
				<td style="background-color:white; vertical-align:middle;">
					<%=memberLoggedIn.getPhone() %>
				</td>
			</tr>
		</table>
		<hr />
		<table class="table">	
			<h3 style="text-align:left; margin-left:200px;">받는사람 정보</h3>
			<tr>
				<th style="width:135px; vertical-align:middle;">이름</th>
				<td>
					<input type="text" class="form-control" name="resName" value="<%=memberLoggedIn.getMemberName() %>" placeholder="<%=memberLoggedIn.getMemberName() %>" />
				</td>
			</tr>
			<tr>
				<th style="width:135px; vertical-align:middle;">배송주소</th>
				<td>
					<input type="text" class="form-control" name="resAddress" id="resAddress" value="<%=memberLoggedIn.getAddress() %>" placeholder="<%=memberLoggedIn.getAddress() %>" />
					<button type="button" style="margin-left:35px;" class="btn btn-link" onclick="showAddress();">최근 배송지 보기</button>
					<button type="button" class="btn btn-link" onclick="execDaumPostcode();" >새로운 배송지 추가</button>
				</td>
			</tr>
			<tr>
				<th style="width:135px; vertical-align:middle;">연락처</th>
				<td>
					<input type="text" class="form-control" name="resPhone" value="<%=memberLoggedIn.getPhone() %>" placeholder="<%=memberLoggedIn.getPhone() %>" />
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
				<th style="width:135px; height:40px; vertical-align:middle;">총 상품가격</th>
				<td style="background-color:white; vertical-align:middle; text-align:right;">
					<%=new DecimalFormat().format(totalPrice) %>원
				</td>
			</tr>
			<tr>
				<th style="width:135px; height:40px; vertical-align:middle;">할인금액</th>
				<td style="background-color:white; vertical-align:middle; text-align:right;">
					-<%=new DecimalFormat().format((int)(totalPrice * sale)) %>원
				</td>
			</tr>
			<tr>
				<th style="width:135px; height:40px; vertical-align:middle;">총 결제금액</th>
				<td style="background-color:white; vertical-align:middle; text-align:right;">
					<%=new DecimalFormat().format((int)(totalPrice-(totalPrice*sale))) %>원
					<input type="hidden" name="afterPrice" value=<%=(int)(totalPrice-(totalPrice*sale)) %> />
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

function showAddress(){
    var url = "<%=request.getContextPath() %>/product/showAddress?memberId=<%=memberLoggedIn.getMemberId()%>";
    var title ="내 주소지 보기";
    var status = "left=500px,top=200px,width=350px,height=365px";
    open(url,title,status);
}

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
	     
		  		$.ajax({
		  			url : "<%=request.getContextPath() %>/product/insertnewAddr?addr="+fullRoadAddr+"&memberId=<%=memberLoggedIn.getMemberId()%>",
		  			success : function(data){
		  				document.getElementById("resAddress").value = fullRoadAddr;
		  			}
		  		})
		  		
	        }
	    }).open();
	  
}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>	