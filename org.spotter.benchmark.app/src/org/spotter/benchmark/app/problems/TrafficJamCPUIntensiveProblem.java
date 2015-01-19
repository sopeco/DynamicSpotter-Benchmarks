package org.spotter.benchmark.app.problems;

public class TrafficJamCPUIntensiveProblem implements Problem {
	public static final String NAME = "TrafficJamCPUintensiveProblem";

	private static final int FIB_NUMBER = 35;
	private static TrafficJamCPUIntensiveProblem instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized TrafficJamCPUIntensiveProblem getInstance() {
		if (instance == null) {
			instance = new TrafficJamCPUIntensiveProblem();
		}
		return instance;
	}

	private TrafficJamCPUIntensiveProblem() {
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
