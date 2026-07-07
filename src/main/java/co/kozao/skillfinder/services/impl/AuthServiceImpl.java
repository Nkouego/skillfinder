package co.kozao.skillfinder.services.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;

import co.kozao.skillfinder.dao.UserDao;
import co.kozao.skillfinder.dao.impl.UserDaoImpl;
import co.kozao.skillfinder.dto.AuthDto.AuthResponse;
import co.kozao.skillfinder.dto.AuthDto.LoginRequest;
import co.kozao.skillfinder.entities.User;
import co.kozao.skillfinder.services.AuthService;

public class AuthServiceImpl implements AuthService {
	
	private static final String INVALID_CREDENTIALS = "email ou mot de passe invalide";
	private final UserDao userDao;
	
	public AuthServiceImpl() {
		this.userDao = new UserDaoImpl();
	}

	@Override
	public AuthResponse login(LoginRequest request) {
		User user = userDao.findByEmail(request.email());
		if(user == null) {
			return  AuthResponse.builder()
					.message(INVALID_CREDENTIALS)
					.success(false)
					.build();
		}
		
		//verify the password correspondance
		boolean match =	BCrypt.verifyer()
							.verify(request.password().toCharArray(), user.getPassword())
							.verified;
				
		if (!match) {
			return  AuthResponse.builder()
					.success(false)
					.message(INVALID_CREDENTIALS)
					.build();
		}
				
		return AuthResponse
				.builder()
				.id(user.getId())
				.email(user.getEmail())
				.fullName(user.getFullName())
				.role(user.getRole().name())
				.message("Connexion reussie")
				.success(true)
				.build();
	}
	
}
