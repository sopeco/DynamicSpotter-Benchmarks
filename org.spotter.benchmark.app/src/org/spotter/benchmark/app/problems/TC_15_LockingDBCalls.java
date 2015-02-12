package org.spotter.benchmark.app.problems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.spotter.benchmark.dummyjdbc.server.rest.DummyDB;

public final class TC_15_LockingDBCalls extends Problem {

	private static final long SLEEP_TIME = 70;
	private static final double SLEEP_DEVIATION = 0.25;
	private static final Random RAND = new Random(System.nanoTime());

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
		long n = SLEEP_TIME + (long) (((2.0 * (nextDouble() - 0.5)) * SLEEP_DEVIATION) * (double) SLEEP_TIME);
		stmt.execute("SELECT a FROM MyTable WHERE " + DummyDB.SLEEP_KEY + n + " AND " + DummyDB.SYNC_KEY + " =2 ");
	}

	private synchronized double nextDouble() {
		return RAND.nextDouble();
	}

}
