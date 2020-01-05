package semi.kh.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.admin.model.service.AdminService;
import semi.kh.board.model.service.BoardService;
import semi.kh.board.model.vo.Board;

/**
 * Servlet implementation class ShowMyQNAListServlet
 */
@WebServlet("/member/showMyQNAList")
public class ShowMyQNAListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String memberId = request.getParameter("memberId");

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
	    	  numPerPage = 7; 
	      }
	      
	      List<Board> list = new BoardService().showMyQNAList(cPage, numPerPage,memberId);
	      int totalContent = new BoardService().showMyQNAListCount(memberId);
	      
	      int totalPage = (int)Math.ceil((double)totalContent/numPerPage); //ceil :올림

		  String pageBar = "";
		  //페이지바 길이
		  int pageBarSize = 5;
		  int startPage = ((cPage-1)/pageBarSize)*pageBarSize +1 ;
		  int endPage = startPage + pageBarSize - 1;
		      //페이지증감변수
		  int pageNo = startPage;
		      
		  //[이전]section
		  if(pageNo == 1) {
		    	  
		  }else {
			  pageBar += "<a href='"+request.getContextPath()+"/member/showMyQNAList?memberId="+memberId+"&cPage="
					  +(pageNo-1)+"&numPerPage="
					  +numPerPage+"'>[이전]</a>";
		    	  
		  }
		      
		  //[페이지]section
		  while(pageNo <= endPage && pageNo <= totalPage) { //페이지증감변수가 페이지바끝보다 같거나 작을때 && 페이지증감변수가 천체페이지보다 같거나 작을때 
			  if(cPage == pageNo) {
				  //현재페이지는 클릭할 필요가 없다 //다른페이지는 클리되어야함(else절)
		    	  pageBar += "<span class='cPage'>"+pageNo+"</span>";
		    		  
			  }else {
				  pageBar += "<a href='"+request.getContextPath()+"/member/showMyQNAList?memberId="+memberId+"&cPage="
		      			  +pageNo+"&numPerPage="
		      			  +numPerPage+"'>"+pageNo+"</a>";
			  }
			 	pageNo++;
		    	  
		 }
		      
		  //[다음]section
		  if(pageNo > totalPage) { //증감변수가 전체페이지보다 클때 
		  	  
		  }else {
			  pageBar += "<a href='"+request.getContextPath()+"/member/showMyQNAList?memberId="+memberId+"&cPage="
				  	  +pageNo+"&numPerPage="
				  	  +numPerPage+"'>[다음]</a>";
		  }
			
			
			request.setAttribute("list", list);
			request.setAttribute("pageBar", pageBar);
		    request.setAttribute("cPage", cPage);
		    request.setAttribute("numPerPage", numPerPage);
			
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/member/showMyQNAList.jsp");
		reqDispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
