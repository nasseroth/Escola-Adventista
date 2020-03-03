package br.com.jetapps.app.controller;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.jetapps.app.domain.User;
import br.com.jetapps.app.model.ClasseModel;
import br.com.jetapps.app.repository.ClasseRepository;
import br.com.jetapps.app.service.ClasseService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/classe")
public class ClasseController {
	//private final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private ClasseService classeService;

    @Autowired
    private ClasseRepository classeRepository;
    
    @PostMapping("/cadastrar")
    public ClasseModel cadastrar(@RequestBody ClasseModel model) {
        return new ClasseModel(classeService.cadastrar(model));
    }
    
    @GetMapping("/mostrar-tudo")
    public List<ClasseModel> mostrarTudo() {
        return classeService.mostrarTudo().stream().map(ClasseModel::new).collect(Collectors.toList());
    }
    
    
    @GetMapping("/busca-classes-filtrando/{id}")
    public List<ClasseModel> mostrarTodos(User usuario) {
        return classeService.mostrarTodasClasses().stream().map(ClasseModel::new).collect(Collectors.toList());
    }
    
    @GetMapping("{id}")
    public ClasseModel get(@PathVariable final Long id) {
        return  classeRepository.findById(id).map(ClasseModel::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("{id}")
    public ClasseModel update(@PathVariable final Long id, @RequestBody ClasseModel model) {

        if (classeRepository.findById(id).isPresent()) {
            //return new ClasseModel(classeRepository.save(new Classe(model)));
        	return new ClasseModel(classeService.alterar(model));
        }
        throw new RuntimeException("Cadastro não encontrado");
    }
    
    @PutMapping("/alterar")
    public ClasseModel alterar(@Valid @RequestBody ClasseModel model, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && Objects.nonNull(model.getId())) {
            return new ClasseModel(classeService.alterar(model));
        }
        throw new RuntimeException("Model inválida");
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable final Long id) {
        classeRepository.deleteById(id);
    }
}
