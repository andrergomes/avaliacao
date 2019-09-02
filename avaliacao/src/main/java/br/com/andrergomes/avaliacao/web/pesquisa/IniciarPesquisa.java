package br.com.andrergomes.avaliacao.web.pesquisa;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.andrergomes.avaliacao.web.ConstantesSistema;
import br.com.andrergomes.avaliacao.web.ILogica;

public class IniciarPesquisa implements ILogica {

	public Map<String, String> executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		HttpSession session = request.getSession();
		String caminhoPastas = (String) session.getAttribute(ConstantesSistema.CAMINHO_PASTAS.name());

		int max = qtdDiretorioImagens(caminhoPastas);

		int numeroAleatorio = gerarNumeroAleatorio(1, max);

		String nomesImagens = obterNomesImagensDiretorio(caminhoPastas, String.valueOf(numeroAleatorio));

		request.setAttribute("numeroAleatorio", Integer.valueOf(numeroAleatorio));
		request.setAttribute("nomesImagens", nomesImagens);

		Map<String, String> map = new HashMap<String, String>();
		map.put(ConstantesSistema.CAMINHO_PAGINA.name(), "/WEB-INF/jsp/exibirimagens-avaliacao.jsp");

		return map;
	}

	private String obterNomesImagensDiretorio(String caminhoPastas, String pastaImagens) {
		String[] arrayNomesImagensDiretorio = obterArrayNomesImagensDiretorio(caminhoPastas, pastaImagens);

		StringBuilder nomesImagens = new StringBuilder("");

		for (int i = 0; i < arrayNomesImagensDiretorio.length; i++) {
			nomesImagens.append(arrayNomesImagensDiretorio[i]);

			if (i < arrayNomesImagensDiretorio.length - 1) {
				nomesImagens.append(",");
			}
		}

		return nomesImagens.toString();
	}

	private String[] obterArrayNomesImagensDiretorio(String caminhoPastas, String pastaImagens) {
		File caminhoImagens = new File(caminhoPastas, pastaImagens);

		if (caminhoImagens.exists()) {
			return caminhoImagens.list();
		}

		return null;
	}

	private int gerarNumeroAleatorio(int min, int max) {
		return (new Random()).nextInt(max - min + 1) + min;
	}

	private int qtdDiretorioImagens(String caminhoPastas) {
		File diretorio = new File(caminhoPastas);

		int max = 0;

		if (diretorio.exists() && diretorio.isDirectory()) {
			File[] diretorios = diretorio.listFiles();

			max = diretorios.length;
		}

		return max;
	}
}
