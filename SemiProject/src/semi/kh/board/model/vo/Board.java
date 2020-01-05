package semi.kh.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int boardNo;
	private String boardTitle;
	private String bcategory;
	private String bPid;
	private String bOrderNo ;
	private String bLockFlag;
	private String boardPwd;
	private String bWriter;
	private String bConetent;
	private Date boardDate;
	//댓글갯수
	private int boardCommentCnt;

	public Board() {}

	public Board(int boardNo, String boardTitle, String bcategory, String bPid, String bOrderNo, String bLockFlag,
			String boardPwd, String bWriter, String bConetent, Date boardDate, int boardCommentCnt) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.bcategory = bcategory;
		this.bPid = bPid;
		this.bOrderNo = bOrderNo;
		this.bLockFlag = bLockFlag;
		this.boardPwd = boardPwd;
		this.bWriter = bWriter;
		this.bConetent = bConetent;
		this.boardDate = boardDate;
		this.boardCommentCnt = boardCommentCnt;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBcategory() {
		return bcategory;
	}

	public void setBcategory(String bcategory) {
		this.bcategory = bcategory;
	}

	public String getbPid() {
		return bPid;
	}

	public void setbPid(String bPid) {
		this.bPid = bPid;
	}

	public String getbOdrderNo() {
		return bOrderNo;
	}

	public void setbOdrderNo(String bOdrderNo) {
		this.bOrderNo = bOdrderNo;
	}

	public String getbLockFlag() {
		return bLockFlag;
	}

	public void setbLockFlag(String bLockFlag) {
		this.bLockFlag = bLockFlag;
	}

	public String getBoardPwd() {
		return boardPwd;
	}

	public void setBoardPwd(String boardPwd) {
		this.boardPwd = boardPwd;
	}

	public String getbWriter() {
		return bWriter;
	}

	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}

	public String getbConetent() {
		return bConetent;
	}

	public void setbConetent(String bConetent) {
		this.bConetent = bConetent;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getBoardCommentCnt() {
		return boardCommentCnt;
	}

	public void setBoardCommentCnt(int boardCommentCnt) {
		this.boardCommentCnt = boardCommentCnt;
	}

	@Override
	public String toString() {
		return boardNo + "," + boardTitle + "," + bcategory + ","
				+ bPid + "," + bOrderNo + "," + bLockFlag + "," + boardPwd
				+ "," + bWriter + "," + bConetent + "," + boardDate
				+ "," + boardCommentCnt;
	}

	
	
	
	
	
	
}
