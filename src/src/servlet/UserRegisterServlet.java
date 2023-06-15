package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDao;

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

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String ID = request.getParameter("userId");
		String PW = request.getParameter("userId");

		// 登録処理を行う
		UsersDao UDao = new UsersDao();
		if (UDao.insert(ID, PW)) {	// 登録成功
			request.setAttribute("result","登録成功！");
			// メニューサーブレットにリダイレクトする
			response.sendRedirect("/syuudeen/HomeServlet");
		}
		else {												// 登録失敗
			request.setAttribute("result","登録失敗！");

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user_register.jsp");
		dispatcher.forward(request, response);
		doGet(request, response);
	}
	}
}
