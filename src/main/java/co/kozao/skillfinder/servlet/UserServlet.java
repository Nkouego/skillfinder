package co.kozao.skillfinder.servlet;

import static co.kozao.skillfinder.validation.ValidationUtil.validate;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.kozao.skillfinder.dto.UserDto.CreateUserRequest;
import co.kozao.skillfinder.dto.UserDto.UpdateUserRequest;
import co.kozao.skillfinder.dto.UserDto.UserResponse;
import co.kozao.skillfinder.exception.DataBaseException;
import co.kozao.skillfinder.services.UserService;
import co.kozao.skillfinder.services.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserService userService;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
        mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String path = request.getPathInfo();

        if (path == null || path.equals("/")) {

            List<UserResponse> users = userService.listUsers();
            mapper.writeValue(response.getWriter(), users);

        } else {

            String id = path.substring(1);

            UserResponse user = userService.getUser(UUID.fromString(id));
            mapper.writeValue(response.getWriter(), user);

        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
	        CreateUserRequest createRequest =
	                mapper.readValue(request.getInputStream(), CreateUserRequest.class);
	
	        Map<String, String> errors = validate(createRequest);
	
	        if (!errors.isEmpty()) {
	            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	            mapper.writeValue(response.getWriter(), errors);
	            return;
	        }
	
	       
        	UserResponse userResponse = userService.createUser(createRequest);
        	
        	if (userResponse.isSuccess()) {
                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
            	response.setStatus(HttpServletResponse.SC_CONFLICT);
            }

        	 mapper.writeValue(response.getWriter(), userResponse);
	            
		} catch (DataBaseException e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				mapper.writeValue(response.getWriter(),
			            Map.of(
			                    "success", false,
			                    "message", "Une erreur interne est survenue"
			            ));
				
		} catch (JsonProcessingException e) {
			 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			 mapper.writeValue(response.getWriter(),
			            Map.of(
			                    "success", false,
			                    "message", "Le format JSON est invalide."
			            ));
		}    
    }

    @Override
    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        try {
        	
        	UpdateUserRequest updateRequest =
                    mapper.readValue(request.getInputStream(), UpdateUserRequest.class);

            Map<String, String> errors = validate(updateRequest);

            if (!errors.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                mapper.writeValue(response.getWriter(), errors);
                return;
            }

            UserResponse userResponse = userService.updateUser(updateRequest);

            if (userResponse.isSuccess()) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

            mapper.writeValue(response.getWriter(), userResponse);
			
		} catch (DataBaseException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			mapper.writeValue(response.getWriter(),
		            Map.of(
		                    "success", false,
		                    "message", "Une erreur interne est survenue"
		            ));
			
		} catch (JsonProcessingException e) {
			 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			 mapper.writeValue(response.getWriter(),
			            Map.of(
			                    "success", false,
			                    "message", "Le format JSON est invalide."
			            ));
		}    

        
    }

    @Override
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String path = request.getPathInfo();
        
        if (path == null || path.length() <= 1) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            mapper.writeValue(response.getWriter(),
                    Map.of("message", "L'identifiant est obligatoire"));

            return;
        }
        
        String id = path.substring(1);

        if (id == null || id.isBlank()) {

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            mapper.writeValue(response.getWriter(),
                    Map.of("message", "L'identifiant est obligatoire"));

            return;
        }

        try {
        	 UserResponse userResponse = userService.deleteUser(UUID.fromString(id));

             if (userResponse.isSuccess()) {
                 response.setStatus(HttpServletResponse.SC_OK);
             } else {
                 response.setStatus(HttpServletResponse.SC_NOT_FOUND);
             }

             mapper.writeValue(response.getWriter(), userResponse);
		} catch (DataBaseException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			mapper.writeValue(response.getWriter(),
		            Map.of(
		                    "success", false,
		                    "message", "Une erreur interne est survenue"
		            ));
		}
       
    }

}