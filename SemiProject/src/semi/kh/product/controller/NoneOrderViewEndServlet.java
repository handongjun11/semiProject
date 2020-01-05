package semi.kh.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.board.model.vo.Board;
import semi.kh.board.model.vo.BoardReview;
import semi.kh.member.model.vo.Member;
import semi.kh.product.model.service.ProductService;
import semi.kh.product.model.vo.Buy;
import semi.kh.product.model.vo.BuyList;

/**
 * Servlet implementation class NoneOrderViewEndServlet
 */
@WebServlet("/product/noneOrderViewEnd")
public class NoneOrderViewEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoneOrderViewEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		
		//파라미터 핸들링
		String orderNo = request.getParameter("orderno");
		String password = request.getParameter("password");
		
		//업무로직
		Member m  = new ProductService().selectNoneMemberIdChk(orderNo , password);
		
		
		//3.뷰단처리
			String view = "/WEB-INF/views/common/msg.jsp";
			String msg = "";
			String loc = "/";
				
	
		 if(m == null) {
			 
			 request.setAttribute("msg", "일치하는 정보가 없습니다.");
			 request.setAttribute("loc", "/product/noneOrderView");
			  loc = "product/noneOrderView";
			 
			 
		 }else {
			 
			 List<Buy> list = new ProductService().showNMBuyList(orderNo);
			 List<BuyList> buyList = new ProductService().selectNoneBuyList(orderNo);
			 List<Board> blist = new ProductService().selectNMShipBoardList(orderNo);
			 List<BoardReview> rlist = new ProductService().selectNMReviewList(orderNo);
			 
			 
			 //주문내역
			 request.setAttribute("orderno", orderNo);
			 request.setAttribute("password", password);
			 request.setAttribute("list", list);
			 request.setAttribute("buyList", buyList);
			 request.setAttribute("blist", blist);
			 request.setAttribute("rlist", rlist);
			 view = "/WEB-INF/views/product/noneOrderViewEnd.jsp" ;
		
		 }
	
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
