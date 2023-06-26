package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserBeans;

//import model.Idpw;

public class UsersDao {
		// ログインできるならtrueを返す
		public boolean isLoginOK(String userId,String userPw) {
			Connection conn = null;
			boolean loginResult = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/syuudeen", "sa", "");

				// SELECT文を準備する
				String sql = "select count(*) from users where user_id = ? and user_pw = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, userId);
				pStmt.setString(2,userPw);

				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// ユーザーIDとパスワードが一致するユーザーがいたかどうかをチェックする
				rs.next();
				if (rs.getInt("count(*)") == 1) {
					loginResult = true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				loginResult = false;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				loginResult = false;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						loginResult = false;
					}
				}
			}

			// 結果を返す
			return loginResult;
		}






	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(String userId,String userPw,String stationId) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/syuudeen", "sa", "");

			// SQL文を準備する
			String sql = "insert into users (USER_ID, USER_PW, STATION_HOME) values (?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
				pStmt.setString(1, userId);
				pStmt.setString(2, userPw);
				pStmt.setString(3, stationId);
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

	public boolean update(String userId,String check, String stationHome, String lastAccess) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/syuudeen", "sa", "");

			// SQL文を準備する
			String sql = "update USERS set STATION_HOME = ?, USER_ALERT = ?, LAST_ACCESS=? where USER_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if(!stationHome.equals("")) {
				pStmt.setString(1, stationHome);
			}
			else {
				pStmt.setString(1, null);
			}
			if(!check.equals("")) {
				pStmt.setString(2, check);
			}
			else {
				pStmt.setString(2, null);
			}
			if(!lastAccess.equals("")) {
				pStmt.setString(3, lastAccess);
			}
			else {
				pStmt.setString(3, null);
			}

			pStmt.setString(4, userId);
			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}


		return result;

	}

public List<UserBeans> select(String userId) {
	Connection conn = null;
	List<UserBeans> cardList = new ArrayList<UserBeans>();

	try {
		// JDBCドライバを読み込む
		Class.forName("org.h2.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/syuudeen", "sa", "");

		// SQL文を準備する
		String sql = "select * from USERS WHERE USER_ID=?";

		PreparedStatement pStmt = conn.prepareStatement(sql);

		// SQL文を完成させる

		pStmt.setString(1,userId);

		// SQL文を実行し、結果表を取得する
		ResultSet rs = pStmt.executeQuery();
		while (rs.next()) {
			UserBeans card = new UserBeans();

			card.setUserId(rs.getString("USER_ID"));
			card.setUserPw(rs.getString("USER_PW"));
			card.setStationHome(rs.getString("STATION_HOME"));
			card.setUserAlert(rs.getString("USER_ALERT"));
			card.setLastAccess(rs.getString("LAST_ACCESS"));

			cardList.add(card);
		}

	}
	catch (SQLException e) {
		e.printStackTrace();
		cardList = null;
	}
	catch (ClassNotFoundException e) {
		e.printStackTrace();
		cardList = null;
	}
	finally {
		// データベースを切断
		if (conn != null) {
			try {
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
				cardList = null;
			}
		}
	}

	// 結果を返す
	return cardList;
}
}