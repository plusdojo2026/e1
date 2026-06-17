package dto;

public class Checklist {
public int  id;//チェックリストに登録する持ち物のid
public String user_id;//ユーザーID
public String  item_name; //チェックリストに登録する持ち物名称
public boolean checked_flag; //チェック済みかどうかのフラグ

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getUser_id() {
	return user_id;
}

public void setUser_id(String user_id) {
	this.user_id = user_id;
}

public String getItem_name() {
	return item_name;
}

public void setItem_name(String item_name) {
	this.item_name = item_name;
}

public boolean isChecked_flag() {
	return checked_flag;
}

public void setChecked_flag(boolean checked_flag) {
	this.checked_flag = checked_flag;
}


}
