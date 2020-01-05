package semi.kh.product.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String pId;
	private String pName;
	private int pPrice;
	private String pCategory;
	private String description;
	private String pCompany;
	private Date pEnrolldate;
	private int pStock;
	private int cnt;
	private int sumrate;
	
	
	public Product() {
		super();
	}

	public Product(String pId, String pName, int pPrice, String pCategory, String description, String pCompany,
				   Date pEnrolldate, int pStock) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pCategory = pCategory;
		this.description = description;
		this.pCompany = pCompany;
		this.pEnrolldate = pEnrolldate;
		this.pStock = pStock;
	}
	
	

	public Product(String pId, String pName, int pPrice, String pCategory, String description, String pCompany,
			Date pEnrolldate, int pStock, int cnt, int sumrate) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pPrice = pPrice;
		this.pCategory = pCategory;
		this.description = description;
		this.pCompany = pCompany;
		this.pEnrolldate = pEnrolldate;
		this.pStock = pStock;
		this.cnt = cnt;
		this.sumrate = sumrate;
	}
	
	

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getSumrate() {
		return sumrate;
	}

	public void setSumrate(int sumrate) {
		this.sumrate = sumrate;
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

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public String getpCategory() {
		return pCategory;
	}

	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getpCompany() {
		return pCompany;
	}

	public void setpCompany(String pCompany) {
		this.pCompany = pCompany;
	}

	public int getpStock() {
		return pStock;
	}

	public void setpStock(int pStock) {
		this.pStock = pStock;
	}
	
	public Date getpEnrolldate() {
		return pEnrolldate;
	}

	public void setpEnrolldate(Date pEnrolldate) {
		this.pEnrolldate = pEnrolldate;
	}

	@Override
	public String toString() {
		return "Product [" + pId + ", " + pName + ", " + pPrice + ", " + pCategory
				+ ", " + description + ", " + pCompany + ", " +pEnrolldate+", "+ pStock + "]";
	}
	
	
	
}
