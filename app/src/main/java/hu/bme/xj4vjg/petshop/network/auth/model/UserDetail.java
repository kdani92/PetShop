package hu.bme.xj4vjg.petshop.network.auth.model;

import com.google.gson.annotations.SerializedName;

public class UserDetail {
	@SerializedName("username")
	private String username;
	@SerializedName("password")
	private String password;

	public UserDetail() {
	}

	public UserDetail(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
