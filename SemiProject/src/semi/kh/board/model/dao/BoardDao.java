package semi.kh.board.model.dao;

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
import semi.kh.board.model.vo.BoardComment;
import semi.kh.board.model.vo.BoardReview;

public class BoardDao {
	Properties prop = new Properties();
	
	public BoardDao() {
		String fileName = BoardDao.class.getResource("/sql/board/board-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
//		System.out.println(list);
		
		
		return list;
	}

	public int BordCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardCount");
		
		try {
			pstmt = conn.prepareStatement(query);
		
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

	public int insertQnaBoard(Connection conn, Board b) {
		int result = 0 ;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertQnaBoard");
		
		try {
			//쿼리객체 생성
			pstmt = conn.prepareStatement(query);
			
			//쿼리 완성
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBcategory());
			pstmt.setString(3, b.getbPid());
			pstmt.setString(4, b.getbLockFlag());
			pstmt.setString(5, b.getBoardPwd());
			pstmt.setString(6, b.getbWriter());
			pstmt.setString(7, b.getbConetent());
			
			
			//쿼리문 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Board selectByBoardNo(Connection conn, int boardNo) {
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectByBoardNo");
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setInt(1, boardNo);
			
	
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Board();
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
				//b.setBoardCommentCnt(rset.getInt("board_comment_cnt"));
				
				
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
				
			}
		
			return b;
	}

	public int updateBoard(Connection conn, Board b) {
		int result =0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateQnaBoard");
		//System.out.println("여기 다오인데 왜 업데이트가 안될까"+b);
		
		try {
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBcategory());
			pstmt.setString(3, b.getbLockFlag());
			pstmt.setString(4, b.getBoardPwd());
			pstmt.setString(5, b.getbWriter());
			pstmt.setString(6, b.getbConetent());
			pstmt.setInt(7, b.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteQnaBoard(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteQnaBoard");
		//System.out.println("다오 쿼리입니다."+query);
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo); 
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
	
		return result;
	}

	public int BoardCommentInsert(Connection conn, BoardComment b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertBoardComment");
		
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			
			pstmt.setString(1,b.getcWriter());
			pstmt.setString(2,b.getcContent());
			pstmt.setInt(3 , b.getcRef());
			
			result = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		return result;
	}

	public List<BoardComment> selectCommentList(Connection conn, int boardNo) {
		List<BoardComment> commentList = null;
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		String query = prop.getProperty("selectCommentList");
		
		try {
			//1.statement객체 생성
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			//2.실행
			rset = pstmt.executeQuery();
			
			//3.list객체 담기
			commentList = new ArrayList<>();
			while(rset.next()) {
				BoardComment bc = new BoardComment();
				bc.setCommentNo(rset.getInt("c_no"));
				bc.setcWriter(rset.getString("c_writer"));
				bc.setcContent(rset.getString("c_content"));
				bc.setcRef(rset.getInt("c_ref"));
				bc.setcDate(rset.getDate("c_comment_date"));
				
				//list에 추가
				commentList.add(bc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//자원반납
			close(rset);
			close(pstmt);
		}
		
		
		return commentList;
	}

	public int boardCommentDelete(Connection conn, int cNo) {
		int result = 0;
		PreparedStatement pstmt = null;

		String query = prop.getProperty("boardCommentDelete");
		
		try {

				pstmt = conn.prepareStatement(query);

				pstmt.setInt(1, cNo);
				result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int ReviewInsert(Connection conn, BoardReview br) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("ReviewInsert");
		//System.out.println("다오보트리뷰"+ br );
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, br.getPid());
			pstmt.setString(2, br.getWriter());
			pstmt.setString(3, br.getContent());
			pstmt.setString(4, br.getOfile());
			pstmt.setString(5, br.getRfile());
			pstmt.setInt(6, br.getRate());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}
		
		return result;
	}

	public List<BoardReview> reviewSelectAll(Connection conn, int cPage, int numPerPage) {
		
		List<BoardReview> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("reviewSelectAllPaging");
		
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
				BoardReview br = new BoardReview();
				br.setbNo(rset.getInt("b_no"));
				br.setPid(rset.getString("pid"));
				br.setWriter(rset.getString("b_writer"));
				br.setContent(rset.getString("b_content"));
				br.setOfile(rset.getString("b_original_filename"));
				br.setRfile(rset.getString("b_renamed_filename"));
				br.setBdate(rset.getDate("b_date"));
				br.setRate(rset.getInt("b_rate"));
			
				list.add(br);
				
			}
			
	
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(rset);
			close(pstmt);
		}

		
		
		return list;
	
	}

	public int ReviewCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectReviewCount");
		
		try {
			pstmt = conn.prepareStatement(query);
		
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

	public int insertShipQnaBoard(Connection conn, Board b) {
		int result = 0 ;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertShipQnaBoard");
		
		try {
			//쿼리객체 생성
			pstmt = conn.prepareStatement(query);
			
			//쿼리 완성
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBcategory());
			pstmt.setString(3, b.getbOdrderNo());
			pstmt.setString(4, b.getbWriter());
			pstmt.setString(5, b.getbConetent());
			
			//쿼리문 실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}
	
	public List<Board> showMyQNAList(Connection conn, int cPage, int numPerPage, String memberId) {
		List<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("showMyQNAList");
		
		try {
			//1.쿼리 객체 준비
			pstmt = conn.prepareStatement(query);
			
	        int startRnum = (cPage-1)*numPerPage +1;
	        int endRnum = cPage*numPerPage;
	         
	         pstmt.setString(1, memberId);
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

				list.add(b);
				
			}
			
	
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(rset);
			close(pstmt);
		}
//		System.out.println(list);
		
		
		return list;
	}

	public int showMyQNAListCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("showMyQNAListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
		
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

	public List<BoardReview> showMyReviewList(Connection conn, int cPage, int numPerPage, String memberId) {
		List<BoardReview> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("showMyReviewList");
		
		try {
			//1.쿼리 객체 준비
			pstmt = conn.prepareStatement(query);
			
	        int startRnum = (cPage-1)*numPerPage +1;
	        int endRnum = cPage*numPerPage;
	         
	         pstmt.setString(1, memberId);
	         pstmt.setInt(2, startRnum);
	         pstmt.setInt(3, endRnum);			
			
			//2.쿼리객체 실행
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
		
			while(rset.next()) {
				BoardReview br = new BoardReview();
				
				br.setbNo(rset.getInt("B_NO"));
				br.setPid(rset.getString("PID"));
				br.setWriter(rset.getString("B_WRITER"));
				br.setContent(rset.getString("B_CONTENT"));
				br.setOfile(rset.getString("B_ORIGINAL_FILENAME"));
				br.setRfile(rset.getString("B_RENAMED_FILENAME"));
				br.setBdate(rset.getDate("B_DATE"));
				br.setRate(rset.getInt("B_RATE"));				

				list.add(br);
				
			}
			
	
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(rset);
			close(pstmt);
		}		
		
		return list;
	}
	
	public int showMyReviewListCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("showMyReviewListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
		
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


	public List<Board> boardSelectOne(Connection conn, String category, int cPage, int numPerPage) {
		List<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		
		try {
			
	         int startRnum = (cPage-1)*numPerPage +1;
	         int endRnum = cPage*numPerPage;
	         
	         String query = "";
	         if(category.equals("모아보기")) {
	        	 pstmt = conn.prepareStatement(prop.getProperty("boardSelectAllPaging"));
	        	 pstmt.setInt(1, startRnum);
	        	 pstmt.setInt(2, endRnum);
	         }
	         else {
	        	 pstmt = conn.prepareStatement(prop.getProperty("boardSelectOnePaging"));
	        	 pstmt.setString(1, category);
	        	 pstmt.setInt(2, startRnum);
	        	 pstmt.setInt(3, endRnum);
	         }
	         
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

	public int BoardCountbyCategory(Connection conn, String category) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("BoardCountbyCategory");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
			
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
	
	public List<Board> boardSelectOneTitle(Connection conn, String searchKeyword, int cPage,int numPerPage,String category) {
		List<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("boardSelectOneTitle");
		
		
		try {
			
	         int startRnum = (cPage-1)*numPerPage +1;
	         int endRnum = cPage*numPerPage;
	       
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, category);
	         pstmt.setString(2, "%"+searchKeyword+"%");
	         pstmt.setInt(3, startRnum);
	         pstmt.setInt(4, endRnum);
	     
	         
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

	public int BoardSelectTitleCount(Connection conn, String category, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("BoardSelectTitleCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
			 pstmt.setString(2, "%"+searchKeyword+"%");
			
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

	public List<Board> boardSelectOneContent(Connection conn, String searchKeyword, int cPage, int numPerPage,String category) {
		List<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("boardSelectOneContent");
		
		
		try {
			
	         int startRnum = (cPage-1)*numPerPage +1;
	         int endRnum = cPage*numPerPage;
	       
	         pstmt = conn.prepareStatement(query);
	         pstmt.setString(1, category);
	         pstmt.setString(2, "%"+searchKeyword+"%");
	         pstmt.setInt(3, startRnum);
	         pstmt.setInt(4, endRnum);
	     
	         
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

	public int BoardSelectContentCount(Connection conn, String category, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("BoardSelectContentCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
			 pstmt.setString(2, "%"+searchKeyword+"%");
			
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
	
	public List<Board> boardSelectNotice(Connection conn, String notice) {
		List<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("boardSelectNotice");	
		
		try {
			
	        
	         pstmt = conn.prepareStatement(query);
	         
	         pstmt.setString(1, notice);
	     
	         
			//2.쿼리객체 실행
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
			
	
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public BoardReview selectBybrBoardNo(Connection conn, int boardno) {
		BoardReview br = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBybrBoardNo");
		
		try {
	
			
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, boardno);
	
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				br = new BoardReview();
				br.setbNo(rset.getInt("b_no"));
				br.setPid(rset.getString("pid"));
				br.setWriter(rset.getString("b_writer"));
				br.setContent(rset.getString("b_content"));
				br.setOfile(rset.getString("b_original_filename"));
				br.setRfile(rset.getString("b_renamed_filename"));
				br.setBdate(rset.getDate("b_date"));
				br.setRate(rset.getInt("b_rate"));
				
				
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
				
			}
		
			return br;
	}

	public int UpdateReview(Connection conn, BoardReview br) {
		int result =0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("UpdateReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, br.getContent());
			pstmt.setString(2, br.getOfile());
			pstmt.setString(3, br.getRfile());
			pstmt.setInt(4, br.getRate());
			pstmt.setInt(5, br.getbNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteReview(Connection conn, int boardno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	public int recommendPlus(Connection conn, int bno) {
		int result =0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("recommendPlus");
		
		try {
			pstmt = conn.prepareStatement(query);
	    
	        pstmt.setInt(1, bno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectRecommend(Connection conn, int bno) {
		int newRecommend = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BoardReview	br =null;
		String query = prop.getProperty("selectRecommend");
		
		try {
	
			
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, bno);
	
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
		    newRecommend =  rset.getInt("recommend"); 
			}
			
			
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
				
			}
		
			return newRecommend;
	}

	
}
