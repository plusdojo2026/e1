package dto;

// 忘れ物情報を保持するDTOクラス
public class LostItems {

    // 忘れ物ID
    private int id;

    // 忘れ物の名称
    private String item_name;

    // 紛失日
    private String lost_date;

    // 当日の天気
    private String weather;

    // 紛失場所
    private String location;

    // 紛失した理由
    private String reason;

    // ユーザーID
    private String user_id;

    // 検索開始日（期間検索用）
    private String startDate;

    // 検索終了日（期間検索用）
    private String endDate;

    // IDを取得する
    public int getId() {
        return id;
    }

    // IDを設定する
    public void setId(int id) {
        this.id = id;
    }

    // 忘れ物の名称を取得する
    public String getItem_name() {
        return item_name;
    }

    // 忘れ物の名称を設定する
    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    // 紛失日を取得する
    public String getLost_date() {
        return lost_date;
    }

    // 紛失日を設定する
    public void setLost_date(String lost_date) {
        this.lost_date = lost_date;
    }

    // 天気を取得する
    public String getWeather() {
        return weather;
    }

    // 天気を設定する
    public void setWeather(String weather) {
        this.weather = weather;
    }

    // 紛失場所を取得する
    public String getLocation() {
        return location;
    }

    // 紛失場所を設定する
    public void setLocation(String location) {
        this.location = location;
    }

    // 紛失理由を取得する
    public String getReason() {
        return reason;
    }

    // 紛失理由を設定する
    public void setReason(String reason) {
        this.reason = reason;
    }

    // ユーザーIDを取得する
    public String getUser_id() {
        return user_id;
    }

    // ユーザーIDを設定する
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    // 検索開始日を取得する
    public String getStartDate() {
        return startDate;
    }

    // 検索開始日を設定する
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    // 検索終了日を取得する
    public String getEndDate() {
        return endDate;
    }

    // 検索終了日を設定する
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}