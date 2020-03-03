package br.com.jetapps.app.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import br.com.jetapps.app.enuns.ProfileEnum;
import br.com.jetapps.app.model.UserModel;

import javax.persistence.Id;
/*
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;*/

import lombok.Getter;


//@NoArgsConstructor
@Getter

@Entity
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	//@Indexed(unique = true)
	//@NotBlank(message = "Email requerido")
	//@Email(message = "Email Valido")
	private String email;

	//@NotBlank(message = "Senha requerido")
	//@Size(min = 6)
	private String password;

	private ProfileEnum profile;


	public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}
	public User() {
		super();
	}

	public User(Long id, String email, String password, ProfileEnum profile) {
		this();
		this.id = id;
		this.email = email;
		this.password = password;
		this.profile = profile;
	}

    
    public void alterar(UserModel model) {
        this.email = model.getEmail();
        this.password = model.getPassword();
        this.profile = model.getProfile();
    }


}
