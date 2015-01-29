package org.spotter.benchmark.app.problems;

/**
 * The OLB problem which is using a synchronized call to a fibonacci
 * calculation.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class TC_21_ClearSync extends Problem {

	
	private static final int FIB_NUMBER = 35;
	private static TC_21_ClearSync instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TC_21_ClearSync getInstance() {
		if (instance == null) {
			instance = new TC_21_ClearSync();
		}
		return instance;
	}

	private TC_21_ClearSync() {
	}

	@Override
	public void test() {
		syncFibonacci();
	}

	/**
	 * Calculates a fibonacci number.
	 */
	public synchronized void syncFibonacci() {
		long start = System.currentTimeMillis();
		fibonacci(FIB_NUMBER);
		System.out.println((System.currentTimeMillis() - start) + " ms");
	}

	private int fibonacci(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return fibonacci(n - 2) + fibonacci(n - 1);
		}
	}

}
