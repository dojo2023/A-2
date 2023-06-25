package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PlanBeans;

public class PlansDao {

    //Update文
	public boolean update(String planId, String planDate, String planName, String place, String remarks, String userId) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/syuudeen", "sa", "");

			// SQL文を準備する
			String sql = "update PLANS set PLAN_ID = ?, PLAN_DATE = ?, PLAN_NAME = ?, PLACE = ?, REMARKS = ? where USER_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (!planId.equals("")) {
				pStmt.setString(1, planId);
			} else {
				pStmt.setString(1, null);
			}
			if (!planDate.equals("")) {
				pStmt.setString(2, planDate);
			} else {
				pStmt.setString(2, null);
			}
			if (!planName.equals("")) {
				pStmt.setString(3, planName);
			} else {
				pStmt.setString(3, null);
			}

			if (!place.equals("")) {
				pStmt.setString(4, place);
			} else {
				pStmt.setString(4, null);
			}

			if (!remarks.equals("")) {
				pStmt.setString(5, remarks);
			} else {
				pStmt.setString(5, null);
			}

			pStmt.setString(6, userId);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	//Select文
	public List<PlanBeans> select(String userId) {
		Connection conn = null;
		List<PlanBeans> cardList = new ArrayList<PlanBeans>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/syuudeen", "sa", "");

			// SQL文を準備する
			String sql = "select * from PLANS WHERE USER_ID=?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる

			pStmt.setString(1, userId);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				PlanBeans card = new PlanBeans();

				card.setUserId(rs.getString("USER_ID"));
				card.setPlanId(rs.getString("PLAN_ID"));
				card.setPlanDate(rs.getString("PLAN_DATE"));
				card.setPlanName(rs.getString("PLAN_NAME"));
				card.setPlace(rs.getString("PLACE"));
				card.setRemarks(rs.getString("REMARKS"));
				cardList.add(card);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			cardList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					cardList = null;
				}
			}
		}

		// 結果を返す
		return cardList;
	}

	//Insert文
	public boolean insert(String userId) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/syuudeen", "sa", "");

			// SQL文を準備する
			String sql = "insert into PLANS (USER_ID) values (?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, userId);
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

}
