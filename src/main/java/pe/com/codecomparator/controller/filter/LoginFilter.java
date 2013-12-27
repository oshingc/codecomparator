package pe.com.codecomparator.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.codecomparator.controller.LoginController;

/**
 * Servlet Filter implementation class LoginFiler
 */
public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// Obtengo el bean que representa el usuario desde el scope sesión
		LoginController loginController = (LoginController) req.getSession()
				.getAttribute("loginController");

		// Procesamos la URL que está requiriendo el cliente
		String urlStr = req.getRequestURL().toString().toLowerCase();

		// Si no requiere protección se continúa normalmente.
		if (notHide(urlStr)) {
			chain.doFilter(request, response);
			return;
		}

		// El usuario no está logueado
		if (loginController == null || !loginController.getLogin()) {
			res.sendRedirect(req.getContextPath() + "");
			return;
		}

		// El recurso requiere protección, pero el usuario ya está logueado.
		chain.doFilter(request, response);
	}

	private boolean notHide(String urlStr) {
		if (urlStr.endsWith("codecomparator/"))
			return true;
		if (urlStr.endsWith("stars.jpg"))
			return true;
		if (urlStr.endsWith("code.jpeg"))
			return true;
		if (urlStr.endsWith("forgot_password.png"))
			return true;
		if (urlStr.endsWith("login.xhtml"))
			return true;
		if (urlStr.indexOf("/javax.faces.resource/") != -1)
			return true;
		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
