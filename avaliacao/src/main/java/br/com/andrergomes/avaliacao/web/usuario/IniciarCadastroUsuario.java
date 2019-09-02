package br.com.andrergomes.avaliacao.web.usuario;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.andrergomes.avaliacao.web.ConstantesSistema;
import br.com.andrergomes.avaliacao.web.ILogica;

public class IniciarCadastroUsuario implements ILogica {

	public Map<String, String> executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		Map<String, String> map = new HashMap<String, String>();

		map.put(ConstantesSistema.CAMINHO_PAGINA.name(), "/WEB-INF/jsp/cadastrousuario-controleacesso.jsp");

		return map;
	}
}
