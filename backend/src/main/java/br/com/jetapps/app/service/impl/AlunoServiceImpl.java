package br.com.jetapps.app.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jetapps.app.domain.Aluno;
import br.com.jetapps.app.domain.Classe;
import br.com.jetapps.app.model.AlunoModel;
import br.com.jetapps.app.repository.AlunoRepository;
import br.com.jetapps.app.service.AlunoService;
import br.com.jetapps.app.service.ClasseService;

@Transactional
@Service
public class AlunoServiceImpl implements AlunoService {

	//Logger logger = LoggerFactory.getLogger(LoggingController.class);
	private final Log logger = LogFactory.getLog(this.getClass());
	
    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private ClasseService classeService;
    /*
    @Override
    public Aluno cadastrar(AlunoModel model, UserModel classe) {
        return alunoRepository.save(new Aluno(model, classe));
    }
    */



    @Override
    public Aluno cadastrar(AlunoModel model) {
    	logger.info("Cadastro de alunos: \n"+ model);
        Optional<Classe> classe = classeService.findById(model.getClasse().getId());
        if (classe.isPresent()) {
        	return alunoRepository.save(new Aluno(model, classe.get()));
        }
        throw new RuntimeException("Classe nao encontrada");
    }
    
    @Override
    public Aluno consultar(Long idAluno) {
        return alunoRepository.findById(idAluno).orElse(null);
    }
    @Override
    public List<Aluno> mostrarTudo() {
    	logger.info("Fazendo a listagem de todos alunos");
        return alunoRepository.findAll();
    }
    
    @Override
    public List<Aluno> mostrarTodosAlunosDaClasse(Classe classe) {
    	//Optional<User> classe = classeService.findById(idClasse);
    	logger.info("Fazendo a listagem de todos alunos filtrados");
    	logger.info("idClasse: "+classe);
        return alunoRepository.findByClasseDoAluno(classe);
    }
    
    @Override
    public Aluno alterar(AlunoModel model) {
    	Aluno aluno = this.consultar(model.getId());
        if (Objects.nonNull(aluno)) {
            aluno.alterar(model);
            return alunoRepository.save(aluno);
        }
        return aluno;
    }

	@Override
	public List<Aluno> mostrarTodosAlunos() {
		return alunoRepository.findAll();
	}

	@Override
	public Integer mostrarTotalAlunos() {
		return alunoRepository.findByContadorTodosAlunos();
	}
}
