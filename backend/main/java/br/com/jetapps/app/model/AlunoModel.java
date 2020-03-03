package br.com.jetapps.app.model;

import br.com.jetapps.app.domain.Aluno;
import br.com.jetapps.app.enuns.EnumSexo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AlunoModel {
	
    private Long id;
    private EnumSexo sexo;
    private String nome;
    private String codigoAluno;
    private ClasseModel classe;
    private String dataCriacao;
    
    public AlunoModel(Aluno domain) {
    	this.id = domain.getId();
        this.sexo = domain.getSexo();
        this.nome = domain.getNome();
        this.codigoAluno = domain.getCodigoAluno();
        this.dataCriacao = domain.getDataCriacao();
        this.classe = new ClasseModel(domain.getClasse());
    }
    
    
}
