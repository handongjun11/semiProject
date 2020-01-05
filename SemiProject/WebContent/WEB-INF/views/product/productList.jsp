<%@page import="java.text.DecimalFormat"%>
<%@page import="oracle.jdbc.diagnostics.DemultiplexingLogHandler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="semi.kh.product.model.vo.*, java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productList.css"/>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

<% 
	List<Product> pList = (List<Product>)request.getAttribute("pList");
	String pageBar = (String)request.getAttribute("pageBar");
	
	//System.out.println(request.getContextPath()); // '/hdj'
%>


<script>

function selectPrdView(pId){
	location.href = "<%=request.getContextPath()%>/product/productView?pId="+pId;
}

//마우스 클릭 이벤트에 따라 추가/삭제 : 새로 생성된 요소에 대하여 이벤트 핸들러 추가
$(function(){
	$(".type_thumb").on("click",function(){	
		$(".prdInfo-wrap>div").attr('class','prdInfo_thumb')
							  .css({"width" : "30%",
					 	   			"transition" : "box-shadow .2s ease"});

	});

	$(".type_list").on("click",function(){	
		$(".prdInfo-wrap>div").attr('class','prdInfo_list').css({"width" : "100%"});
	}); 	

});
</script>

<section id="productList-container">
	<div class="banner">
		<img src="<%=request.getContextPath() %>/images/banner/PC_images.webp" style="width: 940px;" />
	</div>
	<ul id="sort_type">
		<li class="type_list"><i class="fas fa-align-justify" style="font-size:30px;"></i></li>
		<li class="type_thumb"><i class="fas fa-th" style="font-size:30px;"></i></li>		 
		<li><%=pList.get(0).getpCategory()%>&nbsp;>&nbsp;<%=pList.get(0).getDescription()%></li>		 
	</ul>
	
	<div class = "prdInfo-wrap" >
		<% for(Product p : pList){ 
			String price = new DecimalFormat().format(p.getpPrice());	
		%>
			<div class="prdInfo_list" >	
			  	<div class="prdimg">
				  	<img src="<%=request.getContextPath()%>/images/product/<%=p.getpName() %>_thumb1.png" 
				  		 onError="javascript:this.src='<%=request.getContextPath()%>/images/error_thumb.jpg'" 
				  		 onclick = 'selectPrdView("<%=p.getpId()%>");'/>
	    		</div>
	    		
			  	<div class="prdTxt" onclick = 'selectPrdView("<%=p.getpId()%>");'>
			  		<p class="pName" style="font-weight: bold;"><%=p.getpName() %></p>
			  		<p class="pId"><%=p.getpId() %></p>
			  	</div>
			  	
			  	<div class="prdPrice" style="margin-top: 6px;" onclick = 'selectPrdView("<%=p.getpId()%>");'>
			  		<p class="priceTit">판매가&nbsp;&nbsp;&nbsp;&nbsp;
			  			<strong style="font-size:20px"><%=price %></strong><em>원</em></p>
					<p class="priceTit" style="color:red;">최대혜택가
						<strong style="font-size:20px"><%=price %></strong><em>원</em></p>
			  	</div>

			  	<div class="score" onclick = 'selectPrdView("<%=p.getpId()%>");'>
					<div class="star" >
						<span class ="star_rating" style="display:inline; ">
							<div>
								<img src="<%=request.getContextPath()%>/images/graystar.PNG" onclick = 'selectPrdView("<%=p.getpId()%>");'>
								<%if(p.getSumrate() == 6){ %>
								<img  style="margin-top: -40px; margin-left: -180px;"  src="<%=request.getContextPath()%>/images/star.png" onclick = 'selectPrdView("<%=p.getpId()%>");'/>
								<%}else if(p.getSumrate() == 1){ %>
								<img  style="margin-top: -40px; margin-left: -147px;"  src="<%=request.getContextPath()%>/images/star.png" onclick = 'selectPrdView("<%=p.getpId()%>");'/>
								<%}else if(p.getSumrate() == 2){ %>
								<img  style="margin-top: -40px; margin-left: -110px;"  src="<%=request.getContextPath()%>/images/star.png" onclick = 'selectPrdView("<%=p.getpId()%>");'/>
								<%}else if(p.getSumrate() == 3){ %>
								<img  style="margin-top: -40px; margin-left: -73px;"  src="<%=request.getContextPath()%>/images/star.png" onclick = 'selectPrdView("<%=p.getpId()%>");'/>
								<%}else if(p.getSumrate() == 4){ %>
								<img  style="margin-top: -40px; margin-left: -36px;"  src="<%=request.getContextPath()%>/images/star.png" onclick = 'selectPrdView("<%=p.getpId()%>");'/>
								<%}else if(p.getSumrate() == 5){ %>
								<img  style="margin-top: -40px; "  src="<%=request.getContextPath()%>/images/star.png" onclick = 'selectPrdView("<%=p.getpId()%>");'/>
								<%} %>
							</div>
						</span>
					<div style="padding: 0 0 0 22px;">(상품평 <%=p.getCnt() %><em>건</em>)</div>				
					</div>
			    </div>

			</div> <!-- proInfo -->
		<% } %>	<!-- end of for -->		
	</div>
	<div id="pageBar"><%=pageBar%></div>
			
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>