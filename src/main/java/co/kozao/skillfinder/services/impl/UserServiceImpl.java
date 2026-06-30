//package co.kozao.skillfinder.services.impl;
//
//import java.util.List;
//import java.util.UUID;
//
//import co.kozao.skillfinder.dao.UserDao;
//import co.kozao.skillfinder.dao.impl.UserDaoImpl;
//import co.kozao.skillfinder.dto.UserDto.UpdateUserRequest;
//import co.kozao.skillfinder.dto.UserDto.UserResponse;
//import co.kozao.skillfinder.dto.UserDto.CreateUserRequest;
//import co.kozao.skillfinder.entities.User;
//import co.kozao.skillfinder.services.UserService;
//
//public class UserServiceImpl implements UserService {
//
//	private UserDao userDao;
//	public UserServiceImpl() {
//		this.userDao = new UserDaoImpl();
//	}
//
//	@Override
//	public UserResponse createUser(CreateUserRequest request) {
//		
//		User user = User.builder()
//				.id(UUID.randomUUID())
//				.fullName(request.fullName())
//				.email(request.email())
//				.password(request.password())
//				.role(request.role())
//				.build();
//		
//		userDao.save(user);
//		
//		return UserResponse.builder()
//				.fullName(user.getFullName())
//				.email(user.getEmail())
//				.role(user.getRole().name())
//				.build();
//	}
//
//	@Override
//	public UserResponse updateUser(UpdateUserRequest request) {
//		
//		User user = User.builder()
//				.id(UUID.randomUUID())
//				.fullName(request.fullName())
//				.email(request.email())
//				.password(request.password())
//				.role(request.role())
//				.build();
//		
//		User user = userDao.update(user);
//		return UserResponse.builder()
//				.fullName(user.getFullName())
//				.email(user.getEmail())
//				.role(user.getRole().name())
//				.build();
//	}
//
//	@Override
//	public List<UserResponse> listUsers() {
//		return null;
//	}
//
//	@Override
//	public UserResponse getUser() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public UserResponse deleteUser() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//}
//
