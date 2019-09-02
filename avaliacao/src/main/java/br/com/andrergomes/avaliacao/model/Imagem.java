package br.com.andrergomes.avaliacao.model;

public class Imagem {

	private Long id;
	
	private String nome;
	
	private Boolean reconstruida;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getReconstruida() {
		return this.reconstruida;
	}

	public void setReconstruida(Boolean reconstruida) {
		this.reconstruida = reconstruida;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
