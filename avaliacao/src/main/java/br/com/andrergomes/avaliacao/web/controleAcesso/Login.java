package br.com.andrergomes.avaliacao.web.controleAcesso;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.andrergomes.avaliacao.dao.jpa.ConnectionFactory;
import br.com.andrergomes.avaliacao.dao.jpa.UsuarioDao;
import br.com.andrergomes.avaliacao.model.Usuario;
import br.com.andrergomes.avaliacao.web.ConstantesSistema;
import br.com.andrergomes.avaliacao.web.ILogica;

public class Login implements ILogica {

	public Map<String, String> executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		Map<String, String> map = new HashMap<String, String>();

		if (!login.equals("") && !senha.equals("")) {
			Usuario usuario = pesquisarPorLogin(login);

			boolean senhaEhValida = false;

			if (usuario != null) {
				if (usuario.isPesquisaConcluida()) {
					map.put(ConstantesSistema.ERRO.name(), "Usuário já realizou avaliação.");
				} else {

					try {
						senhaEhValida = usuario.senhaEhValida(senha);
					} catch (NoSuchAlgorithmException e) {
						throw new ServletException("Problema na decodificação da senha do usuário");
					} catch (UnsupportedEncodingException e) {
						throw new ServletException("Problema na decodificação da senha do usuário");
					}

					if (senhaEhValida) {
						if (usuario.tipoEhValido()) {
							HttpSession session = request.getSession();
							session.setAttribute(ConstantesSistema.USUARIO_LOGADO.name(), usuario);

							if (usuario.isComum()) {
								map.put(ConstantesSistema.CAMINHO_PAGINA.name(), "/WEB-INF/jsp/iniciar-avaliacao.jsp");
							} else if (usuario.isAdmin()) {
								map.put(ConstantesSistema.CAMINHO_PAGINA.name(),
										"/WEB-INF/jsp/cadastrousuario-controleacesso.jsp");
							}
						} else {

							throw new ServletException("Tipo do usuário não identificado");
						}
					} else {

						map.put(ConstantesSistema.ERRO.name(), "Senha incorreta.");
					}
				}
			} else {

				map.put(ConstantesSistema.ERRO.name(), "Usuário não cadastrado.");
			}
		} else {

			map.put(ConstantesSistema.ERRO.name(), "Login e/ou senha devem ser informados.");
		}

		return map;
	}

	private Usuario pesquisarPorLogin(String login) {
		EntityManager manager = ConnectionFactory.getEntityManager();

		UsuarioDao usuarioDao = new UsuarioDao(manager);
		return usuarioDao.pesquisarPorLogin(login);
	}
}
