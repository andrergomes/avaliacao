package br.com.andrergomes.avaliacao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Resposta {

	@Id
	@SequenceGenerator(name = "RESPOSTA_SEQ", sequenceName = "resposta_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESPOSTA_SEQ")
	private Long id;

	private String nomeImagem;

	private Boolean reconstruida;

	public Resposta() {
	}

	public Resposta(String nomeImagem, Boolean reconstruida) {
		this.nomeImagem = nomeImagem;
		this.reconstruida = reconstruida;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeImagem() {
		return this.nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public Boolean getReconstruida() {
		return this.reconstruida;
	}

	public void setReconstruida(Boolean reconstruida) {
		this.reconstruida = reconstruida;
	}
}
