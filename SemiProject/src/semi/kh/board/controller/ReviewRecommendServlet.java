package semi.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.kh.board.model.service.BoardService;
import sun.nio.cs.HistoricallyNamedCharset;

/**
 * Servlet implementation class ReviewRecommendServlet
 */
@WebServlet("/review/recommend.do")
public class ReviewRecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewRecommendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String memberid = request.getParameter("memberid");
		//	int recommend =Integer.parseInt(request.getParameter("like"))+1;
			
			
			String referer = request.getHeader("Referer");
			System.out.println("referer"+referer);
			
			//int result = new BoardService().recommendPlus(bno);
			
			int result= 0;
			//boardCookie 확인
			Cookie[] cookies = request.getCookies();
			String recommendCookieVal= "";
			boolean Recommended = false;
			//왜냐면 최초 접속은 null을 리턴하니까 null을 잡아야함
			int bno =0;
			//Cookie개게는 반드시 null여부 검사후 진행
			if(cookies != null) {
				for(Cookie c: cookies) {
					String name = c.getName();
					String value = c.getValue();
					System.out.println("nname!!!!!!!!!!!1" + name);
					 bno = Integer.parseInt(request.getParameter("bno"));
					//boardCookie인 경우
					if("recommendCookie".equals(name)) {
						recommendCookieVal = value;
						System.out.println("value!!!!!!!!"+recommendCookieVal);
						//새로 쿠키를 곧 만든다(매번) -> 그때 사용하려고 담아둠
						if(value.contains("|"+bno+memberid+"|")) { //이 문자열이 있니?
							Recommended = true;
							int newRecommend = new BoardService().selectRecommend(bno);
							new Gson().toJson(newRecommend,response.getWriter());
							
							
							break; //읽었다고 하고 반복문 중지.
						}
					}
					
				}
			}
			//boardCookie에서 읽음여부에 따라 부닉
			if(!Recommended) { //읽지 않았다면 , hasRead == false
				
				result = new BoardService().recommendPlus(bno);
				
				//새로 생성: 현재 게시물 번호 추가 
				Cookie recommendCookie = new Cookie("recommendCookie", recommendCookieVal + "|" + bno+memberid + "|");
				recommendCookie.setPath("/hdj");
		//		boardCookie.setMaxAge();영속설정 삭제하지 않으면 계쏙 있음
//				boardCookie.setPath("/mvc/board"); //현재디렉토리 기준으로 설정
				response.addCookie(recommendCookie); 
				
			}
			
			
			
			if(result >0) {
				System.out.println("성공");
				int newRecommend = new BoardService().selectRecommend(bno);
				System.out.println("서블릿"+newRecommend);
				new Gson().toJson(newRecommend,response.getWriter());
				
			}else {
				
				
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
