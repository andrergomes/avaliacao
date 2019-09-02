package br.com.andrergomes.avaliacao.web.pesquisa;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.andrergomes.avaliacao.web.ConstantesSistema;
import br.com.andrergomes.avaliacao.web.ILogica;

@WebServlet(name = "pesquisa", urlPatterns = { "/pesquisa" })
public class PesquisaServlet extends HttpServlet {

	private static final long serialVersionUID = 5203522687022754791L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String nomeClasse = "br.com.pesquisa.avaliacao.web.pesquisa." + acao;

		try {
			Class<?> classe = Class.forName(nomeClasse);
			ILogica regra = (ILogica) classe.newInstance();

			Map<String, String> map = regra.executar(request, response);

			request.getRequestDispatcher((String) map.get(ConstantesSistema.CAMINHO_PAGINA.name())).forward(request,
					response);
		} catch (Exception e) {
			response.setStatus(404);

			throw new ServletException("Problema na lógica da avaliação.");
		}
	}
}
