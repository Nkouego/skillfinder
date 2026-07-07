package co.kozao.skillfinder.services;

import java.util.List;
import java.util.UUID;

import co.kozao.skillfinder.dto.UserDto.UpdateUserRequest;
import co.kozao.skillfinder.dto.UserDto.UserResponse;
import co.kozao.skillfinder.dto.UserDto.CreateUserRequest;

public interface UserService {
	public UserResponse createUser(CreateUserRequest request);
	public UserResponse updateUser(UpdateUserRequest request);
	public List<UserResponse> listUsers();
	public UserResponse getUser(UUID id);
	public UserResponse deleteUser(UUID id);
}
