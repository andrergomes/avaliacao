package br.com.andrergomes.avaliacao.web;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.andrergomes.avaliacao.dao.jpa.ConnectionFactory;
import br.com.andrergomes.avaliacao.dao.jpa.UsuarioDao;
import br.com.andrergomes.avaliacao.model.TipoUsuario;
import br.com.andrergomes.avaliacao.model.Usuario;

@WebListener
public class InicializadorListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent context) {
		EntityManager em = ConnectionFactory.getEntityManager();

		UsuarioDao usuarioDao = new UsuarioDao(em);
		Usuario usuario = usuarioDao.pesquisarPorLogin("admin");

		if (usuario == null) {
			usuario = new Usuario();
			usuario.setLogin("admin");
			usuario.setNome("Admin");
			usuario.setTipo(TipoUsuario.ADMINISTRADOR);
			try {
				usuario.setSenha(criptografarSenha("Adm1n85"));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			try {
				em.getTransaction().begin();
				usuarioDao.salvar(usuario);
				em.getTransaction().commit();
			} finally {

				em.close();
			}
		}
	}

	public void contextDestroyed(ServletContextEvent context) {
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
