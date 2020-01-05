package semi.kh.chart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import semi.kh.chart.model.service.ChartService;
import semi.kh.chart.model.vo.Favorite;
import semi.kh.member.model.vo.Member;

/**
 * Servlet implementation class ChartServlet
 */
@WebServlet("/admin/chartOfFavorite")
public class ChartOfFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChartOfFavoriteServlet() {
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
		System.out.println(searchType);
		//List<Member> chartMember = new ChartService().selectFavoriteChart(searchType);

		switch(searchType){
		case "age" :  
			String ageRange = request.getParameter("checkVal");
			
			List<Integer> age = new ArrayList<>();
			for(int i = 0; i < ageRange.length() ; i=i+2) {
			
			age.add(Integer.parseInt(ageRange.substring(i, i+2))); 
			}
			List<Favorite> ageChart = new ChartService().countAgeFavorite(age);
						
			new Gson().toJson(ageChart, response.getWriter());
			
			break;
		case "gender" : 
			List<Favorite> gender = new ChartService().countGenderFavorite();
			//System.out.println(gender);
			new Gson().toJson(gender, response.getWriter());
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
