package fr.doranco.salaires.model;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DorancoDataSource {
	private DorancoDataSource() {

	}

	public static Connection getConnection() throws Exception {
		String user = "root";
		String password = "root";
		String url = "jdbc:mysql://127.0.0.1:3306/doranco_db";

		// String urlOracle = "jdbc:oracle:thin:@//127.0.0.1:1521:doranco_db";
		// String urlSqlServer = "jdbc:sqlserver://127.0.0.1:1433/doranco_db";
		// String urlPostgreSql = "jdbc:postgreSql://127.0.0.1:5432/doranco_db";

		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
