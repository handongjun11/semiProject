package semi.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.board.model.service.BoardService;
import semi.kh.board.model.vo.Board;

/**
 * Servlet implementation class QnaChkBoardPwdEnd
 */
@WebServlet("/board/ChkQnaBoardPwdEnd")
public class QnaChkBoardPwdEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaChkBoardPwdEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.인코딩
				request.setCharacterEncoding("utf-8"); 
				
		//1.파라미터
				int boardNo = Integer.parseInt(request.getParameter("boardNo"));
				
				//2.업무로직
				Board b = new BoardService().selectByBoardNo(boardNo);
				
				
				//3.뷰단처리
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
