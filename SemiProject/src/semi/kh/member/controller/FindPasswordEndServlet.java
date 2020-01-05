package semi.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.common.ExampleSend;
import semi.kh.member.model.service.MemberService;
import semi.kh.member.model.vo.Member;

/**
 * Servlet implementation class FindPasswordServlet
 */
@WebServlet("/member/findPasswordEnd")
public class FindPasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPasswordEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String phone = request.getParameter("phone");
		
		
		String oldPhone = new MemberService().findPhone(memberId);

		
		String msg = "";
		String loc ="/";
		String view = "/WEB-INF/views/common/msg.jsp";
	
			if((phone).equals(oldPhone)) {
				new ExampleSend().main(memberId,phone);
				msg = "전송된 임시 비밀번호를 사용하여 로그인하세요.";
				String script="self.close()";
				request.setAttribute("msg",msg);
				request.setAttribute("loc",loc);
				request.setAttribute("script",script);
				request.getRequestDispatcher(view).forward(request,response);
				
				
			}else if(!((phone).equals(oldPhone))){
				msg="전화번호를 잘못입력하셨습니다.";
				loc="/member/findPassword";
				request.setAttribute("msg",msg);
				request.setAttribute("loc",loc);
				request.getRequestDispatcher(view).forward(request,response);
			}else {
				msg="아이디를 잘못입력하셨습니다.";
				loc="/member/findPassword";
				request.setAttribute("msg",msg);
				request.setAttribute("loc",loc);
				request.getRequestDispatcher(view).forward(request,response);
			}
			
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

