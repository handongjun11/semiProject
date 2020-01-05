package semi.kh.board.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.josephoconnell.html.HTMLInputFilter;
import com.oreilly.servlet.MultipartRequest;

import semi.kh.board.model.service.BoardService;
import semi.kh.board.model.vo.BoardReview;
import semi.kh.common.MyFileRenamePolicy;

/**
 * Servlet implementation class ReviewFormEndServlet
 */
@WebServlet("/review/reviewFrmEnd")
public class ReviewFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewFormEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) { //request객체를 원래 있던 클래스 ServletFileUpload를 통해 검사
			request.setAttribute("msg", "게시판작성오류![form:enctype]");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return; //실행되지 않도록 리턴처리.
			
		}
		
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload"+File.separator+"board"; //os별로 구분자가 다르다  :File.separator 구분해줌.
		//System.out.printf("[saveDirectory = %s]\n", saveDirectory);
		
		//파일최대 크기 
		int maxPostSize = 1024*1024*10;
		
		//MultipartRequest객체 생성
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8", 
															new MyFileRenamePolicy());
	
		
		//2.파라미터핸들링
		String writer= multiReq.getParameter("writer");
		 if("비회원".equals(writer)) {
			 writer = multiReq.getParameter("memberid");
		 }else {
			 
		 }
		 int rate= Integer.parseInt(multiReq.getParameter("star"));
		 String content  = multiReq.getParameter("content");
		 String pid = multiReq.getParameter("pid");
		 String memberid = multiReq.getParameter("memberid");
		 String password = multiReq.getParameter("password")==null?"":multiReq.getParameter("password");
		
		content = new HTMLInputFilter().filter(content);
				
		String originalFileName = multiReq.getOriginalFileName("up_file"); // getOriginalFileName 
		String renamedFileName = multiReq.getFilesystemName("up_file"); // 2018~.jpg
		
		BoardReview br = new BoardReview();
		
		br.setWriter(writer);
		br.setPid(pid);
		br.setRate(rate);
		br.setContent(content);
		br.setOfile(originalFileName);
		br.setRfile(renamedFileName);
		
		int result = new BoardService().ReviewInsert(br);
		
		//3.뷰단처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/";
		
		
		if(result>0) {
			if("비회원".equals(writer)) {
				msg = "성공적으로 게시글을 등록했습니다.";
				loc ="/product/noneOrderViewEnd?orderno="+memberid+"&password="+password;
				
			}else {
			msg = "성공적으로 게시글을 등록했습니다.";
			loc = "/product/productView?pId="+pid;
			}

		}
		else {
			msg = "게시글 등록에 실패했습니다.";	
			loc="/";
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
