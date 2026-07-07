package co.kozao.skillfinder.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	

    private static final String URL = System.getenv("SkillFinderDB_URL");
    private static final String USER = System.getenv("POSTGRES_USERNAME");
    private static final String PASSWORD = System.getenv("POSTGRES_PASSWORD");
    
    private static Connection connection;
    
    private DBConnection() {
		super();
	}
    
    public static class Holder{
    	private static final DBConnection INSTANCE = new DBConnection();
    }

    public static DBConnection getInstance() {
		return Holder.INSTANCE;
	}

	public Connection getConnection() {
        try {
        	Class.forName("org.postgresql.Driver");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}