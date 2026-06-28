package co.kozao.skillfinder.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import co.kozao.skillfinder.dto.AuthDto.AuthResponse;
import co.kozao.skillfinder.enums.UserRole;

@WebFilter("/admin/*")
public class RoleFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;

	@Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        AuthResponse user = (session != null)
                ? (AuthResponse) session.getAttribute("user")
                : null;

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        if (!UserRole.ADMIN_RH.name().equals(user.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }
}