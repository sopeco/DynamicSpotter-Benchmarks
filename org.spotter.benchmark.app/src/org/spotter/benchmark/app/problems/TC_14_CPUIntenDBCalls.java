package org.spotter.benchmark.app.problems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.spotter.benchmark.dummyjdbc.server.rest.DummyDB;

public class TC_14_CPUIntenDBCalls extends Problem {
	private static Random rand = new Random(System.nanoTime());
	private static Connection connection;
	static {
		try {
			connection = DriverManager.getConnection("any");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static TC_14_CPUIntenDBCalls instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_14_CPUIntenDBCalls getInstance() {
		if (instance == null) {
			instance = new TC_14_CPUIntenDBCalls();
		}
		return instance;
	}

	@Override
	public void test() {
		try {
			executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void executeQuery() throws SQLException {
		Statement stmt = connection.createStatement();
		int n = rand.nextInt(4) + 34;
		stmt.execute("SELECT a FROM MyTable WHERE " + DummyDB.FIB_KEY + n + " AND a=1");
	}

}
