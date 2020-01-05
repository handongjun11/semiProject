package semi.kh.member.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.kh.member.model.service.MemberService;
import semi.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberViewServlet
 */
@WebServlet(name="MemberViewServlet",urlPatterns="/member/memberView")
public class MemberViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
	
		Member m = new MemberService().selectOne(memberId);

		String view ="/WEB-INF/views/member/memberView.jsp";
		String msg ="";
		String loc = "/";
		
		
		
		if(m == null) {
			view = "/WEB-INF/views/common/msg.jsp";
			msg="해당 회원이 존재하지 않습니다.";
		}
		
		request.setAttribute("member", m);
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
		reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
