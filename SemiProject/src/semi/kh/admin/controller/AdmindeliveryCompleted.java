package semi.kh.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.kh.admin.model.service.AdminService;
import semi.kh.product.model.vo.Buy;

/**
 * Servlet implementation class AdmindeliveryCompleted
 */
@WebServlet("/admin/deliveryCompleted")
public class AdmindeliveryCompleted extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("[deliveryCompletesevlet In!!!!! ]\n");
		//인코딩처리
		request.setCharacterEncoding("utf-8");
		
		//업무로직
		List<Buy> list = new ArrayList();
		list = new AdminService().selectDeliveryComplete();
		
		//System.out.printf("list = %s\n",list);
		
		
		//view단 처리
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/orderView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
