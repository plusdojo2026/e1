package dto;

public class LostItems {
private int id;
private String item_name;
private String lost_date;
private String weather;
private String location;
private String reason;
private String user_id;
private String startDate;
private String endDate;

public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}

public String getItem_name() {
return item_name;
}
public void setItem_name(String item_name) {
this.item_name = item_name;
}

public String getLost_date() {
return lost_date;
}
public void setLost_date(String lost_date) {
this.lost_date = lost_date;
}

public String getWeather() {
return weather;
}
public void setWeather(String weather) {
this.weather = weather;
}

public String getLocation() {
return location;
}
public void setLocation(String location) {
this.location = location;
}

public String getReason() {
return reason;
}
public void setReason(String reason) {
this.reason = reason;
}
public String getUser_id() {
    return user_id;
}
public void setUser_id(String user_id) {
    this.user_id = user_id;
}

public String getStartDate() {
    return startDate;
}

public void setStartDate(String startDate) {
    this.startDate = startDate;
}

public String getEndDate() {
    return endDate;
}

public void setEndDate(String endDate) {
    this.endDate = endDate;
}

}