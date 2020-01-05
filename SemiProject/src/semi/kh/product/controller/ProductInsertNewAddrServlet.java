package semi.kh.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.product.model.service.ProductService;

/**
 * Servlet implementation class ProductInsertNewAddrServlet
 */
@WebServlet("/product/insertnewAddr")
public class ProductInsertNewAddrServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("서블릿~");
		
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("memberId");
		String addr = request.getParameter("addr");
		
		int result = new ProductService().insertNewAddr(memberId, addr);
		
		//String referer = request.getHeader("Referer");
		//response.sendRedirect(referer);
		
		//request.getRequestDispatcher("/WEB-INF/views/product/productBuy.jsp").forward(request, response);
		
		//referer = http://localhost:9090/mall/product/productBuy

		//request.getRequestDispatcher("/WEB-INF/views/product/productList.jsp").forward(request, response);
		
		
		
//		String referer = request.getHeader("Referer"); //http://localhost:9090/mvc/board/boardView?boardNo=99
//		String origin = request.getHeader("Origin"); //http://localhost:9090 (크롬 외 브라우저에서는 null)
//		String url = request.getRequestURL().toString(); //http://localhost:9090/mvc/member/login
//		String uri = request.getRequestURI(); //mvc/member/login
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
