package semi.kh.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class BoardReview implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int bNo ;
	private String pid;
	private String writer;
	private String content;
	private String ofile;
	private String rfile;
	private Date bdate;
	private int rate;
	private int recommend ;
	
	public BoardReview() {}

	public BoardReview(int bNo, String pid, String writer, String content, String ofile, String rfile, Date bdate,
			int rate) {
		super();
		this.bNo = bNo;
		this.pid = pid;
		this.writer = writer;
		this.content = content;
		this.ofile = ofile;
		this.rfile = rfile;
		this.bdate = bdate;
		this.rate = rate;
	}
	
	
	public BoardReview(int bNo, String pid, String writer, String content, String ofile, String rfile, Date bdate,
			int rate, int recommend) {
		super();
		this.bNo = bNo;
		this.pid = pid;
		this.writer = writer;
		this.content = content;
		this.ofile = ofile;
		this.rfile = rfile;
		this.bdate = bdate;
		this.rate = rate;
		this.recommend = recommend;
	}
	
	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getbNo() {
		return bNo;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOfile() {
		return ofile;
	}

	public void setOfile(String ofile) {
		this.ofile = ofile;
	}

	public String getRfile() {
		return rfile;
	}

	public void setRfile(String rfile) {
		this.rfile = rfile;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BoardReview [bNo=" + bNo + ", pid=" + pid + ", writer=" + writer + ", content=" + content + ", ofile="
				+ ofile + ", rfile=" + rfile + ", bdate=" + bdate + ", rate=" + rate + "]";
	}
	
	
	
	
	
}
