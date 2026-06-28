package co.kozao.skillfinder.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

public class AuthDto {
	
	@Getter
	@Builder
	public static class AuthResponse{
		 UUID id;
		
		 String fullName;
		
		 String email;
		
		 String role;
		 
		 String message;
		 
		 boolean success;
	}
	
	@Builder
	public static record LoginRequest(
		
		 @NotBlank(message = "L'email est obligatoire")
		 @Email(message = "L'email est invalide")
		 String email,
		 
		 @NotBlank(message = "Le mot de passe est obligatoire")
		 String password
			){
	}
	

}
