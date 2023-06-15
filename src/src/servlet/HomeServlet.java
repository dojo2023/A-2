package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDao;




/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		// ！！！ Cookieを使用 ！！！

//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/simpleBC/Login");
//			return;
//		}

		// フォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 送信されたデータの取得
		String check = request.getParameter("check");
		//userIDを取得
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");

		//DAOを実体化（インスタンス化）
		UsersDao usersdao = new UsersDao();

		//DAOのメソッドに引数を２つ渡してアップデートを行う
		int result = usersdao.checkUpdate(check,userId);

		if(result==0) {
			request.setAttribute("msg", "失敗したよ");
		}else {
			request.setAttribute("msg", "成功したよ");
		}

		//ここまで来たらjspに勝手に処理が戻る
		//${msg}

		// APIのテスト
//		String stationId = UsageTakemura.convertGeoToId("35.70172838341061,139.67169333390262");
//		String stationName = UsageTakemura.convertIdToName(stationId);
//		System.out.println(stationId);
//		System.out.println(stationName);

		// TODO LastTrainServletにリダイレクトするように変更
		response.sendRedirect("/syuudeen/HomeServlet");
	}

}
