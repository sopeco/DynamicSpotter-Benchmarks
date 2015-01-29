package org.spotter.benchmark.app.problems;

public class TC_10_CPUIntensiveApp extends Problem {
	
	private static final int FIB_NUMBER = 35;
	private static TC_10_CPUIntensiveApp instance;

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
		syncFibonacci();
	}

	/**
	 * Calculates a fibonacci number.
	 */
	public void syncFibonacci() {
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
