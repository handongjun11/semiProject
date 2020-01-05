<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="semi.kh.member.model.vo.*, semi.kh.product.model.service.*,java.net.URLEncoder,java.util.*" %>
<%
	Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
boolean saveId = false;
String memberId = "";

Cookie[] cookies = request.getCookies();

for(Cookie c : cookies){
	String key = c.getName();
	String value = c.getValue();
	if("saveId".equals(key)){
		saveId = true;
		memberId = value;
	}
}



%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SHOPINGMALL</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.js"></script>
<script src="<%=request.getContextPath()%>/js/Chart.bundle.js"></script>
<script src="<%=request.getContextPath()%>/js/PPRbgb.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
div.login-container input[type=button]{
	margin:0 auto;
	width:100px;
	height:25px;
}

tr#lasttr input[type=button]{
	position:absolute;
	margin:0 auto;
	width:115px;
	height:25px;
	right:58px;
	
}
div.login-container input[type=checkbox]{
	position: absolute;
	margin:0 auto;
	top:50px;
	right: 45px;
}
div.login-container label{
	position:absolute;
	color:rgb(51,122,194);
	top:50px;
	right: 6px;
}
div.login-container input[type=submit]{
	
	position: absolute;
	margin:0 auto;
	width: 60px;
	height: 35px;
	top:13px;
	right: 3px;
 	padding: 5px 5px;

}

tr#log input[type=button]{
	margin:0 auto;
}
#btn-logout{
	position:absolute;
	margin:0 auto;
	right:60px;
	
	
}
#time{
	position:absolute;
	width:100px;
	top:10px;
}
#timer-button{
	position:absolute;
	margin:0 auto;
	text-align:center;
	font-size:5;
	width:45px;
	left:34px;
	top:3px;
}
#title{
	position:absolute;
	margin-top:-10px;
	margin-left:20px;
 	font-size:50px;
 	color:rgb(110,102,92);
}
#title:hover{
	cursor:pointer;
}
</style>

<script>

function loginValidate() {
	if($("#memberId").val().trim().length ==0){
		alert("아이디를 입력하세요");
		$("#memberId").focus();
		return false;//폼 전송 방지
	}
	if($("#password").val().trim().length==0){
		alert("비밀번호를 입력하세요");
		$("#password").focus();
		return false;
	}
	//form에서 유효성 검사할때는 onsubmit, 로그인버튼 부분에서 유효성검사할때는 onclick
	return true;
}
function findPassword(){
	var url = "<%=request.getContextPath()%>/member/findPassword";
	var title ="findPassword";
	var status = "left=500px,top=200px,width=400px,height=210px";
	open(url,title,status);
}

<%if(memberLoggedIn!=null && !("admin".equals(memberLoggedIn.getMemberId()))) {%>

var iM = 30;
var iS = iM * 60;
var timerchecker = null;

$(function(){
	fncClearTime();
	initTimer();
});

Lpad = function(str,len){
	str = str + "";
	while(str.length < len){
		str = "0"+str;
	}
	return str;
};

initTimer = function(){
	rM = parseInt(iS/60);
	rS = iS % 60;
	if(iS>0){
		
		$("#time").text(Lpad(rM,2)+"M "+Lpad(rS,2)+"S");
		iS--;
		timerchecker = setTimeout("initTimer()",1000);
	}else {
		clearTimeout(timerchecker);
		logout();
	}
}

function fncClearTime(){
	iM = 30;
	iS = iM*60;
	
}

function logout(){
	location.href='<%=request.getContextPath()%>/member/logout';
}

<%}%>

function goToPrdList(obj){
	
	  var pCategory = $(obj).attr("name");
	  console.log($(obj).parent().eq(0));	 /*모바일, 스마트폰,태블릿, 웨어러블 */
	 <%----%> 
	  location.href = 
	 	  "<%=request.getContextPath()%>/product/productList?pCategory="+pCategory;
	 
	}
</script>


</head>
<body>
	<div id="container">
		<header>
		<div id="title" onclick="location.href ='<%=request.getContextPath()%>/'">
			<img src='<%=request.getContextPath()%>/images/LOGO.png' style="width: 80px;"/>
		</div>
					
			<!-- 로그인 폼 -->
			<!-- 로그인 시작 -->
			<div class="login-container">
			<%if(memberLoggedIn == null) { %>
				<form action="<%=request.getContextPath()%>/member/login" method="post" id="LoginFrm" onsubmit="return loginValidate();">
					<table>
						
						<tr>
							<td><input type="text" name="memberId" id="memberId" placeholder="아이디" value="<%=memberId%>" style="width:170px;"></td>
							
							<td></td>
						</tr>
						<tr>
							<td><input type="password" name="password" id="password" placeholder="비밀번호" style="width:170px;"></td>
							<td><input type="submit" class="btn btn-primary" value="로그인" /></td>
						</tr>
						<tr>
						<td colspan="2">
							<input type="button" class="btn btn-link" value="비밀번호찾기"  onclick="findPassword();"/>
							<input type="button" class="btn btn-link" value="회원가입" onclick="location.href='<%=request.getContextPath()%>/member/memberEnroll'"/>
							<input type="checkbox" name="saveId" id="saveId" <%=saveId?"checked":"" %>/> 
							<label for="saveId">ID저장</label>
							</td>
						</tr>
						<tr id="lasttr">
							<td colspan="2">
							<input type="button" class="btn btn-link" value="비회원 주문조회" onclick="location.href = '<%=request.getContextPath()%>/product/noneOrderView'"/>
							
							</td>
						</tr>
					</table>
				</form>
			<% }
				else {%>
				
					<table id="logged-in">
						<tr id="welcome">
							<td><%=memberLoggedIn.getMemberId() %>님, 안녕하세요! Welcome:)</td>
						</tr>
						<tr id="log">
							<td>
							<input type="button" class="btn btn-link" value="내정보보기" onclick='location.href = "<%=request.getContextPath()%>/member/memberView?memberId=<%=memberLoggedIn.getMemberId()%>";' />
							<input type="button" id="btn-logout" value="로그아웃" class="btn btn-link" onclick ="location.href='<%=request.getContextPath()%>/member/logout';"/>
							</td> 
						</tr>
					</table>
					<% if(!("admin".equals(memberLoggedIn.getMemberId()))) {%>
					<table id="timer">
						<tr >
						<td id="time"></td>
						<td id="timer-button"><input type="button" class="btn btn-link" value="연장" onclick="fncClearTime();"/></td>
 
						</tr>
					</table>
					<%} %>
					
				
			<% } %>
			</div>
			<!-- 로그인 폼 -->
			
			<!-- 메인메뉴 시작 -->
			<nav>
				<ul class="main-nav">
					<li><a href="<%=request.getContextPath() %>/">Home</a></li>
					
					<li>
						<a onclick="goToPrdList(this);" name="모바일">모바일</a>				
						<ul class="sub-nav">
							<li><a onclick="goToPrdList(this);" name="스마트폰">스마트폰</a></li>
							<li><a onclick="goToPrdList(this);" name="태블릿">태블릿</a></li>
							<li><a onclick="goToPrdList(this);" name="웨어러블">웨어러블</a></li>
						</ul>		
					</li>
					
					<li><a onclick="goToPrdList(this);" name="PC">PC</a>
						<ul class="sub-nav">
							<li><a onclick="goToPrdList(this);" name="노트북">노트북</a></li>
							<li><a onclick="goToPrdList(this);" name="모니터">모니터</a></li>
							<li><a onclick="goToPrdList(this);" name="데스크탑">데스크탑</a></li>
						</ul>	
					</li>
					
					<li><a href="<%=request.getContextPath()%>/board/boardList">고객센터</a></li>
					<li><a href="<%=request.getContextPath()%>/product/productCart">장바구니</a></li>
				
					 <%-- 관리자인 경우만 보이기 --%>
               <%if(memberLoggedIn != null && ("admin").equals(memberLoggedIn.getMemberId())){ %>
               <li>
                  <a href="#">관리자</a>
                  <ul class="sub-nav">
                     <li><a href="<%=request.getContextPath()%>/admin/memberList">회원관리</a></li>                              
                     <li><a href="<%=request.getContextPath()%>/admin/memberManagement">통계</a></li>
                     <li><a href="<%=request.getContextPath()%>/admin/Management">스케줄러</a></li>
                     <li><a href="<%=request.getContextPath()%>/admin/orderView">주문내역</a></li>
                  </ul>
               </li>
               <%} %>
				</ul>
			</nav>
			<!-- 메인메뉴 끝 -->
		</header>
		<div id="wrapper" style="position:relative">
		<div id="todayList" style="position:absolute; width:130px; min-height:100px; left:970px;">
		<h6 id="todayList_h6"> 최근 본 상품</h6>
	
		<script>
			
			var arr = new Array();
		<%
		request.setCharacterEncoding("UTF-8");
		Cookie[] pcookies = request.getCookies();
		
		
		for(Cookie pc : pcookies){
			String key = pc.getName();
			String value = pc.getValue();
			
			if(key.contains("pcc")){
		%>
	
			var todayList = new Object();
			
			console.log("length"+arr.length);
			if(arr.length >=5){
				arr.shift();
				todayList.toID="<%= key.substring(3) %>";
				todayList.toNAME="<%= value %>";
				arr.push(todayList);
			}else {
				todayList.toID="<%= key.substring(3) %>";
				todayList.toNAME="<%= value %>";
				arr.push(todayList);
				
			}
	
		<% 
				
			}
		}
		
		%>
			var addr = "<%=request.getContextPath() %>/product/productView?pId=";
			var html1 = "<a href='";
			var html2 = "' >"
			var html3 = "</a>";
			var src1 = "<img class='listimg' src='";
			var src2 = "<%=request.getContextPath() %>/images/product/";
			var src3 = "_thumb1.png' "+"onerror= javascript:this.src='<%=request.getContextPath() %>/images/error_thumb.jpg'"+" />";
			<%-- var src2 = "<%=request.getContextPath() %>/images/product/arr[i].toNAME_thumb1.png"; --%>
			for(var i in arr){
				$("#todayList").append( "<div class='todayList_list'>"+html1+addr+arr[i].toID+html2+src1+src2+arr[i].toNAME+src3+" <br />" +arr[i].toNAME+ "<br /></h6>"+html3+"</div>");
				
			}
		
		</script>
	

		</div>
		</div>
		<section id="content" style="background:none;">
		
		