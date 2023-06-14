package model;

import java.io.Serializable;

public class LastTrainBeans implements Serializable {
	private String lastTrainId;
	private String startTime;
	private String overFlag;
	private String goalTime;
	private String userId;

	public String getLastTrainId() {
		return lastTrainId;
	}

	public void setLastTrainId(String lastTrainId) {
		this.lastTrainId = lastTrainId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getOverFlag() {
		return overFlag;
	}

	public void setOverFlag(String overFlag) {
		this.overFlag = overFlag;
	}

	public String getGoalTime() {
		return goalTime;
	}

	public void setGoalTime(String goalTime) {
		this.goalTime = goalTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
