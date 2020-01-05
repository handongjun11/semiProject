<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="semi.kh.product.model.vo.*, java.util.*,java.net.URLEncoder" %>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="semi.kh.product.model.vo.*, java.util.*,semi.kh.board.model.vo.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/PPRbgb.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productView.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
<%
	request.setCharacterEncoding("UTF-8");
	Product p = (Product)request.getAttribute("Product");
	
	/*최근 본 상품 쿠키*/
	Cookie[] pC = request.getCookies();
	for(int j =0;j<pC.length;j++){	    
	     if((pC[j].getName()).contains(p.getpId())){ // 중복일때
	        
	                Cookie pcc = new Cookie("pcc"+p.getpId(),null); //기존것 제거
	                pcc.setMaxAge(0);
	                pcc.setPath("/hdj");
	                response.addCookie(pcc);
	                
	                pcc = new Cookie("pcc"+p.getpId(),p.getpName()); //새로 추가
	                pcc.setMaxAge(2*60);//세션유지 2분
	                pcc.setPath("/hdj");
	                response.addCookie(pcc);
	           	    
	        }else{ //중복이 아닐때	   
	                Cookie pcc = new Cookie("pcc"+p.getpId(),p.getpName()); //추가
	                pcc.setMaxAge(2*60);//세션유지 2분
	                pcc.setPath("/hdj");
	                response.addCookie(pcc);
	      }	        
	}

	List<BoardReview> reviewList = (List<BoardReview>)request.getAttribute("reviewList");	
	int reviewtotalCnt = (int)request.getAttribute("reviewtotalCnt");
   
	List<Board> qnaList = (List<Board>)request.getAttribute("qnaList");
	List<BoardComment> qnaCommentList = (List<BoardComment>)request.getAttribute("qnaCommentList");
	
	String QnApageBar = (String)request.getAttribute("pageBar");
	//int cPage = (int)request.getAttribute("cPage");
	String category = request.getParameter("category");
	
	String loggedInId = memberLoggedIn==null?"":memberLoggedIn.getMemberId();
	
%> 
<script>

$(function(){	
	
	if('qna'=== '<%=category%>'){ //qna 리스트
		$(".list_detail_category button").parent().eq(2).addClass('on');
		$(".box-detail").eq(2).addClass('show');
		
	}else{ //상세보기 이미지
		$(".list_detail_category button").parent().eq(0).addClass('on');
		$(".box-detail").eq(0).addClass('show');
	}
	
	
	
	$(".list_detail_category button").on("click",function(){//버튼 다 걸림
		
		var parent = $(this).parent();
		
		$(this).parent().addClass('on');
		$(".list_detail_category li").not(parent).removeClass('on');
		
		var index = $('.on').index();
		var element = $('div.wrap-box-datail').children().eq(index);
		element.addClass('show');
		
	 	$('.wrap-box-datail div').not(element).removeClass('show');

	}); //정보 탭 바꾸기
	

	$(".btn_comment").click(function(){ //답변버튼
		var $element = $(this).closest('tr').next('tr').next('tr'); 
		$("tr.QnAComment-editor").css("display","none"); //나머지는 숨기기
		
		$element.css("display",""); //해당하는거만 보여주기
	});
	
	$("img.prod-image-list-thumb").on("click",function(){
		$('img.prod-image-detail').attr('src',$(this).attr('src'));			
	});

	
});
var v_flag = false;

function p_fun(flag,no){	
	v_flag = flag; 
	
	if(flag){		
		if(v_flag){
			$("#"+no).closest('tr').css("background","#f9f9f9"); 
			$("#"+no).closest('tr').next('.QnAContent').css("display","").css("background","#f9f9f9"); // 다음행 (질문내용)
			$("#"+no).closest('tr').next('tr').next('.QnACommentContent').css("display",""); //다다음행(답변)	  							
		}
	}
}

function checkId(flag,writer,no){

	$("tr.QnATitle").css("background","none");
	$("tr.QnAContent").css("display","none");
	$("tr.QnACommentContent").css("display","none");

  if(flag === 'N'){  
		$("#"+no).closest('tr').css("background","#f9f9f9"); 
		$("#"+no).closest('tr').next('.QnAContent').css("display","").css("background","#f9f9f9"); // 다음행 (질문내용)
		$("#"+no).closest('tr').next('tr').next('.QnACommentContent').css("display",""); //다다음행(답변)	  
  }else{ //비밀글
	  
	  //비회원이면
	  if('<%=memberLoggedIn %>'=== 'null'){
		  //비밀번호 체크창 띄우기
	 		var url = "<%=request.getContextPath() %>/board/ChkQnaBoardPwd?boardNo="+no;
			var title = "checkQnaPwd";
			var status = "left=500px, top=200px, width=400px, height=210px"
			open(url,title,status); //자식창 열기  
	  }
 	 //로그인값이 notnull이면 (로그인을 했다면)
	  else{
		  //회원이거나 admin 이면	  
		  if("admin" === '<%=loggedInId%>'|| writer === '<%=loggedInId%>'){
			$("#"+no).closest('tr').css("background","#f9f9f9"); 
			$("#"+no).closest('tr').next('.QnAContent').css("display","").css("background","#f9f9f9"); // 다음행 (질문내용)
			$("#"+no).closest('tr').next('tr').next('.QnACommentContent').css("display",""); //다다음행(답변)	  
		  }
	  }  
  }
} // function checkId


function number_check(e){
	 var code = e.which?e.which:event.keyCode;
	    if(code < 48 || code > 57){
	        return false;
	    }
}


function check(){
	
	if($("input[name='lockflag']").is(':checked') ){
	 $("input[name='lockflag']").val("Y");
	 $("input[name='password']").prop("required", true);
	 $("input[name='password']").prop("readonly", false);
	}else{
		$("input[name='lockflag']").val("N");
		$("input[name='password']").val("");
		$("input[name='password']").prop("required", false);
		$("input[name='password']").prop("readonly", true);
	}
}
function like(bno , recommend , writer , memberid){
    
    if(writer === memberid){
        alert("본인 후기에 추천할 수 없습니다.");
    }else if(writer != memberid){
    console.log(bno);
    $.ajax({
        url: "<%=request.getContextPath()%>/review/recommend.do",
        type: "get",
        data: "bno="+bno+"&recommend="+recommend+"&memberid="+memberid,
        success : function(data){
            console.log("index.jsp@data"+data); //자바스크립트 형태
             $("#"+bno).html(data);
        }

    });
    }
    
    
}
function logcheck(){
        

            alert("비회원은 추천할 수 없습니다.");
    
};


</script>
<section id="product-container">	
<!-- 상품 이미지와 주문/장바구니 ------------>	
	<div class="prod-atf">
		<div class="prod-image">
			<div class="prod-image-list">
				<img class= "prod-image-list-thumb" src="<%=request.getContextPath()%>/images/product/<%=p.getpName() %>_thumb1.png"
					 onError="javascript:this.src='<%=request.getContextPath()%>/images/error_thumb.jpg'"/>
				<img class= "prod-image-list-thumb" src="<%=request.getContextPath()%>/images/product/<%=p.getpName() %>_thumb2.png"
					 onError="javascript:this.src='<%=request.getContextPath()%>/images/error_thumb.jpg'"/>
				<img class= "prod-image-list-thumb" src="<%=request.getContextPath()%>/images/product/<%=p.getpName() %>_thumb3.png"
					 onError="javascript:this.src='<%=request.getContextPath()%>/images/error_thumb.jpg'"/>
				<img class= "prod-image-list-thumb" src="<%=request.getContextPath()%>/images/product/<%=p.getpName() %>_thumb4.png"
					 onError="javascript:this.src='<%=request.getContextPath()%>/images/error_thumb.jpg'"/>
			</div>
			<img class="prod-image-detail" src="<%=request.getContextPath()%>/images/product/<%=p.getpName() %>_thumb1.png" 
				 onError="javascript:this.src='<%=request.getContextPath()%>/images/error_thumb.jpg'"/>
		</div>
		<div class="prod-buy-info">
		
			<div class="prod-buy-header">
				<h2 class="prod-buy-pName"><%=p.getpName() %></h2>
			
				<div class="prod-buy-score">
					<span><%=reviewtotalCnt %> 개 상품평</span>
				</div>
			</div> <!--prod-buy-header -->

			<div class="prod-price-container">
				<!-- 재고량 0 이면 품절 표시! -->
				<% if(p.getpStock()==0){ %>		
				<span class="soldout">품절</span>		
				<%}else{
					String price = new DecimalFormat().format(p.getpPrice());	
				%>	
				<span class="pPrice">
					<strong>
						<%=price%><span class="price-unit">원</span>						
					</strong>
				</span>
				<%} %>
			</div> <!-- prod-price-container -->
			
			<div class="prod-pcompany-container">
				<span class="pId">모델 : <%=p.getpId() %></span>
				<span class="pCompany" style="color : #346aff"><%=p.getpCompany() %></span>
			</div>
			
			<!-- 수량 표시 -->
			<div class="quantity">
  				<input type="number" id="quantity" min="1" max="9" step="1" value="1">
			</div>
			
			<script>
			$("#quantity").on("change", function(){
				$("[name='amount']").val($(this).val());
			});
			
			function orderValidate(){
				var orderamount = $("[name=amount]").val();
				var pName = $("[name=pName]").val();
				var price = $("[name=pPrice]").val();
				
				if(!confirm(pName + " " + orderamount + "개를 주문하시겠습니까? (총 금액 : "+ orderamount*price +")")){
					return false;
				}
				return true;
				
			}
			</script>
			
			<div class="prod-buy-footer">			   			
				<div class="prod-onetime-order">			
         			<form action="<%=request.getContextPath()%>/product/productAddItem">
						<input type="hidden" name="pId" value="<%=p.getpId() %>"/>
						<input type="hidden" name="pName" value="<%=p.getpName() %>"/>
						<input type="hidden" name="pPrice" value="<%=p.getpPrice()%>"/>
						<input type="hidden" name="amount" value="1"/>
						<button type="submit" class="prod-cart-btn">장바구니담기</button>
					</form>
					<form action="<%=request.getContextPath()%>/product/productDirectBuy">
						<input type="hidden" name="pId" value="<%=p.getpId() %>"/>
						<input type="hidden" name="pName" value="<%=p.getpName() %>"/>
						<input type="hidden" name="pPrice" value="<%=p.getpPrice()%>"/>
						<input type="hidden" name="amount" value="1"/>
						<button type="submit" class="prod-buy-btn" onclick="return orderValidate();">바로구매</button>
					</form>
        		</div>
			</div> <!-- prod-buy-footer -->	
			
		</div> <!--prod-buy-info -->
	</div>
	
<!-- 상품관련 카테고리 버튼 ---------------->
	<div class="wrap-detail-category" style = "padding-bottom : 0;">
		<div class="inner" style = "padding : 0;">
			<ul class="list_detail_category">
				<li><button type="button" class="tab_detail_info">상세정보</button></li>
				<li><button type="button" class="tab_detail_ps">구매후기</button></li>
				<li><button type="button" class="tab_detail_qna">Q&A</button></li>
			</ul>
		</div>
	</div>
	
	<div class="wrap-box-datail">
	
<!-- 상세정보 --------------------------->
	<div class="box-detail">
		<div class="inner_border">
			<img src="<%=request.getContextPath()%>/images/product/<%=p.getpName() %>_description.png" 
				 onError="javascript:this.src='<%=request.getContextPath()%>/images/error_thumb.jpg'"
				 style="padding-top : 50px;" />
		</div>
	</div>
	
<!-- REVIEW ---------------------------->
	<div class="box-detail"> 
		<div class="inner_border">
			<ul class="review-container">
			 <table style=" width:800px; text-align:center;">
			
			<% if(reviewList == null || reviewList.isEmpty()) { %>
				 <tr style="border-bottom:0.2px solid lightgray;"><td colspan="4" align="center" height="100px">리뷰가 없습니다.</td></tr>  
         	<%} else { 
           		for(BoardReview br : reviewList){ 			 
			  %>			 
				<tr style="border-bottom:0.2px solid lightgray;">
				  	</td>
				    	<%if(br.getRfile()!= null){ %>
				    	<td>
				    	
						<img src='<%=request.getContextPath()%>/upload/board/<%=br.getRfile()%>' style="height:100px; width:150px;"> </td>
						<%}else{ %>
							<td>&nbsp;&nbsp;&nbsp;<%=br.getPid() %></td>
						<%} %>
				  	 
				  	<td style="width:228px;text-align:left; "><span class="post" ><%=br.getContent() %></span></td>
				  	<td style="width:200px; ">
				  	<br>
				    <span class="review-score" value =<%=br.getRate() %>>
				    	<div style=" width: 115px; padding: 0; overflow: hidden;">
				    	<%if(br.getRate() == 5 ){ %>
				   		<img src="<%=request.getContextPath()%>/images/graystar.PNG" style="width:100px; margin-right: 13px; "/>
				    	<img src="<%=request.getContextPath()%>/images/star.png" style="width:100px; height: 23px;margin-top: -31px;margin-right: 13px; "/>
				    	<%}else if(br.getRate() == 4 ){%>
				    	<img src="<%=request.getContextPath() %>/images/graystar.PNG" style="width:100px; margin-right: 13px; "/>
				    	<img src="<%=request.getContextPath() %>/images/star.png" style="width:100px; height: 23px;margin-top: -31px;margin-right: 13px; margin-left: -41px;"/>
				    	<%}else if(br.getRate() == 3 ){%>
				    		<img src="<%=request.getContextPath() %>/images/graystar.PNG" style="width:100px; margin-right: 13px; "/>
				    	<img src="<%=request.getContextPath() %>/images/star.png" style="width:100px; height: 23px;margin-top: -31px;margin-right: 13px; margin-left: -82px;"/>
				    	<%}else if(br.getRate() == 2 ){%>
				    		<img src="<%=request.getContextPath() %>/images/graystar.PNG" style="width:100px; margin-right: 13px; "/>
				    	<img src="<%=request.getContextPath() %>/images/star.png" style="width:100px; height: 23px;margin-top: -31px;margin-right: 13px; margin-left: -122px;"/>
				    	<%}else if(br.getRate() == 1 ){%>
				    		<img src="<%=request.getContextPath() %>/images/graystar.PNG" style="width:100px; margin-right: 13px; "/>
				    	<img src="<%=request.getContextPath() %>/images/star.png" style="width:100px; height: 23px;margin-top: -31px;margin-right: 13px; margin-left: -161px;"/>
				    	<%} %>
				    	</div>
				    </span>    
				    <p style="margin-left: -16px;">
				  	<%if(br.getWriter().length() <= 19){ %>
				  	<%=br.getWriter()%>　　　　　
				  	<%}else{ %>
				  	비회원　　　　　
				  	<%} %>
				  	<p>
				  	
				  	<%if(memberLoggedIn != null ){ %>
      				<i class="far fa-thumbs-up" id="like" onclick="like('<%=br.getbNo() %>' ,'<%=br.getRecommend() %>' ,'<%=br.getWriter()%>' , '<%=memberLoggedIn.getMemberId() %>');" style="margin-left: -120px; cursor:pointer;"></i>
      				<span id="<%=br.getbNo()%>">&nbsp;&nbsp;<%=br.getRecommend() %></span>
      				<%}else{ %>
      					<i class="far fa-thumbs-up" id="like" onclick="logcheck();" style="margin-left: -120px; cursor:pointer;"></i>
      				<span >&nbsp;&nbsp;<%=br.getRecommend() %></span>
      				<%} %>
      				<div class="date" style="margin-left: -95px; padding-top: 0px; padding-bottom: 20px; "><%=br.getBdate() %></div>
				  </div>
      				</td>

				</tr>
				<% }
				}	%>
				
    		</ul>
    		</table>
		</div>
	</div>
	
<!-- QNA ------------------------------->
	<div class="box-detail">
		<div class="inner_border">
		
		<!-- QnA 입력창-->
			<div class="QnA-editor">
				<h4 class="tit prdt_iqry">상품 문의</h4>
				<form action="<%=request.getContextPath()%>/board/qnaBoardFormEnd" method="post" name="boardQnAFrm" >						
					<div style="display : table;">		
						<input type="hidden" name="pId" value="<%=p.getpId()%>" />
						<input type="hidden"  name="writer" 
							  value=<%=memberLoggedIn!=null?memberLoggedIn.getMemberId():"비회원" %> />
						<input type="hidden" name="category" value="상품문의"/>          		   
					   	제목					  
					 	<input type="text" name="title" class="form-control" required>
						
						<input type="checkbox" name="lockflag" value="N" onclick="check();">								
							<%if(memberLoggedIn == null){ %><!-- 비회원일 경우 보이게 하기 -->
							 <input type="password" size='5' maxlength="4" name="password" placeholder="비밀번호" 
							 		onkeypress="return number_check(event)" readonly>
							<%}else{ %>
								<span style="display:inline-block; width: 94px; text-align: left;">비밀글</span>						
							<% }%>
							
						<textarea class="form-control" name="content" cols="60" rows="3"></textarea>
						<button type="submit" class="btn btn-default">질문하기</button>		
					</div>
				</form>	
			</div><!-- QnAComment-editor -->		
				
		<!-- QnA 리스트  -->		
    	<table id="tbl-qna" style ="border-collapse: collapse;"> 
      		<tr><th>답변여부</th><th>카테고리</th><th>제목</th><th>작성자</th><th>등록일</th><th></th></tr>

		<% if(qnaList == null || qnaList.isEmpty()) { %>
            <tr><td colspan="6" align="center" height="100px">검색결과가 없습니다.</td></tr>
	    <% }else { 
            
	    	for(Board b : qnaList) { %>				
			<tr class= "QnATitle" style="background: none;">
			 	<!-- 답변여부(미답변/답변) -->
			 <% if(b.getBoardCommentCnt() <= 0){ %> 
			 	<td>미답변</td>			
			 <%} else {%> 
			 	<td>답변완료</td>  
			 <%} %>						 
			 	<!-- 카테고리(상품문의) -->	
				<td><%=b.getBcategory() %></td>
		
		
				<!-- QnA글 제목(비밀글 여부 판단함수) ---------------------------------------->
				<td style="cursor:pointer;" onclick="checkId('<%=b.getbLockFlag()%>','<%=b.getbWriter()%>','<%=b.getBoardNo()%>');" id="<%=b.getBoardNo()%>" >
						<%=b.getBoardTitle()%>
						<%if("Y".equals(b.getbLockFlag())) {%>
						&nbsp;<i class="fas fa-lock" id="lockqna"></i>
						<%} %>
				</td>			
					
				<td><%=b.getbWriter() %></td><!-- 작성자 -->
				<td><%=b.getBoardDate() %></td><!-- 작성일 -->
				
				<!-- 답변이 안되어있고, admin이 로그인 했을 때만 답변 버튼 보여주기  -->
				<% if(b.getBoardCommentCnt()<= 0 && memberLoggedIn!= null && "admin".equals(memberLoggedIn.getMemberId())){ %>
				<td style="width: 70px;"> 
					<button type="button" name="btn_comment" class="btn_comment">답변</button>
				</td>
				<%} else {%>
				<td> </td>
				<%} %>				
			</tr>
			<!-- 제목을 클릭하면 내용이 보이도록 -->
			<tr class="QnAContent" style="display : none; height: 77px;">
			  <td colspan = "6"><%=b.getbConetent() %></td>
			</tr>
			
			<!-- QnA 코멘트 추가  -->
				<%for(BoardComment bc : qnaCommentList) {
					if(b.getBoardNo() == bc.getcRef()){
				%>
				<tr class="QnACommentContent" style="display : none;">
					<td><div>└</div></td>
					<td colspan = "5" style="text-align : left;">
							<%=bc.getcContent() %> 
							<br />| <%=bc.getcDate() %>									
					</td>
				</tr>	
				<% } 
				} %> <!-- end of for(qnaCommentList) -->
				
			
			<% if(b.getBoardCommentCnt()<= 0){ %>
			<tr class="QnAComment-editor" style='display: none;'>
			<td colspan = "6">	
				
				<!-- 답변 입력창 -->
				<div class="QnAComment-editor" style="margin-left: 50px;">
					<form action="<%=request.getContextPath()%>/board/boardCommentInsert" method="post" name="boardCommentFrm" >
						<input type="hidden" name="pId" value="<%=p.getpId()%>" />
						<input type="hidden" name="boardRef" value="<%=b.getBoardNo()%>" />
						<input type="hidden" name="boardCommentWriter" value="[답변]" />		
						<input type="hidden" name="category" value="qna"/>					
						<div style="display : table;">
							<textarea class="form-control" style="width:600px;"name="boardCommentContent" cols="60" rows="3"></textarea>
							<button type="submit"  class="btn btn-default" >등록</button>
						</div>
					</form>
				</div><!-- QnAComment-editor -->
			</td>
			</tr>
			
			<%} %> <!-- if(b.getBoardCommentCnt()<= 0) -->
		
		<%	} //end of for(QNALIST)
         } %> <!--if(qnaList == null || qnaList.isEmpty())	-->
 
     </table>
		
		 	<div id="QnApageBar"><%=QnApageBar %></div>
		 	
 		 </div> <!-- inner_border -->
 	   </div> <!-- box-detail -->
	</div>
	
	
</section>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>