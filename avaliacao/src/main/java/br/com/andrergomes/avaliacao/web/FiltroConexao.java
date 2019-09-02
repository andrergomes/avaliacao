package br.com.andrergomes.avaliacao.web;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.andrergomes.avaliacao.dao.jpa.ConnectionFactory;

public class FiltroConexao implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		EntityManager em = ConnectionFactory.getEntityManager();
		request.setAttribute(ConstantesSistema.CONEXAO.name(), em);

		chain.doFilter(request, response);

		em.close();
		em = null;
	}

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}
}
