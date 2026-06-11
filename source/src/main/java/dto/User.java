package dto;

import java.io.Serializable;

public class User implements Serializable {
	private int id;					//ID
	private String user_id;			//ログインID
	private String password;		//パスワード
	private String name;			//氏名
	private String mail_address;	//メールアドレス

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail_address() {
		return mail_address;
	}

	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}

	public User(String user_id, String password, String name, String mail_address) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.name = name;
		this.mail_address = mail_address;
	}

	public User(String string, String string2, String string3) {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	
}
