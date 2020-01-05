package semi.kh.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.admin.model.service.AdminService;
import semi.kh.member.model.vo.Member;
import semi.kh.product.model.vo.Buy;

/**
 * Servlet implementation class AdminManagement
 */
@WebServlet("/admin/Management")
public class AdminManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		//System.out.println("@AdminManagementServlet In!!");
		
		//관리자여부 체크
		Member memberLoggedIn = (Member)request.getSession()
											  		  .getAttribute("memberLoggedIn");
		if(memberLoggedIn == null ||
		   !"admin".equals(memberLoggedIn.getMemberId())) {
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
				   .forward(request, response);
			return;
		}	
		
		
		
		//업무로직!!
		/* 쇼핑몰할일 Content*/
		int totalQnA = new AdminService().selectTotalQnA();
//		System.out.printf("[totalQnA=%s]\n",totalQnA);
		
		int noAnswerQnA = new AdminService().selectNoAnswerQnA();
//		System.out.printf("[noAnswerQnA=%s]\n",noAnswerQnA);
		
		int notShipped = new AdminService().selectNotShipped();
//		System.out.printf("[notShipped = %s]", notShipped);
		
		int deliveryCompleted = new AdminService().selectDeliveryCompleted();
//		System.out.printf("[deliveryCompleted = %s]",deliveryCompleted);
		
		int answerCompletedBoardListCount = new AdminService().selectAnswerCompletedBoardListCount();
		//System.out.printf("[answerCompletedBoardListCount = %s]",answerCompletedBoardListCount);
		
		
		/* 쇼핑몰현황 today-part content */
		int todayOrder = new AdminService().selectTodayOrder();//오늘 구매한 횟수	
//		System.out.printf("[todayOrder=%s]\n", todayOrder);
		
		int todaySellTotalCost = new AdminService().selectTodaySellTotalCost();
//		System.out.printf("[todaySellTotalCost=%s]\n", todaySellTotalCost);
		
		int todayMember = new AdminService().selectTodayMember();//오늘 가입한 회원수	
//		System.out.printf("[todayMember=%s]\n", todayMember);
		
		
		/*쇼핑몰 현황  total-part content*/
		int totalOrder =  new AdminService().selectCountBuy();//총 주문갯수
//		System.out.printf("[totalOrder=%s]\n", totalOrder);
		
		int totalSellCost = new AdminService().selectTotalSellCost();//총 주문금액
//		System.out.printf("[totalSellCost = %s]\n", totalSellCost);
		
		int totalCountMember = new AdminService().selectMemberCount();	//총 회원수
//		System.out.printf("[totalCountMember = %s]\n", totalCountMember);
		
		
		
		
		
		//view단
		/* 쇼핑몰할일 */
		request.setAttribute("totalQnA", totalQnA);
		request.setAttribute("noAnswerQnA", noAnswerQnA);
		request.setAttribute("notShipped", notShipped);
		request.setAttribute("deliveryCompleted", deliveryCompleted);
		request.setAttribute("answerCompletedBoardListCount", answerCompletedBoardListCount);
		
		/* 쇼핑몰현황 */
		request.setAttribute("todayOrder", todayOrder);//오늘 구매한 횟수	
		request.setAttribute("todaySellTotalCost", todaySellTotalCost);//오늘 구매한 횟수			
		request.setAttribute("todayMember", todayMember);//오늘 가입환 회원수		
		
		request.setAttribute("totalOrder", totalOrder); //총 주문갯수
		request.setAttribute("totalSellCost", totalSellCost); //총 주문금액
		request.setAttribute("totalCountMember", totalCountMember); //총 회원수
		request.getRequestDispatcher("/WEB-INF/views/admin/Management.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
