package br.com.jetapps.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.jetapps.app.domain.Aluno;
import br.com.jetapps.app.domain.Classe;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	/*
    String query = "SELECT c FROM Cliente c WHERE c.usuario = :id";
    @Query(query)
    List<Cliente> findByUsuarioId(Long id);
    */
	
	@Query("select c from Classe c where c.numero = ?1")
	List<Aluno> findByClasseDoAluno(Classe id);
    
	@Query("select count(a) from Aluno a")
	Integer findByContadorTodosAlunos();
}

