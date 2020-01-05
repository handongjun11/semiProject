<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	String loc = request.getContextPath()+
				 (String)request.getAttribute("loc");
	String script = (String)request.getAttribute("script");
	String password = (String)request.getAttribute("password");
	//System.out.printf("[%s %s %s]\n", msg, loc, script);
%>
<script>
alert("<%=msg%>");
<%=script!=null?script:""%>
location.href = "<%=loc%>";
</script>