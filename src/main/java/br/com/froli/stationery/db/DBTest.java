package br.com.froli.stationery.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBTest {
	
	private static final Logger LOGGER = Logger.getLogger(DBTest.class.getName());

	public static void main(String[] args) {
		Connection con;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/stationery");
			LOGGER.log(Level.INFO, "[Database] connection OK");
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.log(Level.INFO, "[Database] connection error");
			throw new RuntimeException(e);
		}
	}

}
