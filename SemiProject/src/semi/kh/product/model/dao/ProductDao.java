package semi.kh.product.model.dao;

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
import semi.kh.member.model.vo.Address;
import semi.kh.member.model.vo.Member;
import semi.kh.product.model.service.ProductService;
import semi.kh.product.model.vo.Buy;
import semi.kh.product.model.vo.BuyList;
import semi.kh.product.model.vo.Cart;
import semi.kh.product.model.vo.Product;
import semi.kh.product.model.vo.ProductIO;

public class ProductDao {
	
	private Properties prop = new Properties();
	
	
	//기본생성자 
	public ProductDao() {
		try {
			String fileName = ProductService.class
				    		  .getResource("/sql/product/product-query.properties").getPath();	
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	// 전체 상품 갯수 구하기
	public int selectProductCount(Connection conn, String pCategory) {
		PreparedStatement pstmt = null;
		int totalProductCnt = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectProductCount");
		
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pCategory);
			pstmt.setString(2, pCategory);
			rset = pstmt.executeQuery();			
			while(rset.next()){
				totalProductCnt = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return totalProductCnt;
	}

	public List<Product> selectProductList(Connection conn, int cPage, int numPerPage, String pCategory) {
		List<Product> pList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectProductList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pCategory);
			pstmt.setString(2, pCategory);
			pstmt.setInt(3, (cPage-1)*numPerPage+1);
			pstmt.setInt(4, cPage*numPerPage);
			rset = pstmt.executeQuery();
			while(rset.next()){
				Product p = new Product();
				p.setpId(rset.getString("PID"));
				p.setpName(rset.getString("PNAME"));
				p.setpPrice(rset.getInt("PPRICE"));
				p.setpCategory(rset.getString("PCATEGORY"));
				p.setDescription(rset.getString("DESCRIPTION"));
				p.setpCompany(rset.getString("PCOMPANY"));
				p.setpEnrolldate(rset.getDate("PENROLLDATE"));
				p.setpStock(rset.getInt("PSTOCK"));
				p.setCnt(rset.getInt("cnt"));
				p.setSumrate(rset.getInt("sumrate")==0?6:rset.getInt("sumrate"));
				pList.add(p);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return pList;
	}

	public Product selectOneProduct(Connection conn, String pId) {
		Product p = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOneProduct");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				p = new Product();
				p.setpId(rset.getString("PID"));
				p.setpName(rset.getString("PNAME"));
				p.setpPrice(rset.getInt("PPRICE"));
				p.setpCategory(rset.getString("PCATEGORY"));
				p.setDescription(rset.getString("DESCRIPTION"));
				p.setpCompany(rset.getString("PCOMPANY"));
				p.setpEnrolldate(rset.getDate("PENROLLDATE"));
				p.setpStock(rset.getInt("PSTOCK"));
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return p;
	}

	public int addItem(Connection conn, Cart c) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("addItem");
		
		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, c.getpId());
			pstmt.setString(2, c.getpName());
			pstmt.setInt(3, c.getAmount());
			pstmt.setInt(4, c.getpPrice());
			pstmt.setString(5, c.getMemberId());

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();//콘솔로깅용으로 남겨둠
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Cart> selectCartList(Connection conn, String memberId) {
		ArrayList<Cart> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCartList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			
			while(rset.next()) {
				Cart c = new Cart();
				c.setpId(rset.getString("pId"));
				c.setpName(rset.getString("pName"));
				c.setpPrice(rset.getInt("pPrice"));
				c.setAmount(rset.getInt("amount"));
				c.setMemberId(rset.getString("memberId"));
				
				list.add(c);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int insertBuy(Connection conn, Buy b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertBuy");
		
		try {

			pstmt = conn.prepareStatement(query);

			
			pstmt.setString(1, b.getOrderNo());
			pstmt.setString(2, b.getMemberId());
			pstmt.setInt(3, b.getTotalPrice());
			pstmt.setString(4, b.getResName());
			pstmt.setString(5, b.getResAddress());
			pstmt.setString(6, b.getResPhone());
			pstmt.setString(7, b.getResRequirement());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();//콘솔로깅용으로 남겨둠
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertBuyList(Connection conn, BuyList bl) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertBuyList");
		
		try {

			pstmt = conn.prepareStatement(query);

			
			pstmt.setString(1, bl.getOrderNo());
			pstmt.setString(2, bl.getMemberId());
			pstmt.setString(3, bl.getpId());
			pstmt.setString(4, bl.getpName());
			pstmt.setInt(5, bl.getAmount());
			pstmt.setInt(6, bl.getpPrice());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();//콘솔로깅용으로 남겨둠
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertProductIO(Connection conn, ProductIO pi) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertProductIO");
		
		try {

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, pi.getOrderNo());
			pstmt.setString(2, pi.getMemberId());
			pstmt.setString(3, pi.getpId());
			pstmt.setInt(4, pi.getAmount());
			pstmt.setString(5, pi.getStatus());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();//콘솔로깅용으로 남겨둠
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int cartClear(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("cartClear");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteItem(Connection conn, String memberId, String pId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteItem");
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pId);
			pstmt.setString(2, memberId);

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();//콘솔로깅용으로 남겨둠
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Address> selectAllAddress(Connection conn, String memberId) {
		List<Address> addrList = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectAllAddress");
        
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, memberId);
            rset = pstmt.executeQuery();
            
            addrList = new ArrayList<>();
            while(rset.next()) {
                Address addr = new Address();
                addr.setMemberId(rset.getString("memberid"));
                addr.setAddress(rset.getString("address"));
                addr.setInsertDate(rset.getDate("insertdate"));
                
                addrList.add(addr);
                
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
        }
        
        
        return addrList;
	}

	public int insertNewAddr(Connection conn, String memberId, String addr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertNewAddr");
		
		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, addr);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();//콘솔로깅용으로 남겨둠
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateAmount(Connection conn, String memberId, String pId, int amount) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateAmount");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, amount);
			pstmt.setString(2, pId);
			pstmt.setString(3, memberId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int addAmount(Connection conn, String memberId, String pId, int amount) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("addAmount");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, amount);
			pstmt.setString(2, pId);
			pstmt.setString(3, memberId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteAddress(Connection conn, String memberId, String addr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteAddress");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, addr);
			pstmt.setString(2, memberId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public List<Buy> showMyBuyList(Connection conn, String memberId, int cPage, int numPerPage) {
		List<Buy> buyList = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("buyList");
        
        try {
        	 int startRnum = (cPage-1)*numPerPage +1;
	         int endRnum = cPage*numPerPage;
        	
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, memberId);
            pstmt.setInt(2, startRnum);
	         pstmt.setInt(3, endRnum);
	         
            rset = pstmt.executeQuery();
            
            buyList = new ArrayList<>();
            while(rset.next()) {
                Buy buy = new Buy();
                buy.setOrderNo(rset.getString("orderNo"));
                buy.setMemberId(rset.getString("memberid"));
                buy.setTotalPrice(rset.getInt("totalPrice"));
                buy.setOrderDate(rset.getDate("orderdate"));
                buy.setResName(rset.getString("res_Name"));
                buy.setResAddress(rset.getString("res_Address"));
                buy.setResPhone(rset.getString("res_Phone"));
                buy.setResRequirement(rset.getString("res_Requirement"));
                buy.setShipStatus(rset.getString("shipstatus"));
                buy.setShipNo(rset.getString("shipno"));
                
                buyList.add(buy);
                
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

	public int buyListCount(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("buyListCount");
		
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
	
	public List<BuyList> showMyAllBuyList(Connection conn, String memberId) {
		List<BuyList> buyAllList = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("buyAllList");
        
        try {
        
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, memberId);
 
            rset = pstmt.executeQuery();
            
            
            buyAllList = new ArrayList<>();
            while(rset.next()) {
                BuyList buy = new BuyList();
                buy.setOrderNo(rset.getString("orderNo"));
                buy.setMemberId(rset.getString("memberid"));
                buy.setpId(rset.getString("pId"));
                buy.setpName(rset.getString("pName"));
                buy.setAmount(rset.getInt("amount"));
                buy.setpPrice(rset.getInt("pPrice"));
                buy.setOrderDate(rset.getDate("orderdate"));
                
                buyAllList.add(buy);
                
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            close(rset);
            close(pstmt);
        }
        
        
        return buyAllList;
	}
	
	public int insertNM(Connection conn, Member m) {

			int result = 0;
			PreparedStatement pstmt = null;
			String query = prop.getProperty("insertNM");
			
			try {

				pstmt = conn.prepareStatement(query);


				pstmt.setString(1, m.getMemberId());
				pstmt.setString(2, m.getMemberName());
				pstmt.setString(3, m.getEmail());
				pstmt.setString(4, m.getPhone());
				pstmt.setString(5, m.getPassword());
			
				
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();//콘솔로깅용으로 남겨둠
			} finally {
				close(pstmt);
			}
			
			return result;
	}

	public Member selectNoneMemberIdChk(Connection conn, String orderNo, String password) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectNoneMemberIdChk");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, orderNo);
			pstmt.setString(2, password);
			
		
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setBirth(rset.getInt("birth"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setFavorite(rset.getString("favorite"));
				m.setGrade(rset.getString("grade"));
	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

	public List<BuyList> selectNoneBuyList(Connection conn, String orderNo) {
		List<BuyList> list = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectNoneBuyList");
        
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, orderNo);
           
            rset = pstmt.executeQuery();
            
             list = new ArrayList<>();
            while(rset.next()) {
                BuyList buyList = new BuyList();
                buyList.setOrderNo(rset.getString("orderno"));
                buyList.setMemberId(rset.getString("memberid"));
                buyList.setpId(rset.getString("pid"));
                buyList.setpName(rset.getString("pname"));
                buyList.setAmount(rset.getInt("amount"));
                buyList.setpPrice(rset.getInt("pprice"));
                buyList.setOrderDate(rset.getDate("orderdate"));
                
                
               list.add(buyList);
                
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
	
	public List<Buy> showNMBuyList(Connection conn, String orderNo) {
		List<Buy> list = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("BuyNMList");
        
        try {
        	
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, orderNo);
            
            rset = pstmt.executeQuery();
            
            list = new ArrayList<>();
            while(rset.next()) {
                Buy buy = new Buy();
                buy.setOrderNo(rset.getString("orderNo"));
                buy.setMemberId(rset.getString("memberid"));
                buy.setTotalPrice(rset.getInt("totalPrice"));
                buy.setOrderDate(rset.getDate("orderdate"));
                buy.setResName(rset.getString("res_Name"));
                buy.setResAddress(rset.getString("res_Address"));
                buy.setResPhone(rset.getString("res_Phone"));
                buy.setResRequirement(rset.getString("res_Requirement"));
                buy.setShipStatus(rset.getString("shipstatus"));
                buy.setShipNo(rset.getString("shipno"));
                
                list.add(buy);
                
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

	public List<Board> selectNMShipBoardList(Connection conn, String orderNo) {
		List<Board> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectNMShipBoardList");
		
		try {
			//1.쿼리 객체 준비
			pstmt = conn.prepareStatement(query);
			
	     
	         
	         pstmt.setString(1, orderNo);
	     
			
			
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

	/**
	 * 상품별 리뷰와 qna 게시판 관련
	 */
	
	
	/**REVIEW 게시판 관련 */
	
	//REVIEW List
	public List<BoardReview> selectProductRVList(Connection conn, int cPage, int numPerPage, String pId,String tableName) {
		List<BoardReview> reviewList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectProductBoardList");
		query = query.replaceAll("table", tableName);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rset = pstmt.executeQuery();
			while(rset.next()){
				BoardReview br = new BoardReview();
				br.setbNo(rset.getInt("B_NO"));
				br.setPid(rset.getString("PID"));
				br.setWriter(rset.getString("B_WRITER"));
				br.setContent(rset.getString("B_CONTENT"));
				br.setOfile(rset.getString("B_ORIGINAL_FILENAME"));
				br.setRfile(rset.getString("B_RENAMED_FILENAME"));
				br.setBdate(rset.getDate("B_DATE"));
				br.setRate(rset.getInt("B_RATE"));
				reviewList.add(br);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return reviewList;
	}

	
	//REVIEW INSERT
	public int ReviewInsert(Connection conn, BoardReview br) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("PrdReviewInsert");
		
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
			e.printStackTrace();
		}finally {
			close(pstmt);			
		}		
		return result;
	}
	
	
	/**QNA 게시판 관련 */
	
	//QNA 게시판 CNT 메소드
	public int selectProductBoardCount(Connection conn, String pId, String tableName) {
		PreparedStatement pstmt = null;
		int totalRVCnt = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectProductBoardCount");		
		query = query.replaceAll("table", tableName);
		
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pId);
			rset = pstmt.executeQuery();			
			while(rset.next()){
				totalRVCnt = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return totalRVCnt;
	}

	
	//QNA List 메소드
	public List<Board> selectProductQnAList(Connection conn, int cPage, int numPerPage, String pId, String tableName) {
		List<Board> qnaList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectProductBoardList");
		query = query.replaceAll("table", tableName);
		//System.out.println("dao쿼리"+ query);
		//System.out.println("pid cpage numperpage"+ pId + cPage+ numPerPage);

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pId);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rset = pstmt.executeQuery();
			while(rset.next()){
				Board b = new Board();
				b.setBoardNo(rset.getInt("b_no"));
				b.setBoardTitle(rset.getString("b_title"));
				b.setBcategory(rset.getString("b_category"));
				b.setbPid(rset.getString("pid"));
				b.setbLockFlag(rset.getString("lock_flag"));
				b.setBoardPwd(rset.getString("b_pwd"));
				b.setbWriter(rset.getString("b_writer"));
				b.setbConetent(rset.getString("b_content"));
				b.setBoardDate(rset.getDate("b_date"));
				b.setBoardCommentCnt(rset.getInt("board_comment_cnt"));
				
				qnaList.add(b);
			}			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
			
		//System.out.println("qnaList"+ qnaList);
		return qnaList;
	}
	
	
	//QNA Comment List 메소드
	public List<BoardComment> selectProductQnACommentList(Connection conn, String pId){
		List<BoardComment> qnaCommentList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectProductQnACommentList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pId);			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				BoardComment bc = new BoardComment();
				bc.setCommentNo(rset.getInt("c_no"));
				bc.setcWriter(rset.getString("c_writer"));
				bc.setcContent(rset.getString("c_content"));
				bc.setcRef(rset.getInt("c_ref"));
				bc.setcDate(rset.getDate("c_comment_date"));
				bc.setpId(rset.getString("PID"));
				//list에 추가
				qnaCommentList.add(bc);
			}
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return qnaCommentList;
	}
	
	
	//QNA INSERT 메소드
	public int insertQnaBoard(Connection conn, Board b) {
		
		int result = 0 ;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("PrdQnAInsert");
		
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

	public List<BoardReview> selectNMReviewList(Connection conn, String orderNo) {
		List<BoardReview> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectNMReviewList");
		
		try {
		
			pstmt = conn.prepareStatement(query);
		
			pstmt.setString(1, orderNo);
			
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

	public int deleteAllItem(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteAllItem");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int calcTotalPrice(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int total = 0;
		ResultSet rset = null;
		String query = prop.getProperty("calcTotalPrice");		
		
		try{
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();			
			while(rset.next()){
				total = rset.getInt("sum");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return total;

	}
	

	
}
