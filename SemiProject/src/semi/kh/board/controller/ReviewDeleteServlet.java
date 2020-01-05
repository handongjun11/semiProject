package semi.kh.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.board.model.service.BoardService;

/**
 * Servlet implementation class ReviewDeleteServlet
 */
@WebServlet("/review/deleteReview")
public class ReviewDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파라미터 핸들링
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		String rfile = request.getParameter("rfile")==null?"":request.getParameter("rfile");
		String memberId  = request.getParameter("memberId")==null?"":request.getParameter("memberId");
		String orderno = request.getParameter("orderno")==null?"":request.getParameter("orderno");
		String password = request.getParameter("password")==null?"":  request.getParameter("password");
		String NM = request.getParameter("NM")==null?"":request.getParameter("NM");
		
		//System.out.println("딜리트앤드서블릿"+boardno);
		
		//2.비지니스로직 처리
		//db의 게시글 삭제
		int result = new BoardService().deleteReview(boardno);
		
		//첨부파일 삭제
		if(result >0 & !"".equals(rfile)) { 
			String saveDirectory = getServletContext().getRealPath("/upload/board/");
			File delFile = new File(saveDirectory+rfile);
			//System.out.println("delFile="+ delFile);
			//파일삭제
			delFile.delete(); // boolean을 리턴
			//System.out.println("파일삭제 여부 :"+delFile.delete()); // true 아님 false
	
			
			
		}
		//3.뷰단처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/member/showMyReviewList?memberId="+memberId;
		
		if(result > 0) {
			msg = "게시글 삭제 성공!";
			if(NM.equals("")) {
				
			}else {
				loc = "/product/noneOrderViewEnd?orderno="+orderno+"&password="+password;
			}
		}else {
			
			msg = "게시글 삭제 실패!";
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
