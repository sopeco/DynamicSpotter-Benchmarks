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
	private static final int NUM_QUERIES = 50;
	private static final int NUM_QUERIES_DEVIATION = 100;
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
			for(int i = 0; i < n; i++){
				executeQuery();
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void executeQuery() throws SQLException {
		Statement stmt = connection.createStatement();
		String subQuery = getSubQuery(0, 5);
		stmt.execute("SELECT a FROM " + subQuery + " WHERE " + DummyDB.FIB_KEY + "25 AND a=1");
	}

	private String getSubQuery(int count, int maxDepth) {
		int i = rand.nextInt(20);
		String s = UUID.randomUUID().toString();

		String randStr = "";
		int n = rand.nextInt(5);
		;
		if (n < 1) {
			randStr = "SELECT max(a) FROM A WHERE b = 2 ORDER BY x";
		} else if (n < 2) {
			randStr = "SELECT max(a) FROM (SELECT a FROM b) b = 2 GROUP BY x";
		} else if (n < 3) {
			randStr = "SELECT max(a) FROM X WHERE b = 2 GROUP BY x";
		} else if (n < 4) {
			randStr = "SELECT y FROM Y WHERE b = 2 ORDER BY x";
		} else if (n < 5) {
			randStr = "SELECT b FROM X WHERE b = 2 GROUP BY x";
		}

		if (count < maxDepth) {
			count++;
			return "SELECT a FROM (" + randStr + ")," + getSubQuery(count, maxDepth) + " WHERE " + s + "=" + i;
		} else {
			return randStr;
		}
	}

}
