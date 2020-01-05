package semi.kh.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.admin.model.service.AdminService;
import semi.kh.member.model.vo.Member;

@WebServlet("/admin/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.관리자가 아닌 경우
		Member memberLoggedIn = (Member)request.getSession()
									   		   .getAttribute("memberLoggedIn");
		if(memberLoggedIn == null ||
		   !"admin".equals(memberLoggedIn.getMemberId())) {
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
				   .forward(request, response);
			return;
		}	
		
		//1.parameterHandling
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		//System.out.printf("[%s : %s]\n", searchType, searchKeyword);
		
		//cPage, numPerPage에 대한 파라미터처리
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));			
		}catch(NumberFormatException e) {
			cPage = 1;//초기화.
		}
		
		int numPerPage;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));			
		}catch(NumberFormatException e) {
			numPerPage = 5;//초기화.
		}
		//System.out.printf("[searchType=%s, searchKeyword=%s, cPage=%s, numPerPage=%s]\n",searchType, searchKeyword, cPage, numPerPage);
		
		
		//2.businessLogic요청
		//2.1. 컨텐츠영역
		List<Member> list = null;
		switch (searchType) {
		case "memberId"	:list = new AdminService().selectMemberByMemberId(searchKeyword, cPage, numPerPage);break;
		case "memberName"	:list = new AdminService().selectMemberByMemberName(searchKeyword, cPage, numPerPage);break;
		case "gender"	:list = new AdminService().selectMemberByGender(searchKeyword, cPage, numPerPage);break;
		case "grade"	:list = new AdminService().selectMemberByGrade(searchKeyword, cPage, numPerPage);break;
		}
		
		//System.out.println("list@AdminMemberFinderServlet="+list);
		
		//2.2.페이지바영역.
		//검색된 전체회원수
		int totalMember = 0;
		switch (searchType) {
		case "memberId"	:totalMember = new AdminService().selectMemberCountByMemberId(searchKeyword);break;
		case "memberName" :totalMember = new AdminService().selectMemberCountByMemberName(searchKeyword);break;
		case "gender"	:totalMember = new AdminService().selectMemberCountByGender(searchKeyword);break;
		case "grade"	:totalMember = new AdminService().selectMemberCountByGrade(searchKeyword);break;
		}
		
		//totalPage구하기 => startPage, endPage
		int totalPage = (int)Math.ceil((double)totalMember/numPerPage);
		//System.out.println("totalMember="+totalMember+", totalPage="+totalPage);
		
		//페이지바 길이
		int pageBarSize = 5;
		
		int pageStart = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		//종료페이지 번호 세팅
		int pageEnd = pageStart+pageBarSize-1;
		
		//증감변수 pageNo;
		int pageNo = pageStart;
		
		//System.out.printf("[totalPage=%s]\n", totalPage);
		//System.out.printf("[pageStart=%s, pageEnd=%s]\n", pageStart, pageEnd);
		//pageBar : html
		//(주의) 검색어를 링크에 어떻게 포함시킬것인가
		String pageBar = "";
		
		//이전 section
		if(pageNo == 1 ){

		}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/memberFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+(pageNo-pageBarSize)+"&numPerPage="+numPerPage+"'>[이전]</a> ";
		}
		// pageNo section
		// 보통 !(빠져나가는 조건식)으로 많이 쓴다.
//		while(pageNo<=pageEnd && pageNo<=totalPage){
		while(!(pageNo > pageEnd || pageNo > totalPage)){
			if(cPage == pageNo ){
				pageBar += "<span class='cPage'>"+pageNo+"</span> ";
			} 
			else {
				pageBar += "<a href='"+request.getContextPath()+"/admin/memberFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"&numPerPage="+numPerPage+"'>"+pageNo+"</a> ";
			}
			pageNo++;
		}
		
		//다음 section
		if(pageNo > totalPage){
			
		} else {
			pageBar += "<a href='"+request.getContextPath()+"/admin/memberFinder?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>";
		}
		
		//System.out.printf("[pageBar=%s]\n", pageBar);
		//3.view
		request.setAttribute("list", list);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchKeyword", searchKeyword);
		request.setAttribute("cPage",cPage);
		request.setAttribute("pageBar",pageBar);
		request.setAttribute("numPerPage", numPerPage);
		request.getRequestDispatcher("/WEB-INF/views/admin/memberFinder.jsp")
			   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


