package co.kozao.skillfinder.services;

import co.kozao.skillfinder.dto.AuthDto.AuthResponse;
import co.kozao.skillfinder.dto.AuthDto.LoginRequest;

public interface AuthService {
	
	public AuthResponse login(LoginRequest request);
}
