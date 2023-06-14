package model;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsageTakemura {
	/**
	 * @author Kei Takemura
	 * 現在地の緯度と経度を受け取り、現在地からの最寄り駅IDを返すメソッド
	 *
	 * param : geoLocation(String)	…緯度経度をまとめた文字列 formatに準拠
	 * return: stationId(String)	…取得した駅ID
	 */
	public static String convertGeoToId(String geoLocation) {
		String stationId = "";
		String regex = "\"id\": \"[0-9]{8}\"";
		Pattern p = Pattern.compile(regex);
		Matcher m;
		String repGL = geoLocation.replace(",", "%2C");
		String uri = "https://navitime-transport.p.rapidapi.com/transport_node/around?coord=" + repGL + "&limit=1";
		HttpRequest navRequest = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.header("X-RapidAPI-Key", "42fbfc38f7msh32b35a875763945p123a2cjsn5122a195ca21")
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
		System.out.println(navResponse.body());
		if(m.find()) {
			System.out.println("aaaaaaaaaaaaa");
			System.out.println(m.group());
			stationId = m.group().replace("\"", "").split(" ")[1];
		}
		else {
			System.out.println("bbbbbbbbbbbb");
		}
		return stationId;
	}

	/**
	 * @author Kei Takemura
	 * 駅IDを受け取り、それに対応する駅名(NaviTimeによる)を返すメソッド
	 *
	 * param : stationId(String)	…NaviTimeによる8桁の数字の駅ID
	 * return: stationName(String)	…NaviTimeによる駅名
	 */
	public static String convertIdToName(String stationId) {
		String stationName = "";
		String regex = "\"name\": \".+\"";
		Pattern p = Pattern.compile(regex);
		Matcher m;
		String uri = "https://navitime-transport.p.rapidapi.com/transport_node/id?id=" + stationId;
		HttpRequest navRequest = HttpRequest.newBuilder()
				.uri(URI.create(uri))
				.header("X-RapidAPI-Key", "42fbfc38f7msh32b35a875763945p123a2cjsn5122a195ca21")
				.header("X-RapidAPI-Host", "navitime-transport.p.rapidapi.com")
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
		System.out.println(navResponse.body());
		if(m.find()) {
			System.out.println("aaaaaaaaaaaaa");
			System.out.println(m.group());
			stationName = m.group().replace("\"", "").split(" ")[1];
		}
		else {
			System.out.println("bbbbbbbbbbbb");
		}
		return stationName;
	}
}