package semi.kh.chart.model.vo;

import java.io.Serializable;

public class SalesVolume implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String grade;
	private String description;
	private int salePrice;
	private String address;
	private String orderno;
	
	public SalesVolume() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SalesVolume(String grade, String description, int salePrice, String address, String orderno) {
		super();
		this.grade = grade;
		this.description = description;
		this.salePrice = salePrice;
		this.address = address;
		this.orderno = orderno;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "SalesVolume [grade=" + grade + ", description=" + description + ", salePrice=" + salePrice
				+ ", address=" + address + ", orderno=" + orderno + "]";
	}
	
	
}
