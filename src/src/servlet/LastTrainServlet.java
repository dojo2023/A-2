package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LastTrainBeans;

/**
 * Servlet implementation class LastTrainServlet
 */
@WebServlet("/LastTrainServlet")
public class LastTrainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ！！！ Cookieを使用 ！！！
				Cookie cookie[] = request.getCookies();		// Cookieは複数ある可能性があるため配列
				String userId = null;			// userIdが保存されていたらその値、なければnull

				if (cookie != null){
					for (int i = 0 ; i < cookie.length ; i++){
						if (cookie[i].getName().equals("userId")){
						userId = cookie[i].getValue();
						break;
			        }
			      }
			    }

				if (userId == null) {
					response.sendRedirect("/syuudeen/LoginServlet");
					return;
				}
				// 以上ログインの確認
		// 終電表示画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/last_train.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ！！！ Cookieを使用 ！！！
		Cookie cookie[] = request.getCookies();		// Cookieは複数ある可能性があるため配列
		String userId = null;			// userIdが保存されていたらその値、なければnull

		if (cookie != null){
			for (int i = 0 ; i < cookie.length ; i++){
				if (cookie[i].getName().equals("userId")){
				userId = cookie[i].getValue();
				break;
	        }
	      }
	    }

		if (userId == null) {
			response.sendRedirect("/syuudeen/LoginServlet");
			return;
		}
		// 以上ログインの確認
		//ホーム画面に戻る
		response.sendRedirect("/syuudeen/HomeServlet");
	}
	//select文

	public List<LastTrainBeans> select(String userId) {
		Connection conn = null;
		List<LastTrainBeans> cardList = new ArrayList<LastTrainBeans>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/dojo6/src/data/syuudeen", "sa", "");

			// SQL文を準備する
			String sql = "select * from LAST_TRAINS WHERE USER_ID=?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる

			pStmt.setString(1,userId);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				LastTrainBeans card = new LastTrainBeans();

				card.setUserId(rs.getString("USER_ID"));
				card.setLastTrainId(rs.getString("LAST_TRAIN_ID"));
				card.setStartTime(rs.getString("START_TIME"));
				card.setOverFlag(rs.getString("OVER_FLAG"));
				card.setGoalTime(rs.getString("GOAL_TIME"));
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

