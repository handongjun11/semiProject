<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<style>
#wrapper{
	width : 300px;
	margin:0 auto;
	margin-top:150px;
	position:relative;
}
#bbttnn{
	position:absolute;
	left:80px;
	top:130px;
}
#outside{
	margin-top: -121px;
}
#content{
	background: none;
}
</style>
<div id="outside">
<section >
<div id="wrapper">
<form name="noneOrderViewFrm"  action="<%=request.getContextPath() %>/product/noneOrderViewEnd" >
<div class="input-group">
	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
	<input id="orderno" type="text" name="orderno" class="form-control" placeholder="주문번호를 입력하세요.">
</div>
<div class="input-group">
	<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
	<input type="password" class="form-control" name="password" placeholder="비밀번호를 입력하세요.">
</div >
<input type=submit id="bbttnn" class="btn btn-default" value="주문정보 조회하기"  >
</form>
</div>
</section>
</div>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>	