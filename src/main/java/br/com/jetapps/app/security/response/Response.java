package br.com.jetapps.app.security.response;

import java.util.ArrayList;
import java.util.List;

import br.com.jetapps.app.domain.User;

public class Response<T> {

	private User data;
	private List<String> errors;

	
	
	
	
	public User getData() {
		return data;
	}

	public void setData(User users) {
		this.data = users;
	}

	public List<String> getErrors() {
		return this.errors == null ? this.errors = new ArrayList<>() : errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}


}
