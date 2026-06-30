package co.kozao.skillfinder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import co.kozao.skillfinder.config.DBConnection;
import co.kozao.skillfinder.dao.UserDao;
import co.kozao.skillfinder.entities.User;
import co.kozao.skillfinder.enums.UserRole;

public class UserDaoImpl implements UserDao{
	private final Connection conn = DBConnection.getInstance().getConnection();
	
	@Override
	public User findByEmail(String email) {
		String sql = "SELECT * FROM users WHERE email = ?";
		try(PreparedStatement stmt = conn.prepareStatement(sql) ) {
			stmt.setString(1, email);
			
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
					return User.builder()
							.id((UUID)rs.getObject("id"))
							.fullName(rs.getString("full_name"))
							.email(rs.getString("email"))
							.password(rs.getString("password"))
							.role(UserRole.valueOf(rs.getString("role")))
							.build();
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		return null;
	}
}
