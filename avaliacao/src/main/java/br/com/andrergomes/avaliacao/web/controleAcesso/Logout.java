package br.com.andrergomes.avaliacao.web.controleAcesso;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.andrergomes.avaliacao.web.ConstantesSistema;
import br.com.andrergomes.avaliacao.web.ILogica;

public class Logout implements ILogica {

	public Map<String, String> executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		Map<String, String> map = new HashMap<String, String>();

		HttpSession session = request.getSession();
		session.invalidate();

		map.put(ConstantesSistema.CAMINHO_PAGINA.name(), "/WEB-INF/jsp/login.jsp");

		return map;
	}
}
