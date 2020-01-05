package semi.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.board.model.service.BoardService;

/**
 * Servlet implementation class QnaBoardDeleteServlet
 */
@WebServlet("/board/boardQnaDelete")
public class QnaBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.인코딩
		request.setCharacterEncoding("utf-8"); 
		
		//2.파라미터 핸들링
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		//3.업무로직
		
		int result = new BoardService().deleteQnaBoard(boardNo);
		
		
		//4.view단 처리 
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/boardList";
		
		if(result >0 ) {
			msg ="게시물 삭제 성공";
			
		}else {
			msg = "삭제실패";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
