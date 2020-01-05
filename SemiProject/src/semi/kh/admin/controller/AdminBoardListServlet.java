package semi.kh.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.kh.admin.model.service.AdminService;
import semi.kh.board.model.service.BoardService;
import semi.kh.board.model.vo.Board;



/**
 * Servlet implementation class AdminBoardListServlet
 */
@WebServlet("/admin/boardList")
public class AdminBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); 

		String category = request.getParameter("category");
		
		switch(category) {
		case "all" : category = "모아보기"; break;
		case "nomalQnA" : category = "일반문의"; break;
		case "delQnA" : category = "배송문의"; break;
		case "prdQnA" : category = "상품문의"; break;
		}
	    int cPage = Integer.parseInt(request.getParameter("cPage"));
	    int numPerPage = 5;
	    
	    List<Board> list = new BoardService().boardSelectOne(category, cPage, numPerPage);
	    
	    int totalContent = 0;
	    if(category.equals("모아보기")) {
	    	totalContent = new BoardService().BoardCount();   	
	    }
	    else {
	    	totalContent = new BoardService().BoardCountbyCategory(category);
	    }
	    
	    int totalPage = (int)Math.ceil((double)totalContent/numPerPage); //ceil :올림
	    
	    String pageBar = "";
	    int pageBarSize = 5;
	    int startPage = ((cPage-1)/pageBarSize)*pageBarSize +1 ;
	    int endPage = startPage + pageBarSize - 1;
	    int pageNo = startPage;
	      
	      //[이전]section
	      if(pageNo == 1) {
	    	  
	      }else {
	    	  pageBar += "<a href='"+request.getContextPath()+"/admin/boardList?"
	    	  		  + "category="+category+"&cPage="+(pageNo-1)+"'>[이전]</a>";
	      }
	      
	      //[페이지]section
	      while(pageNo <= endPage && pageNo <= totalPage) { //페이지증감변수가 페이지바끝보다 같거나 작을때 && 페이지증감변수가 천체페이지보다 같거나 작을때 
	    	  if(cPage == pageNo) {
	    		  //현재페이지는 클릭할 필요가 없다 //다른페이지는 클리되어야함(else절)
	    		  pageBar += "<span class='cPage'>"+pageNo+"</span>";
	    		  
	    	  }else {
	    		  pageBar += "<a href='"+request.getContextPath()+"/admin/boardList?"
	    		  		+ "category="+category+"&cPage="+pageNo+"'>"+pageNo+"</a>";
	    	  }
	    	  pageNo++;
	    	  
	      }
	      
	      //[다음]section
	      if(pageNo > totalPage) { //증감변수가 전체페이지보다 클때 
	    	  //113이 끝 빠져나올때 (pageNo <= endPage && pageNo <= totalPage여기서) 114때 나오게 된다.여기가 바로 114다
	    	  
	      }else {
	    	  pageBar += "<a href='"+request.getContextPath()+"/admin/boardList?"
	    	  		+ "category="+category+"&cPage="+pageNo+"'>[다음]</a>";
	      }
	      
		//3.뷰단처리
		request.setAttribute("list", list);
		request.setAttribute("category", category);
		request.setAttribute("cPage", cPage);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/WEB-INF/views/admin/boardList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
