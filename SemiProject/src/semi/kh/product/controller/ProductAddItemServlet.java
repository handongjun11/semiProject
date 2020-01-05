package semi.kh.product.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

/**
 * Servlet implementation class ProductAddItemServlet
 */
@WebServlet("/product/productAddItem")
public class ProductAddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(true);		
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		
		String NMId = "";
		boolean NMIdExist = false;
		Cookie[] cookies = request.getCookies();
		for(Cookie c : cookies){
			String key = c.getName();
			String value = c.getValue();
			if("NMId".equals(key)){
				NMId = value;
				NMIdExist = true;
				break;
			}
		}
		if(NMIdExist==false) {
			NMId = ""+(int)(Math.random()*2000000000);
			Cookie nc = new Cookie("NMId", NMId);
			nc.setMaxAge(365*24*60*60);
			nc.setPath("/hdj");
			response.addCookie(nc);
		}
		
		String memberId = memberLoggedIn==null?NMId:memberLoggedIn.getMemberId();
		
		
		String pId = request.getParameter("pId");
		String pName = request.getParameter("pName");
		int pPrice = Integer.parseInt(request.getParameter("pPrice"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		boolean bool = false;
		
		
		List<Cart> list = new ProductService().selectCartList(memberId);
		
		for(Cart cart : list) {
			if(pId.equals(cart.getpId())) {
				int result = new ProductService().addAmount(memberId, pId, amount);
				bool = true;
			}
		}
		
		if(bool==false) {
			Cart c = new Cart();
			c.setpId(pId);
			c.setpName(pName);
			c.setpPrice(pPrice);
			c.setAmount(amount);
			c.setMemberId(memberId);
			
			int result = new ProductService().addItem(c);
		}
		
		String view = "/WEB-INF/views/common/confirm.jsp";
		String msg = "장바구니로 바로 이동하시겠습니까?";
		String loc1 = "/product/productCart";
		String loc2 = "/product/productView?pId="+pId;
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc1", loc1);
		request.setAttribute("loc2", loc2);

		request.getRequestDispatcher(view).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
