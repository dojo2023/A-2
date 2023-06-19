package dao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LastTrainsDao {
	/**
	 * @author Kei Takemura
	 * 駅IDを受け取り、それに対応する終電情報(NaviTimeによる)を返すメソッド
	 *
	 * param : stationId(String)	…NaviTimeによる8桁の数字の駅ID
	 * return: stationName(String)…NaviTimeによる駅名,startTime…出発時刻、goalTime…到着時刻、○○線
	 */
	public static String convertIdToName(String stationId,String homeStation) {
		String stationName = "";
		String startTime = "";
		String goalTime = "";
		String lineName = "";
		String regex = "\"name\": \".+\"";
		Pattern p = Pattern.compile(regex);
		Matcher m;
		String uri = "https://navitime-route-totalnavi.p.rapidapi.com/route_transit?start=35.665251%2C139.712092&goal=35.661971%2C139.703795&"
				+ "start_time=2020-08-19T10%3A00%3A00&datum=wgs84&term=1440&limit=5&coord_unit=degree";
		HttpRequest navRequest = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.header("X-RapidAPI-Key", "SIGN-UP-FOR-KEY")
				.header("X-RapidAPI-Host", "navitime-route-totalnavi.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> navResponse = null;
		try {
			navResponse = HttpClient.newHttpClient().send(navRequest, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		m = p.matcher(navResponse.body());
		if (m.find()) {
			System.out.println(m.group());
			stationName = m.group().replace("\"", "").replace(",", "").split(" ")[1];
		}
		return stationName;
	}
}
