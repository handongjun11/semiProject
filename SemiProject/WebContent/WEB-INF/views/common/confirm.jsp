<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	String loc1 = (String)request.getAttribute("loc1");
	String loc2 = (String)request.getAttribute("loc2");
%>
<script>
if(confirm("<%=msg %>")){
	location.href = "<%=request.getContextPath()+loc1 %>";	
}
else{
	location.href = "<%=request.getContextPath()+loc2 %>";	
};
</script>