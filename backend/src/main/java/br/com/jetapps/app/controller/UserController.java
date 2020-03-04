package br.com.jetapps.app.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jetapps.app.domain.User;
import br.com.jetapps.app.enuns.ProfileEnum;
import br.com.jetapps.app.model.UserModel;
import br.com.jetapps.app.repository.UserRepo;
import br.com.jetapps.app.security.response.Response;
import br.com.jetapps.app.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;
    @Autowired
    private UserRepo userRepository;
    
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@PostMapping
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> cadastrar(HttpServletRequest request, @RequestBody User user, ProfileEnum profile,
			BindingResult result) {

		Response<User> response = new Response<User>();

		try {
			validadeCreateUser(user, result);
			if (result.hasErrors()) {
				result.getAllErrors()
								.forEach(error -> response.getErrors()
										.add(error
												.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User userPersisted = (User) userService.createOrUpdate(user);
			response.setData(userPersisted);
			
		} catch (DuplicateKeyException duplicate) {
			response.getErrors().add("E-mail já registrado !");
			return ResponseEntity.badRequest().body(response);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

		return ResponseEntity.ok(response);
	}
	
	
    @GetMapping("/total-usuarios")
    public Integer mostrarTotalAlunos() {
        return userService.mostrarTotalUsuarios();
    }
    
    @GetMapping("/mostrar-tudo")
    public List<UserModel> mostrarTudo() {
        return userService.mostrarTudo().stream().map(UserModel::new).collect(Collectors.toList());
    }
    @GetMapping("{id}")
    public UserModel get(@PathVariable final Long id) {
        return  userRepository.findById(id).map(UserModel::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("{id}")
    public UserModel update(@PathVariable final Long id, @RequestBody UserModel model) {

        if (userRepository.findById(id).isPresent()) {
        	return new UserModel(userService.alterar(model));
        }
        throw new RuntimeException("Cadastro não encontrado");
    }
    
    @PutMapping("/alterar")
    public UserModel alterar(@Valid @RequestBody UserModel model, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && Objects.nonNull(model.getId())) {
            return new UserModel(userService.alterar(model));
        }
        throw new RuntimeException("Model inválida");
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable final Long id) {
    	userRepository.deleteById(id);
    }
	
	
	
	
	
	
	/*
		@PutMapping
		//@PreAuthorize("hasAnyRole('ADMIN')")
		public ResponseEntity<Response<User>> update(HttpServletRequest request, @RequestBody User user,
				BindingResult result) {
			Response<User> response = new Response<User>();

			try {
				validadeUpdateUser(user, result);
				if (result.hasErrors()) {
					result.getAllErrors()
									.forEach(error -> 
										response
											.getErrors()
											.add(error
												.getDefaultMessage()));
					return ResponseEntity.badRequest().body(response);
				}
				
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				User userPersisted = (User) userService.createOrUpdate(user);
				if(Objects.nonNull(userPersisted)) {
					response.setData(userPersisted);
				}
				
			} catch (Exception e) {
				response.getErrors().add(e.getMessage());
				return ResponseEntity.badRequest().body(response);
			}

			return ResponseEntity.ok(response);
		}

		
	@GetMapping(value = "{id}")
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<User>> findById(@PathVariable("id") Long id) {
		Response<User> response = new Response<User>();
		User user = userService.findById(id).orElse(null);
		
		if (user == null) {
			response.getErrors().add("Registro de usuário não encontrado : " + id);
			return ResponseEntity.badRequest().body(response);
		}
		if(Objects.nonNull(user)) {
			response.setData(user);
		}
		return ResponseEntity.ok(response);
	}	
	
	
	@DeleteMapping(value = "{id}")
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> deleteById(@PathVariable("id") Long id) {
		Response<String> response = new Response<String>();
		User user = userService.findById(id).orElse(null);
		
		if (user == null) {
			response.getErrors().add("Usuário não encontrado : " + id);
			return ResponseEntity.badRequest().body(response);
		}
		userService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}		
		
	/*
	@GetMapping(value = {"{page}/{count}"})
	//@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<Page<User>>> findAll(@PathVariable int page, @PathVariable int count) {
		Response<Page<User>> response = new Response<Page<User>>();
		Page<User> users = userService.findAll(page, count);
		response.setData((User) users);
		return ResponseEntity.ok(response);
	}	
    
    @GetMapping("/mostrar-tudo")
    public List<UserModel> mostrarTudo() {
        return userService.mostrarTudo().stream().map(UserModel::new).collect(Collectors.toList());
    }
	

    @PutMapping("{id}")
    public UserModel update(@PathVariable final Long id, @RequestBody UserModel model) {

        if (userService.findById(id).isPresent()) {
            //return new ClienteModel(clienteRepository.save(new Cliente(model)));
        	return new UserModel(userService.alterar(model));
        }
        throw new RuntimeException("Cadastro não encontrado");
    }
	
	*/
	private void validadeCreateUser(User user, BindingResult result) {
		if (user.getEmail() == null) {
			result.addError(new ObjectError("User", "Email não informado"));
		}
	}
	/*
	private void validadeUpdateUser(User user, BindingResult result) {
		if (user.getId() == null) {
			result.addError(new ObjectError("User", "Id não informado"));
		}
	}*/
}
