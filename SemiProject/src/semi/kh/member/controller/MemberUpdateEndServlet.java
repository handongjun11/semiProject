package semi.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.member.model.service.MemberService;
import semi.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateEndServlet
 */
@WebServlet("/member/memberUpdateEnd")
public class MemberUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String memberName = request.getParameter("memberName");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String[] favoriteArr = request.getParameterValues("favorite");
		String favorite = "";
		
		if(favoriteArr!=null) favorite = String.join(",", favoriteArr);
		
		StringBuffer buf = new StringBuffer();
		String birth_st = String.valueOf(buf.append(year).append(month).append(day));
		int birth = Integer.parseInt(birth_st);
		
		Member m = new Member();
		m.setMemberId(memberId);
		m.setPassword(password);
		m.setMemberName(memberName);
		m.setGender(gender);
		m.setPhone(phone);
		m.setBirth(birth);
		m.setAddress(address);
		m.setEmail(email);
		m.setFavorite(favorite);
		int result = new MemberService().updateMember(m);
		
		String msg = "";
		String loc = "/";
		String view = "/WEB-INF/views/common/msg.jsp";
		
		if(result >0) {
			msg = "회원정보 업데이트 성공";
		}else {
			msg="회원정보 변경에 실패하였습니다.";
			loc = "/WEB-INF/views/member/memberView.jsp";
		}
		request.setAttribute("msg",msg);
		request.setAttribute("loc",loc);
		request.getRequestDispatcher(view).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
