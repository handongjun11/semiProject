package semi.kh.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.kh.member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(servletNames = { "MemberViewServlet" })
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest httpReq = (HttpServletRequest)request;
		HttpServletResponse httpRes = (HttpServletResponse)response;

		String uri = httpReq.getRequestURI();
		
		HttpSession session = httpReq.getSession(true);
		

		String checkId = request.getParameter("memberId");
		
		Member sessionId = (Member)session.getAttribute("memberLoggedIn"); //((HttpServletRequest)request).getAttribute()

		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "잘못된 경로로 접근하셨습니다!";
		String loc = "/";

		if(sessionId == null || checkId==null || !(checkId.equals(sessionId.getMemberId()) || "admin".equals(sessionId.getMemberId()))) {
			request.setAttribute("msg",msg);
			request.setAttribute("loc",loc);
			request.getRequestDispatcher(view).forward(request,response);
			
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
