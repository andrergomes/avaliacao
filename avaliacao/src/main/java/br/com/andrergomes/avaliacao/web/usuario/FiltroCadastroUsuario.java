package br.com.andrergomes.avaliacao.web.usuario;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.andrergomes.avaliacao.model.Usuario;
import br.com.andrergomes.avaliacao.web.ConstantesSistema;



@WebFilter(filterName = "filtroCadastroUsuario", urlPatterns = {"/usuario/*"})
public class FiltroCadastroUsuario implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    HttpSession session = ((HttpServletRequest)request).getSession();
	    Usuario usuarioLogado = (Usuario)session.getAttribute(ConstantesSistema.USUARIO_LOGADO.name());
	    
	    if (usuarioLogado == null || !usuarioLogado.isAdmin()) {
	      request.setAttribute(ConstantesSistema.ERRO.name(), "Acesso não autorizado");
	      request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	    } else {
	      
	      chain.doFilter(request, response);
	    } 
	  }
	  
	  public void destroy() {}
	  
	  public void init(FilterConfig config) throws ServletException {}
}
