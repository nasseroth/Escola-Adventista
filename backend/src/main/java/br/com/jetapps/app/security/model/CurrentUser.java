package br.com.jetapps.app.security.model;

import br.com.jetapps.app.domain.User;

public class CurrentUser {

	private String token;
	private User user;

	
	public CurrentUser(String token, User user) {
		this.token = token;
		this.user = user;
	}

	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
