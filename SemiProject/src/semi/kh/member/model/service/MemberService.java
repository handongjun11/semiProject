package semi.kh.member.model.service;

import static semi.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import semi.kh.member.model.dao.MemberDao;
//import semi.kh.member.model.vo.Address;
import semi.kh.member.model.vo.Member;

public class MemberService {

	public static final int LOGIN_OK = 1;
	public static final int WRONG_PASSWORD= 0;
	public static final int ID_NOT_EXIST = -1;

	public int loginCheck(Member m) {
		int result = -1;
		Connection conn = getConnection();
		result = new MemberDao().loginCheck(conn,m);
		close(conn);
		
		return result;
	}

	public Member selectOne(String memberId) {
		Member p = null;
		
		Connection conn = getConnection();
		
		p= new MemberDao().selectOne(conn,memberId);
		close(conn);
		
		return p;
	}

	public int insertMember(Member m) {
		int result = 0;
		Connection conn = getConnection();
		result = new MemberDao().insertMember(conn,m);
		if(result > 1) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int passwordUpdate(Member m) {
		int result = 0;
		Connection conn = getConnection();
		result = new MemberDao().passwordUpdate(conn,m);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int updateMember(Member m) {
		int result = 0;
		Connection conn = getConnection();
		result = new MemberDao().updateMember(conn,m);
		if(result > 1) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteMember(String memberId) {
		int result = 0;
		Connection conn = getConnection();
		result = new MemberDao().deleteMember(conn,memberId);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int temporaryPwd(String memberId, String value) {
		int result = 0;
		Connection conn = getConnection();
		result = new MemberDao().temporaryPwd(conn,memberId,value);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public String findPhone(String memberId) {
		String findPhone = "";
		Connection conn = getConnection();
		findPhone = new MemberDao().findPhone(conn,memberId);
		close(conn);
		return findPhone;
	}

/*	public List<Address> selectAllAddress(String memberId) {
		List<Address> addrList = null;
		Connection conn = getConnection();
		addrList = new MemberDao().selectAllAddress(conn,memberId);
		close(conn);
		return addrList;
	}*/

}
