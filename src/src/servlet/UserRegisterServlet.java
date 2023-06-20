package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UsersDao;
import model.UserBeans;

/**
 * Servlet implementation class UserResistServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		// ！！！ Cookieを使用 ！！！
		/*Cookie cookie[] = request.getCookies();		// Cookieは複数ある可能性があるため配列
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
		}*/
		// 以上ログインの確認

		// フォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_register.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter("SUBMIT") == null) {
			response.setContentType("application/json; charset=UTF-8");
			response.setHeader("Cache-Control", "nocache");

			String position = request.getParameter("position");

			// for debug
			String stationId = "00000877";
			String stationName = "荻窪";

			//			String stationId = UsageTakemura.convertGeoToId(position);
			//			String stationName = UsageTakemura.convertIdToName(stationId);

			//ArrayListをインスタンス化
			ArrayList<String> list = new ArrayList<>();

			//適当な値を突っ込む
			list.add(stationId);
			list.add(stationName);

			//Jackson機能のmapperをインスタンス（実体）化
			ObjectMapper mapper = new ObjectMapper();
			try {
				//JavaオブジェクトからJSONに変換
				String testJson = mapper.writeValueAsString(list);
				System.out.println(testJson);
				//文字コードの指定（これがないとJSPで文字化けする）
				response.setContentType("text/html;charser=UTF-8");
				//JSONの出力
				response.getWriter().write(testJson);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		} else {
			// リクエストパラメータを取得する
			String ID = request.getParameter("user_id");
			String PW = request.getParameter("user_pw");
			String check = request.getParameter("pw_check");
			String stationId = request.getParameter("station_id");
			int num = 0;

			//エラーチェック

			//ID,PW,PWチェック,駅検索ボタンのすべてが入力なしの場合
			if (ID.equals("") || PW.equals("") || check.equals("") || stationId.equals("")) {
				num += 1;
				request.setAttribute("errMsg", "すべて入力してください");
			} else {
				//DAOを実体化（インスタンス化）
				UsersDao uDao = new UsersDao();
				UserBeans ub = new UserBeans();
				List<UserBeans> ul = new ArrayList<>();

				ul = uDao.select(ID);
				//IDが既に使用されている場合
				if (ul.size() != 0) { //リストがどのくらいの大きさか示す→「.size()」
					num += 1;
					request.setAttribute("errMsg", "IDが既に使用されています");
				} else {
					if (!ID.matches("[0-9a-zA-Z]{1,12}") || !PW.matches("[0-9a-zA-Z]{8,20}")) {

						//IDとPWが指定した形と違う場合
						String idm = "";
						if (!ID.matches("[0-9a-zA-Z]{1,12}")) {
							idm += "IDは12文字以下の半角英数字で入力してください<br>";
						}
						if (!PW.matches("[0-9a-zA-Z]{8,20}")) {
							idm += "PWは8文字以上21文字未満の半角英数字で入力してください";
						}
						request.setAttribute("errMsg", idm);
						num += 1;
					} else {

						//PWとPWチェックが違う場合
						if (!PW.equals(check)) {
							num += 1;
							request.setAttribute("errMsg", "パスワードが一致しません");
						}

					}
				}

			}

			// 登録処理を行う
			if (num == 0) { //成功
				UsersDao UDao = new UsersDao();
				UDao.insert(ID, PW, stationId);// 登録成功
				response.sendRedirect("/syuudeen/LoginServlet"); //ログインサーブレットにリダイレクトする
			} else { //失敗

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_register.jsp");
				dispatcher.forward(request, response);
			}


		}
	}
}
