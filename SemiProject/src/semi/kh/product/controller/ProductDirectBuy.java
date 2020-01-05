package semi.kh.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.kh.member.model.vo.Member;
import semi.kh.product.model.vo.Cart;

/**
 * Servlet implementation class ProductDirectBuy
 */
@WebServlet("/product/productDirectBuy")
public class ProductDirectBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String pId = request.getParameter("pId");
		String pName = request.getParameter("pName");
		int pPrice = Integer.parseInt(request.getParameter("pPrice"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		
		Cart sc = new Cart();
		sc.setpId(pId);
		sc.setpName(pName);
		sc.setpPrice(pPrice);
		sc.setAmount(amount);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("sc", sc);
		
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		if(memberLoggedIn == null) {
			request.getRequestDispatcher("/WEB-INF/views/product/productBuyByNM.jsp").forward(request, response);			
		}
		else {
			request.getRequestDispatcher("/WEB-INF/views/product/productBuy.jsp").forward(request, response);			
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
