package hu.bme.xj4vjg.petshop.model;

import javax.inject.Singleton;

@Singleton
public class Settings {
	private String username;

	public Settings() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
