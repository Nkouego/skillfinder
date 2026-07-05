package co.kozao.skillfinder.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import co.kozao.skillfinder.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

public class UserDto {
	
	public static record CreateUserRequest(
			
		@NotBlank(message = "Le nom est obligatoire")	
		String fullName,
		
		@NotBlank(message = "Le mot de passe est obligatoire")	
		String password,
		
		@NotBlank(message = "L'email est obligatoire")	
		@Email(message = "L'email est invalide")	
		String email,
		
		@NotNull(message = "Le rôle est obligatoire")		
		UserRole role
	) {}
	
	public static record UpdateUserRequest(
			UUID id,
			
			String fullName,
				
			String password,
			
			@Email(message = "L'email est invalide")	
			String email,
				
			UserRole role
			) {
		
	}

	@Getter
	@Builder
	public static class UserResponse{
		 UUID id;
		
		 String fullName;
		
		 String email;
		
		 String role;
		 
		 String message;
		 
		 String createdAt;
		 
		 boolean success;
	}
}
