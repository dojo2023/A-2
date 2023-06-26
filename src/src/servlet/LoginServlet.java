package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LastTrainsDao;
import dao.UsersDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		// ！！！ Cookieを使用 ！！！
		/*Cookie cookie[] = request.getCookies(); // Cookieは複数ある可能性があるため配列
		String userId = null; // userIdが保存されていたらその値、なければnull

		if (cookie != null) {
			for (int i = 0; i < cookie.length; i++) {
				if (cookie[i].getName().equals("userId")) {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");

		// ログイン処理を行う
		UsersDao UDao = new UsersDao();
		if (UDao.isLoginOK(id, pw)) { // ログイン成功
			// セッションスコープにIDを格納する
			Cookie cookie = new Cookie("userId", id);
			cookie.setMaxAge(3 * 24 * 60 * 60);
			response.addCookie(cookie);

			// LastTrainDaoを用意してoverFlagをcookieに設定
			LastTrainsDao ltd = new LastTrainsDao();
			String overFlag = ltd.select(id).get(0).getOverFlag();

			Cookie cookie2 = new Cookie("overFlag", overFlag);
			cookie2.setMaxAge(3 * 24 * 60 * 60);
			response.addCookie(cookie2);

			String userAlert = UDao.select(id).get(0).getUserAlert();
			Cookie cookieCheck = new Cookie("check", userAlert);
			cookieCheck.setMaxAge(3 * 24 * 60 * 60);
			response.addCookie(cookieCheck);

			String stationHome = UDao.select(id).get(0).getStationHome();

			// 最終アクセス日時をDBに追加
			Calendar cl = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
			String lastAccess = sdf.format(cl.getTime());
			UDao.update(id, userAlert, stationHome, lastAccess);

			// メニューサーブレットにリダイレクトする
			response.sendRedirect("/syuudeen/HomeServlet");
		} else { // ログイン失敗
			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			request.setAttribute("result", "IDかパスワードが間違っています！");

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
	//doGet(request, response);
}
