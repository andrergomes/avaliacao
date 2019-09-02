package br.com.andrergomes.avaliacao.web.usuario;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.andrergomes.avaliacao.web.ConstantesSistema;
import br.com.andrergomes.avaliacao.web.ILogica;

public class UsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = -2420268802618871017L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String nomeClasse = "br.com.pesquisa.avaliacao.web.usuario." + acao;

		try {
			Class<?> classe = Class.forName(nomeClasse);
			ILogica regra = (ILogica) classe.newInstance();

			Map<String, String> map = regra.executar(request, response);

			if (map.get(ConstantesSistema.ERRO.name()) != null) {
				request.setAttribute(ConstantesSistema.ERRO.name(), map.get(ConstantesSistema.ERRO.name()));
			}

			request.getRequestDispatcher((String) map.get(ConstantesSistema.CAMINHO_PAGINA.name())).forward(request,
					response);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
