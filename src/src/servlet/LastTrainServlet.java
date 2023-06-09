package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LastTrainServlet
 */
@WebServlet("/LastTrainServlet")
public class LastTrainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ！！！ Cookieを使用 ！！！
		Cookie cookie[] = request.getCookies(); // Cookieは複数ある可能性があるため配列
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
		}
		// 以上ログインの確認
		HttpSession session = request.getSession();
		String st = (String)session.getAttribute("startTime");
		String gt = (String)session.getAttribute("goalTime");
		//終電時刻を取得するたびにはいるよ
		Cookie cookieSt = new Cookie("startTime", st);
		cookieSt.setMaxAge(3 * 24 * 60 * 60);
		response.addCookie(cookieSt);

		Cookie cookieGt = new Cookie("goalTime", gt);
		cookieGt.setMaxAge(3 * 24 * 60 * 60);
		response.addCookie(cookieGt);

		System.out.println(cookieSt.getValue() + "\n" + cookieGt.getValue());

		// 終電表示画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/last_train.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ！！！ Cookieを使用 ！！！
		Cookie cookie[] = request.getCookies(); // Cookieは複数ある可能性があるため配列
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
		}
		// 以上ログインの確認

		//ホーム画面に戻る
		response.sendRedirect("/syuudeen/HomeServlet");
	}

}
