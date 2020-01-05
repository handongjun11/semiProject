package semi.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.kh.board.model.service.BoardService;
import semi.kh.board.model.vo.Board;
import semi.kh.member.model.vo.Member;

/**
 * Servlet implementation class ShipQnaBoardFormEndServlet
 */
@WebServlet("/board/shipQnaBoardFormEnd")
public class ShipQnaBoardFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.인코딩
		request.setCharacterEncoding("utf-8"); 
		
		//2.파라미터핸들링
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String orderNo = request.getParameter("orderNo");
		String writer = request.getParameter("writer");
		String cotent = request.getParameter("content");
		
		String password = request.getParameter("password")==null?"":request.getParameter("password");
		
		Board b = new Board();
		b.setBoardTitle(title);
		b.setBcategory(category);
		b.setbOdrderNo(orderNo);
		b.setbWriter(writer);
		b.setbConetent(cotent);

		//3.업무로직
		int boardNo = new BoardService().insertShipQnaBoard(b);
		
		
		//4.뷰단처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/";
		
		if(boardNo > 0) {
			
			msg = "성공적으로 게시글을 등록했습니다.";
//					loc="/board/boardList?boardNo="+boardNo;
			loc="/member/showMyBuyList?memberId="+writer;
			
		}
		else {
			msg = "게시글 등록에 실패했습니다.";	
			loc="/board/shipQnaBoardForm";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
	
		if(password.equals("")) {
			request.getRequestDispatcher(view).forward(request, response);
		}
		else {
			//System.out.println("여기 들어왔니?");
			loc="/product/noneOrderViewEnd?orderno="+orderNo+"&password="+password;
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);			
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
