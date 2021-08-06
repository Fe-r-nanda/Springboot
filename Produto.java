package br.org.generation.lojagames.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity          // = tabela
@Table(name = "tb_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)   // = auto_increment
	private long id;
	
	@NotNull(message = "O campo NOME deve ser preenchido!")
	private String nome;
	
	@Size(min = 5, max = 500)
	private String descricao;
	
	@NotNull(message = "Defina o console!")
	private String console;
	
	@NotNull
	@Positive // obriga que o valor seja positivo
	private double preco;
	
	@ManyToOne        // muitos pra 1 chave estrangeira
	@JsonIgnoreProperties("produto")  // mostra apenas uma vez, corta a repetição infinita
	private Categoria categoria;      // atributo categoria referente ao prudto que contem a categoria
	

	@ManyToOne       
	@JsonIgnoreProperties("produto")  
	private Usuario usuario;      


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}
