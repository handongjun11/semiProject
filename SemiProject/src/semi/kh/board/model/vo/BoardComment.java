package semi.kh.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BoardComment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int commentNo;
	private String cWriter;
	private String cContent;
	private int cRef;
	private Date cDate;
	private String pId;
	
	public BoardComment() {}

	public BoardComment(int commentNo, String cWriter, String cContent, int cRef, Date cDate,String pId) {
		super();
		this.commentNo = commentNo;
		this.cWriter = cWriter;
		this.cContent = cContent;
		this.cRef = cRef;
		this.cDate = cDate;
		this.pId = pId;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getcWriter() {
		return cWriter;
	}

	public void setcWriter(String cWriter) {
		this.cWriter = cWriter;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public int getcRef() {
		return cRef;
	}

	public void setcRef(int cRef) {
		this.cRef = cRef;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return  commentNo + "," + cWriter + "," + cContent + ","
				+ cRef + "," + cDate+","+pId;
	}
	
	
	
	
	
	
	
}
