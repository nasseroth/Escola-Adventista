package br.com.jetapps.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

import br.com.jetapps.app.domain.Classe;

public interface ClasseRepository extends JpaRepository<Classe, Long> {
	/*
    String query = "SELECT c FROM Cliente c WHERE c.usuario = :id";
    @Query(query)
    List<Cliente> findByUsuarioId(Long id);
    */
	@Query("select c from Classe c where c.numero = ?1")
	Optional<Classe> findByClasseDoAluno(Integer numero);
	
	@Query("select count(c) from Classe c")
	Integer findByContadorTodasClasses();
}
