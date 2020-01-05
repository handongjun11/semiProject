package semi.kh.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import semi.kh.member.model.service.MemberService;
import semi.kh.member.model.vo.Member;
import semi.kh.product.model.service.ProductService;
import semi.kh.product.model.vo.Cart;



/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		
		Member m = new Member();
		m.setMemberId(memberId);
		m.setPassword(password);
		Member memberLoggedIn = null;
		int result = new MemberService().loginCheck(m);

		String msg = "";
		String loc = "/";
		String view = "";
		
		String referer = request.getHeader("Referer");
		String origin = request.getHeader("Origin");
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI();
		
		
		if(origin == null) {
			origin = url.replace(uri,"");
		}
		
		
		if(result == MemberService.LOGIN_OK) {

			String saveId = request.getParameter("saveId");

			if(saveId != null) {
				Cookie c = new Cookie("saveId",memberId);
				c.setMaxAge(7*24*60*60);
				c.setPath("/hdj");
				response.addCookie(c);
				
			}else {
				 Cookie c = new Cookie("saveId",memberId);
				 c.setMaxAge(0);
				 c.setPath("/hdj");
				 response.addCookie(c);
				 
			}
			
			
			memberLoggedIn = new MemberService().selectOne(memberId);
			
			HttpSession session = request.getSession(true);
			
			
			//timeout설정 : web.xml보다 우선순위 높음, 단위 : 초 
//					session.setMaxInactiveInterval(30*60); // 30분
			
			

			String ip = request.getRemoteAddr();

			session.setAttribute("ip", ip);
			session.setAttribute("memberLoggedIn", memberLoggedIn);

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
			
			//비회원 로그인시 장바구니 추가되는 기능
			boolean isExist = false;
			List<Cart> listNM = new ProductService().selectCartList(NMId);	
			List<Cart> list = new ProductService().selectCartList(memberId);
			
			if(listNM!=null) {
				for(Cart cartNM : listNM) {
					for(Cart cart : list) {
						isExist = false;
						if(cartNM.getpId().equals(cart.getpId())) {
							int result1 = new ProductService()
									.addAmount(memberId, cartNM.getpId(), cartNM.getAmount());
							isExist = true;
							break;
						}
					}
					if(isExist==false) {
						cartNM.setMemberId(memberId);
						int result2 = new ProductService().addItem(cartNM);
					}
				}
				new ProductService().cartClear(NMId);
			}
			
			response.sendRedirect(referer);
			
		}else { //로그인 실패 
			view = "/WEB-INF/views/common/msg.jsp";
			if(result == MemberService.WRONG_PASSWORD) {
				msg = "패스워드를 잘못 입력하셨습니다.";
			}else if (result == MemberService.ID_NOT_EXIST) {
				msg="존재하지 않는 아이디 입니다.";
			}
			request.setAttribute("msg",msg);
			request.setAttribute("loc",loc);
			request.getRequestDispatcher(view).forward(request,response);
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
