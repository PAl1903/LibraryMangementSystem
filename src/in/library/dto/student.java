package in.library.dto;

import java.io.Serializable;

public class student implements Serializable{
	private int id;
	private String userid;
	private String password;
	@Override
	public String toString() {
		return "student [userid=" + userid + ", password=" + password + "]";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
