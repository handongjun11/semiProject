package semi.kh.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.kh.admin.model.service.AdminService;
import semi.kh.member.model.vo.Member;
import semi.kh.product.model.service.ProductService;
import semi.kh.product.model.vo.Buy;
import semi.kh.product.model.vo.BuyList;
import semi.kh.product.model.vo.Cart;

/**
 * Servlet implementation class AdminProductOrderNoServlet
 */
@WebServlet("/admin/productOrderNo")
public class AdminProductOrderNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.printf("@AdminProductOrderNoServlet IN!!!!!!!\n");
		
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		//파라미터
		String orderNo = request.getParameter("orderNo");
		
		
		//로직처리
		List<Buy> list = new AdminService().selectProductOrderNo(orderNo);		
		//System.out.printf("[selectProductOrderNo = %s]\n",list);//ㅇㅋ찍힘
		
		List<BuyList> buyList = new AdminService().selectProductOrderNoList(orderNo);
		
		
		//view단처리
		request.setAttribute("list", list);
		request.setAttribute("buyList", buyList);
		request.getRequestDispatcher("/WEB-INF/views/admin/orderNoView.jsp").forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
