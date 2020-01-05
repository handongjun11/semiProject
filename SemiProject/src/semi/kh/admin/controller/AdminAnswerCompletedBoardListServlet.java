package semi.kh.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.admin.model.service.AdminService;
import semi.kh.board.model.vo.Board;

/**
 * Servlet implementation class AdminAnswerCompletedBoardListServlet
 */
@WebServlet("/admin/AdminAnswerCompletedBoardList")
public class AdminAnswerCompletedBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//.out.printf("@AdminAnswerCompletedBoardListServlet In!!!!!!!!!\n");		
		
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		//파라미터핸들링
		int cPage;
		try {
		cPage = Integer.parseInt(request.getParameter("cPage"));
		//cPage에 대해 parameter가 없다면 null값임 근데 integer로 못하니까 에외처리가 피료쓰스스
		} catch(NumberFormatException e) {
			cPage = 1; //null이 발생하면 cPage를 다시 1로 만든다.
		}
		int numPerPage;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch(NumberFormatException e){
			numPerPage = 5; //초기값 세팅, 5개의 boardList가 보여짐
		}
		
		//비즈니스로직
		List<Board> list = new AdminService().selectAnswerCompletedBoardList(cPage, numPerPage);
//		System.out.printf("list = %s", list); //일단 여기까지 찍힘
		
		int totalAnswerCompletedContent = new AdminService().selectTotalAnswerCompltedContent();
//		System.out.printf("[totalAnswerCompletedContent = %s]", totalAnswerCompletedContent);
		
		
		
		int totalPage = (int)Math.ceil((double)totalAnswerCompletedContent/numPerPage); //ceil :올림
		 //페이지바 html
	    String pageBar = "";
	    //페이지바 길이
	    int pageBarSize = 5;
	    //(공식3) 시작페이지 startPage 번호세팅
	    //cPage=5, pageBarSize = 5 , startPage-> 1
	    //cPage=6, pageBarSize = 5 , startPage-> 6
	    int startPage = ((cPage-1)/pageBarSize)*pageBarSize +1 ;
	    int endPage = startPage + pageBarSize - 1;
//	    System.out.printf("[start=%s , end=%s]\n", startPage, endPage);
	    //페이지증감변수
	    int pageNo = startPage;
	    
	    //[이전]section
	    if(pageNo == 1) {
	   	  
	    }
	    else {
	    	pageBar += "<a href='"+request.getContextPath()+"/admin/AdminAnswerCompletedBoardList?cPage="
	    			  	+(pageNo-1)+"&numPerPage="
	    			  	+numPerPage+"'>[이전]</a>";
	    	  
	    }
	      
	    //[페이지]section
	    while(pageNo <= endPage && pageNo <= totalPage) { //페이지증감변수가 페이지바끝보다 같거나 작을때 && 페이지증감변수가 천체페이지보다 같거나 작을때 
	    if(cPage == pageNo) {
	    	//현재페이지는 클릭할 필요가 없다 //다른페이지는 클리되어야함(else절)
	    	pageBar += "<span class='cPage'>"+pageNo+"</span>";
	    	}
	    else {
	    	pageBar += "<a href='"+request.getContextPath()+"/admin/AdminAnswerCompletedBoardList?cPage="
	    			  			+pageNo+"&numPerPage="
	    			  			+numPerPage+"'>"+pageNo+"</a>";
	    	}
	    pageNo++;
	     
	    }
	    
	    //[다음]section
	    if(pageNo > totalPage) { //증감변수가 전체페이지보다 클때 
	     //113이 끝 빠져나올때 (pageNo <= endPage && pageNo <= totalPage여기서) 114때 나오게 된다.여기가 바로 114다
	     
	    }else {
	     pageBar += "<a href='"+request.getContextPath()+"/admin/AdminAnswerCompletedBoardList?cPage="
		 			+pageNo+"&numPerPage="
		 			+numPerPage+"'>[다음]</a>";
	    }
		
		
		//3.뷰단처리
		request.setAttribute("list", list);
		//jsp에서 가져다 쓰려고 속성에 pageBar를 속성에 넣는다.
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
				
		request.getRequestDispatcher("/WEB-INF/views/admin/answerBoardList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
