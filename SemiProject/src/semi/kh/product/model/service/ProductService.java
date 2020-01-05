package semi.kh.product.model.service;

import static semi.kh.common.JDBCTemplate.close;
import static semi.kh.common.JDBCTemplate.commit;
import static semi.kh.common.JDBCTemplate.getConnection;
import static semi.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import semi.kh.board.model.dao.BoardDao;
import semi.kh.board.model.vo.Board;
import semi.kh.board.model.vo.BoardComment;
import semi.kh.board.model.vo.BoardReview;
import semi.kh.member.model.vo.Address;
import semi.kh.member.model.vo.Member;
import semi.kh.product.model.dao.ProductDao;
import semi.kh.product.model.vo.Buy;
import semi.kh.product.model.vo.BuyList;
import semi.kh.product.model.vo.Cart;
import semi.kh.product.model.vo.Product;
import semi.kh.product.model.vo.ProductIO;

public class ProductService {

	// 전체 상품 갯수 구하기
	public int selectProductCount(String pCategory) {
		Connection conn = getConnection();
		int totalProductCnt = new ProductDao().selectProductCount(conn,pCategory);
		close(conn);		
		return totalProductCnt;
	}

	// 상품 리스트 생성
	public List<Product> selectProductList(int cPage, int numPerPage, String pCategory) {
		Connection conn = getConnection();
		List<Product> pList= new ProductDao().selectProductList(conn,cPage,numPerPage,pCategory);
		close(conn);
		return pList;
	}

	public Product selectOneProduct(String pId) {
		Connection conn = getConnection();
		Product p = new ProductDao().selectOneProduct(conn,pId);
		close(conn);
		
		return p;
	}
	

	public int addItem(Cart c) {
		Connection conn = getConnection();
		int result = new ProductDao().addItem(conn, c);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<Cart> selectCartList(String memberId) {
		Connection conn = getConnection();
		List<Cart> list = new ProductDao().selectCartList(conn, memberId);
		close(conn);
		return list;
	}

	public int insertBuy(Buy b) {
		Connection conn = getConnection();
		int result = new ProductDao().insertBuy(conn, b);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int insertBuyList(BuyList bl) {
		Connection conn = getConnection();
		int result = new ProductDao().insertBuyList(conn, bl);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
		
	}

	public int insertProductIO(ProductIO pi) {
		Connection conn = getConnection();
		int result = new ProductDao().insertProductIO(conn, pi);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int cartClear(String memberId) {
		Connection conn = getConnection();
		int result = new ProductDao().cartClear(conn, memberId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteItem(String memberId, String pId) {
		Connection conn = getConnection();
		int result = new ProductDao().deleteItem(conn, memberId, pId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public List<Address> selectAllAddress(String memberId) {
		List<Address> addrList = null;
        Connection conn = getConnection();
        addrList = new ProductDao().selectAllAddress(conn,memberId);
        close(conn);
        return addrList;
	}

	public int insertNewAddr(String memberId, String addr) {
		Connection conn = getConnection();
		int result = new ProductDao().insertNewAddr(conn, memberId, addr);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int updateAmount(String memberId, String pId, int amount) {
		Connection conn = getConnection();
		int result = new ProductDao().updateAmount(conn, memberId, pId, amount);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int addAmount(String memberId, String pId, int amount) {
		Connection conn = getConnection();
		int result = new ProductDao().addAmount(conn, memberId, pId, amount);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteAddress(String memberId, String addr) {
		Connection conn = getConnection();
		int result = new ProductDao().deleteAddress(conn, memberId, addr);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Buy> showMyBuyList(String memberId, int cPage, int numPerPage) {
        List<Buy> buyList = null;
       Connection conn = getConnection();
       buyList = new ProductDao().showMyBuyList(conn,memberId,cPage,numPerPage);
       close(conn);
       return buyList;
    }
	
	public int buyListCount(String memberId) {
		Connection conn = getConnection();
		int totalContent = new ProductDao().buyListCount(conn,memberId);
		close(conn);
		
		return totalContent;
	}

	public List<BuyList> showMyAllBuyList(String memberId) {
		List<BuyList> buyAllList = null;
        Connection conn = getConnection();
        buyAllList = new ProductDao().showMyAllBuyList(conn,memberId);
        close(conn);
        return buyAllList;
	}
	
	public int insertNM(Member m) {
		int result = 0;
		Connection conn = getConnection();
		result = new ProductDao().insertNM(conn,m);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
		
		
	}

	public Member selectNoneMemberIdChk(String orderNo, String password) {
		Member m = null ;
        Connection conn = getConnection();
        m = new ProductDao().selectNoneMemberIdChk(conn, orderNo , password);
        close(conn);
        return m;
	}

	public List<BuyList> selectNoneBuyList(String orderNo) {
		List<BuyList> list = null;
        Connection conn = getConnection();
        list = new ProductDao().selectNoneBuyList(conn, orderNo);
        close(conn);
        return list;
	}
	
	public List<Buy> showNMBuyList(String orderNo) {
		 List<Buy> list = null;
	       Connection conn = getConnection();
	       list = new ProductDao().showNMBuyList(conn, orderNo);
	       close(conn);
	       return list;
	}

	public List<Board> selectNMShipBoardList(String orderNo) {
		 List<Board> list = null;
	       Connection conn = getConnection();
	       list = new ProductDao().selectNMShipBoardList(conn, orderNo);
	       close(conn);
	       return list;
	}


	
	/**
	 * 상품별 리뷰와 qna 게시판 관련
	 */
	
	
	/**REVIEW 게시판 관련 */
	
	//REVIEW List
	public List<BoardReview> selectProductRVList(int cPage, int numPerPage, String pId, String tableName) {
		Connection conn = getConnection();
		List<BoardReview> reviewList= new ProductDao().selectProductRVList(conn,cPage,numPerPage,pId,tableName);
		close(conn);
				
		return reviewList;
	}
	
	//REVIEW INSERT
	public int ReviewInsert(BoardReview br) {
		Connection conn = getConnection();
		int result = new ProductDao().ReviewInsert(conn, br);
		if(result>0) {commit(conn);}
		else {rollback(conn);}
		close(conn);
		return result;
	}
	
	
	/**QNA 게시판 관련 */
	
	//QNA 게시판 CNT 메소드
	public int selectProductBoardCount(String pId, String tableName) {
		Connection conn = getConnection();
		int totalCnt = new ProductDao().selectProductBoardCount(conn,pId,tableName);
		close(conn);
		return totalCnt;
	}
	
	//QNA List 메소드
	public List<Board> selectProductQnAList(int cPage, int numPerPage, String pId, String tableName) {
		Connection conn = getConnection();
		List<Board> qnaList = new ProductDao().selectProductQnAList(conn,cPage,numPerPage,pId,tableName);
		close(conn);
		
		return qnaList;
	}
	
	//QNA Comment List 메소드
	public List<BoardComment> selectProductQnACommentList(String pId){
		Connection conn = getConnection();
		List<BoardComment> qnaCommentList = new ProductDao().selectProductQnACommentList(conn,pId);
		close(conn);
		
		return qnaCommentList;			
	}	
	
	//QNA INSERT 메소드
	public int insertQnaBoard(Board b) {
		
		Connection conn = getConnection();
		int result = new ProductDao().insertQnaBoard(conn, b);
		if(result > 0) {commit(conn);}			
		else {rollback(conn);}
		close(conn);
		
		return result;
	}

	public List<BoardReview> selectNMReviewList(String orderNo) {
		List<BoardReview> list = null;
		Connection conn = getConnection();
		list = new ProductDao().selectNMReviewList(conn , orderNo);
		close(conn);

		return list;
	}

	public int deleteAllItem(String memberId) {
		Connection conn = getConnection();
		int result = new ProductDao().deleteAllItem(conn, memberId);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int calcTotalPrice(String memberId) {
		Connection conn = getConnection();
		int total = new ProductDao().calcTotalPrice(conn, memberId);
		close(conn);
		return total;
	}
}
