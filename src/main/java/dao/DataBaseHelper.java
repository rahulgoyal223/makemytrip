package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import commons.LoggerHelper;
import org.apache.log4j.Logger;

public class DataBaseHelper {

	private static Logger log = LoggerHelper.getLogger(DataBaseHelper.class);
	
	private static String url = "jdbc:mysql://localhost/person";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String userName = "root";
	private static String password = "password";
	private static Connection connection;
	private static DataBaseHelper instance = null;

	public DataBaseHelper() {
		connection = getSingleInstanceConnection();
	}

	public static DataBaseHelper getInstance() {
		if (instance == null) {
			instance = new DataBaseHelper();
		}
		return instance;
	}

	private Connection getSingleInstanceConnection() {
		try {
			Class.forName(driverName);
			try {
				connection = DriverManager.getConnection(url, userName, password);
				if (connection != null) {
					log.info("Connected to data base..");
				}
			} catch (SQLException e) {
				log.error("Failed to create Data base connection.." + e);
			}
		} catch (ClassNotFoundException e) {
			log.info("Driver not found.." + e);
		}
		return connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public static ResultSet getResultSet(String dbQuery) {
		instance = DataBaseHelper.getInstance();
		connection = instance.getConnection();
		log.info("Executing query: " + dbQuery);
		try {
			Statement stmt = connection.createStatement();
			ResultSet result = stmt.executeQuery(dbQuery);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	public synchronized Object getExplainPlan(String query) {
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("explain analyze " + query);
			while (rs.next()) {
				return rs.getObject(1);
			}
		} catch (SQLException e) {
			log.info(e.getMessage());
		}
		return null;

	}

	public synchronized ResultSet executeQuery(String query) {
		log.info(query);
		Statement st;
		ResultSet rs = null;
		try {
			st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			log.info("postgresql Connection failed " + e.getMessage());
		}

		return rs;
	}

	public synchronized String executeSelectQuery(String query) {
		log.info(query);
		Statement st;
		ResultSet rs = null;
		String ret = "";
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
		} catch (SQLException e) {
			log.info("postgresql Connection failed " + e.getMessage());
		}

		try {
			if (rs.next()) {
				ret = rs.getString(1);
			}
		} catch (SQLException e) {
			log.info("resultset does not fetch the column " + e.getMessage());
		}

		return ret;
	}
}
