package semi.kh.member.model.dao;


import static semi.kh.common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//import semi.kh.member.model.vo.Address;
import semi.kh.member.model.vo.Member;

public class MemberDao {

	Properties prop = new Properties();
	
	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int loginCheck(Connection conn, Member m) {
		int result = -1;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("loginCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getMemberId());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("login_check");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}

	public Member selectOne(Connection conn, String memberId) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberId);
			
		
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setBirth(rset.getInt("birth"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setFavorite(rset.getString("favorite"));
				m.setGrade(rset.getString("grade"));
	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

	public int insertMember(Connection conn, Member m) {
		int result = 0;
		int result1 = 0;
		int result2 = 0;
		PreparedStatement pstmt = null;
		
		String query1 = prop.getProperty("insertMember");
		String query2 = prop.getProperty("insertAddr");
		
		try {
			
			pstmt = conn.prepareStatement(query1);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getBirth());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getFavorite());
			
			
			result1 = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(query2);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getAddress());
			
			result2 = pstmt.executeUpdate();
			
			result = result1 + result2;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int passwordUpdate(Connection conn, Member m) {
		int result =0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("passwordUpdate");
	
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMember(Connection conn, Member m) {
		int result = 0;
		int result1 = 0;
		int result2 = 0;
		PreparedStatement pstmt = null;
		
		String query1 = prop.getProperty("memberUpdate");
		String query2 = prop.getProperty("updateAddr");
		
		try {
			pstmt = conn.prepareStatement(query1);
			
			pstmt.setString(1,m.getMemberName());
			pstmt.setString(2,m.getGender());
			pstmt.setInt(3,m.getBirth());
			pstmt.setString(4,m.getEmail());
			pstmt.setString(5,m.getPhone());
			pstmt.setString(6,m.getFavorite());
			pstmt.setString(7,m.getMemberId());
			
			result1  = pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement(query2);
			pstmt.setString(1, m.getAddress());
			pstmt.setString(2, m.getMemberId());
			pstmt.setString(3, m.getMemberId());
			
			result2 = pstmt.executeUpdate();
			
			result = result1+result2;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		int result=0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("memberDelete");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public int temporaryPwd(Connection conn, String memberId, String value) {
		int result=0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("temporaryPwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, value);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}

	public String findPhone(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		String findPhone = "";
		ResultSet rset = null;
		String query = prop.getProperty("findPhone");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				findPhone = rset.getString("phone");
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return findPhone;
	}

	/*public List<Address> selectAllAddress(Connection conn, String memberId) {
		List<Address> addrList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectAllAddress");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			rset = pstmt.executeQuery();
			
			addrList = new ArrayList<>();
			while(rset.next()) {
				Address addr = new Address();
				addr.setMemberId(rset.getString("memberid"));
				addr.setAddress(rset.getString("address"));
				addr.setInsertDate(rset.getDate("insertdate"));
				
				addrList.add(addr);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return addrList;
	}
*/
}
