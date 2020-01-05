package semi.kh.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.kh.member.model.vo.Member;
import semi.kh.product.model.service.ProductService;
import semi.kh.product.model.vo.Cart;
import semi.kh.product.model.vo.Product;

/**
 * Servlet implementation class ProductCartServlet
 */
@WebServlet("/product/productCart")
public class ProductCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(true);		
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		String NMId="";
		
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies){
			String key = c.getName();
			String value = c.getValue();
			if("NMId".equals(key)){
				NMId = value;
				break;
			}
		}
		
		String memberId = memberLoggedIn==null?NMId:memberLoggedIn.getMemberId();
		
		List<Cart> list = new ArrayList();
		list = new ProductService().selectCartList(memberId);	
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/product/productCart.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
