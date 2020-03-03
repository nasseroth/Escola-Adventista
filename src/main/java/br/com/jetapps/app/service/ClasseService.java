package br.com.jetapps.app.service;

import java.util.List;
import java.util.Optional;

import br.com.jetapps.app.domain.Classe;
import br.com.jetapps.app.model.ClasseModel;

public interface ClasseService {
    Classe cadastrar(ClasseModel model);
    List<Classe> mostrarTudo();
	Classe alterar(ClasseModel model);
	Classe consultar(Long idClasse);
	List<Classe> mostrarTodasClasses();
	Optional<Classe> findByNumeroClasse(Integer numero);
	Optional<Classe> findById(Long id);
}
