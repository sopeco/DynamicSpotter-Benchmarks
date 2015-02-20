package org.spotter.benchmark.app.problems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.spotter.benchmark.dummyjdbc.server.rest.DummyDB;

/**
 * The a CPU intensive problem.
 */
public class TC_10_CPUIntensiveApp extends Problem {

	private static final int FIB_NUMBER = 35;
	private static TC_10_CPUIntensiveApp instance;
	private static Random RAND = new Random(System.currentTimeMillis());
	public static Connection connection;
	static {
		try {
			connection = DriverManager.getConnection("any");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_10_CPUIntensiveApp getInstance() {
		if (instance == null) {
			instance = new TC_10_CPUIntensiveApp();
		}
		return instance;
	}

	private TC_10_CPUIntensiveApp() {
	}

	@Override
	public void test() {
		calcFibonacci();
		try {
			if (RAND.nextDouble() < 0.01) {
				executeLockingQuery();
			} else {
				executeQuery();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void executeQuery() throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.execute("SELECT a FROM X WHERE " + DummyDB.FIB_KEY + "5 AND a=1");
	}

	private void executeLockingQuery() throws SQLException {
		Statement stmt = connection.createStatement();
		stmt.execute("SELECT b FROM y WHERE " + DummyDB.SLEEP_KEY + "5 AND " + DummyDB.SYNC_KEY + "=1");
	}

	/**
	 * Calculates a fibonacci number.
	 */
	private void calcFibonacci() {
		long start = System.currentTimeMillis();
		fibonacci(FIB_NUMBER);
		System.out.println((System.currentTimeMillis() - start) + " ms to calculate fib(" + FIB_NUMBER + ")");
	}

	private int fibonacci(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return fibonacci(n - 2) + fibonacci(n - 1);
		}
	}
}
