package semi.kh.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.board.model.service.BoardService;
import semi.kh.board.model.vo.Board;
import semi.kh.board.model.vo.BoardComment;
import semi.kh.board.model.vo.BoardReview;
import semi.kh.product.model.service.ProductService;
import semi.kh.product.model.vo.Product;


/**
 * Servlet implementation class ProductViewServlet
 */
@WebServlet("/product/productView")
public class ProductViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.encoding
		request.setCharacterEncoding("utf-8");
		
		//2.parameterHandling
		String pId = request.getParameter("pId");
		String category = request.getParameter("category");
		
		
		Product p = new ProductService().selectOneProduct(pId);
		request.setAttribute("Product", p);		
		
	
		
	
		  int numPerPage = 5;
		  int cPage; 		  
		  try {
			  cPage = Integer.parseInt(request.getParameter("cPage"));
		  }catch(NumberFormatException e) {
			  cPage=1;
		  }
		
		//3-2-2.
		  List<BoardReview> reviewList = null;
		  List<Board> qnaList = null;
		  List<BoardComment> qnaCommentList = null;
				  
		  int reviewtotalCnt = 0;
		  int qnatotalCnt = 0;
		  int totalPage = 0;

		  
		  reviewList = new ProductService().selectProductRVList(cPage, numPerPage, pId, "B_REVIEW");	
		  reviewtotalCnt = new ProductService().selectProductBoardCount(pId, "B_REVIEW");
	
		  
		  
		  qnaList = new ProductService().selectProductQnAList(cPage, numPerPage, pId, "B_QNA"); 				
		  qnatotalCnt = new ProductService().selectProductBoardCount(pId,"B_QNA"); 
		  totalPage = (int)Math.ceil((double)qnatotalCnt/numPerPage); 
		
		  qnaCommentList = new ProductService().selectProductQnACommentList(pId);
			
		  String pageBar ="";
		  int pageNo = 1; 
		  int pageBarSize = 6; 
		  int i = 1; 
		  
		  pageNo = ((cPage - 1)/pageBarSize) * pageBarSize +1;
	
		  if(pageNo == 1) {}
		  else {
			  pageBar +="<a href='"+request.getContextPath()+"/product/productView?pId="+pId+"&category=qna"+"&cPage="+(pageNo-pageBarSize)+"'><</a>";	
							  
		  }
		  while(!(i++>pageBarSize || pageNo > totalPage)) {
			  if(cPage == pageNo ){
					pageBar += "<a class='cPage'>"+pageNo+"</a>";
				} 
				else {
					pageBar += "<a href='"+request.getContextPath()+"/product/productView?pId="+pId+"&category=qna"+"&cPage="+pageNo+"'>"+pageNo+"</a>";
				}
				pageNo++;
		  }
		  if(pageNo > totalPage){} 
		  else {
			pageBar += "<a href='"+request.getContextPath()+"/product/productView?pId="+pId+"&category=qna"+"&cPage="+pageNo+"'>></a>";
		  }
		    
	
		  
		request.setAttribute("reviewList",reviewList);
		request.setAttribute("reviewtotalCnt", reviewtotalCnt);
		
		request.setAttribute("qnaList",qnaList);
		request.setAttribute("qnaCommentList",qnaCommentList);	
		request.setAttribute("pageBar",pageBar);	
		request.setAttribute("cPage",cPage);
		
		String view = "/WEB-INF/views/product/productView.jsp";
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


