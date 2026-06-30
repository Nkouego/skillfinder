package co.kozao.skillfinder.config;

import java.sql.Connection;

public class Database {

	private static Database instance;
	
	private Database() {
	}
	
	public static Database getInstance() {
		if(instance == null) {
			instance = new Database();
		}
		
		return instance;
	}
	
	public Connection getConnection() {
		return DBConnection.getConnection();
	}
}
