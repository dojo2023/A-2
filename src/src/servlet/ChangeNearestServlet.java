package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UsersDao;
import model.UsageTakemura;
import model.UserBeans;

/**
 * Servlet implementation class ChangeNearestServlet
 */
@WebServlet("/ChangeNearestServlet")
public class ChangeNearestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
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
			response.sendRedirect("/syuudeen/Login");
			return;
		}
		// 以上ログインの確認

		UsersDao uDao = new UsersDao();
		UserBeans ub = new UserBeans();
		ub = uDao.select(userId);

		request.setAttribute("userId", userId);
		request.setAttribute("stationHome", ub.getStationHome());
		request.setAttribute("stationHomeName", UsageTakemura.convertIdToName(ub.getStationHome()));

		// 最寄り駅変更にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/change_nearest.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
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
			response.sendRedirect("/syuudeen/Login");
			return;
		}
		// 以上ログインの確認

		if (request.getParameter("SUBMIT") == null) {
			response.setContentType("application/json");
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
				response.setContentType("text/html;charset=UTF-8");
				//JSONの出力
				response.getWriter().write(testJson);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		else {
			String stationId = request.getParameter("station_id");
			String userAlert = "";

			UsersDao uDao = new UsersDao();
			UserBeans ub = new UserBeans();
			ub = uDao.select(userId);

			userAlert = ub.getUserAlert();
			// TODO selectでuserIdに指定したユーザのuserAlertの状態を取得
			boolean result = uDao.update(userId, userAlert, stationId);
			if(result==false) {
				request.setAttribute("msg", "失敗したよ");
			}else {
				request.setAttribute("msg", "成功したよ");
			}
			response.sendRedirect("/syuudeen/ChangeNearestServlet");
		}

	}

}
