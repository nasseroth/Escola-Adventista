package br.com.jetapps.app.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.jetapps.app.enuns.EnumSexo;
import br.com.jetapps.app.model.AlunoModel;
import br.com.jetapps.app.util.zone.Zones;
import lombok.Getter;

@Getter
@Entity
@Table(name="aluno")

public class Aluno {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name="nome", length = 128)
    private String nome;
    
    @Enumerated(EnumType.STRING)
    @Column(name="sexo")
    private EnumSexo sexo;
    
    @Column(name="data_criacao")
    private String dataCriacao = new Zones().getDataHoraAtual();
    
    @NotNull
    @Column(name="codigo_aluno", length = 128)
    private String codigoAluno;
    
    @NotNull
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "classe_id")
    private Classe classe;

	public Aluno() {
		super();
	}

    public Aluno(AlunoModel model, Classe classe) {
        this();
        this.id = model.getId();
        this.nome = model.getNome();
        this.sexo = model.getSexo();
        this.codigoAluno = model.getCodigoAluno();
        this.classe = classe;
    }
    
    public void alterar(AlunoModel model) {
        
        this.nome = model.getNome();
        this.sexo = model.getSexo();
        this.codigoAluno = model.getCodigoAluno();
    }
}
