package co.kozao.skillfinder.dao;

import co.kozao.skillfinder.entities.User;

public interface UserDao {

	public User findByEmail(String email);
}
