package org.spotter.benchmark.app.problems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.spotter.benchmark.dummyjdbc.server.rest.DummyDB;

public class TC_15_LockingDBCalls extends Problem {
	private static Random rand = new Random(System.nanoTime());
	private static Connection connection;
	static {
		try {
			connection = DriverManager.getConnection("any");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static TC_15_LockingDBCalls instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_15_LockingDBCalls getInstance() {
		if (instance == null) {
			instance = new TC_15_LockingDBCalls();
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
		int n = rand.nextInt(3000) + 1000;
		stmt.execute("SELECT a FROM MyTable WHERE " + DummyDB.SLEEP_KEY + n + " AND " + DummyDB.SYNC_KEY + " ");
	}

}
