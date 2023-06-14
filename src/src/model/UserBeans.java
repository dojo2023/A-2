package model;

import java.io.Serializable;

public class UserBeans implements Serializable {
	private String userId;
	private String userPw;
	private String stationHome;
	private String userAlert;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getStationHome() {
		return stationHome;
	}

	public void setStationHome(String stationHome) {
		this.stationHome = stationHome;
	}

	public String getUserAlert() {
		return userAlert;
	}

	public void setUserAlert(String userAlert) {
		this.userAlert = userAlert;
	}

}
