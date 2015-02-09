package org.spotter.benchmark.app.problems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.spotter.benchmark.dummyjdbc.server.rest.DummyDB;

/**
 * A problem with many equal database calls.
 */
public class TC_12_ManyEqualDBCalls extends Problem {
	public static Random rand = new Random(System.nanoTime());
	public static Connection connection;
	static {
		try {
			connection = DriverManager.getConnection("any");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static TC_12_ManyEqualDBCalls instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_12_ManyEqualDBCalls getInstance() {
		if (instance == null) {
			instance = new TC_12_ManyEqualDBCalls();
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
		stmt.execute("SELECT a FROM (SELECT max(a) FROM A WHERE b = 2 ORDER BY x) WHERE " + DummyDB.FIB_KEY
				+ "35 AND a=1");
	}

}
