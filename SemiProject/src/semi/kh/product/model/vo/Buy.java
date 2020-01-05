package semi.kh.product.model.vo;

import java.sql.Date;

public class Buy {
	
	private String orderNo;
	private String memberId;
	private int totalPrice;
	private Date orderDate;
	private String resName;
	private String resAddress;
	private String resPhone;
	private String resRequirement;
	private String shipStatus;
	private String shipNo;
	 
	public Buy() {}
	
	public Buy(String orderNo, String memberId, int totalPrice, Date orderDate, String resName, String resAddress,
			String resPhone, String resRequirement, String shipStatus, String shipNo) {
		this.orderNo = orderNo;
		this.memberId = memberId;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.resName = resName;
		this.resAddress = resAddress;
		this.resPhone = resPhone;
		this.resRequirement = resRequirement;
		this.shipStatus = shipStatus;
		this.shipNo = shipNo;
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

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResAddress() {
		return resAddress;
	}

	public void setResAddress(String resAddress) {
		this.resAddress = resAddress;
	}

	public String getResPhone() {
		return resPhone;
	}

	public void setResPhone(String resPhone) {
		this.resPhone = resPhone;
	}

	public String getResRequirement() {
		return resRequirement;
	}

	public void setResRequirement(String resRequirement) {
		this.resRequirement = resRequirement;
	}
	
	public String getShipStatus() {
		return shipStatus;
	}

	public void setShipStatus(String shipStatus) {
		this.shipStatus = shipStatus;
	}
	
	public String getShipNo() {
		return shipNo;
	}

	public void setShipNo(String shipNo) {
		this.shipNo = shipNo;
	}

	@Override
	public String toString() {
		return "Buy [orderNo=" + orderNo + ", memberId=" + memberId + ", totalPrice=" + totalPrice + ", orderDate="
				+ orderDate + ", resName=" + resName + ", resAddress=" + resAddress + ", resPhone=" + resPhone
				+ ", resRequirement=" + resRequirement + ", shipStatus=" + shipStatus + ", shipNo=" + shipNo + "]";
	}

}
