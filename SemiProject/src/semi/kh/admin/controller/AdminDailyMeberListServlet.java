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

/**
 * Servlet implementation class AdminDailyMeberListServlet
 */
@WebServlet("/admin/dailyMemberList")
public class AdminDailyMeberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.관리자여부 체크
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
				
//				System.out.printf("[dailyMemberListServlet in!!!!!!!]"); //여기까지 찍힘
				
				//1.파라미터핸들링
				int cPage;
				try {
					cPage = Integer.parseInt(request.getParameter("cPage"));
				} catch(NumberFormatException e) {
					cPage = 1;
				}
				
				int numPerPage;
				try {
					numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
				} catch(NumberFormatException e) {
					numPerPage = 5;
				}
				
				//System.out.printf("[cPage=%s, numPerPage=%s]\n", cPage, numPerPage);
				
				
				//2.업무로직
				//2.1.컨텐츠영역
				List<Member> list = new AdminService().selectDailyMemberList(cPage, numPerPage);
				//System.out.printf("[list = %s]\n", list);
				
				//2.2.페이지바영역
				//전체컨텐츠수(전체회원수)를 구하기
				int totalContent = new AdminService().selectDailyMemberCount();
				//System.out.printf("[totalContent=%s]\n", totalContent);
				
				//(공식2) 전체페이지수구하기
				//totalContent, numPerPage
				//예) numPerPage=10, totalContent=103 => totalPage=11
				int totalPage = (int)Math.ceil((double)totalContent/numPerPage);
				//System.out.printf("[totalPage=%s]\n",totalPage);
				
				//페이지바 html
				String pageBar = "";
				//페이지바 길이
				int pageBarSize = 5;
				//(공식3) 시작페이지startPage 번호세팅
				//cPage=5, pageBarSize=5 -> 1
				//cPage=6, pageBarSize=5 -> 6
				int startPage = ((cPage-1)/pageBarSize) * pageBarSize + 1;
				int endPage = startPage + pageBarSize - 1;
				//System.out.printf("[start=%s, end=%s]\n", startPage, endPage);
				//페이지증감변수 
				int pageNo = startPage;
				
				//[이전]section
				if(pageNo == 1) {
					
				}
				else {
					pageBar += "<a href='"+request.getContextPath()+
										 "/admin/dailyMemberList?"+
										 "cPage="+(pageNo-1)+
										 "&numPerPage="+numPerPage+"'>[이전]</a>";
				}
				
				//[페이지]section
				while(pageNo<=endPage && pageNo<=totalPage) {
					if(cPage == pageNo) {
						pageBar += "<span class='cPage'>"+pageNo+"</span>";
					}
					else {
						pageBar += "<a href='"+request.getContextPath()+
								   "/admin/dailyMemberList?"+
								   "cPage="+pageNo+
								   "&numPerPage="+numPerPage+"'>"+
								   pageNo+"</a>";
					}
					pageNo++;
				}
				
				//[다음]section
				if(pageNo > totalPage) {
					
				} 
				else {
					pageBar += "<a href='"+request.getContextPath()+
							   "/admin/dailyMemberList?"+
							   "cPage="+pageNo+
							   "&numPerPage="+numPerPage+"'>[다음]</a>";
				}
				
				//3.view단처리
				request.setAttribute("list", list);
				request.setAttribute("pageBar", pageBar);
				request.setAttribute("cPage", cPage);
				request.setAttribute("numPerPage", numPerPage);
				request.getRequestDispatcher("/WEB-INF/views/admin/dailyMemberList.jsp")
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
