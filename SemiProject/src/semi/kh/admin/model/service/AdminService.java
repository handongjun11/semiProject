package semi.kh.admin.model.service;

import static semi.kh.common.JDBCTemplate.close;
import static semi.kh.common.JDBCTemplate.commit;
import static semi.kh.common.JDBCTemplate.getConnection;
import static semi.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import semi.kh.admin.model.dao.AdminDao;
import semi.kh.board.model.vo.Board;
import semi.kh.member.model.vo.Member;
import semi.kh.product.model.vo.Buy;
import semi.kh.product.model.vo.BuyList;

public class AdminService {



	//주문 내역 관련
	public List<Buy> selectBuyList() {
		Connection conn = getConnection();
		List<Buy> list = new AdminDao().selectBuyList(conn);
		close(conn);
		return list;
	}

	public int updateShipNo(String orderNo, String shipNo) {
		Connection conn = getConnection();
		int result = new AdminDao().updateShipNo(conn, orderNo, shipNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	//문의 내역 관련
	public List<Board> boardSelectAll(int cPage, int numPerPage) {
		List<Board> list = null;
		Connection conn = getConnection();
		list = new AdminDao().boardSelectAll(conn , cPage, numPerPage);
		close(conn);

		return list;
	}

	public int BoardCount(String categorySQL) {
		Connection conn = getConnection();
		int totalContent = new AdminDao().BordCount(conn,categorySQL);
		close(conn);
		
		return totalContent;
	}

	public List<Board> boardSelectOne(int cPage, int numPerPage, String category_) {
		List<Board> list = null;
		Connection conn = getConnection();
		list = new AdminDao().boardSelectOne(conn , cPage, numPerPage , category_);
		close(conn);

		return list;
	}

	
	/*--------------------------------------병선--------------------------------------*/
	
	//멤버리스트  - #AdminMemberListServlet
	public List<Member> selectMemberList(){
		List<Member> list = null;
		Connection conn = getConnection();
//		list = new AdminDao().selectMemberList(conn);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByMemberId(String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		Connection conn = getConnection();
		list = new AdminDao().selectMemberByMemberId(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public List<Member> selectMemberByMemberName(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list= new AdminDao().selectMemberByMemberName(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByGender(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list= new AdminDao().selectMemberByGender(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public List<Member> selectMemberByGrade(String searchKeyword, int cPage, int numPerPage){
		Connection conn = getConnection();
		List<Member> list = new AdminDao().selectMemberByGrade(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	/**
	 * 페이징처리용 회원조회 - #AdminMemberFinderServlet
	 * @param cPage
	 * @param numPerPage
	 * @return
	 */
	public List<Member> selectMemberList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDao().selectMemberList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectMemberCount() {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectMemberCount(conn);
		close(conn);
		return totalContent;
	}

	public int selectMemberCountByMemberId(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectMemberCountByMemberId(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int selectMemberCountByMemberName(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectMemberCountByMemberName(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int selectMemberCountByGender(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectMemberCountByGender(conn, searchKeyword);
		close(conn);
		return totalContent;
	}
	
	public int selectMemberCountByGrade(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectMemberCountByGrade(conn, searchKeyword);
		close(conn);
		return totalContent;
	}
	/*멤버리스트 끝*/
	
	//총누적 주문 - #AdminManagementServlet
	public int selectCountBuy() {
		Connection conn = getConnection();
		int totalOrder = new AdminDao().selectCountBuy(conn);
		close(conn);
		return totalOrder;
	}

	//총주문금액 - #AdminManagementServlet
	public int selectTotalSellCost() {
		Connection conn = getConnection();
		int totalSellCost = new AdminDao().selectTotalSellCost(conn);
		close(conn);
		return totalSellCost;
	}

	//오늘 주문건 - #AdminManagementServlet
	public int selectTodayOrder() {
		Connection conn = getConnection();
		int todayOrder = new AdminDao().selectTodayOrder(conn);
		close(conn);
		return todayOrder;
	}

	//오늘 가입회원 - #AdminManagementServlet
	public int selectTodayMember() {
		Connection conn = getConnection();
		int todayMember = new AdminDao().selectTodayMember(conn);
		close(conn);
		return todayMember;
	}

	//오늘 판매금액 - #AdminManagementServlet
	public int selectTodaySellTotalCost() {
		Connection conn = getConnection();
		int todaySellTotalCost = new AdminDao().selectTodaySellTotalCost(conn);
		close(conn);
		return todaySellTotalCost;
	}
	
	//총 질문게시판리스트 count - #AdminManagementServlet
	public int selectTotalQnA() {
		Connection conn = getConnection();
		int totalQnA = new AdminDao().selectTotalQnA(conn);
		close(conn);
		return totalQnA;
	}
	
	//미답변게시판리스트 count - #AdminManagementServlet
	public int selectNoAnswerQnA() {
		Connection conn = getConnection();
		int noAnswerQnA = new AdminDao().selectNoAnswerQnA(conn);
		close(conn);
		return noAnswerQnA;
	}	
	
	//미배송 - #AdminManagementServlet
	public int selectNotShipped() {
		Connection conn = getConnection();
		int notShipped = new AdminDao().selectNotShipped(conn);
		close(conn);
		return notShipped;
	}

	//오늘 주문리스트 - #AminDailyOrderViewServlet
	public List<Buy> selectDailyBuyList() {
		Connection conn = getConnection();
		List<Buy> list = new AdminDao().selectDailyBuyList(conn);
		close(conn);
		return list;
	}
	
	//미답변게시판카운트 - #AdminNoAnswerListServlet
	public List<Board> boardSelectAllNoAnswer(int cPage, int numPerPage) {
		List<Board> list = null;
		Connection conn = getConnection();
		list = new AdminDao().boardSelectAllNoAnswer(conn, cPage, numPerPage);
		close(conn);
		
		return list;
	}

	//미답변카운트 - #AdminNoAnswerListServlet
	public int noAnswerBoardCount() {
		Connection conn = getConnection();
		int totalNoAnswerContent = new AdminDao().noAnswerBoardCount(conn);
		close(conn);
		return totalNoAnswerContent;
	}
	
	//배송완료 - #AdmindeliveryCompletedServlet
	public int selectDeliveryCompleted() {
		Connection conn = getConnection();
		int deliveryCompleted = new AdminDao().selectDeliveryCompleted(conn);
		close(conn);
		return deliveryCompleted;
	}

	//미배송리스트 - #AdminOrderViewServlet (order)
	public List<Buy> selectNotShippedList() {
		Connection conn = getConnection();
        List<Buy> list = new AdminDao().selectNotShippedList(conn);
        close(conn);
        return list;
	}

	//배송완료리스트 - #AdminOrderViewServlet (order)
	public List<Buy> selectDeliveryComplete() {
		Connection conn = getConnection();
		List<Buy> list = new AdminDao().selectDeliveryComplete(conn);
		close(conn);
		return list;
	}

	//오늘가입회원리스트 - #AminDailyMemberListServlet
	public List<Member> selectDailyMemberList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDao().selectDailyMemberList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	//오늘가입회원카운트 - #AminDailyMemberListServlet
	public int selectDailyMemberCount() {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectDailyMemberCount(conn);
		close(conn);
		return totalContent;
	}

	/*오늘 가입한 회원리스트*/
	public List<Member> selectDailyMemberByMemberId(String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		Connection conn = getConnection();
		list = new AdminDao().selectDailyMemberByMemberId(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Member> selectDailyMemberByMemberName(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list= new AdminDao().selectDailyMemberByMemberName(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Member> selectDailyMemberByGender(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list= new AdminDao().selectDailyMemberByGender(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Member> selectDailyMemberByGrade(String searchKeyword, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDao().selectDailyMemberByGrade(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectDailyMemberCountByMemberId(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectDailyMemberCountByMemberId(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int selectDailyMemberCountByMemberName(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectDailyMemberCountByMemberName(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int selectDailyMemberCountByGender(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectDailyMemberCountByGender(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int selectDailyMemberCountByGrade(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectDailyMemberCountByGrade(conn, searchKeyword);
		close(conn);
		return totalContent;
	}
	/*오늘 가입한 회원리스트 끝*/
	
	//2019-01-10추가 
	//답변완료 게시판리스트 - #AdminAnswerCompletedBoardListServlet
	public List<Board> selectAnswerCompletedBoardList(int cPage, int numPerPage) {
		List<Board> list = null;
		Connection conn = getConnection();
		list = new AdminDao().selectAnswerCompletedBoardList(conn, cPage, numPerPage);
		close(conn);
		
		return list;
	}
    
	//답변완료 게시판리스트 - #AdminManagementServlet
	public int selectAnswerCompletedBoardListCount() {
		Connection conn = getConnection();
		int answerCompletedBoardListCount = new AdminDao().selectAnswerCompletedBoardListCount(conn);
		close(conn);
		return answerCompletedBoardListCount;
		
	}
	
	//답변완료 게시판리스트 - #AdminAnswerCompletedBoardListServlet
	public int selectTotalAnswerCompltedContent() {
		Connection conn = getConnection();
		int totalAnswerCompletedContent = new AdminDao().selectTotalAnswerCompltedContent(conn);
		close(conn);
		return totalAnswerCompletedContent;
	}
	
	//orderno로 주문물품 정보리스트 - #AdminProductOrderNoServlet
	public List<Buy> selectProductOrderNo(String orderNo) {
		List<Buy> list = null;
		
		Connection conn = getConnection();
		
		list = new AdminDao().selectProductOrderNo(conn, orderNo);
		close(conn);
		return list;
	}

	//orderNo로 상품결제정보 삭제 - #AdminProductDelete
	public int deleteProduct(String orderNo) {
		int result = 0;
		Connection conn = getConnection();
		result = new AdminDao().deleteProduct(conn, orderNo);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	//상품결제정보 수정! #AdminProductUpdateEnd
	public int updateProductList(Buy b) {
		int result = 0;
		Connection conn = getConnection();
		
		result = new AdminDao().updateProductList(conn, b);
		System.out.printf("@@@productUpdate // service result = %s\n",result);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	/*--------------------------------------병선--------------------------------------*/
	
	public List<BuyList> selectProductOrderNoList(String orderNo) {
		List<BuyList> buyList = null;
		Connection conn = getConnection();
		buyList = new AdminDao().selectProductOrderNoList(conn, orderNo);
		close(conn);
		return buyList;
	}
}






