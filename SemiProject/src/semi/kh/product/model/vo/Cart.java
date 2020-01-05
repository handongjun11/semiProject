package semi.kh.product.model.vo;

public class Cart {
	private String pId;
	private String pName;
	private int amount;
	private int pPrice;
	private String memberId;
	
	public Cart() {}
	
	public Cart(String pId, String pName, int amount, int pPrice, String memberId) {
		this.pId = pId;
		this.pName = pName;
		this.amount = amount;
		this.pPrice = pPrice;
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Cart [pId=" + pId + ", pName=" + pName + ", amount=" + amount + ", pPrice=" + pPrice + ", memberId="
				+ memberId + "]";
	}

}
