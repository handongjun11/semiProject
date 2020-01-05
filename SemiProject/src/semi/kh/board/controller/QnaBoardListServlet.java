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

/**
 * Servlet implementation class QnaBoardListServlet
 */
@WebServlet("/board/boardList")
public class QnaBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0.인코딩
		request.setCharacterEncoding("utf-8"); 
		
		
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		 //1.파라미터핸들링 (관리자체크여부끝나고)
	      int cPage;
	      try {
	       cPage = Integer.parseInt(request.getParameter("cPage"));
	           
	       
	      }catch(NumberFormatException e) {
	    	  cPage = 1;
	    	  
	      }
	      int numPerPage;
	      try {
	       numPerPage= Integer.parseInt(request.getParameter("numPerPage"));
	      }catch(NumberFormatException e) {
	    	  numPerPage = 7; //초기값 세팅
	    	 //다섯 행의 boardList가 보여진다.
	      }
	      
	      List<Board> list = null;
	      int totalContent = 0;
	      int totalPage = 0;
	      
	      List<Board> noticeList =  new BoardService().boardSelectNotice("공지사항"); 
	      
	      
	      list = new BoardService().boardSelectOne("일반문의", cPage, numPerPage);
	      totalContent = new BoardService().BoardCountbyCategory("일반문의");
	    	
	      if( "".equals(searchKeyword) && (("title").equals(searchType)|| ("content").equals(searchType))) {
	    	  list=null;
	    	  totalContent=0;
	      } else if("title".equals(searchType)){
	    	  list = new BoardService().boardSelectOneTitle("일반문의",cPage,numPerPage,searchKeyword);
	    	  totalContent = new BoardService().BoardSelectTitleCountbyCategory("일반문의",searchKeyword);
	    	  
	      }else if("content".equals(searchType)) {
	    	  list = new BoardService().boardSelectOneContent("일반문의",cPage,numPerPage,searchKeyword);
	    	  totalContent = new BoardService().BoardSelectContentCount("일반문의",searchKeyword);
	      }
	      
	     
	      totalPage = (int)Math.ceil((double)totalContent/numPerPage);
	      

	      String pageBar = "<ul class='pagination'>";
	      int pageBarSize = 5;
	      int startPage = ((cPage-1)/pageBarSize)*pageBarSize +1 ;
	      int endPage = startPage + pageBarSize - 1;

	      int pageNo = startPage;
	     
	      if(searchKeyword == null){
	    	//[이전]section
	    	if(pageNo == 1) {
	    		
	    	}else {
	    		pageBar += "<li><a style='color:black;' href='"+request.getContextPath()+"/board/boardList?cPage="
	    				+(pageNo-1)+"&numPerPage="
	    				+numPerPage+"'>[이전]</a></li>";
	    		
	    	}
	    	
	    	//[페이지]section
	    	while(pageNo <= endPage && pageNo <= totalPage) { //페이지증감변수가 페이지바끝보다 같거나 작을때 && 페이지증감변수가 천체페이지보다 같거나 작을때 
	    		if(cPage == pageNo) {
	    			//현재페이지는 클릭할 필요가 없다 //다른페이지는 클리되어야함(else절)
	    			pageBar += "<li class='active'><span class='cPage' style='color:white; background-color : #E6E1DB; border:1px solid lightgray;'>"+pageNo+"</span></li>";
	    			
	    		}else {
	    			pageBar += "<li><a style='color:black;' href='"+request.getContextPath()+"/board/boardList?cPage="
	    					+pageNo+"&numPerPage="
	    					+numPerPage+"'>"+pageNo+"</a></li>";
	    		}
	    		pageNo++;
	    		
	    	}
	    	
	    	//[다음]section
	    	if(pageNo > totalPage) { //증감변수가 전체페이지보다 클때 
	    		//113이 끝 빠져나올때 (pageNo <= endPage && pageNo <= totalPage여기서) 114때 나오게 된다.여기가 바로 114다
	    		
	    	}else {
	    		pageBar += "<li><a style='color:black;' href='"+request.getContextPath()+"/board/boardList?cPage="
	    				+pageNo+"&numPerPage="
	    				+numPerPage+"'>[다음]</a></li>";
	    	}
	      }
	      
	      pageBar +="</ul>";
	    	
	    if(("title".equals(searchType) || "content".equals(searchType))&& searchKeyword != null) {
	    	
	    	//[이전]section
	    	if(pageNo == 1) {
	    		
	    	}else {
	    		pageBar += "<a style='color:black;' href='"+request.getContextPath()+"/board/boardList?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="
	    				+(pageNo-1)+"&numPerPage="
	    				+numPerPage+"'>[이전]</a>";
	    		
	    	}
	    	
	    	//[페이지]section
	    	while(pageNo <= endPage && pageNo <= totalPage) { //페이지증감변수가 페이지바끝보다 같거나 작을때 && 페이지증감변수가 천체페이지보다 같거나 작을때 
	    		if(cPage == pageNo) {
	    			//현재페이지는 클릭할 필요가 없다 //다른페이지는 클리되어야함(else절)
	    			pageBar += "<span class='cPage'>"+pageNo+"</span>";
	    			
	    		}else {
	    			pageBar += "<a style='color:black;' href='"+request.getContextPath()+"/board/boardList?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="
	    					+pageNo+"&numPerPage="
	    					+numPerPage+"'>"+pageNo+"</a>";
	    		}
	    		pageNo++;
	    		
	    	}
	    	
	    	//[다음]section
	    	if(pageNo > totalPage) { //증감변수가 전체페이지보다 클때 
	    		//113이 끝 빠져나올때 (pageNo <= endPage && pageNo <= totalPage여기서) 114때 나오게 된다.여기가 바로 114다
	    		
	    	}else {
	    		pageBar += "<a href='"+request.getContextPath()+"/board/boardList?searchType="+searchType+"&searchKeyword="+searchKeyword+"&cPage="
	    				+pageNo+"&numPerPage="
	    				+numPerPage+"'>[다음]</a>";
	    	}
	    	
	    }
	      
	      
	      
		//3.뷰단처리
	 
		request.setAttribute("list", list);
		request.setAttribute("noticeList", noticeList);
	      request.setAttribute("pageBar", pageBar);
	      request.setAttribute("cPage", cPage);
	      request.setAttribute("numPerPage", numPerPage);
		request.getRequestDispatcher("/WEB-INF/views/board/QnaBoard.jsp").forward(request, response);
		
		

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
