package semi.kh.member.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.kh.member.model.service.MemberService;
import semi.kh.member.model.vo.Member;

/**
 * Servlet implementation class updatePasswordEndServlet
 */
@WebServlet("/member/updatePasswordEnd")
public class UpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String memberId = request.getParameter("memberId");
		String password_new = request.getParameter("password_new");
		String password = request.getParameter("password");
		Member m = new Member();
		m.setMemberId(memberId);
		m.setPassword(password);
		
		int result = new MemberService().loginCheck(m);
		String msg = "";
		String loc = "";
		
		String view = "/WEB-INF/views/common/msg.jsp";
		
		if(result==MemberService.LOGIN_OK) {
			m.setPassword(password_new);
			result = new MemberService().passwordUpdate(m);
			
			
			if(result > 0) {
				msg="패스워드 변경 성공";
				String script ="self.close();";
				request.setAttribute("script", script);
			}else {
				msg="패스워드 변경 실패";
				loc="/member/updatePassword?memberId="+memberId;
			}

		}else {
				
			msg="아이디 및 비밀번호를 잘못입력하셨습니다.";
			loc="/member/updatePassword?memberId="+memberId;
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
