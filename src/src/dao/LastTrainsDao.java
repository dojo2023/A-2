package dao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.UserBeans;

public class LastTrainsDao {
	/**
	 * 現在地から最寄りの駅IDを受け取り、それに対応する終電情報(NaviTimeによる)を返すメソッド
	 * @author aiba , katahira
	 *
	 * @param	nearStationId NaviTimeによる8桁の数字の駅ID
	 * @param	userId DBのusersテーブルに保存されている固有のユーザーID
	 * @return 終電情報をすべて持ったjson文字列<br>
	 * 			NaviTimeによる駅名、startTime…出発時刻、goalTime…到着時刻、○○線
	 */
	public static String getLastTrain(String nearStationId, String userId) {

		//26～34行 userIdからそのユーザーの自宅からの最寄り駅を持ってくる
		String homeStation = "";

		UsersDao uDao = new UsersDao();
		UserBeans ub = new UserBeans();
		List<UserBeans> ul = new ArrayList<>();
		ul = uDao.select(userId);
		ub = ul.get(0);

		homeStation = ub.getStationHome();

		Calendar now = Calendar.getInstance(); //現在時刻
		Calendar cn1 = Calendar.getInstance(); //午前2時を超えたか判定するための時刻

		//位置情報検索ボタンを押した日の午前2時に日付を設定する
		cn1.set(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DATE),2,0,0);

		if( now.compareTo(cn1)>=0) { //nowを6月19日17：00  cn1を6月19日02：00とした場合"1"を返す

			//その日の終電 6月19日19時の場合 到着時間を6月20日2時に設定
			cn1.set(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DATE)+1,2,0,0);
		}
		String goal_date = cn1.get(Calendar.YEAR) + "-" + String.format("%02d", cn1.get(Calendar.MONTH)) + "-"
				+ String.format("%02d", cn1.get(Calendar.DATE)) + "T" +
				String.format("%02d", cn1.get(Calendar.HOUR)) + "%3A" + String.format("%02d", cn1.get(Calendar.MINUTE))
				+ "%3A" + String.format("%02d", cn1.get(Calendar.SECOND));

		String uri = "https://navitime-route-totalnavi.p.rapidapi.com/route_transit?start=" + nearStationId + "&goal="
				+ homeStation + "&"
				+ "goal_time=" + goal_date + "&datum=wgs84&term=1440&limit=1&coord_unit=degree";
		HttpRequest navRequest = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.header("X-RapidAPI-Key", "SIGN-UP-FOR-KEY")
				.header("X-RapidAPI-Host", "navitime-route-totalnavi.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> navResponse = null;
		try {
			navResponse = HttpClient.newHttpClient().send(navRequest, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return navResponse.body();
	}

	public boolean update(String lastTrainId, String startTime, String goalTime, String overFlag, String userId) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/syuudeen", "sa", "");

			// SQL文を準備する
			String sql = "update LAST_TRAINS set LAST_TRAIN_ID = ? START_TIME = ?, GOAL_TIME = ?, OVER_FLAG = ?, where USER_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if(!lastTrainId.equals("")) {
				pStmt.setString(1, lastTrainId);
			}
			else {
				pStmt.setString(1, null);
			}
			if(!startTime.equals("")) {
				pStmt.setString(2, startTime);
			}
			else {
				pStmt.setString(2, null);
			}
			if(!goalTime.equals("")) {
				pStmt.setString(3, goalTime);
			}
			else {
				pStmt.setString(3, null);
			}

			if(!overFlag.equals("")) {
				pStmt.setString(4, overFlag);
			}
			else {
				pStmt.setString(4, null);
			}

			pStmt.setString(5, userId);

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
}
