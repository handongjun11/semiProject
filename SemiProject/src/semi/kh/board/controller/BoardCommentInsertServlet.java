package semi.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.board.model.service.BoardService;
import semi.kh.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentInsertServlet
 */
@WebServlet("/board/boardCommentInsert")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentInsertServlet() {
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
		int cRef = Integer.parseInt(request.getParameter("boardRef"));
		String cWriter = request.getParameter("boardCommentWriter");
		String cContent = request.getParameter("boardCommentContent");

		String category = request.getParameter("category"); //qna
		String pId = request.getParameter("pId"); //상품코드
		
		//3.업무로직
		BoardComment b = new BoardComment();
		b.setcRef(cRef);
		b.setcWriter(cWriter);
		b.setcContent(cContent);
		
		int result = new BoardService().BoardCommentInsert(b);
		
		//4.뷰단처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/";

		if(result>0) {
			msg = "답변을 등록했습니다.";
			if("qna".equals(category)) {
				loc = "/product/productView?pId="+pId+"&category="+category;
			}else {
				loc ="/board/boardView?boardNo="+cRef;				
			}

		}else {
			msg = "답변 등록에 실패했습니다.";
			if("qna".equals(category)) {
				loc = "/product/productView?pId="+pId+"&category="+category;
			}else {
				loc ="/board/boardView?boardNo="+cRef;
			}
		}

		request.setAttribute("category",category);
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
