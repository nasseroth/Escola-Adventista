package br.com.jetapps.app.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jetapps.app.domain.Classe;
import br.com.jetapps.app.model.ClasseModel;
import br.com.jetapps.app.repository.ClasseRepository;
import br.com.jetapps.app.service.ClasseService;

@Transactional
@Service
public class ClasseServiceImpl implements ClasseService {

	//Logger logger = LoggerFactory.getLogger(LoggingController.class);
	private final Log logger = LogFactory.getLog(this.getClass());
	
    @Autowired
    private ClasseRepository classeRepository;
    
    @Autowired
    private ClasseService classeService;
    /*
    @Override
    public Classe cadastrar(ClasseModel model, UserModel usuario) {
        return classeRepository.save(new Classe(model, usuario));
    }
    */



    @Override
    public Classe cadastrar(ClasseModel model) {
    	logger.info("Cadastro de classes: \n"+ model);
    	// não permite 2 numeros de classe
        Optional<Classe> numero = classeService.findByNumeroClasse(model.getNumero());
        if (!numero.isPresent()) {
        	return classeRepository.save(new Classe(model));
        }
        throw new RuntimeException("Já existe esse número de classe cadastrado");
    }
    
    @Override
    public Classe consultar(Long idClasse) {
        return classeRepository.findById(idClasse).orElse(null);
    }
    @Override
    public List<Classe> mostrarTudo() {
    	logger.info("Fazendo a listagem de todos classes");
        return classeRepository.findAll();
    }
    
	@Override
	public Optional<Classe> findByNumeroClasse(Integer numero) {
		return classeRepository.findByClasseDoAluno(numero);
	}
    
    @Override
    public List<Classe> mostrarTodasClasses() {
    	//Optional<User> usuario = usuarioService.findById(idUsuario);
    	logger.info("Fazendo a listagem de todos classes filtrados");
        return classeRepository.findAll();
    }
    
    @Override
    public Classe alterar(ClasseModel model) {
    	Classe classe = this.consultar(model.getId());
        if (Objects.nonNull(classe)) {
            classe.alterar(model);
            return classeRepository.save(classe);
        }
        return classe;
    }

	@Override
	public Optional<Classe> findById(Long id) {
		return classeRepository.findById(id);
	}

	@Override
	public Integer mostrarTotalClasses() {
		return classeRepository.findByContadorTodasClasses();
	}


}
