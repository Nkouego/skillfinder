package co.kozao.skillfinder.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String section = request.getParameter("section");

		if (section == null) {
		    section = "home";
		}

		switch (section) {
		case "users":
		    request.setAttribute("contentPage", "/pages/admin/users.jsp");
		    break;

		case "skills":
		    request.setAttribute("contentPage", "/pages/admin/skills.jsp");
		    break;

		default:
		    request.setAttribute("contentPage", "/pages/admin/home.jsp");
		}
		
		request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
	}

}
