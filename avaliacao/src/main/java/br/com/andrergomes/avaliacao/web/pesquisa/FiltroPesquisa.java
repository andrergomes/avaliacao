package br.com.andrergomes.avaliacao.web.pesquisa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

@WebFilter(filterName = "filtroPesquisa", urlPatterns = {"/pesquisa/*"})
public class FiltroPesquisa implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    HttpSession session = ((HttpServletRequest)request).getSession();
	    Usuario usuarioLogado = (Usuario)session.getAttribute(ConstantesSistema.USUARIO_LOGADO.name());


	    
	    if (usuarioLogado == null || !usuarioLogado.isComum()) {
	      request.setAttribute(ConstantesSistema.ERRO.name(), "Acesso não autorizado");
	      request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	    } else {
	      
	      String caminhoPastas = (String)session.getAttribute(ConstantesSistema.CAMINHO_PASTAS.name());
	      
	      if (caminhoPastas == null || caminhoPastas.equals("")) {
	        session.setAttribute(ConstantesSistema.CAMINHO_PASTAS.name(), obterCaminhoPastas(request));
	      }
	      
	      chain.doFilter(request, response);
	    } 
	  }
	  
	  public String obterCaminhoPastas(ServletRequest request) throws IOException {
	    Properties prop = new Properties();
	    String propFileName = "/WEB-INF/config.properties";
	    
	    InputStream inputStream = request.getServletContext().getResourceAsStream(propFileName);
	    
	    if (inputStream != null) {
	      prop.load(inputStream);
	    } else {
	      
	      throw new FileNotFoundException("Arquivo de configurações não encontrado.");
	    } 
	    
	    return prop.getProperty("caminhoPastas");
	  }
	  
	  public void init(FilterConfig config) throws ServletException {}
	  
	  public void destroy() {}
}
