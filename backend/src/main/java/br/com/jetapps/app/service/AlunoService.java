package br.com.jetapps.app.service;

import java.util.List;

import br.com.jetapps.app.domain.Aluno;
import br.com.jetapps.app.domain.Classe;
import br.com.jetapps.app.model.AlunoModel;

public interface AlunoService {
    Aluno cadastrar(AlunoModel model);
    List<Aluno> mostrarTudo();
	Aluno alterar(AlunoModel model);
	Aluno consultar(Long idAluno);
	List<Aluno> mostrarTodosAlunosDaClasse(Classe classe);
	List<Aluno> mostrarTodosAlunos();
	Integer mostrarTotalAlunos();
}
