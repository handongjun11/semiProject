<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	List<Member> list = (List<Member>)request.getAttribute("list");
	System.out.println("list@memberList.jsp="+list);
	
	int cPage = (int)request.getAttribute("cPage");
	int numPerPage = (int)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
	
%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/admin.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/memberList.css" />
<script>

//회원 목록 검색할때 
$(function(){
	var sid = $("#search-memberId");
	var sname = $("#search-memberName");
	var sgender = $("#search-gender");
	
	var sgrade = $("#search-grade"); //등급 추가
	
	$("select#searchType").change(function(){
		sid.hide();
		sname.hide();
		sgender.hide();
		sgrade.hide(); //등급
		
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
			<option value="memberId">아이디</option>
			<option value="memberName">회원명</option>
			<option value="gender">성별</option>			
			
			<option value="grade">등급</option> <!-- 등급추가 -->
		</select>
		
		<div id="search-memberId">
			<!-- 아이디 검색폼 -->
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" 
					   name="numPerPage" 
					   value="<%=numPerPage%>"/>
				<input type="hidden" 
					   name="searchType"
					   value="memberId" />
				<input type="search" 
					   name="searchKeyword"
					   size="25"
					   placeholder="검색할 아이디를 입력하세요."/>
				<button type="submit">검색</button>
			</form>
		</div>
		
		<div id="search-memberName">
			<!-- 회원명 검색폼 -->
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" 
					   name="numPerPage" 
					   value="<%=numPerPage%>"/>
				<input type="hidden" 
					   name="searchType"
					   value="memberName" />
				<input type="search" 
					   name="searchKeyword"
					   size="25"
					   placeholder="검색할 회원명을 입력하세요."/>
				<button type="submit">검색</button>
			</form>
		</div>
		
		<div id="search-gender">
			<!-- 성별 검색폼 -->
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" 
					   name="numPerPage" 
					   value="<%=numPerPage%>"/>
				<input type="hidden" 
					   name="searchType"
					   value="gender" />
					   
				<input type="radio" 
					   name="searchKeyword"
					   value="M"
					   id="gender0"/>
				<label for="gender0">남</label>
				<input type="radio" 
					   name="searchKeyword"
					   value="F"
					   id="gender1"/>
				<label for="gender1">여</label>
				<button type="submit">검색</button>
			</form>
		</div>
		
		
		<div id="search-grade">
			<!-- 등급 검색폼 -->
			<form action="<%=request.getContextPath()%>/admin/memberFinder">
				<input type="hidden" 
					   name="numPerPage" 
					   value="<%=numPerPage%>"/>
				<input type="hidden" 
					   name="searchType"
					   value="grade" />
					   
				<input type="radio" 
					   name="searchKeyword"
					   value="일반"
					   id="grade0"/>
				<label for="grade0">일반</label>
				<input type="radio" 
					   name="searchKeyword"
					   value="Gold"
					   id="grade1"/>
				<label for="grade01">Gold</label>
				<input type="radio" 
					   name="searchKeyword"
					   value="VIP"
					   id="grade2"/>
				<label for="grade2">VIP</label>
				<input type="radio" 
					   name="searchKeyword"
					   value="VVIP"
					   id="grade3"/>
				<label for="grade3">VVIP</label>
				<button type="submit">검색</button>
			</form>
		</div>
		
		
	</div>
	<!-- 검색 끝 -->
	<table id="tbl-member12">
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
				<!-- 
				<th>탈퇴여부</th>
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
				<td><!-- <input type="checkbox" name="AdminDeleteMember" id="AdminDeleteMember"/> 아직보류 -->
					<a href="<%=request.getContextPath()%>/member/memberView?memberId=<%=m.getMemberId()%>">
						<%=m.getMemberId() %>
					</a>
				</td>
				<td><%=m.getMemberName() %></td>
				<td><%="M".equals(m.getGender())?"남":"여"%></td>
				<td><%=m.getBirth() %></td>
			<%-- 	<td><%=m.getEmail()!=null?m.getEmail():"" %></td>
				
				<td><%=m.getPhone() %></td>
				<td><%=m.getFavorite()!=null?m.getFavorite():"" %></td> --%>
				<td><%=m.getGrade()!=null?m.getGrade():""%></td>
				<td><%=m.getEnrollDate() %></td>
				
					
				<%-- <td><%=m.getDelFlag() %></td>			
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







