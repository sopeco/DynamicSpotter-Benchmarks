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
	private static final int NUM_QUERIES = 50;
	private static final int NUM_QUERIES_DEVIATION = 20;
	
	private static final int MIN_FIB_NUM = 27;
	private static final int MAX_FIB_NUM = 33;

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
			int n = NUM_QUERIES + rand.nextInt(NUM_QUERIES_DEVIATION);
			for(int i = 0; i < n; i++){
				executeQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void executeQuery() throws SQLException {
		if(rand.nextDouble() < 0.5){
			Statement stmt = connection.createStatement();
			int fibNum = MIN_FIB_NUM + rand.nextInt(MAX_FIB_NUM - MIN_FIB_NUM);
			stmt.execute("SELECT a FROM TableA WHERE " + DummyDB.FIB_KEY + fibNum
					+ " AND a=1");
		}else{
			Statement stmt = connection.createStatement();
			stmt.execute("SELECT T,V FROM AnotherTable WHERE " + DummyDB.SLEEP_KEY 
					+ "1 AND a=1 AND " + DummyDB.SYNC_KEY + "=2");
		}
		
	}

}
