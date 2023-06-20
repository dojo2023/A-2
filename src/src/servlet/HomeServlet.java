package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.LastTrainsDao;
import dao.UsersDao;
import model.LastTrainBeans;
import model.UsageTakemura;
import model.UserBeans;




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

		request.setAttribute("startTime", request.getAttribute("startTime"));

		// フォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
		response.sendRedirect("/syuudeen/LoginServlet");
		return;
	}
	// 以上ログインの確認

	if (request.getParameter("SUBMIT") == null) {
		// 送信されたデータの取得
		String check = request.getParameter("check");

		System.out.print(check);//テスト用



		//DAOを実体化（インスタンス化）
		UsersDao uDao = new UsersDao();
		UserBeans ub = new UserBeans();
		List<UserBeans> ul = new ArrayList<>();

		ul = uDao.select(userId);
		ub = ul.get(0);
		String stationHome = ub.getStationHome();

		//DAOのメソッドに引数を２つ渡してアップデートを行う

		boolean result = uDao.update(userId, check, stationHome);

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
	}
	else {
		/*
		 * 1. 現在地を受け取る
		 * 2. 現在地からの最寄り駅検索を実行(convertGeoToId)
		 * 3. 終電情報を取得する(LastTrainsDao>〇〇(メソッド))、jsonが返ってくる
		 * 4. json加工、必要情報の切り出し
		 * 5. ユーザIDに基づいて終電テーブルの更新(LastTrainsDao>update)
		 * 6. LastTrainServlet.javaにリダイレクト
		 */

		// 1. 現在地を受け取る
		String geo = request.getParameter("hidden_position");

		// 2. 現在地からの最寄り駅検索を実行
		String nearStationId = UsageTakemura.convertGeoToId(geo);

		// 3. 終電情報を取得する
		LastTrainsDao ltd = new LastTrainsDao();
		String lastTrainInfo = ltd.getLastTrain(nearStationId, userId);

		// 4. json加工
		// 受け取り用変数
		String startTime = "";
		String goalTime = "";
		String lineName = "";
		String stationName = "";

		// 正規表現用変数
		String stRex = "\"from_time\": \".+T[0-9]{2}:[0-9]{2}";
		String gtRex = "\"to_time\": \".+T[0-9]{2}:[0-9]{2}";
		String lnRex = "\"line_name\": \".+\"";
		String snRex = "\"name\": \".+\"";

		// パターン用変数
		Pattern stp = Pattern.compile(stRex);
		Pattern gtp = Pattern.compile(gtRex);
		Pattern lnp = Pattern.compile(lnRex);
		Pattern snp = Pattern.compile(snRex);

		// Matcher変数
		Matcher m;

		// startTime受け取り
		m = stp.matcher(lastTrainInfo);
		if (m.find()) {
			startTime = m.group().replace("\"", "").split("T")[1];
		}

		// goalTime受け取り
		m = gtp.matcher(lastTrainInfo);
		if (m.find()) {
			goalTime = m.group().replace("\"", "").split("T")[1];
		}

		// lineName受け取り
		m = lnp.matcher(lastTrainInfo);
		if (m.find()) {
			lineName = m.group().replace("\"", "").split(" ")[1];
		}

		// stationName受け取り
		m = snp.matcher(lastTrainInfo);
		if (m.find()) {
			stationName = m.group().replace("\"", "").split(" ")[1];
		}

		// 5. 終電テーブルの更新(LastTrainsDao>update)
		LastTrainBeans ltb = new LastTrainBeans();
		List<LastTrainBeans> list = new ArrayList<>();
		// TODO selectメソッド呼び出し
		list = ltd.select(userId);
		String overFlag = list.get(0).getOverFlag();
		ltd.update(lastTrainInfo, startTime, goalTime, overFlag, userId);

		// 6. LastTrainServlet.javaにリダイレクト
		// LastTrainServletでgetAttributeすればデータがとれるはず
		request.setAttribute("startTime", startTime);
		request.setAttribute("lineName", lineName);
		request.setAttribute("stationName", stationName);
		response.sendRedirect("/syuudeen/LastTrainServlet");
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
