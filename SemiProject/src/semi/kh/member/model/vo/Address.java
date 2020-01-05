package semi.kh.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Address implements Serializable {
	
	private String memberId;
	private String address;
	private Date insertDate;
	
	public Address() {
		super();
	}

	public Address(String memberId, String address, Date insertDate) {
		super();
		this.memberId = memberId;
		this.address = address;
		this.insertDate = insertDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	@Override
	public String toString() {
		return "[" + memberId + "," + address + "," + insertDate + "]";
	}
	
	

}
