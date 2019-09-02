package br.com.andrergomes.avaliacao.web.usuario;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.andrergomes.avaliacao.dao.jpa.UsuarioDao;
import br.com.andrergomes.avaliacao.model.TipoUsuario;
import br.com.andrergomes.avaliacao.model.Usuario;
import br.com.andrergomes.avaliacao.web.ConstantesSistema;
import br.com.andrergomes.avaliacao.web.ILogica;

public class CadastrarUsuario implements ILogica {

	public Map<String, String> executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		EntityManager manager = (EntityManager) request.getAttribute(ConstantesSistema.CONEXAO.name());

		String nome = request.getParameter("nome");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setTipo(TipoUsuario.COMUM);

		try {
			usuario.setSenha(criptografarSenha(senha));
		} catch (NoSuchAlgorithmException e) {
			throw new ServletException(e);
		} catch (UnsupportedEncodingException e) {
			throw new ServletException(e);
		}

		UsuarioDao usuarioDao = new UsuarioDao(manager);

		try {
			manager.getTransaction().begin();
			usuarioDao.salvar(usuario);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put(ConstantesSistema.CAMINHO_PAGINA.name(), "/WEB-INF/jsp/cadastrousuario-controleacesso.jsp");

		return map;
	}

	private String criptografarSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte[] messageDigest = algorithm.digest(senha.getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();

		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}

		return hexString.toString();
	}
}
