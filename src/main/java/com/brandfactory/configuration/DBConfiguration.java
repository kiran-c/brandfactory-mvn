package com.brandfactory.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.brandfactory.constants.ApplicationConstant;

public class DBConfiguration {

	public static Connection getDBConnection() {

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(ApplicationConstant.DB_URL, ApplicationConstant.DB_USERNAME,
					ApplicationConstant.DB_PASSWORD);
		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return conn;
	}
}
