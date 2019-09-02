package br.com.andrergomes.avaliacao.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Usuario {

	@Id
	@SequenceGenerator(name = "USUARIO_SEQ", sequenceName = "usuario_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
	private Long id;

	private String nome;

	private String login;

	private String senha;

	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;

	private boolean pesquisaConcluida;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public boolean senhaEhValida(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if (this.senha != null && this.senha.equals(criptografarSenha(senha))) {
			return true;
		}

		return false;
	}

	public boolean isComum() {
		if (this.tipo != null && this.tipo.equals(TipoUsuario.COMUM)) {
			return true;
		}

		return false;
	}

	public boolean isAdmin() {
		if (this.tipo != null && this.tipo.equals(TipoUsuario.ADMINISTRADOR)) {
			return true;
		}

		return false;
	}

	public boolean tipoEhValido() {
		if (this.tipo != null && Arrays.asList(TipoUsuario.values()).contains(this.tipo)) {
			return true;
		}

		return false;
	}

	public boolean isPesquisaConcluida() {
		return this.pesquisaConcluida;
	}

	public void setPesquisaConcluida(boolean pesquisaConcluida) {
		this.pesquisaConcluida = pesquisaConcluida;
	}

	private String criptografarSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		StringBuilder hexString = new StringBuilder();

		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte[] messageDigest = algorithm.digest(senha.getBytes("UTF-8"));

		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}

		return hexString.toString();
	}
}
