package semi.kh.chart.model.service;

import static semi.kh.common.JDBCTemplate.close;
import static semi.kh.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import semi.kh.chart.model.dao.ChartDao;
import semi.kh.chart.model.vo.Favorite;
import semi.kh.chart.model.vo.SalesVolume;
import semi.kh.member.model.vo.Member;
public class ChartService {

//	public List<Member> selectFavoriteChart(String searchType) {
//		// TODO Auto-generated method stub
//		Connection conn = getConnection();
//		List<Member> m = new ChartDao().selectFavoriteChart(conn,searchType);
//		close(conn);
//		return m;
//	}

	public List<Favorite> countGenderFavorite() {
		Connection conn = getConnection();
		List<Favorite> count  = new ArrayList<>();
		Favorite countMale = new ChartDao().countMaleFavorite(conn);
		if(countMale!=null) {
			Favorite countFemale = new ChartDao().countFemaleFavorite(conn);
			count.add(countFemale);
			count.add(countMale);
		}
		close(conn);
		return count;
	}

	public List<Favorite> countAgeFavorite(List<Integer> age) {
		Connection conn = getConnection();
		List<Favorite> count = new ChartDao().countAgeFavortie(conn,age);
		close(conn);
		return count;
	}

	public List<SalesVolume> selectGradeSale() {
		Connection conn = getConnection();
		List<SalesVolume> grade = new ChartDao().selectGradeSale(conn);
		close(conn);
		return grade;
	}

	public List<SalesVolume> selectRegionSale() {
		Connection conn = getConnection();
		List<SalesVolume> region = new ChartDao().selectRegionSale(conn);
		close(conn);
		return region;
	}

	public List<SalesVolume> selectRegionDetailSale(String searchType) {
		Connection conn = getConnection();
		List<SalesVolume> regionDetail = new ChartDao().selectRegionDetailSale(conn,searchType);
		close(conn);
		return regionDetail;
	}


}
