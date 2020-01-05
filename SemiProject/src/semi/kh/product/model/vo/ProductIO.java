package semi.kh.product.model.vo;

import java.sql.Date;

public class ProductIO {
	
	private int ioNo;
	private String orderNo;
	private String memberId;
	private String pId;
	private Date pDate;
	private int amount;
	private String status;
	
	public ProductIO() {}
	
	public ProductIO(int ioNo, String orderNo, String memberId, String pId, Date pDate, int amount, String status) {
		this.ioNo = ioNo;
		this.orderNo = orderNo;
		this.memberId = memberId;
		this.pId = pId;
		this.pDate = pDate;
		this.amount = amount;
		this.status = status;
	}

	public int getIoNo() {
		return ioNo;
	}

	public void setIoNo(int ioNo) {
		this.ioNo = ioNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public Date getpDate() {
		return pDate;
	}

	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProductIO [ioNo=" + ioNo + ", orderNo=" + orderNo + ", memberId=" + memberId + ", pId=" + pId
				+ ", pDate=" + pDate + ", amount=" + amount + ", status=" + status + "]";
	}
	
	
}
