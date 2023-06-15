package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		
		System.out.print(check);//テスト用
		
		//userIDを取得
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("userId");

		//DAOを実体化（インスタンス化）
		UsersDao usersdao = new UsersDao();

		//DAOのメソッドに引数を２つ渡してアップデートを行う
		boolean result = usersdao.checkUpdate(check,userId);

//		boolean result = true; //テスト用
		if(result==false) {
			request.setAttribute("msg", "失敗したよ");
		}else {
			request.setAttribute("msg", "成功したよ");
		}
		
		//ArrayListをインスタンス化
		ArrayList<String> list = new ArrayList<>();

		//Jackson機能のmapperをインスタンス（実体）化
		ObjectMapper mapper = new ObjectMapper();
		try {
			//JavaオブジェクトからJSONに変換
			String testJson = mapper.writeValueAsString(list);
//			System.out.println(testJson);
			//文字コードの指定（これがないとJSPで文字化けする）
			response.setContentType("text/html;charser=UTF-8");
			//JSONの出力
			response.getWriter().write(testJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
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
