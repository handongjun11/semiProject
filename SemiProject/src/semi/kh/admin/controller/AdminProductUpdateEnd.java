package semi.kh.admin.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.kh.admin.model.service.AdminService;
import semi.kh.product.model.vo.Buy;

/**
 * Servlet implementation class AdminProductUpdateEnd
 */
@WebServlet("/admin/productUpdateEnd")
public class AdminProductUpdateEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.printf("@AdminProductUpdateEnd In!!!!!!!!!!!!!!@\n"); //일단 찍힘
		
		//인코딩처리
		request.setCharacterEncoding("utf-8");
		
		//파라미터핸들링
//		휴대폰번호, 배송요청사항, 배송지만 수정
		String orderNo = request.getParameter("orderNo");
		String resAddress = request.getParameter("resAddress");
		String resPhone = request.getParameter("resPhone");
		String resRequirement = request.getParameter("resRequirement");
		
		
		Buy b = new Buy();
		b.setOrderNo(orderNo);		
		b.setResAddress(resAddress);
		b.setResPhone(resPhone);
		b.setResRequirement(resRequirement);
	
		//System.out.printf("b = %s",b); //값을 잘 set함;;;;;;
		
		
		//로직처리
		int result = new AdminService().updateProductList(b);
		//System.out.printf("@productUpdate 서블릿 /// result = %s\n", result);
		
		
		//view단 ㄱㄱ
		String msg = "";
		String loc = "/";
		String view = "/WEB-INF/views/common/msg.jsp";
		
		if(result > 0) {
			msg = "상품결제정보 변경 성공";
			loc = "/admin/notShipped";
			//세션가져오기
			HttpSession session = request.getSession(true);
		}
		else {
			msg="상품결제정보 변경에 실패하였습니다.";
			loc = "/admin/notShipped";
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
