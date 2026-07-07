package co.kozao.skillfinder.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import at.favre.lib.crypto.bcrypt.BCrypt;
import co.kozao.skillfinder.dao.UserDao;
import co.kozao.skillfinder.dao.impl.UserDaoImpl;
import co.kozao.skillfinder.dto.UserDto.CreateUserRequest;
import co.kozao.skillfinder.dto.UserDto.UpdateUserRequest;
import co.kozao.skillfinder.dto.UserDto.UserResponse;
import co.kozao.skillfinder.entities.User;
import co.kozao.skillfinder.services.UserService;

public class UserServiceImpl implements UserService {
	private static final DateTimeFormatter DATE_FORMATTER =
	        DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public UserResponse createUser(CreateUserRequest request){

        User existingUser = userDao.findByEmail(request.email());

        if (existingUser != null) {
            return UserResponse.builder()
                    .success(false)
                    .message("Compte deja existant")
                    .build();
        }

        String hashedPassword = BCrypt.withDefaults()
                .hashToString(12, request.password().toCharArray());

        User user = User.builder()
                .id(UUID.randomUUID())
                .fullName(request.fullName())
                .email(request.email())
                .password(hashedPassword)
                .role(request.role())
                .createdAt(LocalDateTime.now())
                .build();

        userDao.save(user);

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .createdAt(user.getCreatedAt().toString())
                .success(true)
                .message("Utilisateur créé avec succès")
                .build();
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest request) {

        User user = userDao.findById(request.id());

        if (user == null) {
            return UserResponse.builder()
                    .success(false)
                    .message("Utilisateur introuvable")
                    .build();
        }

        if(request.fullName() != null){
            user.setFullName(request.fullName());
        }

        if(request.role() != null){
            user.setRole(request.role());
        }

        if(request.password() != null && !request.password().isBlank()){
            String hashed = BCrypt.withDefaults()
                    .hashToString(12, request.password().toCharArray());

            user.setPassword(hashed);
        }

        

        User updatedUser = userDao.update(user);

        return UserResponse.builder()
                .id(updatedUser.getId())
                .fullName(updatedUser.getFullName())
                .email(updatedUser.getEmail())
                .role(updatedUser.getRole().name())
                .success(true)
                .message("Utilisateur modifié avec succès")
                .build();
    }

    @Override
    public List<UserResponse> listUsers() {

        return userDao.findAll()
                .stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .role(user.getRole().name())
                        .createdAt(LocalDateTime.now().format(DATE_FORMATTER))
                        .success(true)
                        .build())
                .toList();
    }

    @Override
    public UserResponse getUser(UUID id) {

        User user = userDao.findById(id);

        if (user == null) {
            return UserResponse.builder()
                    .success(false)
                    .message("Utilisateur introuvable")
                    .build();
        }

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .success(true)
                .message("Utilisateur trouvé")
                .build();
    }

    @Override
    public UserResponse deleteUser(UUID id) {

        User user = userDao.findById(id);

        if (user == null) {
            return UserResponse.builder()
                    .success(false)
                    .message("Utilisateur introuvable")
                    .build();
        }

        userDao.delete(user);

        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .success(true)
                .message("Utilisateur supprimé avec succès")
                .build();
    }
}