package dto;

public class Alert {

    private int id; 			// ID
    private String alertDate; 	// 通知日時

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(String alertDate) {
        this.alertDate = alertDate;
    }
}