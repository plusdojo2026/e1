package dto;

public class LostItems {
private int id;
private String name;
private String location;
private String date;
private String weather;
private String reason;

public int getId() {
return id;
}
public void setId(int id) {
this.id = id;
}

public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}

public String getLocation() {
return location;
}
public void setLocation(String location) {
this.location = location;
}

public String getDate() {
return date;
}
public void setDate(String date) {
this.date = date;
}

public String getWeather() {
return weather;
}
public void setWeather(String weather) {
this.weather = weather;
}

public String getReason() {
return reason;
}
public void setReason(String reason) {
this.reason = reason;
}
}