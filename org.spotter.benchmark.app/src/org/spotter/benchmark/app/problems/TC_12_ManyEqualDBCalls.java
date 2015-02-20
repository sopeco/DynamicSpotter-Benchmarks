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
	private static final int NUM_QUERIES = 50;
	private static final int NUM_QUERIES_DEVIATION = 20;
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
			int n = NUM_QUERIES + rand.nextInt(NUM_QUERIES_DEVIATION);
			for(int i = 0; i < n; i++){
				executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void executeQuery() throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.execute("SELECT a FROM myTable WHERE " + DummyDB.SLEEP_KEY
				+ "1 AND a=1 AND " + DummyDB.SYNC_KEY + "=2");
	}

}
