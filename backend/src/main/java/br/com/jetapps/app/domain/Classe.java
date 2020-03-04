package br.com.jetapps.app.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import br.com.jetapps.app.model.ClasseModel;
import br.com.jetapps.app.util.zone.Zones;
import lombok.Getter;

@Getter
@Entity
@Table(name="classe")

public class Classe {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //@NotNull
    @Column(name="data_criacao")
    private String dataCriacao = new Zones().getDataHoraAtual();
    
    @NotNull
    @Column(name="nome", length = 128)
    private String nome;

    @Column(name="numero")
    private Integer numero;
    
	public Classe() {
		super();
	}

    public Classe(ClasseModel model) {
        this();
        this.id = model.getId();
        this.nome = model.getNome();
        this.numero = model.getNumero();
    }
    
    public void alterar(ClasseModel model) {
        
        this.nome = model.getNome();
        this.numero = model.getNumero();
    }
}