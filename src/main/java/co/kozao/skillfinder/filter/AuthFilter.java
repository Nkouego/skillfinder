package co.kozao.skillfinder.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({"/login", "/dashboard"})
public class AuthFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        boolean authenticated = session != null
                && session.getAttribute("user") != null;

        String path = request.getServletPath();

        // Utilisateur déjà connecté qui veut accéder au login
        if (authenticated && path.equals("/login")) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return;
        }

        // Utilisateur non connecté qui veut accéder au dashboard
        if (!authenticated && path.equals("/dashboard")) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Laisser passer la requête
        chain.doFilter(request, response);
    }
}