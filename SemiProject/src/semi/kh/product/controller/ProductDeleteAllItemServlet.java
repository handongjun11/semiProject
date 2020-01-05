package semi.kh.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.kh.member.model.vo.Member;
import semi.kh.product.model.service.ProductService;

/**
 * Servlet implementation class ProductDeleteItemServlet
 */
@WebServlet("/product/productDeleteAllItem.do")
public class ProductDeleteAllItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(true);		
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		
		String NMId = "";
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
		
		int result = new ProductService().deleteAllItem(memberId);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
