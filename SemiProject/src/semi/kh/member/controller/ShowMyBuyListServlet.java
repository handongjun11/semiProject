package semi.kh.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.product.model.service.ProductService;
import semi.kh.product.model.vo.Buy;
import semi.kh.product.model.vo.BuyList;

/**
 * Servlet implementation class ShowMyBuyListServlet
 */
@WebServlet("/member/showMyBuyList")
public class ShowMyBuyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowMyBuyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		
		
		 int cPage;
	      try {
	       cPage = Integer.parseInt(request.getParameter("cPage"));
	             //cPage에 대한 prarameter가 없다면 null값 근데 그건 integer로 못함 그래서 예외처리 해줘야함
	       
	      }catch(NumberFormatException e) {
	    	  cPage = 1; //null이 발생하면 cPage를 다시 1로 만든다.
	    	  
	      }
	      int numPerPage;
	      try {
	       numPerPage= Integer.parseInt(request.getParameter("numPerPage"));
	      }catch(NumberFormatException e) {
	    	  numPerPage = 5; //초기값 세팅
	    	 //다섯 행의 boardList가 보여진다.
	      }
		
		
		List<Buy> list = new ProductService().showMyBuyList(memberId,cPage, numPerPage);
		int totalContent = new ProductService().buyListCount(memberId);
		List<BuyList> buyList = new ProductService().showMyAllBuyList(memberId);
		
		int totalPage = (int)Math.ceil((double)totalContent/numPerPage); //ceil :올림
	      
	      //페이지바 html
	      String pageBar = "";
	      //페이지바 길이
	      int pageBarSize = 5;
	      //(공식3) 시작페이지 startPage 번호세팅
	      //cPage=5, pageBarSize = 5 , startPage-> 1
	      //cPage=6, pageBarSize = 5 , startPage-> 6
	      int startPage = ((cPage-1)/pageBarSize)*pageBarSize +1 ;
	      int endPage = startPage + pageBarSize - 1;
//	      System.out.printf("[start=%s , end=%s]\n", startPage, endPage);
	      //페이지증감변수
	      int pageNo = startPage;
	      
	      //[이전]section
	      if(pageNo == 1) {
	    	  
	      }else {
	    	  pageBar += "<a href='"+request.getContextPath()+"/member/showMyBuyList?memberId="+memberId+"&cPage="
	    			  	+(pageNo-1)+"&numPerPage="
	    			  	+numPerPage+"'>[이전]</a>";
	    	  
	      }
	      
	      //[페이지]section
	      while(pageNo <= endPage && pageNo <= totalPage) { //페이지증감변수가 페이지바끝보다 같거나 작을때 && 페이지증감변수가 천체페이지보다 같거나 작을때 
	    	  if(cPage == pageNo) {
	    		  //현재페이지는 클릭할 필요가 없다 //다른페이지는 클리되어야함(else절)
	    		  pageBar += "<span class='cPage'>"+pageNo+"</span>";
	    		  
	    	  }else {
	    		  pageBar += "<a href='"+request.getContextPath()+"/member/showMyBuyList?memberId="+memberId+"&cPage="
	      			  			+pageNo+"&numPerPage="
	      			  			+numPerPage+"'>"+pageNo+"</a>";
	    	  }
	    	  pageNo++;
	    	  
	      }
	      
	      //[다음]section
	      if(pageNo > totalPage) { //증감변수가 전체페이지보다 클때 
	    	  //113이 끝 빠져나올때 (pageNo <= endPage && pageNo <= totalPage여기서) 114때 나오게 된다.여기가 바로 114다
	    	  
	      }else {
	    	  pageBar += "<a href='"+request.getContextPath()+"/member/showMyBuyList?memberId="+memberId+"&cPage="
			  			+pageNo+"&numPerPage="
			  			+numPerPage+"'>[다음]</a>";
	      }
		
		
		request.setAttribute("list", list);
		request.setAttribute("buyList", buyList);
		request.setAttribute("pageBar", pageBar);
	    request.setAttribute("cPage", cPage);
	    request.setAttribute("numPerPage", numPerPage);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/member/showMyBuyList.jsp");
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