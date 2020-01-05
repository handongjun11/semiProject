package semi.kh.member.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String memberId;
	private String memberName;
	private String gender;
	private int birth;
	private String email;
	private String phone;
	private String favorite;
	private String grade;
	private Date enrollDate;
	private String delFlag;
	private Date deleteDate;
	private String regFlag;
	private String password;
	private int totalPurchaseCost;
	private String address;
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Member(String memberId, String memberName, String gender, int birth, String email, String phone,
			String favorite, String grade, Date enrollDate, String delFlag, Date deleteDate,
			String regFlag, String password, int totalPurchaseCost, String address) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.gender = gender;
		this.birth = birth;
		this.email = email;
		this.phone = phone;
		
		this.favorite = favorite;
		this.grade = grade;
		this.enrollDate = enrollDate;
		this.delFlag = delFlag;
		this.deleteDate = deleteDate;
		this.regFlag = regFlag;
		this.password = password;
		this.totalPurchaseCost = totalPurchaseCost;
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getBirth() {
		return birth;
	}
	public void setBirth(int birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getRegFlag() {
		return regFlag;
	}
	public void setRegFlag(String regFlag) {
		this.regFlag = regFlag;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTotalPurchaseCost() {
		return totalPurchaseCost;
	}
	public void setTotalPurchaseCost(int totalPurchaseCost) {
		this.totalPurchaseCost = totalPurchaseCost;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", gender=" + gender + ", age=" + birth
				+ ", email=" + email + ", phone=" + phone +  ", favorite=" + favorite
				+ ", grade=" + grade + ", enrollDate=" + enrollDate + ", delFlag=" + delFlag + ", deleteDate="
				+ deleteDate + ", regFlag=" + regFlag + ", password=" + password + ", totalPurchaseCost="
				+ totalPurchaseCost +   address+"]";
	}
	
}

