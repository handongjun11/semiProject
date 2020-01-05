<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "semi.kh.member.model.vo.*,
					semi.kh.product.model.vo.*,
					semi.kh.member.model.service.*,
					java.util.*" %>
<%

	List<Address> list = (List<Address>)request.getAttribute("list");
	String memberId = request.getParameter("memberId");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내 주소지</title>
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<script>
function changeAddress(address){
	var frm = opener.document.showAddrFrm;
	frm.resAddress.value=address;
	
	//현재창을 제어
	self.close();
}


</script>
<style>
#addrShow{
    width:260px;
    height:110px;
    color:black;
    font-size:20px;
    background-color:white;
    border:none;
    word-break:break-all;
    word-wrap:break-word;
}
#delAddr{
    width:68px;
    height:110px;
}

</style>
</head>
<body>

<% if(list == null || list.isEmpty()) {%>
			<div>주소지가 존재하지 않습니다.</div>
			
<%} else{
	for(Address addr : list){%>
	 <input type="button" id="addrShow" value="<%=addr.getAddress() %>" onclick="changeAddress('<%=addr.getAddress() %>');"/> 
	<button type="button" id="delAddr" class="btn btn-link" name="address" onclick="deleteAddress('<%=addr.getAddress() %>');">삭제</button> <br />
	
	<%}
	} %>
	
<script>
	
function deleteAddress(address){
	<%--  var url = "<%=request.getContextPath() %>/product/deleteAddress?memberId=<%=memberId%>&address="+address; --%>
	 console.log(address);
	 var memberId="<%=memberId%>";
	 
	
	$.ajax({
		url : "<%=request.getContextPath()%>/product/deleteAddress.do",
		data : "memberId="+memberId+"&address="+address, 
		success : function(data){
			location.href="<%=request.getContextPath()%>/product/showAddress?memberId="+memberId; 
			
		}
	});
}


</script>


</body>
</html>