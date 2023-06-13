package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// APIのテスト
		// 他の機能が実装できてからこちらを組み込みます！
//		HttpRequest navRequest = HttpRequest.newBuilder()
//				.uri(URI.create("https://navitime-route-totalnavi.p.rapidapi.com/route_transit?start=00004254&goal=00000148&start_time=2023-06-14T12%3A30%3A00&datum=wgs84&term=1440&limit=5&coord_unit=degree"))
//				.header("X-RapidAPI-Key", "42fbfc38f7msh32b35a875763945p123a2cjsn5122a195ca21")
//				.header("X-RapidAPI-Host", "navitime-route-totalnavi.p.rapidapi.com")
//				.method("GET", HttpRequest.BodyPublishers.noBody())
//				.build();
//		HttpResponse<String> navResponse = null;
//		try {
//			navResponse = HttpClient.newHttpClient().send(navRequest, HttpResponse.BodyHandlers.ofString());
//		} catch (IOException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO 自動生成された catch ブロック
//			e.printStackTrace();
//		}
//		System.out.println(navResponse.body());
//
		// TODO LastTrainServletにリダイレクトするように変更
		response.sendRedirect("/syuudeen/HomeServlet");
	}

}
