package semi.kh.product.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.kh.product.model.service.ProductService;
import semi.kh.product.model.vo.Buy;
import semi.kh.product.model.vo.BuyList;
import semi.kh.product.model.vo.Cart;
import semi.kh.product.model.vo.ProductIO;

/**
 * Servlet implementation class ProductBuyEndServlet
 */
@WebServlet("/product/productBuyEnd")
public class ProductBuyEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		
		String memberId = request.getParameter("memberId");
		String resName = request.getParameter("resName");
		String resAddress = request.getParameter("resAddress");
		String resPhone = request.getParameter("resPhone");
		String resRequirement = request.getParameter("resRequirement");
		String resRequirementEtc = request.getParameter("resRequirementEtc");
		int totalPrice = Integer.parseInt(request.getParameter("afterPrice"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int rndNum = (int)(Math.random()*1000);
		String orderNo = sdf.format(new Date())+rndNum;
		
		Buy b = new Buy();
		b.setOrderNo(orderNo);
		b.setMemberId(memberId);
		b.setTotalPrice(totalPrice);
		b.setResName(resName);
		b.setResAddress(resAddress);
		b.setResPhone(resPhone);
		if(resRequirement.equals("기타")) {
			b.setResRequirement(resRequirementEtc);
		}
		else {
			b.setResRequirement(resRequirement);			
		}
		
		int result1 = new ProductService().insertBuy(b);
		int result2 = 0;
		int result3 = 0;
		
		Cart sc = (Cart)session.getAttribute("sc");
		
		if(sc==null) {
			List<Cart> list = new ProductService().selectCartList(memberId);
			BuyList bl = null;
			ProductIO pi = null;
			
			for(Cart c : list) {
				bl = new BuyList();
				bl.setOrderNo(orderNo);
				bl.setMemberId(memberId);
				bl.setpId(c.getpId());
				bl.setpName(c.getpName());
				bl.setAmount(c.getAmount());
				bl.setpPrice(c.getpPrice());
				result2 = new ProductService().insertBuyList(bl);
				
				pi = new ProductIO();
				pi.setOrderNo(orderNo);
				pi.setMemberId(memberId);
				pi.setpId(c.getpId());
				pi.setAmount(c.getAmount());
				pi.setStatus("O");
				result3 = new ProductService().insertProductIO(pi);
			}
		}
		else {
			BuyList bl = new BuyList();
			bl.setOrderNo(orderNo);
			bl.setMemberId(memberId);
			bl.setpId(sc.getpId());
			bl.setpName(sc.getpName());
			bl.setAmount(sc.getAmount());
			bl.setpPrice(sc.getpPrice());
			result2 = new ProductService().insertBuyList(bl);
		
			ProductIO pi = new ProductIO();
			pi.setOrderNo(orderNo);
			pi.setMemberId(memberId);
			pi.setpId(sc.getpId());
			pi.setAmount(sc.getAmount());
			pi.setStatus("O");
			result3 = new ProductService().insertProductIO(pi);
		}
		
		session.setAttribute("sc", null);
		int result4 = new ProductService().cartClear(memberId);
		
		String msg = "";
		
		if(result3 >0) {
			msg = "주문 성공!";
		}else {
			msg="주문 실패!";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", "/");
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
