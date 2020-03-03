package br.com.jetapps.app.service;

import java.util.List;
import java.util.Optional;

import br.com.jetapps.app.domain.User;
import br.com.jetapps.app.model.UserModel;

public interface UserService {

	User findByEmail(String email);
	User alterar(UserModel model);
	User createOrUpdate(User user);
	List<User> mostrarTudo();
	User consultar(Long idUsuario);
	void delete(Long id);
	Optional<User> findById(Long id);
	
	//User findById2(Long id);
}
