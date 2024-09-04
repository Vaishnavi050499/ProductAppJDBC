package com.productapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionUtil {
	static Connection connection;

	public static Connection openConnection() {
		String url = "jdbc:mysql://localhost:3306/vaishudb";
		String username = "root";
		String password = "Vaishu@99";
		PreparedStatement st = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			st = connection.prepareStatement(Queries.CREATEQUERY);
			st.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
