package org.spotter.benchmark.dummyjdbc.server.rest;

import org.aim.artifacts.records.DBStatisticsRecrod;

public class DummyDB {
	public static final String SYNC_KEY = "sync";
	public static final String SLEEP_KEY = "sleep=";
	public static final String FIB_KEY = "fibonacci=";

	private static DummyDB instance;

	protected static DummyDB getInstance() {
		if (instance == null) {
			instance = new DummyDB();
		}
		return instance;
	}

	private volatile long numQueries = 0;
	private volatile long numLockWaits = 0;
	private volatile long lockTime = 0;

	private DummyDB() {

	}

	/**
	 * Tests the given problem.
	 * 
	 * @param problemName
	 *            the name of the problem to test
	 * @return hello string
	 */

	public String call(String command) {
		numQueries++;
		if (command.contains(SYNC_KEY)) {
			if (command.contains(SLEEP_KEY)) {
				String tmp = command.substring(command.indexOf(SLEEP_KEY));
				tmp = tmp.substring(tmp.indexOf("=") + 1);
				tmp = tmp.substring(0, tmp.indexOf(' '));
				long sleepDuration = Long.parseLong(tmp);
				long wait = syncSleep(sleepDuration);
				if (wait > 0) {
					numLockWaits++;
					lockTime += wait;
				}
			} else if (command.contains(FIB_KEY)) {
				String tmp = command.substring(command.indexOf(FIB_KEY));
				tmp = tmp.substring(tmp.indexOf("=") + 1);
				tmp = tmp.substring(0, tmp.indexOf(' '));
				int fibNumber = Integer.parseInt(tmp);
				long wait = syncFibonacci(fibNumber);
				if (wait > 0) {
					numLockWaits++;
					lockTime += wait;
				}
			}
		} else {
			if (command.contains(SLEEP_KEY)) {
				String tmp = command.substring(command.indexOf(SLEEP_KEY));
				tmp = tmp.substring(tmp.indexOf("=") + 1);
				tmp = tmp.substring(0, tmp.indexOf(' '));
				long sleepDuration = Long.parseLong(tmp);
				sleep(sleepDuration);
			} else if (command.contains(FIB_KEY)) {
				String tmp = command.substring(command.indexOf(FIB_KEY));
				tmp = tmp.substring(tmp.indexOf("=") + 1);
				tmp = tmp.substring(0, tmp.indexOf(' '));
				int fibNumber = Integer.parseInt(tmp);
				fibonacci(fibNumber);
			}
		}
		return "Hallo from Dummy DB";
	}

	public String getStatistics() {
		DBStatisticsRecrod record = new DBStatisticsRecrod();
		record.setTimeStamp(System.currentTimeMillis());
		record.setNumQueueries(numQueries);
		record.setNumLockWaits(numLockWaits);
		record.setProcessId("dummyDB");
		record.setLockTime(lockTime);
		return record.toString();
	}

	private long syncSleep(long duration) {
		long start = System.currentTimeMillis();
		System.out.println("syncSleep " + duration);
		long dur = 0L;
		synchronized (this) {
			dur = sleep(duration);
		}
		return (System.currentTimeMillis() - start) - dur;
	}

	private long sleep(long duration) {
		long start = System.currentTimeMillis();
		System.out.println("sleep " + duration);
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return System.currentTimeMillis() - start;
	}

	/**
	 * Calculates a fibonacci number.
	 */
	private long syncFibonacci(int n) {
		long start = System.currentTimeMillis();
		System.out.println("syncFib " + n);
		long dur = 0L;
		synchronized (this) {
			dur = fibonacci(n);
		}
		return (System.currentTimeMillis() - start) - dur;
	}

	/**
	 * Calculates a fibonacci number.
	 */
	private long fibonacci(int n) {
		long start = System.currentTimeMillis();
		System.out.println("fib " + n);
		fib(n);
		return System.currentTimeMillis() - start;
	}

	private int fib(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return fib(n - 2) + fib(n - 1);
		}
	}
}
