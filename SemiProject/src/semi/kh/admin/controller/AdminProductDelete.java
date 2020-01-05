package semi.kh.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.kh.admin.model.service.AdminService;

/**
 * Servlet implementation class AdminProductDelete
 */
@WebServlet("/admin/deleteProduct")
public class AdminProductDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.printf("@AdminProductDelete In!!!!!!!!!!!\n"); ㅇㅋ찍힘
		
		request.setCharacterEncoding("utf-8");
		
		String orderNo = request.getParameter("orderNo");
//		System.out.printf("orderNo = %s\n",orderNo); ㅇㅋ찍힘
		int result = new AdminService().deleteProduct(orderNo);
//		System.out.printf("[result = %s]\n",result); ㅇㅋ찍힘
		
		String msg = "";
		String loc = "/";
		String view = "/WEB-INF/views/common/msg.jsp";
		
		if(result > 0) {
			msg = "주문취소에 성공 하였습니다.";
			loc = "/admin/notShipped";
			//세션가져오기
			HttpSession session = request.getSession(true);
//			http://localhost:9090/hdj/admin/notShipped
//			http://localhost:9090/hdj/WEB-INF/views/admin/orderNoView.jsp
			//
//			HttpSession session = request.getSession(false);로 해놨더니 세션이 풀리지 개멍청했네ㅅㄱ
			
			
		}
		else {
			msg = "주문취소에 실패하셨습니다.";
			loc = "/WEB-INF/views/admin/orderView.jsp";
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
