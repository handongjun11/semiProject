package semi.kh.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.product.model.service.ProductService;
import semi.kh.product.model.vo.Product;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/product/productList")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 핸들링
		int numPerPage = 6; //한페이지당 수
		int cPage; //요청페이지
		String pCategory = request.getParameter("pCategory");
		//System.out.println("ProductListServlet@pCategory= "+pCategory);
		
		try{
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e){
			cPage = 1;
		}
		
		//2. 업무로직 처리
		//2-1 전체 상품 갯수, 전체 페이지 수
		int totalProductCnt = new ProductService().selectProductCount(pCategory);
		int totalPage = (int)Math.ceil((double)totalProductCnt/numPerPage);
		
		//2-2 현재 페이지의 상품 리스트 
		List<Product> pList = new ProductService().selectProductList(cPage,numPerPage,pCategory);
		
		//2-3 페이지바구성
		String pageBar = "";	
		int pageNo = 1;			//출력될 페이지번호
		int pageBarSize = 6;
		int i = 1; 				//순회용 변수 
		
		//시작페이지 번호
		pageNo = ((cPage - 1)/pageBarSize) * pageBarSize +1;
		
		
		//3. section 태그 
		//이전 section
		if(pageNo == 1) {}
		else {
			pageBar += "<a href='"+request.getContextPath()+"/product/productList?pCategory="+pCategory+"&cPage="
					+(pageNo-pageBarSize)+"'><</a>";
		}
		//중간 section 
		while(!(i++>pageBarSize || pageNo > totalPage)){
			
			if(cPage == pageNo ){
				pageBar += "<a class='cPage'>"+pageNo+"</a>";
			} 
			else {
				pageBar += "<a href='"+request.getContextPath()+"/product/productList?pCategory="+pCategory+"&cPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++; //증가해야하는데 안하고있음
		}
		//다음 section
		if(pageNo > totalPage){
			
		} 
		else {
			pageBar += "<a href='"+request.getContextPath()+"/product/productList?pCategory="+pCategory+"&cPage="+pageNo+"'>></a>";
		}
		
		//4. view단
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/product/productList.jsp");
		//System.out.println(pList);
		request.setAttribute("pList",pList);
		request.setAttribute("pageBar",pageBar);	
		request.setAttribute("cPage",cPage);			
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
