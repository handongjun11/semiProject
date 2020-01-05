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
 * Servlet implementation class QnaBoardUpdateServlet
 */
@WebServlet("/board/qnaBoardUpdateEnd")
public class QnaBoardUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardUpdateEndServlet() {
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
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String writer = request.getParameter("writer");
		String lockflag = request.getParameter("lockflag")==null?"N":"Y";
		String content = request.getParameter("content");
		String password = request.getParameter("password");
		
		//System.out.println("업데이트 서블릿"+boardNo);
		//3.업무로직
		
		Board b = new Board();
		b.setBoardNo(boardNo);
		b.setBoardTitle(title);
		b.setBcategory(category);
		b.setbWriter(writer);
		b.setbLockFlag(lockflag);
		b.setBoardPwd(password);
		b.setbConetent(content);
		
		int result = new BoardService().updateQnaBoard(b);
		
		//4.view단 처리 
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/boardView?boardNo="+boardNo;
		
		if(result >0 ) {
			msg ="게시물 수정성공";
			
		}else {
			msg = "수정실패";
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
