package semi.kh.chart.model.dao;

import static semi.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import semi.kh.chart.model.vo.Favorite;
import semi.kh.chart.model.vo.SalesVolume;
public class ChartDao {
	private Properties prop = new Properties();
	
	public ChartDao() {
		String fileName = ChartDao.class.getResource("/sql/chart/chart-query.properties").getPath();
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
//	public List<Member> selectFavoriteChart(Connection conn, String searchType) {
//		// TODO Auto-generated method stub
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		List<Member> member = null;
//		String query ="";
//		switch(searchType) {
//		case "나이별선호도" : query = prop.getProperty("selectAgeFavorite");  break;
//		case "성별선호도" : query = prop.getProperty("selectGenderFavorite");  break;
//		}
//		try {
//			pstmt = conn.prepareStatement(query);
//			rset = pstmt.executeQuery();
//			member = new ArrayList<>();
//			while(rset.next()) {
//				Member m = new Member();
//				m.setFavorite(rset.getString("favorite"));
//				m.setGender(rset.getString("gender"));
//				member.add(m);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			close(rset);
//			close(pstmt);
//		}
//		return member;
//	}
	public Favorite countMaleFavorite(Connection conn) {
		PreparedStatement pstmt = null;
		Favorite count = null;
		ResultSet rset = null;
		String query = prop.getProperty("countMaleFavorite");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%스마트폰%");
			pstmt.setString(2,"%태블릿%");
			pstmt.setString(3,"%데스크탑%");
			pstmt.setString(4,"%웨어러블%");
			pstmt.setString(5,"%모니터%");
			pstmt.setString(6,"%노트북%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = new Favorite();
				count.setSmartPhone((rset.getInt("smartphone")));
				count.setTablet((rset.getInt("tablet")));
				count.setDesktop(rset.getInt("desktop"));
				count.setWearable(rset.getInt("wearable"));
				count.setMonitor(rset.getInt("monitor"));
				count.setNotebook(rset.getInt("notebook"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}
	public Favorite countFemaleFavorite(Connection conn) {
		PreparedStatement pstmt = null;
		Favorite count = null;
		ResultSet rset = null;
		String query = prop.getProperty("countFemaleFavorite");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"%스마트폰%");
			pstmt.setString(2,"%태블릿%");
			pstmt.setString(3,"%데스크탑%");
			pstmt.setString(4,"%웨어러블%");
			pstmt.setString(5,"%모니터%");
			pstmt.setString(6,"%노트북%");
			rset = pstmt.executeQuery();
			if(rset.next()) {
				count = new Favorite();
				
				count.setSmartPhone((rset.getInt("smartphone")));
				count.setTablet((rset.getInt("tablet")));
				count.setDesktop(rset.getInt("desktop"));
				count.setWearable(rset.getInt("wearable"));
				count.setMonitor(rset.getInt("monitor"));
				count.setNotebook(rset.getInt("notebook"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}
	public List<Favorite> countAgeFavortie(Connection conn, List<Integer> age) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("countAgeFavorite");
		List<Favorite> count = null;
		try {
			count = new ArrayList<>();
			count.add(new Favorite());
			count.add(new Favorite());
			count.add(new Favorite());
			count.add(new Favorite());
			count.add(new Favorite());
			count.add(new Favorite());
			for(int i = 0; i < age.size(); i++) {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1,"%스마트폰%");
				pstmt.setInt(2, age.get(i));
				pstmt.setInt(3, age.get(i)+10);
				pstmt.setString(4,"%태블릿%");
				pstmt.setInt(5, age.get(i));
				pstmt.setInt(6, age.get(i)+10);
				pstmt.setString(7,"%데스크탑%");
				pstmt.setInt(8, age.get(i));
				pstmt.setInt(9, age.get(i)+10);
				pstmt.setString(10,"%웨어러블%");
				pstmt.setInt(11, age.get(i));
				pstmt.setInt(12, age.get(i)+10);
				pstmt.setString(13,"%모니터%");
				pstmt.setInt(14, age.get(i));
				pstmt.setInt(15, age.get(i)+10);
				pstmt.setString(16,"%노트북%");
				pstmt.setInt(17, age.get(i));
				pstmt.setInt(18, age.get(i)+10);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					Favorite ageCount = new Favorite();
					ageCount.setSmartPhone(rset.getInt("smartphone"));
					ageCount.setTablet(rset.getInt("tablet"));
					ageCount.setDesktop(rset.getInt("desktop"));
					ageCount.setWearable(rset.getInt("wearable"));
					ageCount.setMonitor(rset.getInt("monitor"));
					ageCount.setNotebook(rset.getInt("notebook"));
					if(age.get(i) == 10) {
						count.add(0,ageCount);
					}else if(age.get(i) == 20) {
						count.add(1, ageCount);
					}else if(age.get(i) == 30) {
						count.add(2,ageCount);
					}else if(age.get(i) == 40) {
						count.add(3, ageCount);
					}else if(age.get(i) == 50) {
						count.add(4, ageCount);
					}else if(age.get(i) == 60) {
						count.add(5, ageCount);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return count;
	}
	public List<SalesVolume> selectGradeSale(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<SalesVolume> grade = null;
		String query = prop.getProperty("selectGradeSale");
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			grade = new ArrayList<>();
			while(rset.next()) {
				SalesVolume s = new SalesVolume();
				s.setGrade(rset.getString("grade"));
				s.setDescription(rset.getString("description"));
				s.setSalePrice(rset.getInt("salesVolume"));
				grade.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return grade;
	}
	public List<SalesVolume> selectRegionSale(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<SalesVolume> region = null;
		String query = prop.getProperty("selectRegionSale");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			region = new ArrayList<>();
			while(rset.next()) {
				SalesVolume s = new SalesVolume();
				s.setAddress(rset.getString("res_address"));
				s.setSalePrice(rset.getInt("totalprice"));
				s.setDescription(rset.getString("description"));
				s.setOrderno(rset.getString("orderno"));
				region.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return region;
	}
	public List<SalesVolume> selectRegionDetailSale(Connection conn, String searchType) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<SalesVolume> regionDetail = null;
		String query = prop.getProperty("selectRegionDetailSale");
		ArrayList<String> address = null;
		switch(searchType) {
		case "seoul" : address = new ArrayList<>(); address.add("서울"); address.add("경기"); address.add("인천"); break;
		case "kangwon" :  address = new ArrayList<>(); address.add("강원"); break;
		case "chungcheong" :  address = new ArrayList<>(); address.add("충북"); address.add("충남"); address.add("대전"); break;
		case "jeonla" :  address = new ArrayList<>(); address.add("전북"); address.add("전남"); break;
		case "gyeongsang" :  address = new ArrayList<>(); address.add("경북");address.add("경남"); address.add("부산"); break;
		}
		try {
			regionDetail = new ArrayList<>();
			for(int i = 0; i < address.size(); i++) {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+address.get(i)+"%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				SalesVolume s = new SalesVolume();
				s.setAddress(rset.getString("res_address"));
				s.setDescription(rset.getString("description"));
				s.setSalePrice(rset.getInt("totalprice"));
				
				regionDetail.add(s);
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return regionDetail;
	}

}
