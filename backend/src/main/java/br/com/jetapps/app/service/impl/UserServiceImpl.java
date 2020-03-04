package br.com.jetapps.app.service.impl;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.jetapps.app.domain.User;
import br.com.jetapps.app.model.UserModel;
import br.com.jetapps.app.repository.UserRepo;
import br.com.jetapps.app.service.UserService;
@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public User findByEmail(String email) {
		return this.userRepo.findByEmail(email);
	}

	@Override
	public User createOrUpdate(User user) {
		return this.userRepo.save(user);
	}
/*
	@Override
	public User findById(String id) {
		return this.userRepo.findById(id).orElse(null);
		
	}*/

	@Override
	public void delete(Long id) {
		this.userRepo.deleteById(id);
	}
	/*
	@Override
	public Page<User> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		return this.userRepo.findAll(pages);
	}
	*/

/*
	@Override
	public User findById2(Long id) {
		return this.userRepo.findOne(id);
	}*/

	@Override
	public List<User> mostrarTudo() {
        return userRepo.findAll();
	}
    
    @Override
    public User consultar(Long idUsuario) {
    	return userRepo.findById(idUsuario).orElse(null);
    }
	@Override
	public User alterar(UserModel model) {
    	User usuario = this.consultar(model.getId());
        if (Objects.nonNull(usuario)) {
        	usuario.alterar(model);
            return userRepo.save(usuario);
        }
        return usuario;
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepo.findById(id);
	}

	@Override
	public Integer mostrarTotalUsuarios() {
		return userRepo.findByContadorTodosUsuarios();
	}


/*
	@Override
	public User findById(Long id) {
		return userRepo.findById(id);
	}*/

}
