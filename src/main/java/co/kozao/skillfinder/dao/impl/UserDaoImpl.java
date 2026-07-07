package co.kozao.skillfinder.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.kozao.skillfinder.config.DBConnection;
import co.kozao.skillfinder.dao.UserDao;
import co.kozao.skillfinder.entities.User;
import co.kozao.skillfinder.enums.UserRole;
import co.kozao.skillfinder.exception.DataBaseException;

public class UserDaoImpl implements UserDao {

    private final Connection conn = DBConnection.getInstance().getConnection();

    @Override
    public User findByEmail(String email) {

        String sql = "SELECT * FROM users WHERE email = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapUser(rs);
                }
            }

        }  catch (SQLException e) {
			throw new DataBaseException("Erreur lors de l'enregistrement", e);
		}

        return null;
    }

    @Override
    public void save(User user) {

        String sql = """
                INSERT INTO users(id, full_name, email, password, role)
                VALUES (?, ?, ?, ?, ?::user_role)
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, user.getId());
            stmt.setString(2, user.getFullName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getRole().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
			throw new DataBaseException("Erreur lors de l'enregistrement", e);
		}

    }

    @Override
    public User update(User user) {

        String sql = """
                UPDATE users
                SET full_name = ?,
                    password = ?,
                    role = ?::user_role
                WHERE id = ?
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole().name());
            stmt.setObject(4, user.getId());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                return findById(user.getId());
            }

        }  catch (SQLException e) {
			throw new DataBaseException("Erreur lors de l'enregistrement", e);
		}

        return null;
    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users ORDER BY created_at ASC;";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(mapUser(rs));
            }

        }  catch (SQLException e) {
			throw new DataBaseException("Erreur lors de la lecture", e);
		}

        return users;
    }

    @Override
    public User findById(UUID id) {

        String sql = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return mapUser(rs);
                }
            }

        }  catch (SQLException e) {
			throw new DataBaseException("Erreur lors de la lecture", e);
		}

        return null;
    }

    @Override
    public void delete(User user) {

        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, user.getId());

            stmt.executeUpdate();

        }  catch (SQLException e) {
			throw new DataBaseException("Erreur lors de l'enregistrement", e);
		}

    }

    /**
     * Convertit une ligne du ResultSet en objet User.
     */
    private User mapUser(ResultSet rs) throws SQLException {

        return User.builder()
                .id((UUID) rs.getObject("id"))
                .fullName(rs.getString("full_name"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .role(UserRole.valueOf(rs.getString("role")))
                .build();
    }

}