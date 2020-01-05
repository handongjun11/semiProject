package semi.kh.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.board.model.service.BoardService;
import semi.kh.board.model.vo.Board;
import semi.kh.board.model.vo.BoardComment;

/**
 * Servlet implementation class QnaBoardViewServlet
 */
@WebServlet("/board/boardView")
public class QnaBoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardViewServlet() {
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
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
	//	System.out.println(boardNo);
		
		Board b = new BoardService().selectByBoardNo(boardNo);
		
		//3.업무로직
		//게시글에 대한 댓글 
		List<BoardComment> commentList = new BoardService().selectCommentList(boardNo);
				
				request.setAttribute("b", b);
				request.setAttribute("commentList", commentList);
		
		
		//4.뷰단 처리
//		request.setAttribute("b", b);
		String view = "/WEB-INF/views/common/msg.jsp";
		

		if(b == null) {
			request.setAttribute("msg", "상세조회실패");
			request.setAttribute("loc", "/board/boardList");
			
		}else {
		
			request.setAttribute("b", b);
			view = "/WEB-INF/views/board/QnaBoardView.jsp";
			
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
