package br.com.jetapps.app.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.com.jetapps.app.domain.Classe;
import br.com.jetapps.app.model.AlunoModel;
import br.com.jetapps.app.repository.AlunoRepository;
import br.com.jetapps.app.service.AlunoService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
	//private final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private AlunoService alunoService;

    @Autowired
    private AlunoRepository alunoRepository;
    
    @PostMapping("/cadastrar")
    public AlunoModel cadastrar(@RequestBody AlunoModel model) {
        return new AlunoModel(alunoService.cadastrar(model));
    }
    
    @GetMapping("/mostrar-tudo")
    public List<AlunoModel> mostrarTudo() {
        return alunoService.mostrarTudo().stream().map(AlunoModel::new).collect(Collectors.toList());
    }
    
    
    @GetMapping("/total-alunos")
    public Integer mostrarTotalAlunos() {
        return alunoService.mostrarTotalAlunos();
    }
    
    @GetMapping("/busca-alunos-filtrando/{id}")
    public List<AlunoModel> mostrarTodos(Classe classe) {
        return alunoService.mostrarTodosAlunosDaClasse(classe).stream().map(AlunoModel::new).collect(Collectors.toList());
    }
    
    @GetMapping("{id}")
    public AlunoModel get(@PathVariable final Long id) {
        return  alunoRepository.findById(id).map(AlunoModel::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("{id}")
    public AlunoModel update(@PathVariable final Long id, @RequestBody AlunoModel model) {

        if (alunoRepository.findById(id).isPresent()) {
            //return new AlunoModel(alunoRepository.save(new Aluno(model)));
        	return new AlunoModel(alunoService.alterar(model));
        }
        throw new RuntimeException("Cadastro não encontrado");
    }
    
    @PutMapping("/alterar")
    public AlunoModel alterar(@Valid @RequestBody AlunoModel model, BindingResult bindingResult) {
        if (!bindingResult.hasErrors() && Objects.nonNull(model.getId())) {
            return new AlunoModel(alunoService.alterar(model));
        }
        throw new RuntimeException("Model inválida");
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable final Long id) {
        alunoRepository.deleteById(id);
    }
}
