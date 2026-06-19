package dto;

public class Regist {

	private int id; // 忘れ物ID
	private String item_name; // 忘れ物名称
	private String lost_date; // 忘れ物した日付
	private String weather; // 忘れ物した日の天気
	private String location; // 忘れ物した場所
	private String reason; // 忘れ物した原因
	private String user_id; // ユーザーid

	// idを取得する
	public int getId() {
		return id;
	}

//idを設定する
	public void setId(int id) {
		this.id = id;
	}

//忘れ物名称を取得する
	public String getItem_name() {
		return item_name;
	}

//忘れ物名称を設定する
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

//忘れ物した日付けを取得する
	public String getLost_date() {
		return lost_date;
	}

	public void setLost_date(String lost_date) {
		this.lost_date = lost_date;
	}

//忘れ物した日の天気取得
	public String getWeather() {
		return weather;
	}

//忘れ物した日の天気を設定する
	public void setWeather(String weather) {
		this.weather = weather;
	}

//忘れ物した場所取得
	public String getLocation() {
		return location;
	}

//忘れ物した場所を設定する
	public void setLocation(String location) {
		this.location = location;
	}

//忘れ物した原因を取得する
	public String getReason() {
		return reason;
	}

//忘れ物をした原因を設定する
	public void setReason(String reason) {
		this.reason = reason;
	}

//ユーザーidを取得する
	public String getUser_id() {
		return user_id;
	}

//ユーザーidを設定する
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Regist() {

	}

	public Regist(int id, String item_name, String lost_date, String weather, String location, String reason,
			String user_id) {
		this.id = 0;
		this.item_name = item_name;
		this.lost_date = lost_date;
		this.weather = weather;
		this.location = location;
		this.reason = reason;
		this.user_id = user_id;
	}
}
