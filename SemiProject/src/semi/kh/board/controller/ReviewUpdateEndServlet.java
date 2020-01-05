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
 * Servlet implementation class ReviewUpdateEnd
 */
@WebServlet("/review/modifyEnd")
public class ReviewUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateEndServlet() {
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
			return; 
			
		}
		
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload"+File.separator+"board"; 

		//파일최대 크기 
		int maxPostSize = 1024*1024*10;
		
	
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8", 
															new MyFileRenamePolicy());
		
		
	
		
		//2.파라미터핸들링
		 int boardno = Integer.parseInt(multiReq.getParameter("boardno"));
		 String writer= multiReq.getParameter("writer");
		 int rate= Integer.parseInt(multiReq.getParameter("star"));
		 String content  = multiReq.getParameter("content");
		 String pid = multiReq.getParameter("pid");
		 
		 String password = multiReq.getParameter("password").equals("null")?"":multiReq.getParameter("password");
		 String orderno = multiReq.getParameter("orderno")==null?"":multiReq.getParameter("orderno");

		String renamedFileName = multiReq.getFilesystemName("up_file");
		String originalFileName = multiReq.getOriginalFileName("up_file");
		
		//이전파일 삭제용
		String oldFile  = multiReq.getParameter("old_renamed_file");
		
		File f = multiReq.getFile("up_file");
		
		//1.업로드한 파일이 있는 경우
		if(f!=null && f.length() >0) {
			//기존파일 삭제 처리
			File delFile = new File(saveDirectory+oldFile);
			delFile.delete(); //파일 삭제 
			
		}
		
		//2.첨부파일 삭제가 체크된 경우
		else if(multiReq.getParameter("del_file")!=null) { 
			//기존파일 삭제 처리
			File delFile = new File(saveDirectory+oldFile); 
			delFile.delete();//파일 삭제 
		
			
		}
		//3.첨부파일 없는 경우
		else {
			//기존값 유지
			originalFileName = multiReq.getParameter("old_original_file").equals("null")?"":multiReq.getParameter("old_original_file"); 
			renamedFileName = multiReq.getParameter("old_renamed_file").equals("null")?"":multiReq.getParameter("old_renamed_file"); 
		}
		
		
		BoardReview br = new BoardReview();
		br.setbNo(boardno);
		br.setWriter(writer);
		br.setPid(pid);
		br.setRate(rate);
		br.setContent(content);
		//xss방어코드 
		br.setContent(new HTMLInputFilter().filter(content)); 
		br.setOfile(originalFileName);
		br.setRfile(renamedFileName);

		
		int result = new BoardService().UpdateReview(br);
		
		//3.뷰단처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/";

		
		if(result>0) {
			if(password.equals("")) {
			msg = "성공적으로 게시글을 수정했습니다.";
			loc = "/review/modify?boardno="+boardno;
			}else {
				msg = "성공적으로 게시글을 수정했습니다.";
				loc = "/product/noneOrderViewEnd?orderno="+writer+"&password="+password;
				
			}

		}
		else {
			msg = "게시글 등록에 수정했습니다.";	
			loc = "/review/modify?boardno="+boardno;
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
