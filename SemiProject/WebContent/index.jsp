<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="semi.kh.product.model.vo.*, java.util.*" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/index.css" />

 <style>
.carousel-inner > .item > img,
.carousel-inner > .item > a > img {
	width: 100%;
	height:350px;
    margin: auto;
 }
</style>

<%@ include file="/WEB-INF/views/common/header.jsp"%>


<div id="myCarousel" class="carousel slide" data-ride="carousel">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
		<li data-target="#myCarousel" data-slide-to="3"></li>
	</ol>

	<!-- Wrapper for slides -->
	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<img src="<%=request.getContextPath()%>/images/galuxynote9.png"
				style="height: 350px">
		 	<div class="carousel-caption"  style="right:20%; left:-20%; padding-bottom:110px;">
				<h3>Galaxy Note9</h3>
				<p>The new super powerful Note <br />
				   Galxy Note9 Alpine blue 출시!!
				</p>				
				<a href="<%=request.getContextPath()%>/product/productView?pId=SM-N960NZWAKOE" style="color:white;">자세히보기</a>	&nbsp;	&nbsp;
				<a href="<%=request.getContextPath()%>/product/productDirectBuy?pId=SM-N960NZWAKOE&pName=갤럭시노트9&pPrice=1094500&amount=1" style="color:white;">바로구매</a>
			</div> 
		</div>

		<div class="item">
			<img src="<%=request.getContextPath()%>/images/lggram17.png"
				style="height: 350px">
			<div class="carousel-caption" style="right:20%; left:-20%; padding-bottom:110px; color:black" >
				<h3>대화면을 그램하다</h3>
				<h2>LG gram 17</h2>
				<a href="<%=request.getContextPath()%>/product/productView?pId=17Z990-VA70K" style="color:black;">자세히보기</a>	&nbsp;	&nbsp;
				<a href="<%=request.getContextPath()%>/product/productDirectBuy?pId=17Z990-VA70K&pName=LG그램17&pPrice=2340000&amount=1" style="color:black;">바로구매</a>
			</div>
		</div>

		<div class="item">
			<img src="<%=request.getContextPath()%>/images/3.PNG"
				style="height: 350px">		
		</div>

		<div class="item">
			<img src="<%=request.getContextPath()%>/images/1.PNG"
				style="height: 350px">			
		</div>
	</div>

	<!-- Left and right controls -->
	<a class="left carousel-control" href="#myCarousel" role="button"
		data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"
		aria-hidden="true"></span> <span class="sr-only">Previous</span>
	</a> <a class="right carousel-control" href="#myCarousel" role="button"
		data-slide="next"> <span class="glyphicon glyphicon-chevron-right"
		aria-hidden="true"></span> <span class="sr-only">Next</span>
	</a>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>