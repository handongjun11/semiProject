package semi.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.board.model.service.BoardService;
import semi.kh.board.model.vo.Board;
import semi.kh.board.model.vo.BoardReview;

/**
 * Servlet implementation class ReviewUpdateServlet
 */
@WebServlet("/review/modify")
public class ReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.인코딩
		request.setCharacterEncoding("utf-8"); 
		
		//2.파라미터핸들링
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		String password = request.getParameter("password");
	
		
		BoardReview br = new BoardService().selectBybrBoardNo(boardno);
		
		request.setAttribute("br", br);
		request.setAttribute("password", password);
		String view = "/WEB-INF/views/board/ReviewView.jsp";
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
