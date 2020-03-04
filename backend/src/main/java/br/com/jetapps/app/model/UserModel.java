package br.com.jetapps.app.model;

import br.com.jetapps.app.domain.User;
import br.com.jetapps.app.enuns.ProfileEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserModel {
    private Long id;
    private String email;
    private String password;
    private ProfileEnum profile;
    
	public UserModel(User domain, ProfileEnum profile) {
		this.id = domain.getId();
		this.email = domain.getEmail();
		this.password = domain.getPassword();
		this.profile = domain.getProfile();
	}
    
	public UserModel(User domain) {
		this.id = domain.getId();
		this.email = domain.getEmail();
		this.password = domain.getPassword();
		this.profile = domain.getProfile();
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", email=" + email + ", password=" + password + ", profile=" + profile + "]";
	}
    
}
