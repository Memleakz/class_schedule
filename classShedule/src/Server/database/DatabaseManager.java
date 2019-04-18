/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lawar15
 */
public class DatabaseManager {

 	private Connection connection;
	private static DatabaseManager dbManager = null;

	private DatabaseManager() {
		
		String url = "jdbc:postgresql://localhost:5432/classScheduling";
		String user = "lawar15";
		String password = "0000";
		 
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Connection to database successful!");
		} catch (SQLException ex) {
			System.out.println("Connection to database failed.");
		}
	}
	
public static DatabaseManager getInstance() {
		if (dbManager == null) {
			dbManager = new DatabaseManager();
		}
		return dbManager;
	}
public void execute(String query) {
		try (Statement statement = connection.createStatement()) {
			statement.execute(query);
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void executeUpdate(String query) {
		try (Statement statement = connection.createStatement()) {
			statement.executeUpdate(query);
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public ResultSet executeQuery(String query) {
		try {
			return connection.createStatement().executeQuery(query);
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public int executeInsertAndGetId(String query) {
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
			statement.executeUpdate();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			} else {
				return -1;
			}
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
			return -1;
		}
	}
}
