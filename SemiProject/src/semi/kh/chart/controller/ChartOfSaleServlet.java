package semi.kh.chart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.kh.chart.model.service.ChartService;
import semi.kh.chart.model.vo.Favorite;
import semi.kh.chart.model.vo.SalesVolume;
import semi.kh.member.model.vo.Member;

/**
 * Servlet implementation class ChartOfSaleServlet
 */
@WebServlet("/admin/chartOfSale")
public class ChartOfSaleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartOfSaleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		Member memberLoggedIn = (Member)request.getSession().getAttribute("memberLoggedIn");
//		if(memberLoggedIn == null || !"admin".equals(memberLoggedIn.getMemberId())) {
//			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
//			request.setAttribute("loc", "/");
//			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
//			return;
//			
//		}
		//1. parameterHandling
		String searchType = request.getParameter("searchType");
		//System.out.println(searchType);
		//List<Member> chartMember = new ChartService().selectFavoriteChart(searchType);

		switch(searchType){
		case "grade" :  
			List<SalesVolume> gradeChart = new ChartService().selectGradeSale();
			//System.out.println(gradeChart);
			new Gson().toJson(gradeChart, response.getWriter());
			
			break;
		case "region" : 
			List<SalesVolume> region = new ChartService().selectRegionSale();
			//System.out.println(region);
			new Gson().toJson(region, response.getWriter());
			break;
		default : 
			List<SalesVolume> regionDetail = new ChartService().selectRegionDetailSale(searchType);
			//System.out.println(regionDetail);	
			new Gson().toJson(regionDetail,response.getWriter());
		break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
