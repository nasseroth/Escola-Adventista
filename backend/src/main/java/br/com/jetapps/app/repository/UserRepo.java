package br.com.jetapps.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jetapps.app.domain.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User findByEmail(String email);

	@Query("select count(u) from User u")
	Integer findByContadorTodosUsuarios();
}
