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
 * Servlet implementation class QnaBoardFormEnd
 */
@WebServlet("/board/qnaBoardFormEnd")
public class QnaBoardFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardFormEndServlet() {
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
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String writer = request.getParameter("writer");
		String lockflag = request.getParameter("lockflag")==null?"N":"Y";
		String cotent = request.getParameter("content");
		String password = request.getParameter("password");
		

		String pId = request.getParameter("pId")==null?"":request.getParameter("pId");
		
		if(writer.equals("admin")) {
			category = "공지사항";
			lockflag = "N";
		}
		
		Board b = new Board();
		b.setBoardTitle(title);
		b.setBcategory(category);
		b.setbWriter(writer);
		b.setbLockFlag(lockflag);
		b.setBoardPwd(password);
		b.setbConetent(cotent);
	

		b.setbPid(pId);
		

	
		//3.업무로직
		int boardNo = new BoardService().insertQnaBoard(b);
		
		
		//4.뷰단처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/";
		
		if(boardNo > 0) {
				msg = "성공적으로 게시글을 등록했습니다.";
			if("상품문의".equals(category)) {
				msg="상품문의글을 등록했습니다.";
				loc = "/product/productView?pId="+pId+"&category=qna";
			}else {
				loc="/board/boardList";				
			}			
//			loc="/board/boardList?boardNo="+boardNo;

		}
		else {
			msg = "게시글 등록에 실패했습니다.";	
			if("상품문의".equals(category)) {
				msg="상품문의글을 등록했습니다.";
				loc = "/product/productView?pId="+pId+"&category=qna";
			}else {
				loc="/board/qnaBoardForm";				
			}
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
