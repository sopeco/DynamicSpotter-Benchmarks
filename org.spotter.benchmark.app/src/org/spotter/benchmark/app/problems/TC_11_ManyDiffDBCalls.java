package org.spotter.benchmark.app.problems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.UUID;

import org.spotter.benchmark.dummyjdbc.server.rest.DummyDB;

/**
 * A problem with many different database calls.
 */
public class TC_11_ManyDiffDBCalls extends Problem {
	private static final int NUM_QUERIES = 40;
	private static final int NUM_QUERIES_DEVIATION = 10;
	public static Random rand = new Random(System.nanoTime());
	public static Connection connection;
	static {
		try {
			connection = DriverManager.getConnection("any");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static TC_11_ManyDiffDBCalls instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_11_ManyDiffDBCalls getInstance() {
		if (instance == null) {
			instance = new TC_11_ManyDiffDBCalls();
		}
		return instance;
	}

	@Override
	public void test() {
		try {
			int n = NUM_QUERIES + rand.nextInt(NUM_QUERIES_DEVIATION);
			for (int i = 0; i < n; i++) {
				executeQuery(i);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void executeQuery(int index) throws SQLException {
		Statement stmt = connection.createStatement();
		String query = getQuery(index);
		int fib = 28 + rand.nextInt(5);
		stmt.execute(query + " WHERE " + DummyDB.FIB_KEY + fib + " AND a=1");
	}

	private static String getQuery(int i) {
		String[] strArray = { "a", "b", "c", "d", "e", "f", "g", "h", "j", "i" };
		String tableName = "";
		String iStr = String.valueOf(i);
		char[] chars = iStr.toCharArray();
		for (char c : chars) {
			String s = "" + c;
			tableName += strArray[Integer.parseInt(s)];
		}
		return "SELECT X,Y FROM " + tableName;
	}
}
