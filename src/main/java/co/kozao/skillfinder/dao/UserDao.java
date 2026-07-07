package co.kozao.skillfinder.dao;

import java.util.List;
import java.util.UUID;

import co.kozao.skillfinder.entities.User;

public interface UserDao {

	public User findByEmail(String email);

	public void save(User user);

	public User update(User user);

	public List<User> findAll();

	public User findById(UUID id);

	public void delete(User user);
}
