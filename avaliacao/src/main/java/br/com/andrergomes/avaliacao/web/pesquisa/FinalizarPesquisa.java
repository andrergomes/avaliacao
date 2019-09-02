package br.com.andrergomes.avaliacao.web.pesquisa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.andrergomes.avaliacao.dao.jpa.PesquisaDao;
import br.com.andrergomes.avaliacao.dao.jpa.UsuarioDao;
import br.com.andrergomes.avaliacao.model.Pesquisa;
import br.com.andrergomes.avaliacao.model.Resposta;
import br.com.andrergomes.avaliacao.model.Usuario;
import br.com.andrergomes.avaliacao.web.ConstantesSistema;
import br.com.andrergomes.avaliacao.web.ILogica;

public class FinalizarPesquisa implements ILogica {

	public Map<String, String> executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		EntityManager manager = (EntityManager) request.getAttribute(ConstantesSistema.CONEXAO.name());

		HttpSession session = request.getSession();

		String resposta = request.getParameter("resposta");

		Usuario usuario = (Usuario) session.getAttribute(ConstantesSistema.USUARIO_LOGADO.name());

		List<Resposta> respostas = obterRespostas(resposta);

		try {
			manager.getTransaction().begin();

			UsuarioDao usuarioDao = new UsuarioDao(manager);
			usuario.setPesquisaConcluida(true);
			usuarioDao.alterar(usuario);

			Pesquisa pesquisa = new Pesquisa();
			pesquisa.setUsuario(usuario);
			pesquisa.setRespostas(respostas);
			pesquisa.setData(Calendar.getInstance());

			PesquisaDao pesquisaDao = new PesquisaDao(manager);
			pesquisaDao.salvar(pesquisa);

			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}

		session.invalidate();

		Map<String, String> map = new HashMap<String, String>();
		map.put(ConstantesSistema.CAMINHO_PAGINA.name(), "/WEB-INF/jsp/finalizar-avaliacao.jsp");

		return map;
	}

	private List<Resposta> obterRespostas(String stringRespostas) {
		List<Resposta> respostas = new ArrayList<Resposta>();

		String[] arrayRespostas = stringRespostas.split(";");

		String[] arrayResposta = null;

		for (String resposta : arrayRespostas) {
			arrayResposta = resposta.split(",");
			respostas.add(new Resposta(arrayResposta[0], Boolean.valueOf(arrayResposta[1])));
		}

		return respostas;
	}
}
