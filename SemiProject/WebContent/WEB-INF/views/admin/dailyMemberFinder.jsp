<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	List<Member> list = (List<Member>)request.getAttribute("list");
	//System.out.println("list@memberList.jsp="+list);
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	
	int numPerPage = (int)request.getAttribute("numPerPage");
	int cPage = (int)request.getAttribute("cPage");
	
	String pageBar = (String)request.getAttribute("pageBar");
	
	
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin.css" />
<style>
div#search-memberId{
	display: <%="memberId".equals(searchType)?"inline-block":"none"%>;
}
div#search-memberName{
	display: <%="memberName".equals(searchType)?"inline-block":"none"%>;
}
div#search-gender{
	display: <%="gender".equals(searchType)?"inline-block":"none"%>;
}
div#search-grade{
	display: <%="grade".equals(searchType)?"inline-block":"none"%>;
}
</style>
<script>
$(function(){
	var sid = $("#search-memberId");
	var sname = $("#search-memberName");
	var sgender = $("#search-gender");
	var sgrade = $("#search-grade");
	
	$("select#searchType").change(function(){
		sid.hide();
		sname.hide();
		sgender.hide();
		sgrade.hide();
		
		
		$("#search-"+$(this).val()).css("display","inline-block");
	});
	
});
</script>
<section id="memberList-container">
	<h2>회원관리</h2>
	<!-- 검색 시작 -->
	<div id="search-container">
		검색타입 : 
		<select id="searchType">
			<option value="memberId" <%="memberId".equals(searchType)?"selected":"" %>>아이디</option>
			<option value="memberName" <%="memberName".equals(searchType)?"selected":"" %>>회원명</option>
			<option value="gender" <%="gender".equals(searchType)?"selected":"" %>>성별</option>
			<option value="grade" <%="grade".equals(searchType)?"selected":"" %>>등급</option>
		</select>
		<div id="search-memberId">
			<!-- 아이디 검색폼 -->
			<form action="<%=request.getContextPath()%>/admin/dailyMemberFinder">
				<input type="hidden" 
					   name="numPerPage" 
					   value="<%=numPerPage%>"/>
				<input type="hidden" 
					   name="searchType"
					   value="memberId" />
				<input type="search" 
					   name="searchKeyword"
					   size="25"
					   placeholder="검색할 아이디를 입력하세요."
					   value="<%="memberId".equals(searchType)?searchKeyword:""%>"/>
				<button type="submit">검색</button>
			</form>
		</div>
		<div id="search-memberName">
			<!-- 회원명 검색폼 -->
			<form action="<%=request.getContextPath()%>/admin/dailyMemberFinder">
				<input type="hidden" 
					   name="numPerPage" 
					   value="<%=numPerPage%>"/>
				<input type="hidden" 
					   name="searchType"
					   value="memberName" />
				<input type="search" 
					   name="searchKeyword"
					   size="25"
					   placeholder="검색할 회원명을 입력하세요."
					   value="<%="memberName".equals(searchType)?searchKeyword:""%>"/>
				<button type="submit">검색</button>
			</form>
		</div>
		<div id="search-gender">
			<!-- 성별 검색폼 -->
			<form action="<%=request.getContextPath()%>/admin/dailyMemberFinder">
				<input type="hidden" 
					   name="numPerPage" 
					   value="<%=numPerPage%>"/>
				<input type="hidden" 
					   name="searchType"
					   value="gender" />
				<input type="radio" 
					   name="searchKeyword"
					   value="M"
					   id="gender0"
					   <%="gender".equals(searchType)&&"M".equals(searchKeyword)?"checked":"" %>/>
				<label for="gender0">남</label>
				<input type="radio" 
					   name="searchKeyword"
					   value="F"
					   id="gender1"
					   <%="gender".equals(searchType)&&"F".equals(searchKeyword)?"checked":"" %>/>
				<label for="gender1">여</label>
				<button type="submit">검색</button>
			</form>
		</div>
		
		<div id="search-grade">
			<!-- 등급 검색폼 -->
			<form action="<%=request.getContextPath()%>/admin/dailyMemberFinder">
				<input type="hidden" 
					   name="numPerPage" 
					   value="<%=numPerPage%>"/>
				<input type="hidden" 
					   name="searchType"
					   value="grade" />
					   
				<input type="radio" 
					   name="searchKeyword"
					   value="일반"
					   id="grade0"
					   <%="grade".equals(searchType)&&"일반".equals(searchKeyword)?"checked":"" %>/>
				<label for="grade0">일반</label>
				
				<input type="radio" 
					   name="searchKeyword"
					   value="Gold"
					   id="grade1"
					   <%="grade".equals(searchType)&&"Gold".equals(searchKeyword)?"checked":"" %>/>
				<label for="grade1">Gold</label>
				
				<input type="radio" 
					   name="searchKeyword"
					   value="VIP"
					   id="grade2"
					   <%="grade".equals(searchType)&&"VIP".equals(searchKeyword)?"checked":"" %>/>
				<label for="grade2">VIP</label>
				
				<input type="radio" 
					   name="searchKeyword"
					   value="VVIP"
					   id="grade3"
					   <%="grade".equals(searchType)&&"VVIP".equals(searchKeyword)?"checked":"" %>/>
				<label for="grade3">VVIP</label>
				<button type="submit">검색</button>
			</form>
		</div>
		
		
	</div>
	<!-- 검색 끝 -->
	<table id="tbl-member">
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<!-- <th>이메일</th>
				
				<th>전화번호</th>
				
				<th>선호기기</th> -->
				<th>등급</th>
				<th>가입일</th>
				
				<!-- <th>탈퇴여부</th>
				<th>탈퇴일</th>
				<th>비회원여부</th>
				<th>비밀번호</th>	
				<th>총구매비용</th> -->
			</tr>
		</thead>
		<tbody>
		<% if(list == null || list.isEmpty()){ %>
			<tr>
				<td colspan="6" align="center">
					검색결과가 없습니다.
				</td>
			</tr>
		<%} 
		else {
			for(Member m : list){
		%>
			
			
			<tr>
				<td>
					<a href="<%=request.getContextPath()%>/member/memberView?memberId=<%=m.getMemberId()%>">
						<%=m.getMemberId() %>
					</a>
				</td>
				<td><%=m.getMemberName() %></td>
				<td><%="M".equals(m.getGender())?"남":"여"%></td>
				<td><%=m.getBirth() %></td>
				<%-- <td><%=m.getEmail()!=null?m.getEmail():"" %></td>
				
				<td><%=m.getPhone() %></td>
				
				<td><%=m.getFavorite()!=null?m.getFavorite():"" %></td> --%>
				<td><%=m.getGrade()!=null?m.getGrade():""%></td>
				<td><%=m.getEnrollDate() %></td>
				<%-- 	
				<td><%=m.getDelFlag() %></td>			
				<td><%=m.getDeleteDate() %></td>		
				<td><%=m.getRegFlag() %></td>		
				<td><%=m.getPassword() %></td>		
				<td><%=m.getTotalPurchaseCost() %></td>	 --%>					
			</tr>
		<% }
		} %>
		</tbody>
	</table>
	<div id="pageBar">
	<%=pageBar %>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>







