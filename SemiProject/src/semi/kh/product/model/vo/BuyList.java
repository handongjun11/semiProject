package semi.kh.product.model.vo;

import java.sql.Date;

public class BuyList {
	
	private String orderNo;
	private String memberId;
	private String pId;
	private String pName;
	private int amount;
	private int pPrice;
	private Date orderDate;
	
	public BuyList() {}
	
	public BuyList(String orderno, String memberId, String pId, String pName, int amount, int pPrice, Date orderDate) {
		this.orderNo = orderno;
		this.memberId = memberId;
		this.pId = pId;
		this.pName = pName;
		this.amount = amount;
		this.pPrice = pPrice;
		this.orderDate = orderDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderno) {
		this.orderNo = orderno;
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

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "BuyList [orderno=" + orderNo + ", memberId=" + memberId + ", pId=" + pId + ", pName=" + pName
				+ ", amount=" + amount + ", pPrice=" + pPrice + ", orderDate=" + orderDate + "]";
	}

}
