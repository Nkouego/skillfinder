package co.kozao.skillfinder.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ValidationException;

import static co.kozao.skillfinder.validation.ValidationUtil.validate;

import java.io.IOException;
import java.util.Map;

import co.kozao.skillfinder.dto.AuthDto.AuthResponse;
import co.kozao.skillfinder.dto.AuthDto.LoginRequest;
import co.kozao.skillfinder.services.AuthService;
import co.kozao.skillfinder.services.impl.AuthServiceImpl;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AuthService authService;
   
	public void init() throws ServletException {
		authService = new AuthServiceImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
			LoginRequest loginRequest = LoginRequest.builder()
												.email(email)
												.password(password)
												.build();
			
			Map<String, String> errors = validate(loginRequest);
		
			if(!errors.isEmpty()) {
				request.setAttribute("errors", errors);
			    request.setAttribute("email", email);

			    request.getRequestDispatcher("/login.jsp") .forward(request, response);
			    
			    return;
			}
			
			AuthResponse authResponse = authService.login(loginRequest);
			
			if(authResponse.isSuccess()) {
				HttpSession session = request.getSession();
				session.setAttribute("user", authResponse);
				
	            response.sendRedirect(request.getContextPath() + "/dashboard");
			} else {
				request.setAttribute("authError", authResponse.getMessage());
				request.setAttribute("email", email);
				
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}

}
