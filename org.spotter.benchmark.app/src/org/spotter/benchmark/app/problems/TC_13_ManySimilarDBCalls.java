package org.spotter.benchmark.app.problems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.spotter.benchmark.dummyjdbc.server.rest.DummyDB;

/**
 * A problem with many similar database calls.
 */
public class TC_13_ManySimilarDBCalls extends Problem {

	private static final int MIN_FIB_NUM = 30;
	private static final int MAX_FIB_NUM = 36;

	public static Random rand = new Random(System.nanoTime());
	public static Connection connection;
	static {
		try {
			connection = DriverManager.getConnection("any");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static TC_13_ManySimilarDBCalls instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_13_ManySimilarDBCalls getInstance() {
		if (instance == null) {
			instance = new TC_13_ManySimilarDBCalls();
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
		int fibNum = MIN_FIB_NUM + rand.nextInt(MAX_FIB_NUM - MIN_FIB_NUM);
		stmt.execute("SELECT a FROM (SELECT max(a) FROM A WHERE b = 2 ORDER BY x) WHERE " + DummyDB.FIB_KEY + fibNum
				+ " AND a=1");
	}

}
