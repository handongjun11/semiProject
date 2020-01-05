package semi.kh.board.model.service;

import static semi.kh.common.JDBCTemplate.*;


import java.sql.Connection;
import java.util.List;

import semi.kh.board.model.dao.BoardDao;
import semi.kh.board.model.vo.Board;
import semi.kh.board.model.vo.BoardComment;
import semi.kh.board.model.vo.BoardReview;

public class BoardService {

	public List<Board> boardSelectAll(int cPage, int numPerPage) {
		List<Board> list = null;
		Connection conn = getConnection();
		list = new BoardDao().boardSelectAll(conn , cPage, numPerPage);
		close(conn);

		return list;
	}

	public int BoardCount() {
		Connection conn = getConnection();
		int totalContent = new BoardDao().BordCount(conn);
		close(conn);
		
		return totalContent;
	}

	public int insertQnaBoard(Board b) {
		Connection conn = getConnection();
		int boardNo = new BoardDao().insertQnaBoard(conn, b);
		if(boardNo > 0) {
			//System.out.println("인서트 커밋");
			commit(conn);
			
		}else {
			rollback(conn);
		}

		close(conn);
		return boardNo;
	}

	public Board selectByBoardNo(int boardNo) {
		Connection conn = getConnection();
		Board b = new BoardDao().selectByBoardNo(conn, boardNo);
		close(conn);

		return b;
	}

	public int updateQnaBoard(Board b) {
		int result = 0;
		Connection conn = getConnection();
		result = new BoardDao().updateBoard(conn, b);
		//트랜잭션 : dml
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		//자원반납
		close(conn);
		
		return result;
	}

	public int deleteQnaBoard(int boardNo) {
		int result = 0;
		Connection conn = getConnection();
		result = new BoardDao().deleteQnaBoard(conn, boardNo);
		if(result >0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		
		return result;
	}

	public int BoardCommentInsert(BoardComment b) {
		int result = 0;
		Connection conn = getConnection();
		result = new BoardDao().BoardCommentInsert(conn,b);
		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		//자원반납
		close(conn);
		
		return result;
	}

	public List<BoardComment> selectCommentList(int boardNo) {
		List<BoardComment> commentList = null;
		Connection conn = getConnection();
		commentList = new BoardDao().selectCommentList(conn , boardNo);
		close(conn); 
		//트랙잭션 필요 업다. dml이  아니야 
		
		return commentList;
	}

	public int boardCommentDelete(int cNo) {
		int result = 0;
		Connection conn = getConnection();
		result = new BoardDao().boardCommentDelete(conn, cNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int ReviewInsert(BoardReview br) {
		Connection conn = getConnection();
		int result = new BoardDao().ReviewInsert(conn, br);
		if(result>0) {
	
			commit(conn);
			}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public List<BoardReview> reviewSelectAll(int cPage, int numPerPage) {
		List<BoardReview> list = null;
		Connection conn = getConnection();
		list = new BoardDao().reviewSelectAll(conn , cPage, numPerPage);
		close(conn);

		return list;
	}

	public int ReviewCount() {
		Connection conn = getConnection();
		int totalContent = new BoardDao().ReviewCount(conn);
		close(conn);
		
		return totalContent;
	}

	public int insertShipQnaBoard(Board b) {
		Connection conn = getConnection();
		int boardNo = new BoardDao().insertShipQnaBoard(conn, b);
		if(boardNo > 0) {
			commit(conn);
			
		}else {
			rollback(conn);
		}
		close(conn);
		return boardNo;
	}
	
	public List<Board> showMyQNAList(int cPage, int numPerPage, String memberId) {
		List<Board> list = null;
		Connection conn = getConnection();
		list = new BoardDao().showMyQNAList(conn , cPage, numPerPage,memberId);
		close(conn);

		return list;
	}

	public int showMyQNAListCount(String memberId) {
		Connection conn = getConnection();
		int totalContent = new BoardDao().showMyQNAListCount(conn,memberId);
		close(conn);
		return totalContent;
	}

		public List<BoardReview> showMyReviewList(int cPage, int numPerPage, String memberId) {
		List<BoardReview> list = null;
		Connection conn = getConnection();
		list = new BoardDao().showMyReviewList(conn , cPage, numPerPage,memberId);
		close(conn);

		return list;
	}

	public int showMyReviewListCount(String memberId) {
		Connection conn = getConnection();
		int totalContent = new BoardDao().showMyReviewListCount(conn,memberId);
		close(conn);
		return totalContent;
	}

	public List<Board> boardSelectOne(String category, int cPage, int numPerPage) {
		List<Board> list = null;
		Connection conn = getConnection();
		list = new BoardDao().boardSelectOne(conn, category, cPage, numPerPage);
		close(conn);

		return list;
	}

	public int BoardCountbyCategory(String category) {
		Connection conn = getConnection();
		int totalContent = new BoardDao().BoardCountbyCategory(conn, category);
		close(conn);
		
		return totalContent;
	}
	
	public List<Board> boardSelectOneTitle(String category, int cPage, int numPerPage, String searchKeyword) {
		List<Board> list = null;
		Connection conn = getConnection();
		list = new BoardDao().boardSelectOneTitle(conn, searchKeyword, cPage, numPerPage,category);
		close(conn);

		return list;
	}

	public int BoardSelectTitleCountbyCategory(String category, String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new BoardDao().BoardSelectTitleCount(conn, category,searchKeyword);
		close(conn);
		
		return totalContent;
	}

	public List<Board> boardSelectOneContent(String category, int cPage, int numPerPage, String searchKeyword) {
		List<Board> list = null;
		Connection conn = getConnection();
		list = new BoardDao().boardSelectOneContent(conn, searchKeyword, cPage, numPerPage,category);
		close(conn);

		return list;
	}

	public int BoardSelectContentCount(String category, String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new BoardDao().BoardSelectContentCount(conn, category,searchKeyword);
		close(conn);
		
		return totalContent;
	}
	
	public List<Board> boardSelectNotice(String notice) {
		List<Board> list = null;
		Connection conn = getConnection();
		list = new BoardDao().boardSelectNotice(conn, notice);
		close(conn);

		return list;
	}

	public BoardReview selectBybrBoardNo(int boardno) {
		Connection conn = getConnection();
		BoardReview br = new BoardDao().selectBybrBoardNo(conn, boardno);
		close(conn);

		return br;
	}

	public int UpdateReview(BoardReview br) {
		int result = 0;
		Connection conn = getConnection();
		result = new BoardDao().UpdateReview(conn, br);

		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		//자원반납
		close(conn);
		
		return result;
	}

	public int deleteReview(int boardno) {
		int result = 0;
		Connection conn = getConnection();
		result = new BoardDao().deleteReview(conn, boardno);
		if(result >0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	public int recommendPlus(int bno) {
		int result = 0;
		Connection conn = getConnection();
		result = new BoardDao().recommendPlus(conn, bno );

		if(result >0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		//자원반납
		close(conn);
		
		return result;
	}

	public int selectRecommend(int bno) {
		int newRecommend = 0;
		Connection conn = getConnection();
		newRecommend = new BoardDao().selectRecommend(conn, bno);
		close(conn);

		return newRecommend;
	}
	
}
