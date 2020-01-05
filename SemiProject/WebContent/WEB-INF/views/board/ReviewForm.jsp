<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<%
	String pid = (String)request.getAttribute("pid");
	String memberid = (String)request.getAttribute("memberid");
	String password = (String)request.getAttribute("password");

%> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<style>
/*내용 작성 폼*/
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
        $("#userprofile").on('change', function(){
            readURL(this);
        });
   });

   function readURL(input){
       if(input.files && input.files[0]){
           var reader = new FileReader();

           reader.onload = function(e){
               $('#profilePre').attr('src', e.target.result);
           }

           reader.readAsDataURL(input.files[0]);
           $('#profilePre').css("display", "block");
       }
   }
</script>


<div id="review-div">
<h2>게시판 작성</h2>
<form action="<%=request.getContextPath() %>/review/reviewFrmEnd" enctype="multipart/form-data" method="post">
 <!--  파일 업로드 속성 추가 enctype="multipart/form-data" : 파일 업로드를 위한 필수 속성-->
  <input type="hidden" name="star" value="1" >
	<table id="tbl-board-review" class="table" style="width:600px;">
	<input type="hidden" name="memberid" value=<%=memberid%> >
	<input type="hidden" name="pid" value=<%=pid %> >
	<input type="hidden" name="password" value="<%=password%>">
	<tr>
		<th>카테고리</th>
		<td><input type="text" name="category" id="reviewcategory" value="상품후기" readonly ></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<%if(memberLoggedIn == null){ %>
               <input type="text" name="writer" value="비회원"
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
    			<a href="#" name="rate" id="1" value="1" class="on">★</a>
    			<a href="#" name="rate" id="2" value="2">★</a>
    			<a href="#" name="rate" id="3" value="3">★</a>
    			<a href="#" name="rate" id="4" value="4">★</a>
    			<a href="#" name="rate" id="5" value="5">★</a>
			</p>                                   
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>		
			<div id="div-profilePre">
			<input type="file" name="up_file" id="userprofile">
			<br /><br />
			<img id="profilePre" style="width:50px;">
			</div>	
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="50" name="content"></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" class="btn btn-primary btn-lg" value="등록하기" onclick="return validate();">
		</th>
	</tr>
	
</table>
</form>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>