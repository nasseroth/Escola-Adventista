package br.com.jetapps.app.model;

import br.com.jetapps.app.domain.Classe;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ClasseModel {
	
    private Long id;
    private String nome;
    private Integer numero;
    private String dataCriacao;
    
	public ClasseModel(Classe domain) {
    	this.id = domain.getId();
        this.numero = domain.getNumero();
        this.nome = domain.getNome();
        this.dataCriacao = domain.getDataCriacao();
	}

    

    
}
