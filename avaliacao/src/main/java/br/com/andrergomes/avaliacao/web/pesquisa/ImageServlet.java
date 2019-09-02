package br.com.andrergomes.avaliacao.web.pesquisa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.andrergomes.avaliacao.web.ConstantesSistema;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = -3576518256545087256L;
	private String imagePath;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedImage = request.getPathInfo();

		if (requestedImage == null) {
			response.sendError(404);

			return;
		}
		HttpSession session = request.getSession();
		this.imagePath = (String) session.getAttribute(ConstantesSistema.CAMINHO_PASTAS.name());

		File image = new File(this.imagePath, URLDecoder.decode(requestedImage, "UTF-8"));

		if (!image.exists()) {
			response.sendError(404);

			return;
		}
		String contentType = getServletContext().getMimeType(image.getName());

		if (contentType == null || !contentType.startsWith("image")) {
			response.sendError(404);

			return;
		}
		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(image.length()));

		InputStream input = null;

		try {
			input = new FileInputStream(image);

			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				response.getOutputStream().write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
		}
	}
}
