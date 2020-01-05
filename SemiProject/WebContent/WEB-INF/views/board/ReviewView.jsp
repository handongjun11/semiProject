<%@page import="semi.kh.board.model.vo.BoardReview"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<%
     BoardReview br = (BoardReview)request.getAttribute("br");
	String password = (String)request.getAttribute("password");
	String orderno = (String)request.getAttribute("orderno");
	int graystar = 0 ;

%> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
/*내용 업데이트 폼*/
/*#star-container{
	width : 192;
	height : 38;
	overflow:hidden;
}*/

.star_rating {font-size:0; letter-spacing:-4px;}
.star_rating a {
    font-size:22px;
    letter-spacing:0;
    display:inline-block;
    margin-left:5px;
    color:#ccc;
    text-decoration:none;
}
.star_rating a:first-child {margin-left:0;}
.star_rating a.on {color:red;}
#reviewcategory{
	background : none;
	border : none;

}
#reviewWriter{
background : none;
	border : none;
}
#profilePre{
	
  display : none;

}
#div-profilePre{
	width : 100px;
	height : 100px;
}

#review-div{
    margin-top: 46px;
    margin-left: 156px;
}

</style>

<script>
function validate(){
	//내용을 작성하지 않은 경우에 대한 유효성 검사하세요.
	//공백만 작성한 경우도 폼이 제출되어서는 안됨.

	var content = $("textarea[name=content]");

	if(content.val().trim().length  ==  0 ){
		alert("내용을 입력하세요");
		return false;
	}
	return true;
}

$(function(){
	$(function() {
		$( ".star_rating a" ).click(function() {
		    $(this).parent().children("a").removeClass("on");
		    $(this).addClass("on").prevAll("a").addClass("on");
		     var star = $(this).attr("id");
		    $("[name='star']").val(star);
		    return false;
		});
		})	
	

		
})
$(function(){
	   /* 첨부파일이 바뀌면 체인지 */
	   $("[name=up_file]").change(function(){
	      console.log($(this).val());
	      if($(this).val() != ""){
	         /* 값이 없으면 숨기기 */
	         $("span#fname").hide();
	      }
	      else{
	         /* 값이 있으면 보여주기 */
	         $("span#fname").show();
	      }
	      
	});
		
});
</script>


<div id="review-div">
<h2>게시판 작성</h2>
<form action="<%=request.getContextPath() %>/review/modifyEnd" enctype="multipart/form-data" method="post">
<input type="hidden" name="boardno" value="<%=br.getbNo()%>">
<input type="hidden" name="password" value="<%=password %>">
<input type="hidden" name="orderno" value="<%=orderno %>">
 <!--  파일 업로드 속성 추가 enctype="multipart/form-data" : 파일 업로드를 위한 필수 속성-->
  <input type="hidden" name="star" value="<%=br.getRate()%>" >
	<table id="tbl-board-review" class="table" style="width:600px;">
	<tr>
		<th>카테고리</th>
		<td><input type="text" name="category" id="reviewcategory" value="상품후기" readonly ></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<%if(memberLoggedIn == null){ %>
               <input type="text" name="writer" value="<%=br.getWriter() %>"
                     id="reviewWriter"  placeholder="비회원"  readonly />                 
             <%}else{ %>
             	 <input type="text" name="writer" value='<%=memberLoggedIn.getMemberId()%>'
                     id="reviewWriter"  readonly />              
             <%} %>        
		</td>
	</tr>
	<tr>
		<th>평점</th>
		<td>
		<p class="star_rating">
               
               <%for(int i=1 ; i <= br.getRate() ; i++) {%>
                             <a href="#" name="rate" id="<%=i %>" value="<%=i %>" class="on">★</a> <% }%>
                 <%for(int i=1 ; i <= (5-br.getRate()) ; i++) {
                 graystar = i+5-br.getRate()+1 ;
                         if(br.getRate() == 4){ graystar = 5;}%>
                   <a href="#" name="rate" id="<%=graystar%>" value="<%=graystar%>" >★</a><% }%>
                
        </p>                          
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		
		<td style="position:relative;">			
	
			<input type="file" name="up_file" >
			<span id="fname"><%=br.getOfile()!=null?br.getOfile():"" %></span>
			
			<!-- 파일 변경시 삭제-->
			<input type="hidden" name="old_renamed_file" value="<%=br.getRfile()%>" />
			<input type="hidden" name="old_original_file" value="<%=br.getOfile()%>" />
			
			<%if(br.getOfile() != null){ %>
			<br />
			<input type="checkbox" name="del_file" id="del_file" />
			<label for="del_file">첨부파일 삭제</label>
			<%} %>
		
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="50" name="content"><%=br.getContent() %></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" class="btn btn-primary btn-lg" value="수정하기" onclick="return validate();">
		</th>
	</tr>
	
</table>
</form>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>