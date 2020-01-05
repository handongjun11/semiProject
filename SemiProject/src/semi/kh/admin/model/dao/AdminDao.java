package semi.kh.admin.model.dao;

import static semi.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import semi.kh.board.model.vo.Board;
import semi.kh.member.model.vo.Member;
import semi.kh.product.model.vo.Buy;
import semi.kh.product.model.vo.BuyList;

public class AdminDao {
	
	private Properties prop = new Properties();

	public AdminDao() {
		String fileName = AdminDao.class
							      .getResource("/sql/admin/admin-query.properties")
							      .getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	

	//주문내역 관련
	public List<Buy> selectBuyList(Connection conn) {
		ArrayList<Buy> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBuyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rset.next()) {
				Buy b = new Buy();

				b.setOrderNo(rset.getString("orderno"));
				b.setMemberId(rset.getString("memberid"));
				b.setTotalPrice(rset.getInt("totalprice"));
				b.setOrderDate(rset.getDate("orderdate"));
				b.setResName(rset.getString("res_name"));
				b.setResAddress(rset.getString("res_address"));
				b.setResPhone(rset.getString("res_phone"));
				b.setResRequirement(rset.getString("res_requirement"));
				b.setShipStatus(rset.getString("shipStatus"));
				b.setShipNo(rset.getString("shipno"));
				
				list.add(b);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int updateShipNo(Connection conn, String orderNo, String shipNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateShipNo");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "배송중");
			pstmt.setString(2, shipNo);
			pstmt.setString(3, orderNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	//배송문의 관련
	public List<Board> boardSelectAll(Connection conn, int cPage, int numPerPage) {
		List<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("boardSelectAllPaging");
		
		try {
			//1.쿼리 객체 준비
			pstmt = conn.prepareStatement(query);
			
	         int startRnum = (cPage-1)*numPerPage +1;
	         int endRnum = cPage*numPerPage;
	         
	         
	         pstmt.setInt(1, startRnum);
	         pstmt.setInt(2, endRnum);
			
			
			//2.쿼리객체 실행
			rset = pstmt.executeQuery();
			
			
			list = new ArrayList<>();
			
	         // 다음 행이 있으면 true, 없으면 false
	         // 행의 길이만큼 반복될 것임
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("b_no"));
				b.setBoardTitle(rset.getString("b_title"));
				b.setBcategory(rset.getString("b_category"));
				b.setbPid(rset.getString("pid"));
				b.setbOdrderNo(rset.getString("orderno"));
				b.setbLockFlag(rset.getString("lock_flag"));
				b.setBoardPwd(rset.getString("b_pwd"));
				b.setbWriter(rset.getString("b_writer"));
				b.setbConetent(rset.getString("b_content"));
				b.setBoardDate(rset.getDate("b_date"));
				b.setBoardCommentCnt(rset.getInt("board_comment_cnt"));
			
			
				list.add(b);
				
			}
			
	
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int BordCount(Connection conn, String categorySQL) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			if("모아보기".equals(categorySQL)) {
				categorySQL = "";	
			}
				
			pstmt.setString(1, "%"+categorySQL+"%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalContent;
	}

	public List<Board> boardSelectOne(Connection conn, int cPage, int numPerPage, String category_) {
		List<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("boardSelectOne");
		System.out.println("여기는 다오 카테고리는?"+category_);
		
		try {
			//1.쿼리 객체 준비
			pstmt = conn.prepareStatement(query);
			
	         int startRnum = (cPage-1)*numPerPage +1;
	         int endRnum = cPage*numPerPage;
	         
	         pstmt.setString(1, category_);
	         pstmt.setInt(2, startRnum);
	         pstmt.setInt(3, endRnum);
			
			
			//2.쿼리객체 실행
			rset = pstmt.executeQuery();
			
			
			list = new ArrayList<>();
			
	         // 다음 행이 있으면 true, 없으면 false
	         // 행의 길이만큼 반복될 것임
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("b_no"));
				b.setBoardTitle(rset.getString("b_title"));
				b.setBcategory(rset.getString("b_category"));
				b.setbPid(rset.getString("pid"));
				b.setbOdrderNo(rset.getString("orderno"));
				b.setbLockFlag(rset.getString("lock_flag"));
				b.setBoardPwd(rset.getString("b_pwd"));
				b.setbWriter(rset.getString("b_writer"));
				b.setbConetent(rset.getString("b_content"));
				b.setBoardDate(rset.getDate("b_date"));
				b.setBoardCommentCnt(rset.getInt("board_comment_cnt"));
			
			
				list.add(b);
				
			}
			
	
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	/*--------------------------------------병선--------------------------------------*/
	/*memberlist, finder*/
	public List<Member> selectMemberList(Connection conn, int cPage, int numPerPage){
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberListByPaging");
		
		try {
			//1.쿼리객체 준비
			pstmt = conn.prepareStatement(query);
			//startRnum, endRnum
			//numPerPage=5, cPage=1인경우 => 1~5
			//numPerPage=5, cPage=2인경우 => 6~10
			//numPerPage=5, cPage=3인경우 => 11~15
			int startRnum = (cPage-1)*numPerPage+1;
//			int startRnum = (cPage*numPerPage)-(numPerPage-1);
			int endRnum = cPage*numPerPage;
			
			pstmt.setInt(1, startRnum);		
			pstmt.setInt(2, endRnum);		
					
			
			//2.쿼리객체 실행
			rset = pstmt.executeQuery();
			
			//3.실행결과 list에 담기
			list = new ArrayList<>();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setBirth(rset.getInt("birth"));
				m.setEmail(rset.getString("email"));
				
				m.setPhone(rset.getString("phone"));
			
				m.setFavorite(rset.getString("favorite"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				m.setDelFlag(rset.getString("delflag"));				
				m.setDeleteDate(rset.getDate("deletedate"));
				m.setRegFlag(rset.getString("regflag"));
				m.setPassword(rset.getString("password"));
				m.setTotalPurchaseCost(rset.getInt("totalpurchasecost"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		
		return list;
	}

	public List<Member> selectMemberByMemberId(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectMemberByMemberIdByPaging");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setBirth(rset.getInt("birth"));
				m.setEmail(rset.getString("email"));
				
				m.setPhone(rset.getString("phone"));
				
				m.setFavorite(rset.getString("favorite"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				m.setDelFlag(rset.getString("delflag"));				
				m.setDeleteDate(rset.getDate("deletedate"));
				m.setRegFlag(rset.getString("regflag"));
				m.setPassword(rset.getString("password"));
				m.setTotalPurchaseCost(rset.getInt("totalpurchasecost"));
				
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	
	public List<Member> selectMemberByMemberName(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectMemberByMemberNameByPaging");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				//컬럼명은 대소문자 구분이 없다.
				m.setMemberId(rset.getString("memberid"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setBirth(rset.getInt("birth"));
				m.setEmail(rset.getString("email"));
				
				m.setPhone(rset.getString("phone"));
				
				m.setFavorite(rset.getString("favorite"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				m.setDelFlag(rset.getString("delflag"));				
				m.setDeleteDate(rset.getDate("deletedate"));
				m.setRegFlag(rset.getString("regflag"));
				m.setPassword(rset.getString("password"));
				m.setTotalPurchaseCost(rset.getInt("totalpurchasecost"));
				
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	
	public List<Member> selectMemberByGender(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectMemberByGenderByPaging");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				//컬럼명은 대소문자 구분이 없다.
				m.setMemberId(rset.getString("memberid"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setBirth(rset.getInt("birth"));
				m.setEmail(rset.getString("email"));
				
				m.setPhone(rset.getString("phone"));
				
				m.setFavorite(rset.getString("favorite"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				m.setDelFlag(rset.getString("delflag"));				
				m.setDeleteDate(rset.getDate("deletedate"));
				m.setRegFlag(rset.getString("regflag"));
				m.setPassword(rset.getString("password"));
				m.setTotalPurchaseCost(rset.getInt("totalpurchasecost"));
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}
	
	public List<Member> selectMemberByGrade(Connection conn, String searchKeyword, int cPage, int numPerPage) {
			List<Member> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String query = prop.getProperty("selectMemberByGradeByPaging");
			try{
				//미완성쿼리문을 가지고 객체생성. 
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, searchKeyword);
				pstmt.setInt(2, (cPage-1)*numPerPage+1);
				pstmt.setInt(3, cPage*numPerPage);
				
				//쿼리문실행
				//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
				rset = pstmt.executeQuery();
				
				while(rset.next()){
					Member m = new Member();
					//컬럼명은 대소문자 구분이 없다.
					m.setMemberId(rset.getString("memberid"));
					m.setMemberName(rset.getString("membername"));
					m.setGender(rset.getString("gender"));
					m.setBirth(rset.getInt("birth"));
					m.setEmail(rset.getString("email"));
					
					m.setPhone(rset.getString("phone"));
					
					m.setFavorite(rset.getString("favorite"));
					m.setGrade(rset.getString("grade"));
					m.setEnrollDate(rset.getDate("enrolldate"));
					
					m.setDelFlag(rset.getString("delflag"));				
					m.setDeleteDate(rset.getDate("deletedate"));
					m.setRegFlag(rset.getString("regflag"));
					m.setPassword(rset.getString("password"));
					m.setTotalPurchaseCost(rset.getInt("totalpurchasecost"));
					list.add(m);
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				close(rset);
				close(pstmt);
			}
			
			
			return list;
		}
	
	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	public int selectMemberCountByMemberId(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByMemberId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	public int selectMemberCountByMemberName(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByMemberName");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	public int selectMemberCountByGender(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByGender");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	public int selectMemberCountByGrade(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByGrade");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}
	/*memberlist, finder*/
	
	//총주문건수 - #AdminManagementServlet
	public int selectCountBuy(Connection conn) {
		PreparedStatement pstmt = null;
		int totalOrder = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectBuyCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalOrder = rset.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalOrder;
	}
	
	//총판매금액 - #AdminManagementServlet
	public int selectTotalSellCost(Connection conn) {
		PreparedStatement pstmt = null;
		int totalSellCost = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalSellCost");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalSellCost = rset.getInt("sum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalSellCost;
	}
	
	//오늘주문건수 - #AdminManagementServlet
	public int selectTodayOrder(Connection conn) {
		PreparedStatement pstmt = null;
		int todayOrder = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectTodayOrder");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				todayOrder = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return todayOrder;
	}

	//오늘가입회원수 - #AdminManagementServlet
	public int selectTodayMember(Connection conn) {
		PreparedStatement pstmt = null;
		int todayMember = 0;
		ResultSet rset = null;
		
		String query =prop.getProperty("selectTodayMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				todayMember = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rset);
			close(pstmt);
		}
		return todayMember;
		
	}

	//오늘총판매금액 - #AdminManagementServlet
	public int selectTodaySellTotalCost(Connection conn) {
		PreparedStatement pstmt = null;
		int todaySellTotalCost = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectTodaySellTotalCost");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				todaySellTotalCost = rset.getInt("todayselltotalcost");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn);
			close(pstmt);
		}
		
		return todaySellTotalCost;
	}
	
	//오늘판매리스트 - #AminDailyOrderViewServlet
	public List<Buy> selectDailyBuyList(Connection conn) {
		ArrayList<Buy> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectDailyBuyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rset.next()) {
				Buy b = new Buy();
				
				b.setOrderNo(rset.getString("orderno"));
				b.setMemberId(rset.getString("memberid"));
				b.setTotalPrice(rset.getInt("totalprice"));
				b.setOrderDate(rset.getDate("orderdate"));
				b.setResName(rset.getString("res_name"));
				b.setResAddress(rset.getString("res_address"));
				b.setResPhone(rset.getString("res_phone"));
				b.setResRequirement(rset.getString("res_requirement"));
				b.setShipStatus(rset.getString("shipStatus"));
				b.setShipNo(rset.getString("shipno"));
				
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	//총게시글수 -#AminDailyOrderViewServlet
	public int selectTotalQnA(Connection conn) {
		PreparedStatement pstmt = null;
		int totalQnA = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectTotalQnA");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalQnA = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn);
			close(pstmt);
		}
		
		return totalQnA;
	}
	
	//미답변게시글 - #AminDailyOrderViewServlet
	public int selectNoAnswerQnA(Connection conn) {
		PreparedStatement pstmt = null;
		int noAnswerQnA = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectNoAnswerQnA");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				noAnswerQnA = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return noAnswerQnA;
		
	}

	//미답변게시글 - #AdminNoAnswerListServlet
	public List<Board> boardSelectAllNoAnswer(Connection conn, int cPage, int numPerPage) {
		List<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("boardSelectAllNoAnswer");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			int startRnum = (cPage-1)*numPerPage + 1;
			int endRnum = cPage*numPerPage;
			
			pstmt.setInt(1, startRnum);
			pstmt.setInt(2, endRnum);
			
			//쿼리객체 실행
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("b_no"));
				b.setBoardTitle(rset.getString("b_title"));
				b.setBcategory(rset.getString("b_category"));
				b.setbPid(rset.getString("pid"));
				b.setbOdrderNo(rset.getString("orderno"));
				b.setbLockFlag(rset.getString("lock_flag"));
				b.setBoardPwd(rset.getString("b_pwd"));
				b.setbWriter(rset.getString("b_writer"));
				b.setbConetent(rset.getString("b_content"));
				b.setBoardDate(rset.getDate("b_date"));
			
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}
	
	//미답변게시글 - #AdminNoAnswerListServlet
	public int noAnswerBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalNoAnswerContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectNoAnswerBoardCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalNoAnswerContent = rset.getInt("cnt");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalNoAnswerContent;
	}

	//미배송 - #AdminManagementServlet
	public int selectNotShipped(Connection conn) {
		PreparedStatement pstmt = null;
		int notShipped = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectNotShipped");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				notShipped = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return notShipped;

		
	}

	//배송완료 - #AdmindeliveryCompletedServlet
	public int selectDeliveryCompleted(Connection conn) {
		PreparedStatement pstmt = null;
		int deliveryCompleted = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectDeliveryCompletedCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				deliveryCompleted = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return deliveryCompleted;
	}

	//미배송리스트 - #AdminOrderViewServlet (order)
	public List<Buy> selectNotShippedList(Connection conn) {
		ArrayList<Buy> list = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectNotShippedList");
        
        try {
            pstmt = conn.prepareStatement(query);
            rset = pstmt.executeQuery();
            list = new ArrayList<>();
            
            while(rset.next()) {
                Buy b = new Buy();

                b.setOrderNo(rset.getString("orderno"));
                b.setMemberId(rset.getString("memberid"));
                b.setTotalPrice(rset.getInt("totalprice"));
                b.setOrderDate(rset.getDate("orderdate"));
                b.setResName(rset.getString("res_name"));
                b.setResAddress(rset.getString("res_address"));
                b.setResPhone(rset.getString("res_phone"));
                b.setResRequirement(rset.getString("res_requirement"));
                b.setShipStatus(rset.getString("shipStatus"));
                b.setShipNo(rset.getString("shipno"));
                
                list.add(b);
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }

        return list;
	}

	//배송완료 - #AdminOrderViewServlet (order)
	public List<Buy> selectDeliveryComplete(Connection conn) {
		ArrayList<Buy> list = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectDeliveryComplete");
        
        try {
            pstmt = conn.prepareStatement(query);
            rset = pstmt.executeQuery();
            list = new ArrayList<>();
            
            while(rset.next()) {
                Buy b = new Buy();

                b.setOrderNo(rset.getString("orderno"));
                b.setMemberId(rset.getString("memberid"));
                b.setTotalPrice(rset.getInt("totalprice"));
                b.setOrderDate(rset.getDate("orderdate"));
                b.setResName(rset.getString("res_name"));
                b.setResAddress(rset.getString("res_address"));
                b.setResPhone(rset.getString("res_phone"));
                b.setResRequirement(rset.getString("res_requirement"));
                b.setShipStatus(rset.getString("shipStatus"));
                b.setShipNo(rset.getString("shipno"));
                
                list.add(b);
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }

        return list;
	}

	//오늘신규회원 - #AminDailyMemberListServlet
	public List<Member> selectDailyMemberList(Connection conn, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectDailyMemberListByPaging");
		
		try {
			//1.쿼리객체 준비
			pstmt = conn.prepareStatement(query);
			//startRnum, endRnum
			//numPerPage=5, cPage=1인경우 => 1~5
			//numPerPage=5, cPage=2인경우 => 6~10
			//numPerPage=5, cPage=3인경우 => 11~15
			int startRnum = (cPage-1)*numPerPage+1;
//			int startRnum = (cPage*numPerPage)-(numPerPage-1);
			int endRnum = cPage*numPerPage;
			
			pstmt.setInt(1, startRnum);		
			pstmt.setInt(2, endRnum);		
					
			
			//2.쿼리객체 실행
			rset = pstmt.executeQuery();
			
			//3.실행결과 list에 담기
			list = new ArrayList<>();
			
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setBirth(rset.getInt("birth"));
				m.setEmail(rset.getString("email"));
				
				m.setPhone(rset.getString("phone"));
			
				m.setFavorite(rset.getString("favorite"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				m.setDelFlag(rset.getString("delflag"));				
				m.setDeleteDate(rset.getDate("deletedate"));
				m.setRegFlag(rset.getString("regflag"));
				m.setPassword(rset.getString("password"));
				m.setTotalPurchaseCost(rset.getInt("totalpurchasecost"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		
		
		return list;
	}

	//오늘신규회원 - #AminDailyMemberListServlet
	public int selectDailyMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectDailyMemberCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}
	
	/*DailyMemberList, Finder*/
	public List<Member> selectDailyMemberByMemberId(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectDailyMemberByMemberIdByPaging");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setBirth(rset.getInt("birth"));
				m.setEmail(rset.getString("email"));
				
				m.setPhone(rset.getString("phone"));
				
				m.setFavorite(rset.getString("favorite"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				m.setDelFlag(rset.getString("delflag"));				
				m.setDeleteDate(rset.getDate("deletedate"));
				m.setRegFlag(rset.getString("regflag"));
				m.setPassword(rset.getString("password"));
				m.setTotalPurchaseCost(rset.getInt("totalpurchasecost"));
				
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public List<Member> selectDailyMemberByMemberName(Connection conn, String searchKeyword, int cPage,
			int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectDailyMemberByMemberNameByPaging");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				//컬럼명은 대소문자 구분이 없다.
				m.setMemberId(rset.getString("memberid"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setBirth(rset.getInt("birth"));
				m.setEmail(rset.getString("email"));
				
				m.setPhone(rset.getString("phone"));
				
				m.setFavorite(rset.getString("favorite"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				m.setDelFlag(rset.getString("delflag"));				
				m.setDeleteDate(rset.getDate("deletedate"));
				m.setRegFlag(rset.getString("regflag"));
				m.setPassword(rset.getString("password"));
				m.setTotalPurchaseCost(rset.getInt("totalpurchasecost"));
				
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public List<Member> selectDailyMemberByGender(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectDailyMemberByGenderByPaging");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				//컬럼명은 대소문자 구분이 없다.
				m.setMemberId(rset.getString("memberid"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setBirth(rset.getInt("birth"));
				m.setEmail(rset.getString("email"));
				
				m.setPhone(rset.getString("phone"));
				
				m.setFavorite(rset.getString("favorite"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				m.setDelFlag(rset.getString("delflag"));				
				m.setDeleteDate(rset.getDate("deletedate"));
				m.setRegFlag(rset.getString("regflag"));
				m.setPassword(rset.getString("password"));
				m.setTotalPurchaseCost(rset.getInt("totalpurchasecost"));
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public List<Member> selectDailyMemberByGrade(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectDailyMemberByGradeByPaging");
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				Member m = new Member();
				//컬럼명은 대소문자 구분이 없다.
				m.setMemberId(rset.getString("memberid"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setBirth(rset.getInt("birth"));
				m.setEmail(rset.getString("email"));
				
				m.setPhone(rset.getString("phone"));
				
				m.setFavorite(rset.getString("favorite"));
				m.setGrade(rset.getString("grade"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				
				m.setDelFlag(rset.getString("delflag"));				
				m.setDeleteDate(rset.getDate("deletedate"));
				m.setRegFlag(rset.getString("regflag"));
				m.setPassword(rset.getString("password"));
				m.setTotalPurchaseCost(rset.getInt("totalpurchasecost"));
				list.add(m);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public int selectDailyMemberCountByMemberId(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectDailyMemberCountByMemberId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	public int selectDailyMemberCountByMemberName(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectDailyMemberCountByMemberName");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	public int selectDailyMemberCountByGender(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectDailyMemberCountByGender");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	public int selectDailyMemberCountByGrade(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectDailyMemberCountByGrade");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContent;
	}

	/*DailyMemberList, Finder 끝*/
	//답변완료 게시판리스트 - #AdminAnswerCompletedBoardListServlet
	public int selectAnswerCompletedBoardListCount(Connection conn) {
		PreparedStatement pstmt = null;
		int answerCompletedBoardListCount = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectAnswerCompletedBoardListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				answerCompletedBoardListCount = rset.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return answerCompletedBoardListCount;
	}
	
	//답변완료 게시판리스트 - #AdminManagementServlet
	public List<Board> selectAnswerCompletedBoardList(Connection conn, int cPage, int numPerPage) {
		List<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAnswerCompletedBoardList");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			int startRnum = (cPage-1)*numPerPage + 1;
			int endRnum = cPage*numPerPage;
			
			pstmt.setInt(1, startRnum);
			pstmt.setInt(2, endRnum);
			
			//쿼리객체 실행
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while(rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("b_no"));
				b.setBoardTitle(rset.getString("b_title"));
				b.setBcategory(rset.getString("b_category"));
				b.setbPid(rset.getString("pid"));
				b.setbOdrderNo(rset.getString("orderno"));
				b.setbLockFlag(rset.getString("lock_flag"));
				b.setBoardPwd(rset.getString("b_pwd"));
				b.setbWriter(rset.getString("b_writer"));
				b.setbConetent(rset.getString("b_content"));
				b.setBoardDate(rset.getDate("b_date"));
			
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	//답변완료게시판 - #AdminAnswerCompletedBoardListServlet
	public int selectTotalAnswerCompltedContent(Connection conn) {
		PreparedStatement pstmt = null;
		int totalAnswerCompletedContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectTotalAnswerCompltedContent");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalAnswerCompletedContent = rset.getInt("cnt");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalAnswerCompletedContent;
	}



	
	//orderno로 주문물품 정보리스트 - #AdminProductOrderNoServlet
	public List<Buy> selectProductOrderNo(Connection conn, String orderNo) {
		List<Buy> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectProductOrderNo");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, orderNo);
		
			rset = pstmt.executeQuery();
			//2.쿼리객체 실행
			rset = pstmt.executeQuery();
			
			//3.실행결과 list에 담기
			list = new ArrayList<>();
			
			while(rset.next()) {
				Buy b = new Buy();
				b.setOrderNo(rset.getString("orderno"));
				b.setMemberId(rset.getString("memberid"));
				b.setTotalPrice(rset.getInt("totalprice"));
				b.setOrderDate(rset.getDate("orderdate"));
				b.setResName(rset.getString("res_name"));
				
				b.setResAddress(rset.getString("res_address"));
				b.setResPhone(rset.getString("res_phone"));
				b.setResRequirement(rset.getString("res_requirement"));
				b.setShipStatus(rset.getString("shipstatus"));
				b.setShipNo(rset.getString("shipno"));
				list.add(b);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}


	//orderNo로 상품결제정보 삭제 - #AdminProductDelete
	public int deleteProduct(Connection conn, String orderNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteProduct");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, orderNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}


	//상품결제정보 수정! #AdminProductUpdateEnd
	public int updateProductList(Connection conn, Buy b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("updateProductList");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, b.getResAddress());
			pstmt.setString(2, b.getResPhone());
			pstmt.setString(3, b.getResRequirement());
			pstmt.setString(4, b.getOrderNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	/*--------------------------------------병선--------------------------------------*/
	
	public List<BuyList> selectProductOrderNoList(Connection conn, String orderNo) {
		List<BuyList> buyList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectProductOrderNoList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, orderNo);
		
			rset = pstmt.executeQuery();
			//2.쿼리객체 실행
			rset = pstmt.executeQuery();
			
			//3.실행결과 list에 담기
			buyList = new ArrayList<>();
			
			while(rset.next()) {
				BuyList b = new BuyList();
				b.setOrderNo(rset.getString("orderNo"));
                b.setMemberId(rset.getString("memberid"));
                b.setpId(rset.getString("pId"));
                b.setpName(rset.getString("pName"));
                b.setAmount(rset.getInt("amount"));
                b.setpPrice(rset.getInt("pPrice"));
                b.setOrderDate(rset.getDate("orderdate"));
                
                buyList.add(b);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return buyList;
	}
}







