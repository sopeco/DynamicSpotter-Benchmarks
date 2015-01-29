package org.spotter.benchmark.app.problems;

/**
 * The OLB problem which is using a synchronized call to a fibonacci
 * calculation.
 * 
 * @author Denis Knoepfle
 * 
 */
public final class OLBProblem implements Problem {

	public static final String NAME = "olb";

	private static final int FIB_NUMBER = 35;
	private static OLBProblem instance;

	/**
	 * @return the singleton instance
	 */
	public static synchronized OLBProblem getInstance() {
		if (instance == null) {
			instance = new OLBProblem();
		}
		return instance;
	}

	private OLBProblem() {
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