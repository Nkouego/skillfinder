package co.kozao.skillfinder.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import co.kozao.skillfinder.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private UUID id;
	
	private String fullName;
	
	private String password;
	
	private String email;
	
	private UserRole role;
	
	private LocalDateTime date;
}
